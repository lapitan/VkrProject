package lapitan.vkr.ApiService.subject.mapper;

import lapitan.vkr.ApiService.subject.dto.SubjectDto;
import lapitan.vkr.ApiService.subject.entity.Subject;
import lapitan.vkr.ApiService.subject.request.SubjectRequest;
import lapitan.vkr.ApiService.user.entity.Person;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "Spring")
public interface SubjectMapper {

    default SubjectDto subjectToSubjectDto(Subject subject) {
        if (subject == null) {
            return null;
        }

        SubjectDto subjectDto = new SubjectDto();

        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());

        subjectDto.setLecturerId(subject.getLecturer().getId());
        List<Long> teachIds = subject.getPracticeTeachers().stream()
                .map(Person::getId).toList();

        subjectDto.setTeacherIds(teachIds);

        return subjectDto;
    }

    ;

    Subject subjectDtoToSubject(SubjectDto subjectDto);

    SubjectDto subjectRequestToSubjectDto(SubjectRequest subjectRequest);

    SubjectRequest subjectDtoToSubjectRequest(SubjectDto subjectDto);

    SubjectRequest subjectToSubjectRequest(Subject person);

    Subject subjectRequestToSubject(SubjectRequest subjectRequest);

}
