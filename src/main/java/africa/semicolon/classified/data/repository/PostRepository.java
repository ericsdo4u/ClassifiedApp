package africa.semicolon.classified.data.repository;
import africa.semicolon.classified.data.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Post findPostByUsername(String username);

    Post findPostByTitle(String title);

    List<Post> findByUsername(String username);

    List<Post> findAllPostByTitle(String title);

}
