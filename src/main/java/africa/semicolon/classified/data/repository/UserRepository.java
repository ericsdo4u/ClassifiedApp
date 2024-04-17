package africa.semicolon.classified.data.repository;

import africa.semicolon.classified.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository <User, String>{
    User findByUsername(String username);
    Optional<User> findUserByUsername(String username);

}
