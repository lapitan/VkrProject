package lapitan.vkr.DiscordBotService.repository;

import lapitan.vkr.DiscordBotService.entity.Person;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Person p where p.name =:name")
    Optional<Person> findPersonByName(
            @Param("name") String name);

}
