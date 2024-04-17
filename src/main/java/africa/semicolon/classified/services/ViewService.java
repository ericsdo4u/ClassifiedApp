package africa.semicolon.classified.services;

import africa.semicolon.classified.dtos.ViewRequest;
import africa.semicolon.classified.response.ViewResponse;

public interface ViewService {
    ViewResponse viewPost(ViewRequest request);
    long getListOfViewers();
    long getNumberOfViews(String postId);
}
