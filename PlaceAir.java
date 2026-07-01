package p7;

public class PlaceAir extends Place {
    
    //Atributos de instancia

    private AirService airService;

    //Constructor

    public PlaceAir(short lane, short num, short level){
        super(lane, num, level);
        airService = new AirService();
    }
    
    //Metodos de instancia

    //Esto se permite o es ilegal??? Preguntar en clase

    public void activateAirService(){
        airService.activate();
    }
    
    public void deactivateAirService(){
        airService.deactivate();
    }

    //Setter y getter

    public void setAirService(AirService airService){
        this.airService = airService;
    }

    public AirService getAirService(){
        return airService;
    }
}
