package africa.semicolon.classified.response;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PostReturnResponse {
    private String title;
    private String content;
    private String id;
    private String author;
    private String postedAt;
}
