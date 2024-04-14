package africa.semicolon.classified.data.repository;

import africa.semicolon.classified.data.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
    Post findPostByAuthor(String author);

}
