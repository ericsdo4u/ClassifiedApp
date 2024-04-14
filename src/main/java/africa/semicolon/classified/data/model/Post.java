package africa.semicolon.classified.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Post {
    private String title;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String author;
    @Id
    private String id;
    private String username;
    private List<View> views;
    private String content;

}
