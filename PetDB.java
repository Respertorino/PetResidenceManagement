package p7;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PetDB{
 
    //Atributos de instancia

    private ArrayList<Pet> pets = new ArrayList<Pet>();

    
    
    
    
    
    
    
    
    //Metodos

    //Metodos estáticos

    //Metodos de validación de mascotas

    private static boolean isValidParrot(String id, String name, float wei, int spen, short speak){
        if (Parrot.isValidID(id)){
            if(Parrot.isValidName(name)){
                if(Parrot.isValidWeight(wei)){
                    if(Parrot.isValidSpending(spen)){
                        if(Parrot.isValidSpeak(speak)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isValidDog(String id, String name, float wei, int spen, char dangerous){
        if (Dog.isValidID(id)){
            if(Dog.isValidName(name)){
                if(Dog.isValidWeight(wei)){
                    if(Dog.isValidSpending(spen)){
                        if(Dog.isValidDanger(dangerous)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isValidHuntDog(String id, String name, float wei, int spen, char dangerous,char prey){
        if (HuntDog.isValidID(id)){
            if(HuntDog.isValidName(name)){
                if(HuntDog.isValidWeight(wei)){
                    if(HuntDog.isValidSpending(spen)){
                        if(HuntDog.isValidDanger(dangerous)){
                            if(HuntDog.isValidPrey(prey)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }









    //Metodos de instancia

    //Metodo getPetFromId, recibe un string con un ID y devuelve la mascota con ese ID del ArrayList de pets

    public Pet getPetFromId(String id){ //Antes de invocar el metodo se debería comprobar si la ID es valida

        for(int i=0;i<pets.size(); i++){
            if(pets.get(i).getID().equals(id))return pets.get(i);
        }
        return null;

    }

    //Metodo computeTotalSpending devuelve la suma de todos los gastos de las mascotas de ArrayList de pets

    public int computeTotalSpending(){

        int totalSpending = 0;

        for(int i=0;i<pets.size();i++)totalSpending = totalSpending + pets.get(i).getSpending();

        return totalSpending;
    }

    //Metodo computeTotalFidelityPoints calcula el total de los puntos de fidelidad de todas las mascotas

    public int computeTotalFidelityPoints(){

        int totalFidelityPoints = 0;

        for(int i=0;i<pets.size();i++)totalFidelityPoints = totalFidelityPoints + pets.get(i).getFidelityPoints();

        return totalFidelityPoints;
    }

    //Metodo computeAverageDangerousDogs calcula la media de los perros peligros sobre al total de perros

    public float computeAverageDangerousDogs(){

        int totalDogs = 0, dangerousDogs = 0;

        for(int i=0;i<pets.size();i++){
            if(pets.get(i) instanceof Dog){
                totalDogs++;
                if(((Dog)pets.get(i)).getDangerous()=='Y') dangerousDogs++;
            }
        }

        return (float)totalDogs / (float)dangerousDogs;
    }

    //Metodo increasePetSpending añade un gasto a la mascota con el id pasado por parametro

    public void increasePetSpending(String id, int newPayment){

        Pet pet = getPetFromId(id);

        if(pet!=null)pet.setSpending(pet.getSpending() + newPayment);

        return;
    }



    
    
    
    
    
    
    //Metodo sortBySpendingAndId usado por el Comparator para ordernar el ArrayList de mascotas por gasto e ID

    public void sortBySpendingAndId(){
        
        PetComparatorBySpendingAndId pcSpendingandId = new PetComparatorBySpendingAndId();
        
        Collections.sort(pets, pcSpendingandId);
        
    }
    
    
    
    
    
    
    
    
    
    //Metodo de lectura del fichero pets

    public void readPetsFile (String filename) throws FileNotFoundException{

        File petsFile = new File(filename);
        Scanner lineReader;
        String currentLine;
        String[] petDataArray;

        if(!petsFile.exists()){
            System.out.println("El fichero " + filename + " no existe");
            System.exit(-1);
        }
        lineReader = new Scanner(petsFile);

        while(lineReader.hasNext()){
            currentLine = lineReader.nextLine().trim();
            if(!currentLine.startsWith("#") && !currentLine.isEmpty()){
                petDataArray = new String[currentLine.split(";").length];
                petDataArray = currentLine.split(";");
                if(petDataArray[0].length()==1){
                    switch(petDataArray[0].charAt(0)){
                        case 'P':
                        if (isValidParrot(petDataArray[1],petDataArray[2],Float.parseFloat(petDataArray[3].replace(',','.')),Integer.parseInt(petDataArray[4]),Short.parseShort(petDataArray[5]))){
                            pets.add(new Parrot(petDataArray[1],petDataArray[2],Float.parseFloat(petDataArray[3].replace(',','.')),Integer.parseInt(petDataArray[4]),Short.parseShort(petDataArray[5])));
                        }
                        break;
                        case 'D':
                        if(petDataArray[5].length()==1){    
                            if (isValidDog(petDataArray[1],petDataArray[2],Float.parseFloat(petDataArray[3].replace(',','.')),Integer.parseInt(petDataArray[4]),petDataArray[5].charAt(0))){
                                pets.add(new Dog(petDataArray[1],petDataArray[2],Float.parseFloat(petDataArray[3].replace(',','.')),Integer.parseInt(petDataArray[4]),petDataArray[5].charAt(0)));
                            }
                        }
                        break;
                        case 'H':
                        if(petDataArray[5].length()==1 && petDataArray[6].length()==1){
                            if (isValidHuntDog(petDataArray[1],petDataArray[2],Float.parseFloat(petDataArray[3].replace(',','.')),Integer.parseInt(petDataArray[4]),petDataArray[5].charAt(0),petDataArray[6].charAt(0))){
                                pets.add(new HuntDog(petDataArray[1],petDataArray[2],Float.parseFloat(petDataArray[3].replace(',','.')),Integer.parseInt(petDataArray[4]),petDataArray[5].charAt(0),petDataArray[6].charAt(0)));
                            }
                        }
                        break;
                        default:
                        break;
                    }
                }
            }
        }
        lineReader.close();
    }

    //Metodo savePetsToFile guarda el ArrayList de pets en un archivo cuyo nombre se pasa por parametro

    public void savePetsToFile(String filename) throws FileNotFoundException{

        File petsFile = new File(filename);
        PrintWriter lineWriter = new PrintWriter(petsFile);

        for(int i=0;i<pets.size();i++){

            lineWriter.println(pets.get(i).toString());   
        
        }

        lineWriter.close();
    }
    
}
