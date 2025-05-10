package start;

import view.Menu;

/**
 * A Main osztály a program belépési pontja.
 * <p>
 * Az osztály public, így más csomagokból és osztályokból is példányosítható és
 * elérhető, de a main metódus miatt főként futtatásra szolgál.
 */
public class Main {
    /**
     * Alapértelmezett konstruktor a Main osztályhoz.
     * Ez a konstruktor public, így más osztályokból is példányosítható.
     */
    public Main() {
    }

    /**
     * A program belépési pontja.
     * Ez a metódus public static, hogy a Java futtatókörnyezet el tudja indítani az
     * alkalmazást.
     * 
     * @param args parancssori argumentumok
     */
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}