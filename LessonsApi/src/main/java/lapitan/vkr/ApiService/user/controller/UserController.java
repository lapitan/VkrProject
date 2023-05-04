package lapitan.vkr.ApiService.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.facade.UserFacade;
import lapitan.vkr.ApiService.user.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "discordLessons/user",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    @Operation
    public UserDto createUser(@RequestBody UserRequest userRequest){
        return userFacade.createUser(userRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update user by id")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        return userFacade.updateUser(id, userRequest);
    }

    @GetMapping ("/{id}")
    @Operation(summary = "get user by id")
    public UserDto getUserById(@PathVariable Long id){
        return userFacade.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete user by id")
    public void deleteUser(@PathVariable Long id){
        userFacade.deleteUser(id);
    }

    @GetMapping
    @Operation(summary = "get current user personal account")
    public UserDto getPersonalAccount(){
        return userFacade.getPersonalAccount();
    }

    @PutMapping
    @Operation(summary = "update current user personal account")
    public UserDto updatePersonalAccount(){
        return userFacade.updatePersonalAccount();
    }

}
