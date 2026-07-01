package p7;
import java.util.Comparator;

public class PlaceComparatorByLaneNumAndLevel implements Comparator<Place>{

    /*Criterio de ordenación: Se empieza en el nivel más alto y se aumenta el num hasta su máximo.
      Luego se vuelve al primer num habiendo decrementado un nivel, una vez completado toda al linea
      se pasa a la siguiente y se repite el proceso
    */

    public int compare(Place place1, Place place2){

        PlaceLocator placeLocator1 = place1.getPlaceLocator();
        PlaceLocator placeLocator2 = place2.getPlaceLocator();

        if(placeLocator1.getLane()<placeLocator2.getLane()) return -1;
        if(placeLocator1.getLane()>placeLocator2.getLane()) return 1;
        if(placeLocator1.getLevel()>placeLocator2.getLevel()) return -1;
        if(placeLocator1.getLevel()<placeLocator2.getLevel()) return 1;
        if(placeLocator1.getNum()<placeLocator2.getNum()) return -1;
        if(placeLocator1.getNum()>placeLocator2.getNum()) return 1;

        //if(placeLocator1.getLane()<placeLocator2.getLane() || placeLocator1.getLevel()>placeLocator2.getLevel() || placeLocator1.getNum()<placeLocator2.getNum())return -1;
        //if(placeLocator1.getLane()>placeLocator2.getLane() || placeLocator1.getLevel()<placeLocator2.getLevel() || placeLocator1.getNum()>placeLocator2.getNum())return 1;

        return 0;
    }
    
}
