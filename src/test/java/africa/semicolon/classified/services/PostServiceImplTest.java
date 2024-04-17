package africa.semicolon.classified.services;

import africa.semicolon.classified.data.model.User;
import africa.semicolon.classified.data.repository.PostRepository;
import africa.semicolon.classified.data.repository.UserRepository;
import africa.semicolon.classified.dtos.DeleteRequest;
import africa.semicolon.classified.dtos.LoginRequest;
import africa.semicolon.classified.dtos.PostRequest;
import africa.semicolon.classified.dtos.RegisterRequest;
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
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void setup(){
        postRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Test
    public void testThatUserCanInitiate_A_Post(){
        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericsonericdon66@gmail.com");
        registerRequest.setUsername("ericso");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);
        assertEquals(1, userService.getNumberOfRegisteredUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ericso");
        loginRequest.setPassword("1234");
        userService.login(loginRequest);
        assertFalse(user.isLocked());

        PostRequest postRequest = new PostRequest();
        postRequest.setUsername("ericso");
        postRequest.setTitle("weather");
        postRequest.setDetail("is a rainy day");
        postRequest.setSeller("don");
        postRequest.setPrice(100.2);
        postService.postAds(postRequest);
        assertEquals(1, postService.getListOfPost());
    }
    @Test
    public void testThatUserCanInitiate_Two_Post(){

        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericsonericdon66@gmail.com");
        registerRequest.setUsername("ericson");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);
        assertEquals(1, userService.getNumberOfRegisteredUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ericson");
        loginRequest.setPassword("1234");
        userService.login(loginRequest);
        assertFalse(user.isLocked());

        PostRequest postRequest = new PostRequest();
        postRequest.setUsername("ericson");
        postRequest.setSeller("dd");
        postRequest.setTitle("weather");
        postRequest.setDetail("is a rainy day");
        postRequest.setPrice(23.4);
        postService.postAds(postRequest);
        assertEquals(1, postService.getListOfPost());

//        PostRequest post = new PostRequest();
//        post.setTitle("weather");
//        List<Post> posts = postService.findAllBy(post.getTitle());
//        assertEquals("weather", posts);

    }
    @Test
    public void testThatPost_CanBeEdited(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setDetail("is a rainy day");
        postRequest.setSeller("donald");
        postService.postAds(postRequest);


        postRequest.setTitle("weather report");
        postRequest.setDetail("it's a sunny day");
        postRequest.setSeller("donald");
        postService.editPost(postRequest);
        assertEquals("it's a sunny day",postRequest.getDetail());
    }
    @Test
    public void createTwoPost_UserCanDelete_One_PostTest(){
        PostRequest postRequest = new PostRequest();
        postRequest.setTitle("weather");
        postRequest.setDetail("is a rainy day");
        postRequest.setSeller("ddon");
        postService.postAds(postRequest);

        PostRequest postRequest1 = new PostRequest();
        postRequest1.setTitle("cold");
        postRequest1.setDetail("the weather is cold");
        postRequest1.setSeller("eric");
        postService.postAds(postRequest1);
        assertEquals(2, postService.getListOfPost());

        DeleteRequest request = new DeleteRequest();
        request.setUsername("ddon");
        postService.deletePost(request);
        assertEquals(1, postRepository.count());
    }

}