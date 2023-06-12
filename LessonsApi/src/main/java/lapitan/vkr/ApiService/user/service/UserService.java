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

import java.util.List;

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
    public Person createUser(Person user) {

        if (checkUserByUsername(user.getUsername())) {
            throw new NotUniqueUsernameException("there is another one user with name: " + user.getUsername());
        }

        return userRepository.save(user);
    }

    @Transactional
    public Person updateUser(Long id, Person user) {

        Person personInDb = userRepository.findById(id).orElseThrow(() ->
                new NoSuchUserException("update user: can't find user to update with id: " + id));
        user.setId(id);
        user.setUsername(personInDb.getUsername());

        return userRepository.save(user);
    }

    @Transactional
    public Person getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NoSuchUserException("Can't get user: No such user with id: " + id));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() ->
                new NoSuchUserException("Can't delete user: No such user with id: " + id));
        userRepository.deleteById(id);
    }

    @Transactional
    public Person getUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() ->
                new NoSuchUserException("Can't get user: No such user with username: " + username));
    }

    @Transactional
    public List<Person> getPersonWithGroupp(String groupp){
        return userRepository.findAllUsersWithGroup(groupp);
    }

    @Transactional
    public void saveUser(Person person){
        userRepository.save(person);
    }

    private boolean checkUserById(Long id) {
        return userRepository.findById(id).isPresent();
    }

    private boolean checkUserByUsername(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

}
