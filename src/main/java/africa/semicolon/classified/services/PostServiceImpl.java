package africa.semicolon.classified.services;

import africa.semicolon.classified.data.model.Post;
import africa.semicolon.classified.data.repository.PostRepository;
import africa.semicolon.classified.dtos.DeleteRequest;
import africa.semicolon.classified.dtos.PostRequest;
import africa.semicolon.classified.exceptions.AuthorNotFoundException;
import africa.semicolon.classified.exceptions.PostNotFoundException;
import africa.semicolon.classified.response.DeleteReturnResponse;
import africa.semicolon.classified.response.PostReturnResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.classified.utilities.MapperClass.mapPostAds;
import static africa.semicolon.classified.utilities.MapperClass.mapPostReturnMessage;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    private PostRepository postRepository;
    @Override
    public PostReturnResponse postAds(PostRequest postRequest) {
        Post post = mapPostAds(postRequest);
        Post newPost = postRepository.save(post);
        return mapPostReturnMessage(newPost);
    }
    @Override
    public PostReturnResponse editPost(PostRequest postRequest) {
        Optional<Post> foundPost = Optional.ofNullable(postRepository.findPostByAuthor(postRequest.getAuthor()));

        if (foundPost.isEmpty()) {
            throw new AuthorNotFoundException("no author found");
        }
        Post existingPost = foundPost.get();
        existingPost.setTitle(postRequest.getTitle());
        existingPost.setContent(postRequest.getContent());
        Post editedPost = postRepository.save(existingPost);
        return mapPostReturnMessage(editedPost);
    }
    @Override
    public DeleteReturnResponse deletePost(DeleteRequest request){
        Post foundPost = postRepository.findPostByAuthor(request.getAuthor());
        if (foundPost == null){
            throw new PostNotFoundException("post not found");
        }
        postRepository.delete(foundPost);
        DeleteReturnResponse response = new DeleteReturnResponse();
        response.setMessage("post deleted");
        return response;
    }
    @Override
    public long getListOfPost() {
        return postRepository.count();
    }
}
