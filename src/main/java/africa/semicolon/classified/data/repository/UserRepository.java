package africa.semicolon.classified.data.repository;

import africa.semicolon.classified.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, String>{
    User findByUsername(String username);

}
