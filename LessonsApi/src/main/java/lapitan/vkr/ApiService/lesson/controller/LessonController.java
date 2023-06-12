package lapitan.vkr.ApiService.lesson.controller;

import io.swagger.v3.oas.annotations.Operation;
import lapitan.vkr.ApiService.lesson.dto.LessonDto;
import lapitan.vkr.ApiService.lesson.facade.LessonsFacade;
import lapitan.vkr.ApiService.lesson.request.LessonRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/lesson",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class LessonController {

    LessonsFacade lessonsFacade;

    public LessonController(LessonsFacade lessonsFacade) {
        this.lessonsFacade = lessonsFacade;
    }

    @PostMapping
    @Operation(summary = "add lesson")
    @PreAuthorize("hasAuthority('lessons:write')")
    public LessonDto addLesson(@RequestBody LessonRequest lessonRequest,
                               @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return lessonsFacade.addLesson(lessonRequest, token);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get lesson by id")
    @PreAuthorize("hasAuthority('lessons:read')")
    public LessonDto getLesson(@PathVariable Long id) {
        return lessonsFacade.getLesson(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update lesson by id")
    @PreAuthorize("hasAuthority('lessons:write')")
    public LessonDto updateLesson(@PathVariable Long id,
                                  @RequestBody LessonRequest lessonRequest,
                                  @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return lessonsFacade.updateLesson(id, lessonRequest, token);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete lesson by id")
    @PreAuthorize("hasAuthority('lessons:write')")
    public void deleteLesson(@PathVariable Long id) {
        lessonsFacade.deleteLesson(id);
    }

    @GetMapping("/my")
    @Operation(summary = "get current user's lessons")
    @PreAuthorize("hasAuthority('lessons:read')")
    public List<LessonDto> getMyLessons() {
        return lessonsFacade.getMyLessons();
    }

    @PutMapping("/subscribe/{id}")
    @Operation(summary = "subscribe on lesson by id")
    @PreAuthorize("hasAuthority('lessons:read')")
    public LessonDto subscribeOnLesson(@PathVariable Long id) {
        return lessonsFacade.subscribeOnLesson(id);
    }

    @DeleteMapping("/subscribe/{id}")
    @Operation(summary = "unsubscribe on lesson by id")
    @PreAuthorize("hasAuthority('lessons:read')")
    public LessonDto unsubscribeOnLesson(@PathVariable Long id) {
        return lessonsFacade.unsubscribeOnLesson(id);
    }

    @PutMapping("/subscribe")
    @Operation(summary = "subscribe user with id on lesson by id")
    @PreAuthorize("hasAuthority('lessons:write')")
    public LessonDto subscribeUserOnLesson(Long userId, Long lessonId) {
        return lessonsFacade.subscribeOnLesson(userId, lessonId);
    }

    @DeleteMapping("/subscribe")
    @Operation(summary = "unsubscribe user with id on lesson by id")
    @PreAuthorize("hasAuthority('lessons:write')")
    public LessonDto unsubscribeUserOnLesson(Long userId, Long lessonId) {
        return lessonsFacade.unsubscribeOnLesson(userId, lessonId);
    }

}
