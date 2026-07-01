package p7;
public class PlaceLocator implements Comparable<PlaceLocator> {

    //Atributos

    //Atributos estaticos

    //Atributos de instancia

    private short lane, num, level;

    //Constructores
    
    //Constructor por 3 argumentos short

    public PlaceLocator(short lane, short num, short level){
        this.lane = lane;
        this.num = num;
        this. level = level;
    }

    //Metodos

    //Metodos de instancia

    //Metodo compareTo para el TreeSet

    public int compareTo(PlaceLocator anotherPlaceLocator){

        if(lane<anotherPlaceLocator.lane) return -1;
        if(lane>anotherPlaceLocator.lane) return 1;
        if(num<anotherPlaceLocator.num) return -1;
        if(num>anotherPlaceLocator.num) return 1;
        if(level<anotherPlaceLocator.level) return -1;
        if(level>anotherPlaceLocator.level) return 1;

        //if(lane<anotherPlaceLocator.lane || level<anotherPlaceLocator.level || num<anotherPlaceLocator.num) return -1;
        //if(lane>anotherPlaceLocator.lane || level>anotherPlaceLocator.level || num>anotherPlaceLocator.num) return 1;
        
        return 0;

    }

    //Metodo equals, compara el placeLocator del objeto con uno dado por parametro

    public boolean equals(PlaceLocator anotherPlaceLocator){
        
        if(lane==anotherPlaceLocator.lane && num==anotherPlaceLocator.num && level==anotherPlaceLocator.level) return true;
        
        return false;
        
    }

    //Setters y Getters

    public short getLane(){
        return lane;
    }

    public void setLine(short lane){
        this.lane = lane;
    }

    public short getNum(){
        return num;
    }

    public void setLane(short num){
        this.num = num;
    }

    public short getLevel(){
        return level;
    }

    public void setLevel(short level){
        this.level = level;
    }

    //Metodo toString, devuelve un String con los valores de placeLocator

    public String toString(){
        return lane + ":" + num + ":" + level;
    }

}