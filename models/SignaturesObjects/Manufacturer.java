package models.SignaturesObjects;

import java.io.Serializable;

public class Manufacturer implements Serializable{
    private String name;
    private int quantDoses; 

    private Manufacturer(String name, int quantDoses){
        this.name = name;
        this.quantDoses = quantDoses;
    }
    static public String[] getNamesOfManofacturer(){
        String[] arrayReturn = {"Sinovac","AstraZeneca","Pfizer","Janssen"};
        return arrayReturn;
    }
    public static Manufacturer selectManufacture(String option) throws Exception{
        switch(option){
            case "Sinovac":
                return Manufacturer.sinovac;
            case "AstraZeneca": 
                return Manufacturer.astraZeneca;
            case "Pfizer": 
                return Manufacturer.pfizer;
            case "Janssen": 
                return Manufacturer.Janssen;
            default:
            throw new Exception("vacina invalida");
        }
    }

    public final static Manufacturer sinovac = new Manufacturer("Sinovac", 2);
    public final static Manufacturer astraZeneca = new Manufacturer("AstraZeneca", 2);
    public final static Manufacturer pfizer = new Manufacturer("Pfizer", 2);
    public final static Manufacturer Janssen = new Manufacturer("Janssen", 1);

    public int getDoses(){
        return this.quantDoses;
    }

    public String getName(){
        return this.name;
    }
}
