package africa.semicolon.classified.services;

import africa.semicolon.classified.data.repository.PostRepository;
import africa.semicolon.classified.dtos.DeleteRequest;
import africa.semicolon.classified.dtos.PostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostServiceImplTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @BeforeEach
    public void setup(){
        postRepository.deleteAll();
    }
    @Test
    public void testThatUserCanInitiate_A_Post(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postService.postAds(postRequest);
        assertEquals(1, postService.getListOfPost());
    }
    @Test
    public void testThatUserCanInitiate_Two_Post(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postService.postAds(postRequest);

        PostRequest postRequest1 = new PostRequest();
        postRequest1.setTitle("cold");
        postRequest1.setContent("the weather is cold");
        postService.postAds(postRequest1);
        assertEquals(2, postService.getListOfPost());
    }
    @Test
    public void testThatPost_CanBeEdited(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postRequest.setAuthor("donald");
        postService.postAds(postRequest);

        postRepository.findPostByAuthor("donald");

        postRequest.setTitle("weather report");
        postRequest.setContent("it's a sunny day");
        postRequest.setAuthor("donald");
        postService.editPost(postRequest);
        assertEquals("it's a sunny day",postRequest.getContent());
    }
    @Test
    public void createTwoPost_UserCanDelete_One_PostTest(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setContent("is a rainy day");
        postRequest.setAuthor("ddon");
        postService.postAds(postRequest);

        PostRequest postRequest1 = new PostRequest();
        postRequest1.setTitle("cold");
        postRequest1.setContent("the weather is cold");
        postRequest1.setAuthor("eric");
        postService.postAds(postRequest1);
        assertEquals(2, postService.getListOfPost());

        DeleteRequest request = new DeleteRequest();
        request.setAuthor("ddon");
        postService.deletePost(request);
        assertEquals(1, postRepository.count());
    }

}