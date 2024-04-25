package Model.Services;

import Model.Animals.Interfaces.Infotable;
import Model.Exceptions.IdNotFoundException;
import Model.Animals.Interfaces.Teachable;
import Model.Exceptions.IllegalValueException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServiceHumanFriends {
    private final List<Teachable> animals = new ArrayList<>();
    private static ServiceHumanFriends serviceHumanFriends;

    private ServiceHumanFriends() {
        serviceHumanFriends = this;
    }
    private final Builder builder = new Builder();
    private List<Infotable> foundAnimalList; //Список животных формируется при выборе по id

    public static ServiceHumanFriends getServiceHumanFriends() { // Возвращаем ссылку на единственный экземпляр класса
        if(serviceHumanFriends == null) {
            new ServiceHumanFriends();
        }
        return serviceHumanFriends;
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

    public boolean addCommand(String command) {
        if(!foundAnimalList.isEmpty()) {
            Teachable animal = (Teachable) foundAnimalList.removeFirst();
            if (!command.isEmpty()) {
                animal.teachCommand(command);
            }
            return true;
        }
        return false;
    }

    public void findAnimalById(int id) throws IdNotFoundException {
        foundAnimalList = new ArrayList<>();

        for(Teachable animal : animals) {
            if(animal.getId() == id) {
                foundAnimalList.add(animal);
            }
        }
        if (foundAnimalList.isEmpty()) {
            throw new IdNotFoundException("id не найден");
        }
    }

    public String showCommands() throws IdNotFoundException {
        StringBuilder sb = new StringBuilder();
        foundAnimalList.forEach(n ->
             sb.append(((Teachable)n).getShortInfo())
                    .append(", команды: ")
                    .append(((Teachable)n).showCommands())
                    .append("\n")
        );
        if(sb.isEmpty()){
            throw new IdNotFoundException("id не найден");
        }
        return sb.toString(); //((Teachable)foundAnimal).showCommands();
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

    public String getFoundItem() {
        if(foundAnimalList.isEmpty()){
            return "";
        } else {
            return foundAnimalList.getFirst().getShortInfo();
        }
    }


}
