package Model.Animals.HumanFriends;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public abstract class Pet extends HumanFriend {
    private static int countId;
    private final String animalHouse;
    public Pet(String name, Date birthday, List<String> commands, String animalHouse) throws ParseException {
        super(++countId, name, birthday, commands);
        this.animalHouse = animalHouse;
    }


    @Override
    public String toString() {
        return super.toString() + ", место содержания: " + animalHouse ;
    }
}
