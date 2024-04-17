package africa.semicolon.classified.dtos;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class DeleteRequest {
    private String username;
    private String id;
    private String content;

}
