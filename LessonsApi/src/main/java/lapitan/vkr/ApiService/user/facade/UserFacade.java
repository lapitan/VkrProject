package lapitan.vkr.ApiService.user.facade;

import lapitan.vkr.ApiService.exception.IllegalException;
import lapitan.vkr.ApiService.lesson.entity.Lesson;
import lapitan.vkr.ApiService.lesson.service.LessonsService;
import lapitan.vkr.ApiService.security.jwt.provider.JwtTokenProvider;
import lapitan.vkr.ApiService.security.model.Role;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.entity.Person;
import lapitan.vkr.ApiService.user.mapper.UserMapper;
import lapitan.vkr.ApiService.user.request.UserRequest;
import lapitan.vkr.ApiService.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserFacade {

    private UserService userService;
    private LessonsService lessonsService;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private AmqpTemplate amqpTemplate;
    @Value("${app.mode}")
    String mode;

    public UserFacade(UserService userService, LessonsService lessonsService, UserMapper userMapper,
                      PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AmqpTemplate amqpTemplate) {
        this.userService = userService;
        this.lessonsService = lessonsService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.amqpTemplate = amqpTemplate;
    }

    public UserDto createUser(UserRequest userRequest) {

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        Person person = userService.createUser(userMapper.userRequestToUser(userRequest));

        List<Lesson> lessons = lessonsService.getAllGrouppLessons(userRequest.getGroupp()).stream()
                .peek(lesson -> lesson.addStudent(person))
                .peek(lessonsService::subscribeLesson)
                .toList();

        person.setLessons(lessons);

        return userMapper.userToUserDto(person);
    }

    public UserDto updateUser(Long id, UserRequest userRequest) {
        return userMapper.userToUserDto(
                userService.updateUser(
                        id, userMapper.userRequestToUser(userRequest)));
    }

    public UserDto getUserById(Long id) {
        return userMapper.userToUserDto(userService.getUserById(id));
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public UserDto getPersonalAccount(String token) {

        String username = jwtTokenProvider.getUsername(token);
        return userMapper.userToUserDto(userService.getUserByUsername(username));
    }

    public UserDto updatePersonalAccount(String token, UserRequest userRequest) {

        String username = jwtTokenProvider.getUsername(token);
        if (!username.equals(userRequest.getUsername())) throw new IllegalException("You can't change your username");
        Person person = userService.getUserByUsername(username);

        return userMapper.userToUserDto(
                userService.updateUser(person.getId(),
                        userMapper.userRequestToUser(userRequest))
        );
    }

    public UserDto changeUserRole(Long id, String role) {
        Person person = userService.getUserById(id);
        person.setRole(Enum.valueOf(Role.class, role));

        userService.saveUser(person);

        if (mode.equals("run")) {
            String message = "{command:addRole, username: \"" + person.getUsername() + "\", role: \"" + role + "\"}";
            amqpTemplate.convertAndSend("DiscordBotQueue", message);
        }

        return userMapper.userToUserDto(person);
    }

    public void confirm(String username) {

        Person person = userService.getUserByUsername(username);
        person.setConfirmed(true);
        userService.saveUser(person);

        lessonsService.getAllGrouppLessons(person.getGroupp()).stream()
                .peek(lesson -> lesson.addStudent(person))
                .peek(lessonsService::subscribeLesson);

        if (mode.equals("run")) {
            String message = "{command:confirm, username: \"" + username + "\"}";
            amqpTemplate.convertAndSend("DiscordBotQueue", message);
        }
    }

    public void test(String message) {
        amqpTemplate.convertAndSend("DiscordBotQueue", message);
//        log.info("Abobs");
    }

}
