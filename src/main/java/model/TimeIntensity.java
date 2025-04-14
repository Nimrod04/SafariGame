/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author nimro
 */
public enum TimeIntensity {
    NORMAL(0),FAST(5),FASTEST(10);
    
    private final int multi;
    
    private TimeIntensity(int multi){
        this.multi = multi;
    }
    public int getMulti(){
        return multi;
    }
    
}
