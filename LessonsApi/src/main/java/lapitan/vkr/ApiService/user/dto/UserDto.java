package lapitan.vkr.ApiService.user.dto;

import lapitan.vkr.ApiService.security.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String groupp;
    private String firstname;
    private String lastname;
    private boolean confirmed;
    private Role role;

    private List<Long> lessonsIds;
    private List<Long> allowedSubjectIds;

}
