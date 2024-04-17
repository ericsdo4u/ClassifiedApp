package africa.semicolon.classified.services;

import africa.semicolon.classified.data.model.Post;
import africa.semicolon.classified.data.model.View;
import africa.semicolon.classified.data.repository.PostRepository;
import africa.semicolon.classified.data.repository.ViewRepository;
import africa.semicolon.classified.dtos.ViewRequest;
import africa.semicolon.classified.exceptions.PostNotFoundException;
import africa.semicolon.classified.response.ViewResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.classified.utilities.MapperClass.*;

@Service
@AllArgsConstructor
public class ViewServiceImpl implements ViewService{
    private ViewRepository viewRepository;
    private PostRepository postRepository;
    private UserService userService;


    @Override
    public ViewResponse viewPost(ViewRequest request) {
        userService.checkUser(request.getUsername());
        Optional<Post> post = postRepository.findById(request.getPostId());
        if(post.isEmpty()) throw new PostNotFoundException("Post Not Found");

        View view = mapView(request);
        viewRepository.save(view);
        return mapViewResponse(view);
    }
    @Override
    public long getListOfViewers() {
        return viewRepository.count();
    }
    public long getNumberOfView(String postId){
        List<View> views = viewRepository.findViewByPostId(postId);
        return views.size();
    }
    @Override
    public long getNumberOfViews(String username){
        List<View> views = viewRepository.findViewByUsername(username);
        return views.size();
    }

}
