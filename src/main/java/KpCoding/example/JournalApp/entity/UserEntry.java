package KpCoding.example.JournalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_entries")
@Data
@NoArgsConstructor
public class UserEntry {
    @Id
    public ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    private String email;
    private Boolean SentimentAnalysis;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;

}
