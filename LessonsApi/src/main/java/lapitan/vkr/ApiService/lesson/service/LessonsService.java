package lapitan.vkr.ApiService.lesson.service;

import lapitan.vkr.ApiService.lesson.dto.LessonDto;
import lapitan.vkr.ApiService.lesson.entity.Lesson;
import lapitan.vkr.ApiService.lesson.exception.NoSuchLessonException;
import lapitan.vkr.ApiService.lesson.mapper.LessonsMapper;
import lapitan.vkr.ApiService.lesson.repository.LessonsRepository;
import lapitan.vkr.ApiService.lesson.request.LessonRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LessonsService {

    LessonsRepository lessonsRepository;
    LessonsMapper lessonsMapper;

    public LessonsService(LessonsRepository lessonsRepository, LessonsMapper lessonsMapper) {
        this.lessonsRepository = lessonsRepository;
        this.lessonsMapper = lessonsMapper;
    }

    @Transactional
    public Lesson addLesson(Lesson lesson) {
        return lessonsRepository.save(lesson);
    }

    @Transactional
    public Lesson getLesson(Long id) {
        return lessonsRepository.findById(id).orElseThrow(() ->
                new NoSuchLessonException("get lesson: can't find lesson with id: " + id));
    }

    @Transactional
    public Lesson updateLesson(Long id, Lesson lesson) {
        Lesson savedLesson=lessonsRepository.findById(id).orElseThrow(() ->
                new NoSuchLessonException("update lesson: can't find lesson with id: " + id));

        lesson.setId(savedLesson.getId());
        return lessonsRepository.save(lesson);
    }

    @Transactional
    public void deleteLesson(Long id) {
        lessonsRepository.findById(id).orElseThrow(() ->
                new NoSuchLessonException("delete lesson: can't find lesson with id: " + id));
        lessonsRepository.deleteById(id);
    }

    @Transactional
    public List<LessonDto> getFilteredLessons() {
        return null;
    }

    @Transactional
    public List<LessonDto> getMyLessons() {
        return null;
    }

    @Transactional
    public Lesson subscribeLesson(Lesson lesson) {
        return lessonsRepository.save(lesson);
    }

    @Transactional
    public List<Lesson> getAllGrouppLessons(String groupp){
        return lessonsRepository.findAllGroupLessons(groupp);
    }
}
