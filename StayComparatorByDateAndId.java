package p7;
import java.util.Comparator;

public class StayComparatorByDateAndId implements Comparator<Stay>{

    public int compare(Stay stay1, Stay stay2){
        
        if(Residence.stayDays(stay1.getEntryDate(), stay2.getEntryDate())>0)return -1;
        if(Residence.stayDays(stay1.getEntryDate(), stay2.getEntryDate())<0)return 1;
        return stay1.getId().compareTo(stay2.getId());
    }
    
}
