package Presenter;

import Model.Exceptions.IdNotFoundException;
import Model.Services.ServiceHumanFriends;
import View.UI;

import java.util.Date;

public class Presenter {
    private final ServiceHumanFriends serviceHumanFriends;
    UI ui;

    public Presenter(UI ui) {
        this.serviceHumanFriends = ServiceHumanFriends.getServiceHumanFriends();
        this.ui = ui;
    }

    public String showAnimalsList () {
        return serviceHumanFriends.toString();
    }

    public void addAnimal(String name, String type, Date birthday, String commands, String otherData) {
        try{
            serviceHumanFriends.addAnimal(name, type, birthday,commands,otherData);
        } catch (Exception e) {
            UI.showMessage(e.getMessage());
        }
    }

    public boolean addCommand(String command) {
        if(command.isEmpty()) {
            UI.showMessage("Пропущено\n");
            return serviceHumanFriends.addCommand(command);
        } else if (serviceHumanFriends.addCommand(command)) {
            UI.showMessage("Команда добавлена\n");
            return true;
        }
        return false;
    }

    public void findAnimalById(int id) throws IdNotFoundException {
        serviceHumanFriends.findAnimalById(id);
    }

    public String showCommands() throws IdNotFoundException {
        return serviceHumanFriends.showCommands();
    }

    public String showBirthdayAnimals(Date birthday) {
        return  serviceHumanFriends.showBirthdayAnimals(birthday);
    }

    public String getFoundItem() {
        return serviceHumanFriends.getFoundItem();
    }
}
