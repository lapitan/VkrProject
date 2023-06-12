package lapitan.vkr.ApiService.subject.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubjectDto {

    private Long id;
    private String name;
    private Long lecturerId;
    private List<Long> teacherIds;

}
