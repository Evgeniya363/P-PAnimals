package Model.Animals.HumanFriends;

import java.text.ParseException;
import java.util.List;

public class PackAnimal extends HumanFriend {
    private final int loadCapacity;
    public PackAnimal(String name, String birthday, List<String> commands, String loadCapacity) throws ParseException {
        super(name, birthday, commands);
        try{
        this.loadCapacity = Integer.parseInt(loadCapacity);
        } catch (Exception e) {
            throw new RuntimeException("Неверно задана грузоподъемность: требуется ввести число(кг)");
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", грузоподъемность: " + this.loadCapacity ;
    }
}
