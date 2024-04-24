package View;

import Model.Exceptions.IdNotFoundException;
import Presenter.Presenter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final Presenter presenter;
    Scanner scanner = new Scanner(System.in);

    public UI() throws IdNotFoundException {
        this.presenter = new Presenter(this);
        init();
        System.out.println("\nРеестр домашних животных\n    \"Human friends\"");
        new Menu(this, scanner);
    }

    public void addAnimal(){
        System.out.println("\nДобавление животного:");
        String name, type, birthday, commands, otherData;
        boolean isPets, isPackAnimals;

        List<String> pets = Arrays.asList(new String[]{"Dog", "Cat", "Hamster"});
        List<String> packAnimals = Arrays.asList(new String[]{"Camel","Horse","Donkey"});

        System.out.print("\nДоступно: Dog, Cat, Hamster, Horse, Camel, Donkey\nВведите вид: ");
        type = scanner.nextLine();
        isPets = pets.contains(type);
        isPackAnimals = packAnimals.contains(type);

        if (isPets || isPackAnimals) {
            System.out.print("\nИмя: ");
            name = scanner.nextLine();
            System.out.print("\nДата рождения: ");
            birthday = scanner.nextLine();
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
        presenter.addAnimal("Rex","Dog","01.01.2020", "Sit, Stay, Fetch","Cage");
        presenter.addAnimal("Whiskers","Cat","15.05.2019", "Sit, Pounce","Box");
        presenter.addAnimal("Hammy","Hamster","10.03.2021", "Roll, Hide","Cage");
        presenter.addAnimal("Thunder","Horse","21.07.2021", "Trot, Canter, Gallop","Cage");
        presenter.addAnimal("Sandy","Camel","03.11.2016", "Sit, Stay, Roll","Cage");
        presenter.addAnimal("Eeyore","Donkey","18.09.2017", "Walk, Carry Load, Bray","Cage");
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
}