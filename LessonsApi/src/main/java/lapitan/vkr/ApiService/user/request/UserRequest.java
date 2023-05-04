package lapitan.vkr.ApiService.user.request;

import lombok.Data;

@Data
public class UserRequest {

    private String username;

    private String password;

    private String groupp;

    private String firstname;

    private String lastname;

    private boolean confirmed;

    private String status;

}
