package africa.semicolon.classified.services;

import africa.semicolon.classified.data.model.Post;
import africa.semicolon.classified.data.model.User;
import africa.semicolon.classified.data.repository.PostRepository;
import africa.semicolon.classified.data.repository.UserRepository;
import africa.semicolon.classified.dtos.DeleteRequest;
import africa.semicolon.classified.dtos.PostRequest;
import africa.semicolon.classified.exceptions.PostNotFoundException;
import africa.semicolon.classified.exceptions.TitleNotFoundException;
import africa.semicolon.classified.response.DeleteResponse;
import africa.semicolon.classified.response.PostResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.classified.services.UserServiceImpl.checkAccountState;
import static africa.semicolon.classified.utilities.MapperClass.mapPostAds;
import static africa.semicolon.classified.utilities.MapperClass.mapPostReturnMessage;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public PostResponse postAds(PostRequest postRequest) {
        User foundUser = userRepository.findByUsername(postRequest.getUsername());
        checkAccountState(foundUser);
        africa.semicolon.classified.data.model.Post post = mapPostAds(postRequest);
        africa.semicolon.classified.data.model.Post newPost = postRepository.save(post);
        return mapPostReturnMessage(newPost);
    }
    @Override
    public PostResponse editPost(PostRequest postRequest) {
        Optional<africa.semicolon.classified.data.model.Post> foundPost = Optional.ofNullable(postRepository.findPostByTitle(postRequest.getTitle()));
        if (foundPost.isEmpty()) {
            throw new TitleNotFoundException("title not found");
        }
        africa.semicolon.classified.data.model.Post existingPost = foundPost.get();
        existingPost.setTitle(postRequest.getTitle());
        existingPost.setDetail(postRequest.getDetail());
        africa.semicolon.classified.data.model.Post editedPost = postRepository.save(existingPost);
        return mapPostReturnMessage(editedPost);
    }
    @Override
    public DeleteResponse deletePost(DeleteRequest request){
        Post foundPost = postRepository.findPostByUsername(request.getUsername());
        if (foundPost == null){
            throw new PostNotFoundException("post not found");
        }
        postRepository.delete(foundPost);
        DeleteResponse response = new DeleteResponse();
        response.setMessage("post deleted");
        return response;
    }

    @Override
    public Post findPostBy(String username) {
        Post post = postRepository.findPostByUsername(username);
        if (post == null)
            throw new PostNotFoundException("post not found");
        postRepository.save(post);
        return post;
    }

    @Override
    public List<Post> findAllBy(String title) {
        List<Post> posts = postRepository.findAllPostByTitle(title);
        if (posts.isEmpty())
         throw new TitleNotFoundException("title not found");
        postRepository.saveAll(posts);
        return posts;
    }
    @Override
    public List<Post> findAllPostBelongingTo(String username) {
       List<Post> posts = postRepository.findByUsername(username);
        if (posts.isEmpty())
            throw new PostNotFoundException("post not found");
        postRepository.saveAll(posts);
        return posts;
    }

    @Override
    public long getListOfPost() {
        return postRepository.count();
    }
    @Override
    public List<Post> findAllPost() {
       return postRepository.findAll();
    }
}
