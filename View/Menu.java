package View;

import Model.Exceptions.IdNotFoundException;
import View.Commands.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    List<MenuItem> menu = new ArrayList<>();

    public Menu(UI ui, Scanner scanner) throws IdNotFoundException {

        menu.add(new Create(ui));
        menu.add(new Show(ui));
        menu.add(new AddCommand(ui));
        menu.add(new ShowCommands(ui));
        menu.add(new Exit(ui));

        MenuItem menuItem; // = menu.getFirst();
        boolean access = true;
        do {
            System.out.println("\n        Меню:");
            System.out.println("-".repeat(20));
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i).getName());
            }
            System.out.println("-".repeat(20));
            System.out.print("Ваш выбор: ");
            try {
                menuItem = menu.get(scanner.nextInt() - 1);
                scanner.nextLine();
                access = (menuItem.execute());
            } catch (Exception e) {
                scanner.nextLine();
                ui.showMessage("Неверный ввод. Введите число от 1 до " + menu.size());
            }
        } while(access);  //(menuItem.execute());
    }
}
