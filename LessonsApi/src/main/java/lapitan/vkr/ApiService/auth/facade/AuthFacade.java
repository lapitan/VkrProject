package lapitan.vkr.ApiService.auth.facade;

import lapitan.vkr.ApiService.auth.request.AuthRequest;
import lapitan.vkr.ApiService.auth.service.TokenService;
import lapitan.vkr.ApiService.security.jwt.provider.JwtTokenProvider;
import lapitan.vkr.ApiService.security.model.Role;
import lapitan.vkr.ApiService.user.entity.Person;
import lapitan.vkr.ApiService.user.facade.UserFacade;
import lapitan.vkr.ApiService.user.mapper.UserMapper;
import lapitan.vkr.ApiService.user.request.UserRequest;
import lapitan.vkr.ApiService.user.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthFacade {

    private AuthenticationManager authenticationManager;
    private UserFacade userFacade;
    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;
    private UserMapper userMapper;
    private AmqpTemplate amqpTemplate;
    private TokenService tokenService;
    private PasswordEncoder passwordEncoder;
    @Value("${url.root}")
    private String url;
    @Value("${app.mode}")
    String mode;

    public AuthFacade(AuthenticationManager authenticationManager, UserFacade userFacade,
                      UserService userService, JwtTokenProvider jwtTokenProvider,
                      UserMapper userMapper, AmqpTemplate amqpTemplate,
                      TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userFacade = userFacade;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userMapper = userMapper;
        this.amqpTemplate = amqpTemplate;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> login(AuthRequest authRequest){

        try {
            String username = authRequest.getUsername();
            String password = authRequest.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            Person person = userService.getUserByUsername(username);

            String token = jwtTokenProvider.createToken(username, person.getRole().name());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        }catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid username/password", HttpStatus.FORBIDDEN);
        }
    }

    public String register(UserRequest userRequest){

        userRequest.setConfirmed(false);
        userRequest.setRole(Role.STUDENT.name());

        Person person = userMapper.userRequestToUser(userRequest);

        person.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        userService.createUser(person);

        String token = jwtTokenProvider.createToken(userRequest.getUsername(), userRequest.getRole());

        tokenService.saveToken(token);

        if (mode.equals("run")) {
            String message = "{command:sendVerificationMessage, username:\""+userRequest.getUsername()+"\", url:\""
                    +url+"/auth/confirm/"+token+"\"}";

            amqpTemplate.convertAndSend("DiscordBotQueue", message);
        }

        return "You need to confirm your Discord Account.\nTo do that - go to your Discord private messages and click link from out bot";
    }

    public String confirm(String token){

        jwtTokenProvider.validateToken(token);
        tokenService.checkToken(token);

        String username = jwtTokenProvider.getUsername(token);

        userFacade.confirm(username);

        return "You confirmed your account!\nNow you can login.\n go to our login page: "+url+"/auth/login";


    }

}
