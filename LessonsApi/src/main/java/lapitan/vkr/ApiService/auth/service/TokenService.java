package lapitan.vkr.ApiService.auth.service;

import lapitan.vkr.ApiService.auth.entity.ConfirmToken;
import lapitan.vkr.ApiService.auth.repository.ConfirmTokenRepository;
import lapitan.vkr.ApiService.exception.IllegalException;
import org.springframework.stereotype.Component;

@Component
public class TokenService {

    ConfirmTokenRepository confirmTokenRepository;

    public TokenService(ConfirmTokenRepository confirmTokenRepository) {
        this.confirmTokenRepository = confirmTokenRepository;
    }

    public void saveToken(String token) {

        if (confirmTokenRepository.existsById(token)) throw new IllegalException("Token already exists. Try again");

        ConfirmToken confirmToken = new ConfirmToken();
        confirmToken.setToken(token);

        confirmTokenRepository.save(confirmToken);

    }

    public void checkToken(String token){
        if (!confirmTokenRepository.existsById(token)) throw new IllegalException("Wrong Token!");
        confirmTokenRepository.deleteById(token);
    }
}
