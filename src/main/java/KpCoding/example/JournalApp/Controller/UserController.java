package KpCoding.example.JournalApp.Controller;

import KpCoding.example.JournalApp.Constants.RandomCity;
import KpCoding.example.JournalApp.api.resoponse.WeatherResponse;
import KpCoding.example.JournalApp.entity.UserEntry;
import KpCoding.example.JournalApp.service.UserService;
import KpCoding.example.JournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserEntry userEntry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntry userInDB = userService.findByUserName(userName);
        userInDB.setUserName(userEntry.getUserName());
        userInDB.setPassword(userEntry.getPassword());
        userService.saveNewUser(userInDB);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        RandomCity randomCity = new RandomCity();
        String city = randomCity.selectRandomCity();

        WeatherResponse weatherResponse = weatherService.getWeather(city);
        String greeting = "";
        if(weatherResponse != null){
            greeting = ", In " + city + ", Weather feels like " + weatherResponse.getCurrent().getFeelslike() + " and weather is like " + weatherResponse.getCurrent().getWeatherDescriptions().get(0);
        }

        return new ResponseEntity<>("Hi " + userName + greeting,HttpStatus.OK);
    }

}
