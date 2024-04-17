package africa.semicolon.classified.dtos;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PostRequest {
    private String username;
    private String title;
    private String detail;
    private String seller;
    private Double price;
    private String id;
}
