package africa.semicolon.classified.utilities;

import africa.semicolon.classified.data.model.Post;
import africa.semicolon.classified.data.model.User;
import africa.semicolon.classified.data.model.View;
import africa.semicolon.classified.dtos.PostRequest;
import africa.semicolon.classified.dtos.RegisterRequest;
import africa.semicolon.classified.response.PostResponse;
import africa.semicolon.classified.response.RegisterResponse;
import africa.semicolon.classified.dtos.ViewRequest;
import africa.semicolon.classified.response.ViewResponse;

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
    public static africa.semicolon.classified.data.model.Post mapPostAds(PostRequest postRequest){
        africa.semicolon.classified.data.model.Post post = new africa.semicolon.classified.data.model.Post();
        post.setUsername(postRequest.getUsername());
        post.setTitle(postRequest.getTitle());
        post.setDetail(postRequest.getDetail());
        post.setSeller(postRequest.getSeller());
        post.setPrice(postRequest.getPrice());
        return post;
    }
    public static PostResponse mapPostReturnMessage(Post post){
        PostResponse postReturnResponse = new PostResponse();
        postReturnResponse.setTitle(post.getTitle());
        postReturnResponse.setDetail(post.getDetail());
        postReturnResponse.setId(post.getId());
        postReturnResponse.setSeller(post.getSeller());
        postReturnResponse.setPrice(post.getPrice());
        postReturnResponse.setPostedAt(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(post.getCreatedAt()));
        return postReturnResponse;
    }
    public static View mapView(ViewRequest request){
        View view = new View();
        view.setUsername(request.getUsername());
        return view;
    }
    public static ViewResponse mapViewResponse(View view){
        ViewResponse viewResponse = new ViewResponse();
        viewResponse.setUsername(view.getUsername());
        viewResponse.setTimeOfView(DateTimeFormatter.ofPattern("dd/MM/yyyy 'at' HH:mm:ss a").format(view.getTimeOfView()));
        return viewResponse;
    }
    public static boolean isPasswordIncorrect(User foundUser, String password) {
        return !foundUser.getPassword().equals(password);
    }
}
