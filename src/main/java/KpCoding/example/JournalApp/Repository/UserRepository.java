package KpCoding.example.JournalApp.Repository;

import KpCoding.example.JournalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntry, ObjectId>{
    UserEntry findByUserName(String userName);
    void deleteByUserName(String userName);
}
