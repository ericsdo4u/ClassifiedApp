package africa.semicolon.classified.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("view-for-classified-ads")
public class View {
    private String username;
    @Id
    private String id;
    private String postId;
    private LocalDateTime timeOfView = LocalDateTime.now();
}
