package p7;
public class Place implements Comparable<Place> {

    //Atributos

    //Atributos estaticos

    //Atributos de instancia

    private PlaceLocator placeLocator;
    private String idPet, entryDate;

    //Constructores

    //Constructor solo con placeLocator, el idPet se tiene que dar con el setter

    public Place(short lane, short num, short level){
        placeLocator = new PlaceLocator(lane, num, level);
        idPet = null;
        entryDate = null;
    }

    //Metodos

    //Metodos estaticos

    //Metodos de instancia

    //Metodo hashCode

    public int hashCode(){

        return 0;
        
    }

    //Metodo compareTo para el TreeMap

    public int compareTo(Place place){

        return placeLocator.compareTo(place.placeLocator);

    }

    //Metodo equals, compara el placeLocator del objeto con uno dado por parametro

    public boolean equals(Place place){

        return placeLocator.equals(place.placeLocator);
        
    }

    //Setters y Getters

    public PlaceLocator getPlaceLocator(){
        return placeLocator;
    }

    public void setPlaceLocator(PlaceLocator placeLocator){
        this.placeLocator = placeLocator;
    }

    public String getIdPet(){
        return idPet;
    }

    public void setIdPet(String idPet){
        this.idPet = idPet;
    }

    public String getEntryDate(){
        return entryDate;
    }

    public void setEntryDate(String entryDate){
        this.entryDate = entryDate;
    }

    //Metodo toString, devuelve un String con los valores del placeLocator, y el idPet si no vale null

    public String toString(){

        if(idPet==null)return placeLocator.toString();
        else return placeLocator.toString() + ";" + idPet + ";" + entryDate;
    }
}
