package p7;

import java.util.Comparator;

public class PetComparatorBySpendingAndId implements Comparator<Pet> {
    
    public int compare(Pet pet1,Pet pet2){

        if(pet1.getSpending()<pet2.getSpending()) return -1;
        if(pet1.getSpending()>pet2.getSpending()) return 1;
        return pet1.getID().compareTo(pet2.getID());
    
    }

}
