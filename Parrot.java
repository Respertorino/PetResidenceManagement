package p7;
public class Parrot extends Pet{
    
    // Atributos de instancia
    private int speakCapability;

    // Atributos estáticos
    final static private int MAX_SPEAK = 60;

    // constructor vacío
    public Parrot() {
    }

    //Constructor con argumentos

    public Parrot(String id, String name, float weight, int spending, int speakCapability){
        super(id,name,weight,spending);
        this.speakCapability = speakCapability;
    }

    // Métodos

    //Métodos estáticos o de clase

    public static Boolean isValidSpeak(int speakCapability){

        if(speakCapability<0 || speakCapability>MAX_SPEAK){
            System.out.println("Incorrect speak value: <" + speakCapability + ">. Valid range is <0>-<" + MAX_SPEAK + ">");
            return false;
        }
        return true;
    }

    // Métodos de instancia
    
    public int getSpeakCapability(){
        return speakCapability;
    }

    public void setSpeakCapability(int speakCapability){
        this.speakCapability = speakCapability;
    }

    public String toString(){
        return("P;" + super.toString() + ";" + speakCapability);
    }

    public int getFidelityPoints(){
        int score;

        score = getSpending() / 100 * 10;

        return score;
    }
}
