package p7;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P7 {
    
    //Atributos estaticos

    //Metodos estaticos

    //Metodo processIO

    public static void processIO(PetDB pdb, Residence miresidencia, String fileName) throws FileNotFoundException{

        File IOfile = new File(fileName);
        Scanner lineReader;
        String line, id, date;

        if(!IOfile.exists()){
            System.out.println("El fichero " + fileName + " no existe");
            System.exit(-1);
        }
        lineReader = new Scanner(IOfile);

        while(lineReader.hasNext()){
            line = lineReader.nextLine().trim();
            if(!line.startsWith("#") && !line.isEmpty()){
                id = line.split(";")[1];
                date = line.split(";")[2];
                if(line.startsWith("I")){
                    miresidencia.petIn(pdb.getPetFromId(id), date);
                }if(line.startsWith("O")){
                    String inDate = miresidencia.petOut(id);
                    miresidencia.registerStayPayment(id, inDate, miresidencia.computeDaysCost(pdb.getPetFromId(id), inDate, date));
                    pdb.increasePetSpending(id, miresidencia.computeDaysCost(pdb.getPetFromId(id), inDate, date));
                }
            }
        }
        lineReader.close();
    }

    //Metodo saveResidenceMap para guardar el mapa en un fichero

    public static void saveResidenceMap(String fileName, Residence laResidencia) throws FileNotFoundException{

        File residenceMapFile = new File(fileName);
        PrintWriter lineWriter = new PrintWriter(residenceMapFile);

        lineWriter.print(laResidencia.toMap('-'));

        lineWriter.close();
    }

    //Metodo main

    public static void main(String[] args) throws FileNotFoundException, IOException{

        /* Argumentos del programa:

        file0: fichero con la estructura de la Residencia (Residence_Info.txt)
        
        file1: fichero con entradas y salidas de la Residencia (Residence_IO.txt)
        
        fiel2: fichero donde se guarda la Residencia (Residence_Out.txt)
        
        file3: fichero con todas las mascotas (Pets.txt)

        file4: fichero donde se guardan las mascotas (Pets_Out.txt)

        file5: fichero donde se guardan los ingresos (Stay_Payments.txt)

        file6: fichero del toMap (Residence_Map.txt)
        
        */

        PetDB pdb = new PetDB();

        pdb.readPetsFile(args[3]);

        Residence miResidencia = new Residence(args[0]);

        processIO(pdb, miResidencia, args[1]);

        miResidencia.saveResidenceToFile(args[2]);

        pdb.sortBySpendingAndId();

        pdb.savePetsToFile(args[4]);

        miResidencia.saveStayPayments(args[5]);

        saveResidenceMap(args[6], miResidencia);

        return;
    }
}
