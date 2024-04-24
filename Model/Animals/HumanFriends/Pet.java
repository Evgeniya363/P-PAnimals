package Model.Animals.HumanFriends;

import java.text.ParseException;
import java.util.List;

public class Pet extends HumanFriend {
    private String animalHouse;
    public Pet(String name, String birthday, List<String> commands, String animalHouse) throws ParseException {
        super(name, birthday, commands);
        this.animalHouse = animalHouse;
    }

    @Override
    public String toString() {
        return super.toString() + ", место содержания: " + animalHouse ;
    }
}
