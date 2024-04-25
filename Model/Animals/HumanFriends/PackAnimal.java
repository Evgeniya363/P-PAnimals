package Model.Animals.HumanFriends;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public abstract class PackAnimal extends HumanFriend {
    private static int countId;
    private final int loadCapacity;
    public PackAnimal(String name, Date birthday, List<String> commands, String loadCapacity) throws ParseException {
        super(++countId, name, birthday, commands);
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
