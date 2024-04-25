package Model.Animals;

import Model.Animals.Interfaces.Infotable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Animal implements Infotable {
    private int id;
//    private static int countId;
    private final Date birthday;
    public static DateFormat df;

    static {
        df = new SimpleDateFormat("dd.MM.yyyy");
    }

    public Animal(int id, Date birthday) throws ParseException {
        this.id = id;
        this.birthday = birthday;
    }
//    private int genId(){
//        return ++countId;
//    }

    public int getId() {
        return id;
    }

//    public static int getCountId() {
//        return countId;
//    }

    public Date getBirthday() {
        return birthday;
    }


    @Override
    public String getInfo() {
        return  getShortInfo() +
                ", день рождения: " + df.format(birthday);
    }

    @Override
    public String getShortInfo() {
        return  "id: " + id +
                ", животное: " + this.getClass().getSimpleName();
    }

}
