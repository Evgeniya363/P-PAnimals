package Model.Animals.HumanFriends;

import Model.Animals.Animal;
import Model.Animals.Interfaces.Teachable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class HumanFriend extends Animal implements Teachable {
    private String name;
    private List<String> commands;

    public HumanFriend(int id, String name, Date birthday, List<String> commands) throws ParseException {
        super(id, birthday);
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
                ", день рождения: " + df.format(this.getBirthday())  +
                ", команды: " + this.commands;
    }

    @Override
    public String getShortInfo() {
        return  super.getShortInfo() +
                ", имя: " + name;
    }

}
