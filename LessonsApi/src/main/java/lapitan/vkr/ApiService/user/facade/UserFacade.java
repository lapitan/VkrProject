package lapitan.vkr.ApiService.user.facade;

import io.swagger.v3.oas.annotations.Operation;
import lapitan.vkr.ApiService.lesson.service.LessonsService;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.request.UserRequest;
import lapitan.vkr.ApiService.user.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.testcontainers.shaded.org.apache.commons.lang3.NotImplementedException;

@Component
public class UserFacade {

    private UserService userService;
    private LessonsService lessonsService;

    public UserFacade(UserService userService, LessonsService lessonsService) {
        this.userService = userService;
        this.lessonsService = lessonsService;
    }

    public UserDto createUser(UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    public UserDto updateUser(Long id, UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    public UserDto getUserById(Long id) {
        return userService.getUserById(id);
    }

    public void deleteUser(Long id) {
        throw new NotImplementedException();
    }

    public UserDto getPersonalAccount() {
        throw new NotImplementedException();
    }

    public UserDto updatePersonalAccount() {
        throw new NotImplementedException();
    }

}
