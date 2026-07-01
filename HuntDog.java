package p7;
public class HuntDog extends Dog{
    
    // Atributos de instancia
    private char prey;

    // Atributos estáticos
    final static private char PREY_PRATIDGE = 'P';
    final static private char PREY_RABBIT = 'R';
    final static private char PREY_FOX = 'F';
    final static private float POINTS_FACTOR = 1.3F;

    // constructor vacío
    public HuntDog() {
    }

    //Constructor con argumentos

    public HuntDog(String id, String name, float weight, int spending, char dangerous,char prey){
        super(id,name,weight,spending,dangerous);
        this.prey = prey;
    }

    // Métodos

    //Métodos estáticos o de clase

    public static Boolean isValidPrey(char prey){

        if(prey!=PREY_FOX && prey!=PREY_PRATIDGE && prey!=PREY_RABBIT){
            System.out.println("Incorrect prey identifier<" + prey + ">. Valid identifier is <" + PREY_PRATIDGE + ">/<" + PREY_RABBIT + ">/<" + PREY_FOX + ">");
            return false;
        }
        return true;
    }

    // Métodos de instancia
    
    public char getPrey(){
        return prey;
    }

    public void setPrey(char prey){
         this.prey = prey;
    }

    public String toString(){
        return(super.toString().replaceFirst("D", "H") + ";" + prey);
    }

    public int getFidelityPoints(){
        int score;

        score = getSpending() / 100 * 10;

        score = (int)(score * POINTS_FACTOR);

        return score;
    }
}
