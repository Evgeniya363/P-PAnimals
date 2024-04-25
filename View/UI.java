package View;

import Model.Exceptions.DateParseException;
import Model.Exceptions.IdNotFoundException;
import Presenter.Presenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final Presenter presenter;
    Scanner scanner = new Scanner(System.in);
    public static DateFormat df;

    static {
        df = new SimpleDateFormat("dd.MM.yyyy");
    }

    public UI() throws IdNotFoundException {
        this.presenter = new Presenter(this);
        init();
        System.out.println("\nРеестр домашних животных\n    \"Human friends\"");
        new Menu(this, scanner);
    }

    public void addAnimal(){
        System.out.println("\nДобавление животного:");
        String name, commands, otherData;
        Date birthday = null;
        boolean isPets, isPackAnimals;

        List<String> pets = Arrays.asList(new String[]{"Dog", "Cat", "Hamster"});
        List<String> packAnimals = Arrays.asList(new String[]{"Camel","Horse","Donkey"});

        System.out.print("\nДоступно: Dog, Cat, Hamster, Horse, Camel, Donkey\nВведите вид: ");
        String type = scanner.nextLine();
        isPets = pets.contains(type);
        isPackAnimals = packAnimals.contains(type);

        if (isPets || isPackAnimals) {
            System.out.print("\nИмя: ");
            name = scanner.nextLine();
            try {
                birthday = inputDate("\nДата рождения: ");
            } catch (Exception e) {
                showMessage(e.getMessage());
            }


//            System.out.print("\nДата рождения: ");
//            birthday = scanner.nextLine();
            System.out.print("\nПример команд: Sit, Stay, Fetch, Roll, Hide, Paw, Bark..."
                    + "\nВведите команды через запятую: ");
            commands = scanner.nextLine();
            System.out.println();
            if(isPets) {
                System.out.print("Пример места содержания: booth, aviary, aquarium, terrarium, box, shed, garage\nМесто содержания: ");
            } else {
                System.out.print("Грузоподъемность (кг): ");
            }
            otherData = scanner.nextLine();
            presenter.addAnimal(name, type, birthday, commands, otherData);
            showMessage("\nДобавлено!");
        } else {
            showMessage("Неизвестное животное");
        }
    }

    private void init() {
        try {
            presenter.addAnimal("Rex", "Dog", df.parse("01.01.2020"), "Sit, Stay, Fetch", "Cage");
            presenter.addAnimal("Whiskers", "Cat", df.parse("15.05.2019"), "Sit, Pounce", "Box");
            presenter.addAnimal("Hammy", "Hamster", df.parse("10.03.2021"), "Roll, Hide", "Terrarium");
            presenter.addAnimal("Thunder", "Horse", df.parse("21.07.2021"), "Trot, Canter, Gallop", "300");
            presenter.addAnimal("Sandy", "Camel", df.parse("03.11.2016"), "Sit, Stay, Roll", "600");
            presenter.addAnimal("Eeyore", "Donkey", df.parse("01.01.2019"), "Walk, Carry Load, Bray", "250");
        } catch (Exception e){}
    }

    public void exit() {
        System.out.println("\nДо встречи!");
    }

    public void showAnimalList() {
        System.out.println("\nСписок животных:\n" + presenter.showAnimalsList());
    }

    public void addCommand() {
        System.out.print("\nДобавление команды\nВведите id животного: ");
        int id = choisAnimal();
        if(id != 0) {
            System.out.print("Введите команду: ");
            String command = scanner.nextLine();
            presenter.addCommand(command);
        }
    }

    private int choisAnimal () {
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            findAnimalById(id);
            return id;
        } catch (IdNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (Exception e) {
            showMessage("Неверный ввод: ожидается число");
        }
       return 0;
    }

    public void showCommands() throws IdNotFoundException {
        System.out.print("\nВывод команд\nВведите id животного: ");
        int id = choisAnimal();
        if(id > 0) {
            System.out.print("\nСписок команд: ");
            System.out.println(presenter.showCommands());
        }
    }

    private void findAnimalById(int id) throws IdNotFoundException {
        presenter.findAnimalById(id);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showBirthdayAnimals() {
        System.out.println("\nВывод именинников");
        try {
            Date birthday = inputDate("Введите дату: ");
            System.out.println("\nСписок именинников:");
            showMessage(presenter.showBirthdayAnimals(birthday));
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    private Date inputDate(String msg) throws DateParseException {
        System.out.print(msg);
        String input = scanner.nextLine();
        try {
            return df.parse(input);
        } catch ( ParseException e) {
            throw new DateParseException("Неверный формат даты: " + input, 0);
        }
    }
}