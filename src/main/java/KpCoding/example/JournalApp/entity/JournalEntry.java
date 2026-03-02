package KpCoding.example.JournalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
public class JournalEntry {
    @Id
    public ObjectId id;
    public String title;
    public String content;
    public LocalDateTime Date;

}
