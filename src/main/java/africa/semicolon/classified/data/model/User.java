package africa.semicolon.classified.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private boolean locked;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
