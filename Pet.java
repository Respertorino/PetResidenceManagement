package p7;
public abstract class Pet{ //Hacer actividad 3 y 4 de la P5
    
    // Atributos de instancia
    private String id,name;
    private float weight;
    private int spending;

    // Atributos estáticos
    final static private String ID_FORMAT = "LLLDDD"; // D=0..9, L=A..Z, constante accesible desde el exterior de la clase
    final static private float MAX_WEIGHT = 100;

    // constructor vacío
    public Pet() {
    }

    //Constructor con argumentos

    public Pet(String id, String name, float weight, int spending){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.spending = spending;
    }

    // Métodos

    //Métodos estáticos o de clase
    public static Boolean isValidID(String id){
            
        if(id.length()==6){
            for(int i=0;i<3;i++){
                try{
                    Integer.parseInt(id.substring(i, i+1));
                    System.out.println("Incorrect id value: <" + id + ">. Not comply with format <" + ID_FORMAT + ">");
                    return false;
                }catch(NumberFormatException e){
                }
            }

            for(int i=3;i<6;i++){
                try{
                    Integer.parseInt(id.substring(i, i+1));
                }catch(NumberFormatException e){
                    System.out.println("Incorrect id value: <" + id + ">. Not comply with format <" + ID_FORMAT + ">");
                    return false;
                }
            }
        }else{
            System.out.println("Incorrect id value: <" + id + ">. Not comply with format <" + ID_FORMAT + ">");
            return false;
        }

    return true;
    }

    public static Boolean isValidName(String name){

        for(int i=0;i<name.length();i++){
            try{
                Integer.parseInt(name.substring(i,i+1));
                System.out.println("Incorrect name value: <" + name + ">. Valid name must contain only letters");
                return false;
            }catch(NumberFormatException e){
            }
        }
        return true;
    }

    public static Boolean isValidWeight(float weight){

        if(weight<=0 || weight>MAX_WEIGHT){
            System.out.println("Incorrect weight value: <" + weight + ">. Valid range is <0>-<" + MAX_WEIGHT + ">");
            return false;
        }
        return true;
    }

    public static Boolean isValidSpending(int spending){

        if(spending<0){
            System.out.println("Incorrect spending value: <" + spending + ">. Valid spending must be equal or greater than <0>");
            return false;
        }
        return true;
    }

    // Métodos de instancia
    public String getID(){
        return id;
    }

    public void setID(String id){
        this.id = id.substring(0,3).toUpperCase() + id.substring(3,6);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public float getWeight(){
        return weight;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

    public int getSpending(){
        return spending;
    }

    public void setSpending(int spending){
        this.spending = spending;
    }

    public String toString(){
        return(id + ";" + name + ";" + String.format("%.2f",weight) + ";" + spending);
    }

    //Métodos abstractos

    public abstract int getFidelityPoints();

}
