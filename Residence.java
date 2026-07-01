package p7;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Residence{

    //Atributos

    //Atributos estaticos

    private final static short SIZE_LEVELS = 2; //Número de pisos que tienen todas las residencias
    private final static int PARROT_COST_PER_DAY = 50;
    private final static int DOG_COST_PER_DAY = 60;
    private final static int HUNTDOG_COST_PER_DAY = 70;

    //Atributos de instancia

    private short sizeLanes, sizeNums, minHuntDogLane;
    private HashSet<Place> busyPlaces;
    private TreeSet<Place> freePlaces;
    private TreeMap<Stay, Integer> stayPayments;

    /*=================================minHuntDogLane NOTAS================================== 
    -El pasillo 0 está reservado para los objetos de la clase Parrot
    -Del pasillo 1 al pasillo minHuntDogLane-1 está reservado para los objetos de las clases
     Parrot y Dog
    -Del pasillo minHuntDogLane al último pasillo, se pueden colocar objetos de cualquier
    clase; Parrot, Dog y HuntDog

    Es decir: minHuntDogLane debe:
        > 0
        <= sizeLanes // Puede ser que si se sale del número de lineas, significa que no hay
                        plazas reservadas para perros cazadores, pero no se especifica
    Y para cada pasillo solo se puede:
        pasillo 0 <-- Parrot
        pasillo 1 <-> minHuntDogLane-1 <-- Parrot || Dog
        pasillo minHuntDogLane <-> sizeLanes <-- Parrot || Dog || HuntDOg
    =========================================================================================*/

    //Constructores

    //Constructor por parametro File

    public Residence(String fileName) throws FileNotFoundException{
        
        File residenceFile = new File(fileName);
        Scanner lineReader = new Scanner(residenceFile);
        String line, placeLocatorString;
        short lanePet, numPet, levelPet;

        //Primer bucle, lee el fichero e inicializa el array places con sus respectivos placeLocators
        while(lineReader.hasNext()){
            line = lineReader.nextLine().trim();
            if(!line.startsWith("#") && !line.isEmpty()){
                sizeLanes=Short.parseShort(line.split(";")[0]);
                sizeNums=Short.parseShort(line.split(";")[1]);
                minHuntDogLane=Short.parseShort(line.split(";")[2]);

                freePlaces = new TreeSet<Place>();
                busyPlaces = new HashSet<Place>();
                StayComparatorByDateAndId sCByDI = new StayComparatorByDateAndId();
                stayPayments = new TreeMap<Stay, Integer>(sCByDI);

                for (short level=0;level<SIZE_LEVELS;level++){
                    for(short num=0;num<sizeNums;num++){
                        for(short lane=0;lane<sizeLanes;lane++){
                            if(lane<minHuntDogLane) freePlaces.add(new PlaceAir(lane,num,level));
                            else freePlaces.add(new Place(lane, num, level));
                        }
                    }
                }
                break;
            }
        }

        //Segundo bucle, en caso de haber mascotas en el archivo, las coloca en su respectiva celda
        while(lineReader.hasNext()){
            line = lineReader.nextLine().trim();
            if(!line.startsWith("#") && !line.isEmpty()){
                placeLocatorString = line.split(";")[0];
                lanePet = Short.parseShort(placeLocatorString.split(":")[0]);
                numPet = Short.parseShort(placeLocatorString.split(":")[1]);
                levelPet = Short.parseShort(placeLocatorString.split(":")[2]);

                Place placeRef = new Place(lanePet, numPet, levelPet);
                placeRef = freePlaces.ceiling(placeRef);
                placeRef.setIdPet(line.split(";")[1]);
                placeRef.setEntryDate(line.split(";")[2]);
                busyPlaces.add(placeRef);
                freePlaces.remove(placeRef);
            }
        }

        lineReader.close();
    }









    //Metodos

    //Metodos estáticos

    //Antes de pasarle el minHuntDogLane se comprueba la validez con este metodo

    public static Boolean isValidMinHuntDogLane(short minHuntDogLane){
        if(minHuntDogLane>0){
            return true;
        }
        return false;
    }

    //Metodo importado stayDays que calcula los días que hay entre dos fechas dadas

    public static int stayDays (String inDate, String outDate){
        long days;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");

        LocalDate parsedDateIn = LocalDate.parse(inDate, formatter);
        LocalDate parsedDateOut = LocalDate.parse(outDate, formatter);

        days = ChronoUnit.DAYS.between(parsedDateIn, parsedDateOut);

        return (int)days;        
    }








    
    //Metodos de instancia

    //Metodo saveResidenceToFile permite almacenar todos los datos de una residencia en un fichero

    public void saveResidenceToFile (String fileName) throws FileNotFoundException{

        File residenceFile = new File(fileName);
        PrintWriter writeResidenceFile = new PrintWriter(residenceFile);
        TreeSet<Place> busyPlacesOrganized = new TreeSet<Place>();
        busyPlacesOrganized.addAll(busyPlaces);

        writeResidenceFile.println(sizeLanes + ";" + sizeNums + ";" + minHuntDogLane);

        
	    while(!busyPlacesOrganized.isEmpty()){
            writeResidenceFile.println(busyPlacesOrganized.pollFirst().toString());
        }

        writeResidenceFile.close();
    }









    //Metodo saveStayPayments que guarda los valores del TreeMap stayPayments en el orden dado en un fichero

    public boolean saveStayPayments (String fileName) throws FileNotFoundException, IOException{

        try{
            int totalSpending = 0;
            File stayPaymentsFile = new File(fileName);
            PrintWriter lineWriter;
            StayComparatorById sCByI = new StayComparatorById();
            StayComparatorByDateAndId sCByDI = new StayComparatorByDateAndId();
            TreeSet<Stay> staySetById = new TreeSet<Stay>(sCByI);
            HashSet<Integer> staySetValue = new HashSet<Integer>();
            TreeSet<Stay> staySet = new TreeSet<Stay>(sCByDI);

            if(stayPaymentsFile.exists()){
                lineWriter = new PrintWriter(new FileWriter(stayPaymentsFile, true));
                lineWriter.println();
            }else lineWriter = new PrintWriter(stayPaymentsFile);

            lineWriter.print("ids:");
            staySetById.addAll(stayPayments.keySet());

            while(!staySetById.isEmpty()){
                lineWriter.print(staySetById.pollFirst().getId());
                if(!staySetById.isEmpty()) lineWriter.print(";");
            }

            lineWriter.println();

            staySetValue.addAll(stayPayments.values());

            for(Integer value: staySetValue){
                totalSpending = totalSpending + value;
            }

            lineWriter.println("total:" + totalSpending);

            staySet.addAll(stayPayments.keySet());

            while(!staySet.isEmpty()){

                Stay stayRef = staySet.pollFirst();

                lineWriter.println(stayRef.getEntryDate() + ";" + stayRef.getId() + ";" + stayPayments.get(stayRef));
            }
        
            lineWriter.close();
            return true;
        }catch(IOException e){
            return false;
        }
    }









    //Metodos petIn añande una mascota a la residencia

    public void petIn(Pet pet, String date){

        short petMinLane;

        if(pet instanceof Parrot) petMinLane = 0;
        else if(pet instanceof Dog){
            petMinLane = 1;
            if (pet instanceof HuntDog) petMinLane = minHuntDogLane;
        }else return;
        
        Place placeRef = new Place(petMinLane, (short)0, (short)0);
        placeRef = freePlaces.ceiling(placeRef);
        placeRef.setIdPet(pet.getID());
        placeRef.setEntryDate(date);

        if(placeRef instanceof PlaceAir) ((PlaceAir)placeRef).activateAirService();
        
        busyPlaces.add(placeRef);
        freePlaces.remove(placeRef);
        return;

    }

    //Metodos petOut saca una mascota de la residencia

    public String petOut(String idPet){

        String date;

        for(Place placeRef: busyPlaces){

            if(placeRef.getIdPet().equals(idPet)){
            placeRef.setIdPet(null);
            date = placeRef.getEntryDate();
            placeRef.setEntryDate(null);
            if(placeRef instanceof PlaceAir) ((PlaceAir)placeRef).deactivateAirService();
            freePlaces.add(placeRef);
            busyPlaces.remove(placeRef);
            return date;
            }
        }
        return null;
    }









    //Metodo toMap crea un mapa de la residencia

    /*Formato toMap (el caracter * representa los espacios, el caracter c representa el separador)

    ccccccccccccccccccccccccccccccccccccccccccccccccccccc
    *|*P**|*0:0:1*AAA111*|*0:1:1********|*0:2:1********|*
    *|*P**|*0:0:0********|*0:1:0********|*0:2:0********|*
    ccccccccccccccccccccccccccccccccccccccccccccccccccccc
    *|*PD*|*1:0:1********|*1:1:1*BBB222*|*1:2:1********|*
    *|*PD*|*1:0:0********|*1:1:0********|*1:2:0********|*
    ccccccccccccccccccccccccccccccccccccccccccccccccccccc
    *|*PDH|*2:0:1********|*2:1:1********|*2:2:1********|*
    *|*PDH|*2:0:0********|*2:1:0********|*2:2:0*CCC333*|*
    ccccccccccccccccccccccccccccccccccccccccccccccccccccc

    La cantidad de separadores es igual 9 + 15 * sizeNums
    
    */

    public String toMap(){

        return toMap('-');

    }

    //toMap pero con un caracter por parametro para usar como separador

    public String toMap(char c){

        String mapFinal = "";
        int sepatorCount = 9 + 15 * sizeNums;
        PlaceComparatorByLaneNumAndLevel pCByLNL = new PlaceComparatorByLaneNumAndLevel();
        TreeSet<Place> totalSet = new TreeSet<Place>(pCByLNL);
        totalSet.addAll(freePlaces);
        totalSet.addAll(busyPlaces);

        for(int i=0;i<sepatorCount;i++)mapFinal = mapFinal + c;
        mapFinal = mapFinal + "\n";
        
        for(short lane=0;lane<sizeLanes;lane++){
            for(short level=SIZE_LEVELS-1;level>=0;level--){
                mapFinal = mapFinal + " | ";
                if(lane==0)mapFinal = mapFinal + "P  ";
                if(lane<minHuntDogLane && lane>0)mapFinal = mapFinal + "PD ";
                if(lane>=minHuntDogLane)mapFinal = mapFinal + "PDH";
                for(short num=0;num<sizeNums;num++){
                    Place placeRef = new Place(lane, num, level);
                    placeRef = totalSet.ceiling(placeRef);
                    String[] place = new String[placeRef.toString().split(";").length];
                    place = placeRef.toString().split(";");
                    if(place.length==1)mapFinal = mapFinal +  " | " + place[0] + "       ";
                    if(place.length>=2)mapFinal = mapFinal +  " | " + place[0] + " " + place[1];
                }
                mapFinal = mapFinal + " | \n";
            }
            for(int i=0;i<sepatorCount;i++)mapFinal = mapFinal + c;
            mapFinal = mapFinal + "\n";
            
        }
        return mapFinal;
    }









    //Metodo computeDaysCost que calcula el coste de cada mascota segun la fecha de entrada

    public int computeDaysCost(Pet pet, String inDate, String outDate){

        int daysBetween,costPerDay = -1;

        if(pet instanceof Parrot) costPerDay = PARROT_COST_PER_DAY;
        else if(pet instanceof HuntDog) costPerDay = HUNTDOG_COST_PER_DAY;
        else if (pet instanceof Dog) costPerDay = DOG_COST_PER_DAY;

        daysBetween = stayDays(inDate, outDate);

        return daysBetween*costPerDay;
    }

    //Metodo registerStayPayment guarda el pago de una mascota en el HashMap de stayPayments

    public void registerStayPayment (String id, String inDate, int income){

        Stay stayRef = new Stay();

        stayRef.setId(id);
        stayRef.setEntryDate(inDate);

        stayPayments.put(stayRef, Integer.valueOf(income));
    }







    

    //Setters y Getters

    public void setMinHuntDogLane(short minHuntDogLane){
        this.minHuntDogLane = minHuntDogLane;
    }

    public short getMinHuntDogLane(){
        return minHuntDogLane;
    }

    public void setSizeLanes(short sizeLanes) {
        this.sizeLanes = sizeLanes;
    }

    public short getSizeLanes() {
        return sizeLanes;
    }

    public void setSizeNums(short sizeNums) {
        this.sizeNums = sizeNums;
    }

    public short getSizeNums() {
        return sizeNums;
    }

}
