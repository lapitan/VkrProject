package lapitan.vkr.ApiService.user.service;

import lapitan.vkr.ApiService.user.entity.Person;
import lapitan.vkr.ApiService.user.exception.NoSuchUserException;
import lapitan.vkr.ApiService.user.exception.NotUniqueUsernameException;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.mapper.UserMapper;
import lapitan.vkr.ApiService.user.repository.UserRepository;
import lapitan.vkr.ApiService.user.request.UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDto createUser(UserRequest userRequest) {

        userRepository.findUserByUsername(userRequest.getUsername()).orElseThrow(() ->
                new NotUniqueUsernameException("cant create user: username " + userRequest.getUsername() + " is already used"));

        return userMapper.userToUserDto(userRepository.save(userMapper.userRequestToUser(userRequest)));
    }

    @Transactional
    public UserDto updateUser(Long id, UserRequest userRequest) {

        Person person = userRepository.findById(id).orElseThrow(() ->
                new NoSuchUserException("update user: can't find user to update with id: " + id));
        Person savedPerson = userMapper.userRequestToUser(userRequest);
        savedPerson.setId(id);
        savedPerson.setUsername(person.getUsername());

        return userMapper.userToUserDto(userRepository.save(savedPerson));
    }

    @Transactional
    public UserDto getUserById(Long id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(() ->
                new NoSuchUserException("Can't get user: No such user with id: " + id)));
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.findById(id).orElseThrow(() ->
                new NoSuchUserException("Can't delete user: No such user with id: " + id));
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDto getUserByUsername(String username){
        return userMapper.userToUserDto(userRepository.findUserByUsername(username).orElseThrow(()->
                new NoSuchUserException("Can't get user: No such user with username: "+username)));
    }


    private boolean checkUserById(Long id) {
        return userRepository.findById(id).isPresent();
    }

    private boolean checkUserByUsername(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

}
