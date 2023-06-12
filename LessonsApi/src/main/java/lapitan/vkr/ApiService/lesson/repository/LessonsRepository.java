package lapitan.vkr.ApiService.lesson.repository;

import lapitan.vkr.ApiService.lesson.entity.Lesson;
import lapitan.vkr.ApiService.user.entity.Person;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface LessonsRepository extends CrudRepository<Lesson, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from Lesson l where l.groupp =:groupp")
    List<Lesson> findAllGroupLessons(
            @Param("groupp") String groupp);

}
