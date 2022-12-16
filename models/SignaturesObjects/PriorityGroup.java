package models.SignaturesObjects;

public class PriorityGroup {
    private String tipo;

    private PriorityGroup(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }

    static public String[] getNamesOfPriorityGroup(){
        String[] arrayReturn = {"Trabalhador da Saúde", "Portador de idade igual ou superior a 60 anos", "Indígena residente em terras indígenas", "Portador de comorbidades", "Funcionário do sistema de privação de liberdade", "Membro de forças de segurança e salvamento", "Trabalhador da educação"};
        return arrayReturn;
    }

    public static PriorityGroup selectedPriorityGroup(String option) throws Exception{
        switch(option){
            case "Trabalhador da Saúde":
                return PriorityGroup.HEALH_GROUP;
            case "Portador de idade igual ou superior a 60 anos": 
                return PriorityGroup.OLDER_GROUP;
            case "Indígena residente em terras indígenas":
                return PriorityGroup.INDIGENA_GROUP;
            case "Portador de comorbidades":
                return PriorityGroup.COMORBITIES_GROUP;
            case "Funcionário do sistema de privação de liberdade":
                return PriorityGroup.PRISON_GROUP;
            case "Membro de forças de segurança e salvamento":
                return PriorityGroup.SECURITY_GROUP;
            case "Trabalhador da educação":
                return PriorityGroup.EDUCATION_GROUP;
            default:
                throw new Exception("grupo de prioridade invalido");
        }
    }
    public final static PriorityGroup HEALH_GROUP = new PriorityGroup("Trabalhador da Saúde");
    public final static PriorityGroup OLDER_GROUP = new PriorityGroup("Portador de idade igual ou superior a 60 anos");
    public final static PriorityGroup INDIGENA_GROUP = new PriorityGroup("Indígena residente em terras indígenas");
    public final static PriorityGroup COMORBITIES_GROUP = new PriorityGroup("Portador de comorbidades");
    public final static PriorityGroup PRISON_GROUP = new PriorityGroup("Funcionário do sistema de privação de liberdade");
    public final static PriorityGroup SECURITY_GROUP = new PriorityGroup("Membro de forças de segurança e salvamento");
    public final static PriorityGroup EDUCATION_GROUP = new PriorityGroup("Trabalhador da educação");

    public String toString(){
    return this.tipo;
 }
}
