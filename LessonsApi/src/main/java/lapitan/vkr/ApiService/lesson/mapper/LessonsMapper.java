package lapitan.vkr.ApiService.lesson.mapper;

import lapitan.vkr.ApiService.lesson.dto.LessonDto;
import lapitan.vkr.ApiService.lesson.entity.Lesson;
import lapitan.vkr.ApiService.lesson.request.LessonRequest;
import lapitan.vkr.ApiService.user.entity.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface LessonsMapper {

    default LessonDto lessonToLessonDto(Lesson lesson) {
        if (lesson == null) {
            return null;
        }

        LessonDto lessonDto = new LessonDto();

        lessonDto.setGroupp(lesson.getGroupp());
        lessonDto.setId(lesson.getId());
        lessonDto.setTeacherId(lesson.getTeacher().getId());
        lessonDto.setSubjectId(lesson.getSubject().getId());

        lessonDto.setLessonStudents(
                lesson.getLessonStudents().stream()
                        .map(Person::getId).toList());

        return lessonDto;
    }

//    Lesson lessonDtoToLesson(LessonDto lessonDto);

    LessonDto lessonRequestToLessonDto(LessonRequest lessonRequest);

    LessonRequest lessonDtoToLessonRequest(LessonDto lessonDto);

    LessonRequest lessonToLessonRequest(Lesson person);

    Lesson lessonRequestToLesson(LessonRequest lessonRequest);

}
