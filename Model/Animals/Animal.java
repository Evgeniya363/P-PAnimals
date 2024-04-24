package Model.Animals;

import Model.Animals.Interfaces.Infotable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Animal implements Infotable {
    private int id;
    private static int countId;
    private final Date birthday;
    public static SimpleDateFormat simpleDateFormat;
    private static DateFormat df;

    static {
        simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        df = new SimpleDateFormat("dd.MM.yyyy");
    }

    public Animal(String birthday) throws ParseException {
        this.id = genId();
        this.birthday = df.parse(birthday);
    }
    private int genId(){
        return ++countId;
    }

    public int getId() {
        return id;
    }

    public static int getCountId() {
        return countId;
    }

    public Date getBirthday() {
        return birthday;
    }


    @Override
    public String getInfo() {
        return  getShortInfo() +
                ", день рождения: " + simpleDateFormat.format(birthday);
    }

    @Override
    public String getShortInfo() {
        return  "id: " + id +
                ", животное: " + this.getClass().getSimpleName();
    }



}
