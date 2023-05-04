package lapitan.vkr.ApiService.user.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String groupp;

    private String firstname;

    private String lastname;

    private boolean confirmed;

    private String status;

}
