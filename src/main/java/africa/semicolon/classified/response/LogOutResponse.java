package africa.semicolon.classified.response;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class LogOutResponse {
    private String message;
}
