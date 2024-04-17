package africa.semicolon.classified.services;

import africa.semicolon.classified.data.model.User;
import africa.semicolon.classified.dtos.LoginRequest;
import africa.semicolon.classified.dtos.PostRequest;
import africa.semicolon.classified.dtos.RegisterRequest;
import africa.semicolon.classified.dtos.ViewRequest;
import africa.semicolon.classified.response.PostResponse;
import africa.semicolon.classified.response.RegisterResponse;
import africa.semicolon.classified.response.ViewResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ViewServiceImplTest {


    @Autowired
    private ViewService viewService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @Test
    public void testThatPostCnaBeViewed() {
        User user = new User();
        RegisterRequest request = new RegisterRequest();
        request.setEmail("ericson@gmail.com");
        request.setUsername("don");
        request.setPassword("password");
        RegisterResponse response = userService.register(request);
        assertEquals(1, userService.getNumberOfRegisteredUsers());
        assertEquals("don", response.getUsername());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("don");
        loginRequest.setPassword("password");
        userService.login(loginRequest);
        assertFalse(user.isLocked());

        PostRequest postRequest = new PostRequest();
        postRequest.setUsername("don");
        postRequest.setTitle("jac");
        postRequest.setDetail("programming requires thinking");
        PostResponse postReturnResponse = postService.postAds(postRequest);
        assertEquals(1, postService.getListOfPost());
        assertEquals("jac", postReturnResponse.getTitle());

        ViewRequest viewRequest = new ViewRequest();
        viewRequest.setUsername("don");
        ViewResponse viewResponse = viewService.viewPost(viewRequest);
        assertEquals(1, viewService.getListOfViewers());
        assertEquals("don", viewResponse.getUsername());
    }
}
