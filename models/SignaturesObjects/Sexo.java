package models.SignaturesObjects;

public class Sexo {
    private char tipo;

    private Sexo(char tipo){
        this.tipo = tipo;
    }

    public final static Sexo homem = new Sexo('H');
    public final static Sexo mulher = new Sexo('M');

    static public String[] getNamesOfSexo(){
        String[] arrayReturn = {"H", "M"};
        return arrayReturn;
    }

    public static Sexo selectedSexo(String option) throws Exception{
        switch(option){
            case "H":
                return homem;
            case "M": 
                return mulher;
            default:
                throw new Exception("Valor digitado invalido");
        }
    }

    public String toString(){
    if(tipo == 'H'){
        return "Masculino";
    }
    if(tipo == 'M'){
        return "Feminino";
    }
    return null;
 }
}