package africa.semicolon.classified.response;


import lombok.Data;

@Data
public class PostResponse {
    private String title;
    private String detail;
    private String id;
    private String seller;
    private Double price;
    private String username;
    private String postedAt;
}
