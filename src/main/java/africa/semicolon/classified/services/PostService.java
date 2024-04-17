package africa.semicolon.classified.services;

import africa.semicolon.classified.data.model.Post;
import africa.semicolon.classified.dtos.DeleteRequest;
import africa.semicolon.classified.dtos.PostRequest;
import africa.semicolon.classified.response.DeleteResponse;
import africa.semicolon.classified.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    PostResponse postAds(PostRequest postRequest);

    PostResponse editPost(PostRequest postRequest);

    DeleteResponse deletePost(DeleteRequest request);

    Post findPostBy(String username);

    List<Post> findAllPostBelongingTo(String username);

    List<Post> findAllBy(String title);

    long getListOfPost();

    List<Post> findAllPost();
}
