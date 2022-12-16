package models;

import java.io.Serializable;
import java.util.Date;

import models.SignaturesObjects.Dose;
import models.SignaturesObjects.Manufacturer;

public class VaccinationRecord implements Serializable {
    Person person;
    Date applicationDate;
    Manufacturer manufacturer;
    Dose dose;

    public VaccinationRecord(Person person, Date applicationDate, Manufacturer manufacture, Dose dose){
        this.person = person;
        this.applicationDate = applicationDate;
        this.manufacturer = manufacture; 
        this.dose = dose;
    }

    public Dose getDose(){
        return this.dose;
    }

    public Person getPerson(){
        return this.person;
    }

    public Manufacturer getManufacture(){
        return this.manufacturer;
    }

    public boolean isFullVaccinated(){
        int dosetemp = (dose.getTipo().equals("Primeira") || dose.getTipo().equals("Unica")) ? 1 : 2;
        return dosetemp == manufacturer.getDoses();
    }

    public void print(){
        System.out.println("Pessoa: " + person.getName());
        System.out.println("Data de aplicaçao: " + applicationDate);
        System.out.println("Fornecedor de vacina: " + manufacturer.getName());
        System.out.println("Dose: " + dose.getTipo());
    }

    @Override
    public String toString() {
        return "Pessoa: " + person.getName() + "\nData de aplicaçao: " + applicationDate + "\nFornecedor de vacina: " + manufacturer.getName() + "\nDose: " + dose.getTipo();
    }
    
}