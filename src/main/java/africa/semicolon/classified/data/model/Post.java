package africa.semicolon.classified.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("post-for-classified")
public class Post {
    private String title;
    private String detail;
    private String seller;
    private Double price;
    private String username;
    private LocalDateTime createdAt = LocalDateTime.now();
    @Id
    private String id;
    private List<View> views = new ArrayList<>();

}
