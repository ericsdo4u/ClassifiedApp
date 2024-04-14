package africa.semicolon.classified.services;

import africa.semicolon.classified.dtos.DeleteRequest;
import africa.semicolon.classified.dtos.PostRequest;
import africa.semicolon.classified.response.DeleteReturnResponse;
import africa.semicolon.classified.response.PostReturnResponse;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    PostReturnResponse postAds(PostRequest postRequest);
    PostReturnResponse editPost(PostRequest postRequest);
    DeleteReturnResponse deletePost(DeleteRequest request);
    long getListOfPost();
}
