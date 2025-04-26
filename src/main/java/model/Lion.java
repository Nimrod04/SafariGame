package model;

public class Lion extends Carnivore {

    public static final int PRICE = 2000;

    public void roar() {
        // Oroszlán üvöltés
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

}
