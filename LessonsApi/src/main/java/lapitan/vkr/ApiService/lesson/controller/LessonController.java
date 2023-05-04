package lapitan.vkr.ApiService.lesson.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "discordLessons/lesson",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class LessonController {

    @PostMapping
    @Operation(summary = "add lesson")
    public void addLesson(){

    }

    @GetMapping("/{id}")
    @Operation(summary = "get lesson by id")
    public void getLesson(@PathVariable Long id){

    }

    @PutMapping ("/{id}")
    @Operation(summary = "update lesson by id")
    public void updateLesson(@PathVariable Long id){

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete lesson by id")
    public void deleteLesson(@PathVariable Long id){

    }

    @GetMapping
    @Operation(summary = "get filtered lessons")
    public void getFilteredLessons(){

    }

    @GetMapping("/my")
    @Operation(summary = "get current user's lessons")
    public void getMyLessons(){

    }

    @PutMapping("/subscribe/{id}")
    @Operation(summary = "subscribe on lesson by id")
    public void subscribeOnLesson(@PathVariable Long id){

    }

    @PutMapping("/unsubscribe/{id}")
    @Operation(summary = "unsubscribe on lesson by id")
    public void unsubscribeOnLesson(@PathVariable Long id){

    }

}
