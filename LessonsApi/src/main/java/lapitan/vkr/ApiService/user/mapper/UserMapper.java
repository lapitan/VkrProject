package lapitan.vkr.ApiService.user.mapper;

import lapitan.vkr.ApiService.lesson.entity.Lesson;
import lapitan.vkr.ApiService.subject.entity.Subject;
import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.entity.Person;
import lapitan.vkr.ApiService.user.request.UserRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default UserDto userToUserDto(Person person) {
        if (person == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(person.getId());
        userDto.setUsername(person.getUsername());
        userDto.setPassword(person.getPassword());
        userDto.setGroupp(person.getGroupp());
        userDto.setFirstname(person.getFirstname());
        userDto.setLastname(person.getLastname());
        userDto.setConfirmed(person.isConfirmed());
        userDto.setRole(person.getRole());

        List<Long> lessonsIds = person.getLessons().stream()
                .map(Lesson::getId).toList();
        userDto.setLessonsIds(lessonsIds);

        List<Long> allowedSubjectIds = person.getAllowedSubjects().stream()
                .map(Subject::getId).toList();
        userDto.setAllowedSubjectIds(allowedSubjectIds);

        return userDto;
    }

    ;

    Person userDtoToUser(UserDto userDto);

    UserDto userRequestToUserDto(UserRequest userRequest);

    UserRequest userDtoToUserRequest(UserDto userDto);

    UserRequest userToUserRequest(Person person);

    Person userRequestToUser(UserRequest userRequest);

}
