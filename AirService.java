package p7;

public class AirService {
    
    //Atributos de instancia

    private boolean activated;

    //Constructor

    public AirService(){
        activated = false;
    }

    //Metodos de instancia

    public void activate(){
        activated = true;
    }

    public void deactivate(){
        activated = false;
    }

    //Getters y Setters

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

}