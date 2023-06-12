package lapitan.vkr.ApiService.auth.repository;

import lapitan.vkr.ApiService.auth.entity.ConfirmToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmTokenRepository extends CrudRepository<ConfirmToken, String> {
}
