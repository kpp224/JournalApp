package KpCoding.example.JournalApp.Constants;

public class RandomCity {
    public enum RandomCities{
        MUMBAI,
        NEW_YORK,
        LONDON,
        TOKYO,
        PARIS,
        DUBAI,
        SYDNEY
    }

    public String selectRandomCity(){
        RandomCities[] cities = RandomCities.values();
        int randomIndex = (int) (Math.random() * cities.length);
        return cities[randomIndex].name().replace("_"," ");
    }
}
