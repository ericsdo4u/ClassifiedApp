package africa.semicolon.classified.data.repository;

import africa.semicolon.classified.data.model.View;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ViewRepository extends MongoRepository<View, String> {
    List<View>  findViewByPostId(String postId);
    List<View>  findViewByUsername(String postId);
}
