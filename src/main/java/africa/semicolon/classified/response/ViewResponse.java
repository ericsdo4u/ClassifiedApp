package africa.semicolon.classified.response;

import africa.semicolon.classified.data.model.User;
import lombok.Data;

@Data
public class ViewResponse {
    private String username;
    private User viewer;
    private String timeOfView;
    private String message;
}
