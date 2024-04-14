package africa.semicolon.classified.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PostRequest {
    private String title;
    private String content;
    private String id;
    private String username;
    private String author;
}
