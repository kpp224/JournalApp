package KpCoding.example.JournalApp.Repository;

import KpCoding.example.JournalApp.entity.ConfigJournalAppEntity;
import KpCoding.example.JournalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
