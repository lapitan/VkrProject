package lapitan.vkr.ApiService.lesson.facade;


import io.swagger.v3.oas.annotations.Operation;
import lapitan.vkr.ApiService.exception.IllegalException;
import lapitan.vkr.ApiService.lesson.dto.LessonDto;
import lapitan.vkr.ApiService.lesson.entity.Lesson;
import lapitan.vkr.ApiService.lesson.exception.NoSuchLessonException;
import lapitan.vkr.ApiService.lesson.mapper.LessonsMapper;
import lapitan.vkr.ApiService.lesson.request.LessonRequest;
import lapitan.vkr.ApiService.lesson.service.LessonsService;
import lapitan.vkr.ApiService.subject.entity.Subject;
import lapitan.vkr.ApiService.subject.exception.NoSuchSubjectException;
import lapitan.vkr.ApiService.subject.mapper.SubjectMapper;
import lapitan.vkr.ApiService.subject.service.SubjectService;
import lapitan.vkr.ApiService.user.entity.Person;
import lapitan.vkr.ApiService.user.exception.NoSuchUserException;
import lapitan.vkr.ApiService.user.mapper.UserMapper;
import lapitan.vkr.ApiService.user.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public class LessonsFacade {

    LessonsService lessonsService;
    UserService userService;
    SubjectService subjectService;
    LessonsMapper lessonsMapper;
    UserMapper userMapper;
    SubjectMapper subjectMapper;
    AmqpTemplate amqpTemplate;


    public LessonsFacade(LessonsService lessonsService, UserService userService,
                         SubjectService subjectService, LessonsMapper lessonsMapper,
                         UserMapper userMapper, SubjectMapper subjectMapper,
                         AmqpTemplate amqpTemplate) {
        this.lessonsService = lessonsService;
        this.userService = userService;
        this.subjectService = subjectService;
        this.lessonsMapper = lessonsMapper;
        this.userMapper = userMapper;
        this.subjectMapper = subjectMapper;
        this.amqpTemplate = amqpTemplate;
    }

    public LessonDto addLesson(LessonRequest lessonRequest, String token) {

        Subject subject;
        Person teacher;

        try {
            subject = subjectService.getSubject(lessonRequest.getSubjectId());
            teacher = userService.getUserById(lessonRequest.getTeacherId());
        } catch (NoSuchSubjectException e) {
            throw new NoSuchSubjectException("can't add lesson. cause: " + e.getMessage());
        } catch (NoSuchUserException e) {
            throw new NoSuchUserException("can't add lesson. cause: " + e.getMessage());
        }

        List<Long> teacherIds = subject.getPracticeTeachers().stream()
                .map(Person::getId).toList();

        if (!teacherIds.contains(teacher.getId())) {
            throw new IllegalException("You can't add lesson to subject with name: " + subject.getName());
        }



        List<Person> personList = userService.getPersonWithGroupp(lessonRequest.getGroupp());

        Lesson lesson = lessonsMapper.lessonRequestToLesson(lessonRequest);
        lesson.setLessonStudents(personList);
        lesson.setTeacher(teacher);
        lesson.setSubject(subject);

        return lessonsMapper.lessonToLessonDto(lessonsService.addLesson(lesson));
    }

    public LessonDto getLesson(Long id) {
        return lessonsMapper.lessonToLessonDto(lessonsService.getLesson(id));
    }

    public LessonDto updateLesson(Long id, LessonRequest lessonRequest, String token) {

        deleteLesson(id);
        return addLesson(lessonRequest, token);

    }

    public void deleteLesson(Long id) {
        lessonsService.deleteLesson(id);
    }

    public List<LessonDto> getMyLessons() {
        return null;
    }

    public LessonDto subscribeOnLesson(Long id) {
        return null;
    }

    public LessonDto unsubscribeOnLesson(Long id) {
        return null;
    }

    public LessonDto subscribeOnLesson(Long userId, Long lessonId) {

        Lesson lesson;
        Person student;

        try {
            lesson = lessonsService.getLesson(lessonId);
            student = userService.getUserById(userId);
        } catch (NoSuchLessonException e) {
            throw new NoSuchLessonException("can't subscribe on lesson with id: " + lessonId + ". cause: " + e.getMessage());
        } catch (NoSuchUserException e) {
            throw new NoSuchUserException("can't subscribe on lesson with id: " + lessonId + ". cause: " + e.getMessage());
        }

        lesson.addStudent(student);

        return lessonsMapper.lessonToLessonDto(lessonsService.subscribeLesson(lesson));
    }

    public LessonDto unsubscribeOnLesson(Long userId, Long lessonId) {
        Lesson lesson;
        Person student;

        try {
            lesson = lessonsService.getLesson(lessonId);
            student = userService.getUserById(userId);
        } catch (NoSuchLessonException e) {
            throw new NoSuchLessonException("can't unsubscribe on lesson with id: " + lessonId + ". cause: " + e.getMessage());
        } catch (NoSuchUserException e) {
            throw new NoSuchUserException("can't unsubscribe on lesson with id: " + lessonId + ". cause: " + e.getMessage());
        }

        lesson.removeStudent(student);

        return lessonsMapper.lessonToLessonDto(lessonsService.subscribeLesson(lesson));
    }
}
