package Presenter;

import Model.Exceptions.IdNotFoundException;
import Model.Services.ServiceHumanFriends;
import View.UI;

public class Presenter {
    private final ServiceHumanFriends serviceHumanFriends;
    UI ui;

    public Presenter(UI ui) {
        this.serviceHumanFriends = ServiceHumanFriends.getAnimalList();
        this.ui = ui;
    }

    public String showAnimalsList () {
        return serviceHumanFriends.toString();
    }

    public void addAnimal(String name, String type, String birthday, String commands, String otherData) {
        try{
            serviceHumanFriends.addAnimal(name, type,birthday,commands,otherData);
//            ui.showMessage("\nДобавлено!");
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
        }
    }

    public void addCommand(String command) {
        try {
            serviceHumanFriends.addCommand(command);
            ui.showMessage("Команда добавлена");
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
        }
    }

    public void findAnimalById(int id) throws IdNotFoundException {
        ui.showMessage("\nВыбрано: " + serviceHumanFriends.findAnimalById(id));
    }

    public String showCommands() throws IdNotFoundException {
        return serviceHumanFriends.showCommands();
    }
}
