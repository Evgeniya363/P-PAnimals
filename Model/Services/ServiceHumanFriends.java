package Model.Services;

import Model.Animals.Interfaces.Infotable;
import Model.Exceptions.IdNotFoundException;
import Model.Animals.Interfaces.Teachable;
import Model.Exceptions.IllegalValueException;

import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServiceHumanFriends {
    private final List<Teachable> animals = new ArrayList<>();
    private static ServiceHumanFriends animalList;

    private ServiceHumanFriends() {
        animalList = this;
    }
    private final Builder builder = new Builder();
    private Infotable foundAnimal; //Животное, найденное при последней попытке поиска

    public static ServiceHumanFriends getAnimalList() { // Возвращаем ссылку на единственный экземпляр класса
        if(animalList == null) {
            new ServiceHumanFriends();
        }
        return animalList;
    }

    public void addAnimal(Teachable animal) {
        animals.add(animal);
    }

    public List<Teachable> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Teachable an: animals) {
            sb.append("\n").append(an.getInfo());
        }
        return  sb.toString();
    }

    public void addCommand(String command) throws IdNotFoundException {
        if(foundAnimal != null){
            ((Teachable)foundAnimal).teachCommand(command);
        } else {
            throw new IdNotFoundException("id не найден");
        }
    }

    public String findAnimalById(int id) throws IdNotFoundException {
        foundAnimal = null;
        int pos = 0;
        for(Teachable animal : animals) {
            if(animal.getId() == id) {
                foundAnimal = animals.get(pos);
                return foundAnimal.getShortInfo();
            }
            pos++;
        }
        throw new IdNotFoundException("id не найден");
    }

    public String showCommands() throws IdNotFoundException {
        if(foundAnimal != null){
            return ((Teachable)foundAnimal).showCommands();
        } else {
            throw new IdNotFoundException("id не найден");
        }
    }

    public void addAnimal(String name, String type, Date birthday, String commands, String otherData) throws ParseException, IllegalValueException {
        Teachable animal = builder.build(name, type, birthday, commands, otherData);
        addAnimal(animal);
    }

    public String showBirthdayAnimals(Date birthday) {
        StringBuilder sb = new StringBuilder();

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(birthday);
        int d = calendar1.get(Calendar.DAY_OF_MONTH);
        int m = calendar1.get(Calendar.MONTH);

        for (Teachable an : animals) {
            calendar2.setTime(an.getBirthday());
            if(d == calendar2.get(Calendar.DAY_OF_MONTH) && m == calendar2.get(Calendar.MONTH)) {
                sb.append(an.getShortInfo());
                sb.append("\n");
           }
        }
        return sb.toString();
    }
}
