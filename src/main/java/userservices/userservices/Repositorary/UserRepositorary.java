package userservices.userservices.Repositorary;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import userservices.userservices.Enitity.User;
@Repository
public interface UserRepositorary extends MongoRepository<User, String> {
}
