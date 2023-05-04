package lapitan.vkr.ApiService.lesson.repository;

import lapitan.vkr.ApiService.lesson.entity.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonsRepository extends CrudRepository<Lesson, Long> {
}
