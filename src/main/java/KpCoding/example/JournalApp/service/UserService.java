package KpCoding.example.JournalApp.service;

import KpCoding.example.JournalApp.Repository.UserRepository;
import KpCoding.example.JournalApp.entity.UserEntry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j // Logger
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public boolean saveNewUser(UserEntry userEntry){
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("USERS"));
            userRepository.save(userEntry);
            return true;
        } catch (Exception e){
            log.info("This is Logger info!");
            log.error("This is Logger error by : {} UserName ", userEntry.getUserName());
            log.warn("This is Logger warn!");
            log.debug("This is Logger debug!");
            log.trace("This is Logger trace!");
            return false;
        }
    }

    public void saveUser(UserEntry userEntry){
        userRepository.save(userEntry);
    }

    public List<UserEntry> getAllEntries(){
        return userRepository.findAll();
    }

    public void deleteByUserName(String userName){
        userRepository.deleteByUserName(userName);
    }

    public UserEntry findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void saveAdmin(UserEntry userEntry){
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(Arrays.asList("USERS","ADMIN"));
        userRepository.save(userEntry);
    }
}


// controller --> service --> repository