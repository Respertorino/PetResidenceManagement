package p7;
public class Dog extends Pet{
    
    // Atributos de instancia
    private char dangerous;

    // Atributos estáticos
    final static private char CHOICE_TRUE = 'Y';
    final static private char CHOICE_FALSE = 'N';
    final static private float POINTS_FACTOR = 1.2F;

    // constructor vacío
    public Dog() {
    }

    //Constructor con argumentos

    public Dog(String id, String name, float weight, int spending, char dangerous){
        super(id,name,weight,spending);
        this.dangerous = dangerous;
    }

    // Métodos

    //Métodos estáticos o de clase
    
    public static Boolean isValidDanger(char dangerous){

        if(dangerous!=CHOICE_TRUE && dangerous!=CHOICE_FALSE ){
            System.out.println("Incorrect danger state: <" + dangerous + ">. Valid state is <" + CHOICE_TRUE + ">/<" + CHOICE_FALSE + ">");
            return false;
        }
        return true;
    }

    // Métodos de instancia

    public char getDangerous(){
        return dangerous;
    }

    public void setDangerous(char dangerous){
        this.dangerous = dangerous;
    }

    public String toString(){
        return("D;" + super.toString() + ";" + dangerous);
    }

    public int getFidelityPoints(){
        int score;

        score = getSpending() / 100 * 10;

        score = (int)(score * POINTS_FACTOR);

        return score;
    }

}
