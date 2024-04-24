package Model.Animals.HumanFriends;

import Model.Animals.Animal;
import Model.Animals.Interfaces.Teachable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HumanFriend extends Animal implements Teachable {

    private String name;
    private List<String> commands;

    public HumanFriend(String name, String birthday, List<String> commands) throws ParseException {
        super(birthday);
        this.name = name;
        this.commands=new ArrayList<>();
        this.commands.addAll(commands);
    }


    @Override
    public void teachCommand(String command) {
        commands.add(command);
    }

    @Override
    public String showCommands() {
        return this.commands.toString();
    }


    @Override
    public String getInfo() {
        return  getShortInfo() +
                ", день рождения: " + simpleDateFormat.format(this.getBirthday())  +
                ", команды: " + this.commands;
    }

    @Override
    public String getShortInfo() {
        return  super.getShortInfo() +
                ", имя: " + name;
    }

}
