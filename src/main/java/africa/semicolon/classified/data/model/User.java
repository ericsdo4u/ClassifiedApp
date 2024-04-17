package africa.semicolon.classified.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("user-for-classified-ads")
@Data
public class User {
    private String email;
    private String username;
    private String password;
    @Id
    private String id;
    private boolean locked;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
