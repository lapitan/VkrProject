package lapitan.vkr.DiscordBotService.repository;

import lapitan.vkr.DiscordBotService.entity.Category;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Category c where c.name =:name")
    Optional<Category> findCategoryByName(
            @Param("name") String name);

}
