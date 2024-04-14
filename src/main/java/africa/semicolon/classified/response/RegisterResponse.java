package africa.semicolon.classified.response;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class RegisterResponse {
    private String username;
    private String email;
    private String id;
    private String dateCreated;

}
