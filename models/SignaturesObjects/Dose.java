package models.SignaturesObjects;

import java.io.Serializable;

public class Dose implements Serializable{

    private String tipo;

    private Dose(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }

    public final static Dose firts = new Dose("Primeira");
    public final static Dose second = new Dose("Segunda");
    public final static Dose unique = new Dose("Unica");

    public static boolean compareDoseTypes(Dose dose1, Dose dose2){
        return dose1 == dose2;
    }
}
