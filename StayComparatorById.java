package p7;
import java.util.Comparator;

public class StayComparatorById implements Comparator<Stay>{

    public int compare(Stay stay1, Stay stay2){
        
        return stay1.getId().compareTo(stay2.getId());
        
    }
    
}