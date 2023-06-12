package lapitan.vkr.ApiService.subject.controller;

import io.swagger.v3.oas.annotations.Operation;
import lapitan.vkr.ApiService.subject.dto.SubjectDto;
import lapitan.vkr.ApiService.subject.facade.SubjectFacade;
import lapitan.vkr.ApiService.subject.request.SubjectRequest;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/subjects",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {

    SubjectFacade subjectFacade;

    public SubjectController(SubjectFacade subjectFacade) {
        this.subjectFacade = subjectFacade;
    }

    @PostMapping
    @Operation(summary = "add subject")
    @PreAuthorize("hasAuthority('subjects:write')")
    public SubjectDto addSubject(@RequestBody SubjectRequest subjectRequest){
        return subjectFacade.addSubject(subjectRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get subject by id")
    @PreAuthorize("hasAuthority('subjects:read')")
    public SubjectDto getSubject(@PathVariable Long id){
        return subjectFacade.getSubject(id);
    }

    @PutMapping ("/{id}")
    @Operation(summary = "update subject by id")
    @PreAuthorize("hasAuthority('subjects:write')")
    public SubjectDto updateSubject(@PathVariable Long id,@RequestBody SubjectRequest subjectRequest){
        return subjectFacade.updateSubject(id, subjectRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete subject by id")
    @PreAuthorize("hasAuthority('subjects:write')")
    public void deleteSubject(@PathVariable Long id){
        subjectFacade.deleteSubject(id);
    }

    @PostMapping("/teacher/{id}")
    @Operation(summary = "add Teacher to subject")
    @PreAuthorize("hasAuthority('subjects:write')")
    public SubjectDto addSubjectTeacher(@PathVariable Long id, String teacherUsername){
        return subjectFacade.addSubjectTeacher(id, teacherUsername);
    }

    @GetMapping("/teacher/{id}")
    @Operation(summary = "get all subject Teachers")
    @PreAuthorize("hasAuthority('subjects:write')")
    public List<UserDto> getSubjectTeachers(@PathVariable Long id){
        return subjectFacade.getSubjectTeachers(id);
    }

    @DeleteMapping("/teacher/{id}")
    @Operation(summary = "delete subject Teacher")
    @PreAuthorize("hasAuthority('subjects:write')")
    public void deleteSubjectTeacher(@PathVariable Long id, String teacherUsername){
        subjectFacade.deleteSubjectTeacher(id, teacherUsername);
    }


}
