package Model.Animals.Types;

import Model.Animals.HumanFriends.Pet;

import java.text.ParseException;
import java.util.List;


public class Dog extends Pet {
    public Dog(String name, String birthday, List<String> commands, String animalHouse) throws ParseException {
        super(name, birthday, commands, animalHouse);
    }
}
