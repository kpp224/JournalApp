package KpCoding.example.JournalApp.service;

import KpCoding.example.JournalApp.Repository.JournalEntryRepository;
import KpCoding.example.JournalApp.entity.JournalEntry;
import KpCoding.example.JournalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            UserEntry user = userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e){
            throw new RuntimeException("Error occurred while saving entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
       return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        try {
            UserEntry user = userService.findByUserName(userName);
            Boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e){
            throw new RuntimeException("An error occurred while deleting entry ", e);
        }
    }

}


// controller --> service --> repository