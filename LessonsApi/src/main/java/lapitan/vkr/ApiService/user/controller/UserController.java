package lapitan.vkr.ApiService.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.facade.UserFacade;
import lapitan.vkr.ApiService.user.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/user",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    @Operation
    @PreAuthorize("hasAuthority('users:write')")
    public UserDto createUser(@RequestBody UserRequest userRequest) {
        return userFacade.createUser(userRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update user by id")
    @PreAuthorize("hasAuthority('users:write')")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userFacade.updateUser(id, userRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get user by id")
    @PreAuthorize("hasAuthority('users:write')")
    public UserDto getUserById(@PathVariable Long id) {
        return userFacade.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete user by id")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
    }

    @GetMapping
    @Operation(summary = "get current user personal account")
    @PreAuthorize("hasAuthority('users:read')")
    public UserDto getPersonalAccount(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return userFacade.getPersonalAccount(token);
    }

    @PutMapping
    @Operation(summary = "update current user personal account")
    @PreAuthorize("hasAuthority('users:read')")
    public UserDto updatePersonalAccount(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                         @RequestBody UserRequest userRequest) {
        return userFacade.updatePersonalAccount(token, userRequest);
    }

    @PutMapping("/role/{id}")
    @Operation(summary = "change role of user")
    @PreAuthorize("hasAuthority('users:write')")
    public UserDto changeUserRole(@PathVariable Long id, @RequestBody String role) {
        return userFacade.changeUserRole(id, role);
    }

    @GetMapping("/test")
    public void test(@RequestBody String message) {
        userFacade.test(message);
    }

}
