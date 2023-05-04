package lapitan.vkr.ApiService.subject.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "discordLessons/subjects",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {

    @PostMapping
    @Operation(summary = "add subject")
    public void addSubject(){

    }

    @GetMapping("/{id}")
    @Operation(summary = "get subject by id")
    public void getSubject(@PathVariable Long id){

    }

    @PutMapping ("/{id}")
    @Operation(summary = "update subject by id")
    public void updateSubject(@PathVariable Long id){

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete subject by id")
    public void deleteSubject(@PathVariable Long id){

    }

    @GetMapping
    @Operation(summary = "get filtered subjects")
    public void getFilteredSubjects(){

    }

    @PostMapping("/teacher")
    @Operation(summary = "add Teacher to subject")
    public void addSubjectTeacher(){

    }

    @GetMapping("/teacher")
    @Operation(summary = "get all subject Teachers")
    public void getSubjectTeachers(){

    }

    @DeleteMapping("/teacher")
    @Operation(summary = "delete subject Teacher")
    public void deleteSubjectTeacher(){

    }


}
