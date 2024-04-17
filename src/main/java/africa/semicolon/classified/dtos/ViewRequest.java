package africa.semicolon.classified.dtos;

import africa.semicolon.classified.data.model.User;
import lombok.Data;

@Data
public class ViewRequest {
    private String username;
    private User viewer;
    private String postId;
    private String timeOfView;
}
