package lapitan.vkr.ApiService.user.mapper;

import lapitan.vkr.ApiService.user.dto.UserDto;
import lapitan.vkr.ApiService.user.entity.Person;
import lapitan.vkr.ApiService.user.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto (Person person);

    Person userDtoToUser (UserDto userDto);

    UserDto userRequestToUserDto (UserRequest userRequest);

    UserRequest userDtoToUserRequest(UserDto userDto);

    UserRequest userToUserRequest(Person person);

    Person userRequestToUser(UserRequest userRequest);

}
