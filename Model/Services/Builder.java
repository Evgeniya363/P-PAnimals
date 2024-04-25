package Model.Services;

import Model.Animals.Interfaces.Teachable;
import Model.Animals.Types.*;
import Model.Exceptions.DateParseException;
import Model.Exceptions.IllegalValueException;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Builder {
    public Teachable build (String name, String type, Date birthday, String commands, String otherData) throws ParseException, IllegalValueException {
        Teachable animal = null;
        List<String> commandList = Arrays.asList(commands.split(","));
        try {
            animal = switch (type) {
                case "Dog" -> new Dog(name, birthday, commandList, otherData);
                case "Cat" -> new Cat(name, birthday, commandList, otherData);
                case "Hamster" -> new Hamster(name, birthday, commandList, otherData);
                case "Camel" -> new Camel(name, birthday, commandList, otherData);
                case "Horse" -> new Horse(name, birthday, commandList, otherData);
                case "Donkey" -> new Donkey(name, birthday, commandList, otherData);
                default -> animal;
            };
            return animal;
        } catch ( ParseException e) {
            throw new DateParseException("Неверный формат даты: " + birthday, 0);
        }
    }
}
