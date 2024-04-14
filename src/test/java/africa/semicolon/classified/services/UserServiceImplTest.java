package africa.semicolon.classified.services;

import africa.semicolon.classified.data.model.User;
import africa.semicolon.classified.data.repository.UserRepository;
import africa.semicolon.classified.dtos.DeleteRequest;
import africa.semicolon.classified.dtos.LogOutRequest;
import africa.semicolon.classified.dtos.LoginRequest;
import africa.semicolon.classified.dtos.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void setUp(){
        userService = new UserServiceImpl(userRepository);
    }
    @Test
    public void registerOneUser_OneUserRegisteredTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("ddon");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("ericsonericdon@gmail.com");
        userService.register(registerRequest);
        assertEquals(1, userService.getNumberOfRegisteredUsers());

    }
    @Test
    public void registerTwoUserWithSameUsername_ThrowExceptionOneUserRegisteredTest(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("ddon");
        registerRequest.setPassword("1234");
        registerRequest.setEmail("ericsonericdon@gmail.com");
        userService.register(registerRequest);
        RegisterRequest registerRequest2 = new RegisterRequest();
        registerRequest2.setUsername("ddon");
        registerRequest2.setPassword("1234");
        registerRequest2.setEmail("ericsonericdon@gmail.com");
        userService.register(registerRequest2);
        assertEquals(2, userService.getNumberOfRegisteredUsers());

    }
    @Test
    public void registerOneUserAndLogInUser_UserLoginTestTest(){
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
    }
    @Test
    public void registerOneUserLoginAndLogOut_UserLogOutTest(){
        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericsonericdon66@gmail.com");
        registerRequest.setUsername("ericson2");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);
        assertEquals(1, userService.getNumberOfRegisteredUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ericson2");
        loginRequest.setPassword("1234");
        userService.login(loginRequest);
        assertFalse(user.isLocked());

        LogOutRequest logOutRequest = new LogOutRequest();
        logOutRequest.setUsername("ericson2");
        logOutRequest.setPassword("1234");
        userService.logOut(logOutRequest);
        assertFalse(user.isLocked());
    }
    @Test
    public void registerOneUserLoginAndPostABlog_BlogPostTest(){
        User user = new User();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("ericsonericdon66@gmail.com");
        registerRequest.setUsername("ericson2");
        registerRequest.setPassword("1234");
        userService.register(registerRequest);
        assertEquals(1, userService.getNumberOfRegisteredUsers());

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ericson2");
        loginRequest.setPassword("1234");
        userService.login(loginRequest);
        assertFalse(user.isLocked());
    }
    @Test
    public void testThatOneUserIsRegistered_AndDeleted_RepoIsEmpty(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("eric");
        request.setPassword("password");
        request.setEmail("ericson@gmail.com");
        userService.register(request);
        assertEquals(1, userRepository.count());

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setUsername("eric");
        userService.deleteByUsername(deleteRequest);
        assertEquals(0, userRepository.count());
    }
}