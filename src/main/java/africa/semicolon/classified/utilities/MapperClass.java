package africa.semicolon.classified.utilities;

import africa.semicolon.classified.data.model.Post;
import africa.semicolon.classified.data.model.User;
import africa.semicolon.classified.dtos.PostRequest;
import africa.semicolon.classified.dtos.RegisterRequest;
import africa.semicolon.classified.response.PostReturnResponse;
import africa.semicolon.classified.response.RegisterResponse;
import africa.semicolon.classified.dtos.LogOutRequest;
import africa.semicolon.classified.dtos.LoginRequest;

import java.time.format.DateTimeFormatter;

public class MapperClass {
    public static User map(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        return user;
    }
    public static RegisterResponse mapRegResponse(User user){
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setEmail(user.getEmail());
        registerResponse.setUsername(user.getUsername());
        registerResponse.setId(user.getId());
        registerResponse.setDateCreated(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(user.getDateCreated()));
        return registerResponse;
    }
    public static User mapLoginResponse(LoginRequest loginRequest){
        User user = new User();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(loginRequest.getPassword());
        return user;
    }
    public static User mapLogOutResponse(LogOutRequest logOutRequest){
        User user = new User();
        user.setUsername(logOutRequest.getUsername());
        user.setPassword(logOutRequest.getPassword());
        return user;
    }
    public static Post mapPostAds(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(postRequest.getAuthor());
        return post;
    }
    public static PostReturnResponse mapPostReturnMessage(Post post){
        PostReturnResponse postReturnResponse = new PostReturnResponse();
        postReturnResponse.setTitle(post.getTitle());
        postReturnResponse.setContent(post.getContent());
        postReturnResponse.setId(post.getId());
        postReturnResponse.setAuthor(post.getAuthor());
        postReturnResponse.setPostedAt(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(post.getCreatedAt()));
        return postReturnResponse;
    }
    public static boolean isPasswordIncorrect(User foundUser, String password) {
        return !foundUser.getPassword().equals(password);
    }
}
