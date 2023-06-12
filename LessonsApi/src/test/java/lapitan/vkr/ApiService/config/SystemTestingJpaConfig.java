package lapitan.vkr.ApiService.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EntityScan({"lapitan.vkr.ApiService.auth.entity",
        "lapitan.vkr.ApiService.lesson.entity",
        "lapitan.vkr.ApiService.subject.entity",
        "lapitan.vkr.ApiService.user.entity"})
@EnableJpaRepositories(basePackages = {"lapitan.vkr.ApiService.auth.repository",
        "lapitan.vkr.ApiService.lesson.repository",
        "lapitan.vkr.ApiService.subject.repository",
        "lapitan.vkr.ApiService.user.repository"})
//@ComponentScan({"lapitan.vkr.ApiService.auth.repository",
//        "lapitan.vkr.ApiService.lesson.repository",
//        "lapitan.vkr.ApiService.subject.repository",
//        "lapitan.vkr.ApiService.user.repository"})
@ComponentScan({"lapitan.vkr.ApiService.subject",
        "lapitan.vkr.ApiService.user",
        "lapitan.vkr.ApiService.lesson",
//        "lapitan.vkr.ApiService.config",
        "lapitan.vkr.ApiService.security.jwt.provider",
        "lapitan.vkr.ApiService.security.service"})
public class SystemTestingJpaConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
