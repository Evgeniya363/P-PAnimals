package View.Commands;

import View.UI;

public class Create extends MenuItem{
    public Create( UI ui) {
        super( "Новое животное", ui);
    }
    @Override
    public boolean execute() {
        getUi().addAnimal();
        return true;
    }
}
