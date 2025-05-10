/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 * A TimeIntensity enum a játék időintenzitását (sebességét) reprezentálja.
 * Minden értékhez tartozik egy multiplikátor, amely meghatározza az idő gyorsításának mértékét.
 * <p>
 * Az enum public, így más csomagokból és osztályokból is használható és elérhető.
 */
public enum TimeIntensity {
    /**
     * Normál időintenzitás.
     * Az enum érték public, így más osztályokból is használható.
     */
    NORMAL(0),
    /**
     * Gyors időintenzitás.
     * Az enum érték public, így más osztályokból is használható.
     */
    FAST(5),
    /**
     * Leggyorsabb időintenzitás.
     * Az enum érték public, így más osztályokból is használható.
     */
    FASTEST(10);

    private final int multi;

    /**
     * Privát konstruktor a TimeIntensity enumhoz.
     * Csak az enum elemei példányosíthatják.
     * @param multi az idő gyorsításának mértéke
     */
    private TimeIntensity(int multi){
        this.multi = multi;
    }

    /**
     * Visszaadja az idő gyorsításának mértékét.
     * Ez a metódus public, hogy más osztályok is lekérhessék az enumhoz tartozó multiplikátort.
     * @return az idő gyorsításának mértéke
     */
    public int getMulti(){
        return multi;
    }
}
