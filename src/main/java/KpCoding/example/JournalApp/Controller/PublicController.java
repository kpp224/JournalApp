package KpCoding.example.JournalApp.Controller;

import KpCoding.example.JournalApp.entity.UserEntry;
import KpCoding.example.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody UserEntry userEntry){
        userService.saveNewUser(userEntry);
    }
}
