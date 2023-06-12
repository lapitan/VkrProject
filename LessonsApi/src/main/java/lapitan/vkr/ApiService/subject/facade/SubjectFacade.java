package lapitan.vkr.ApiService.subject.facade;

import io.swagger.v3.oas.annotations.Operation;
import lapitan.vkr.ApiService.security.model.Role;
import lapitan.vkr.ApiService.subject.dto.SubjectDto;
import lapitan.vkr.ApiService.subject.entity.Subject;
import lapitan.vkr.ApiService.subject.exception.NoSuchSubjectException;
import lapitan.vkr.ApiService.subject.mapper.SubjectMapper;
import lapitan.vkr.ApiService.subject.request.SubjectRequest;
import lapitan.vkr.ApiService.subject.service.SubjectService;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.entity.Person;
import lapitan.vkr.ApiService.user.exception.NoSuchUserException;
import lapitan.vkr.ApiService.user.facade.UserFacade;
import lapitan.vkr.ApiService.user.mapper.UserMapper;
import lapitan.vkr.ApiService.user.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public class SubjectFacade {

    SubjectService subjectService;
    UserService userService;
    SubjectMapper subjectMapper;
    UserMapper userMapper;

    UserFacade userFacade;
    AmqpTemplate amqpTemplate;

    @Value("${app.mode}")
    String mode;

    public SubjectFacade(SubjectService subjectService, UserService userService,
                         SubjectMapper subjectMapper, UserMapper userMapper,
                         AmqpTemplate amqpTemplate) {
        this.subjectService = subjectService;
        this.userService = userService;
        this.subjectMapper = subjectMapper;
        this.userMapper = userMapper;
        this.amqpTemplate = amqpTemplate;
    }

    public SubjectDto addSubject(SubjectRequest subjectRequest) {
        Person lecturer;
        try {
            lecturer = userService.getUserById(subjectRequest.getLecturerId());
        } catch (NoSuchUserException e) {
            throw new NoSuchUserException("Can't add Subject: " + e.getMessage());
        }

        Subject subject = subjectMapper.subjectRequestToSubject(subjectRequest);
        subject.setLecturer(lecturer);
        subject = subjectService.addSubject(subject);

        amqpTemplate.convertAndSend("DiscordBotQueue",
                "{command:createSubjectCategory, name:\""
                        + subjectRequest.getName() + "\"}");


        addSubjectTeacher(subject.getId(), lecturer.getUsername());

        return subjectMapper.subjectToSubjectDto(subjectService.getSubject(subject.getId()));
    }

    public SubjectDto getSubject(Long id) {
        return subjectMapper.subjectToSubjectDto(subjectService.getSubject(id));
    }

    public SubjectDto updateSubject(Long id, SubjectRequest subjectRequest) {
        Person lecturer;
        try {
            lecturer = userService.getUserById(subjectRequest.getLecturerId());
        } catch (NoSuchUserException e) {
            throw new NoSuchUserException("Can't update subject with id: " + id + " cause: " + e.getMessage());
        }

        Subject subject = subjectMapper.subjectRequestToSubject(subjectRequest);
        subject.setLecturer(lecturer);

        return subjectMapper.subjectToSubjectDto(subjectService.updateSubject(id, subject));
    }

    public void deleteSubject(Long id) {
        subjectService.deleteSubject(id);
    }

    public SubjectDto addSubjectTeacher(Long id, String teacherUsername) {
        Person teacher;
        Subject subject;
        try {
            teacher = userService.getUserByUsername(teacherUsername);
            subject = subjectService.getSubject(id);
        } catch (NoSuchUserException | NoSuchSubjectException e) {
            throw new NoSuchUserException("Can't add teacher with username: " + teacherUsername + "to subject with id: " + id + " cause: " + e.getMessage());
        }

        if (teacher.getRole().equals(Role.STUDENT)) {
            userFacade.changeUserRole(teacher.getId(), "TEACHER");
        }

        amqpTemplate.convertAndSend("DiscordBotQueue", "{command: addUserToCategory, username: \""
                + teacherUsername + "\", categoryName: \"" + subject.getName() + "\"}");


        return subjectMapper.subjectToSubjectDto(subjectService.addSubjectTeacher(id, teacher));
    }

    public List<UserDto> getSubjectTeachers(Long id) {
        return subjectService.getSubjectTeachers(id).stream()
                .map(userMapper::userToUserDto).toList();
    }

    public void deleteSubjectTeacher(Long id, String teacherUsername) {
        Person teacher;
        try {
            teacher = userService.getUserByUsername(teacherUsername);
        } catch (NoSuchUserException e) {
            throw new NoSuchUserException("Can't delete teacher from subject with id: " + id + " cause: " + e.getMessage());
        }
        subjectService.deleteSubjectTeacher(id, teacher);
    }
}
