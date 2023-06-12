package lapitan.vkr.ApiService.security.service;

import lapitan.vkr.ApiService.security.model.SecurityUser;
import lapitan.vkr.ApiService.user.entity.Person;
import lapitan.vkr.ApiService.user.exception.NoSuchUserException;
import lapitan.vkr.ApiService.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("login: can't find user with username: " + username));
        return SecurityUser.fromPerson(person);
    }
}
