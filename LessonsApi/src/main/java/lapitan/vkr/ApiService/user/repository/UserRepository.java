package lapitan.vkr.ApiService.user.repository;

import lapitan.vkr.ApiService.user.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Person, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Person p where p.username =:username")
    Optional<Person> findUserByUsername(
            @Param("username") String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Person p where p.groupp =:groupp")
    List<Person> findAllUsersWithGroup(
            @Param("groupp") String groupp);

}
