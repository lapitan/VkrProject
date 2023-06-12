package lapitan.vkr.ApiService.lesson.dto;

import lombok.Data;

import java.util.List;

@Data
public class LessonDto {

    private Long id;
    private String groupp;
    private Long teacherId;
    private Long subjectId;

    private List<Long> lessonStudents;

}
