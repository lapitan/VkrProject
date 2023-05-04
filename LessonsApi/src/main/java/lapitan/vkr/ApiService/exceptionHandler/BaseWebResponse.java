package lapitan.vkr.ApiService.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseWebResponse {
    private String errorMessage;
}
