package lapitan.vkr.ApiService.auth.controller;

import lapitan.vkr.ApiService.auth.facade.AuthFacade;
import lapitan.vkr.ApiService.auth.request.AuthRequest;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = "/auth",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    AuthFacade authFacade;

    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return authFacade.login(authRequest);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRequest userRequest){
        return authFacade.register(userRequest);
    }

    @GetMapping("/confirm/{token}")
    public String confirm(@PathVariable String token){
        return authFacade.confirm(token);
    }
}
