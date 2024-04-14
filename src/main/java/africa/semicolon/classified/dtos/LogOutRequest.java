package africa.semicolon.classified.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class LogOutRequest {
    private String password;
    private  String username;
}
