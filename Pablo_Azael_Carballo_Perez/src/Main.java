import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Aquí pondré los tamaños de los aleatorios
    public static int TamPoliza = 4;
    public static int TamCliente = 25;
    public static int TamMatricula = 8;
    public static int TamCuota = 8;

    public static void main(String[] args) {
        WriteObj();
        LeerSegurosObj();
         CambiarCuota();
         LeerArchivoAsci();

    }

    //Primero Hemos creado la clase Seguros con sus datos, ahora crearemos un fichero a partir de ese objeto
    private static void WriteObj() {

        ArrayList<Seguros> arrayObj = new ArrayList<>();
        Seguros segurosEj1 = new Seguros(2020, "Luisa Ramírez", "2889 BX", 302.25);
        Seguros segurosEj2 = new Seguros(3015, "Iker Casillas", "6988 CRM", 520.50);
        Seguros segurosEj3 = new Seguros(1918, "Pablo Azael", "512 EPR", 510.50);
        Seguros segurosEj4 = new Seguros(5123, "Pedro Benito", "632 ADE", 315.00);
        arrayObj.add(segurosEj1);
        arrayObj.add(segurosEj2);
        arrayObj.add(segurosEj3);
        arrayObj.add(segurosEj4);
        File fl = new File("./src/Seguros.obj");
        try {
            FileOutputStream fo = new FileOutputStream(fl);
            ObjectOutputStream oj = new ObjectOutputStream(fo);
            oj.writeObject(arrayObj);
            oj.flush();
            oj.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Aquí leeremos el objeto guardaremos las variables y crearemos un fichero con acceso Aleatorio
    private static void LeerSegurosObj() {
        File fl = new File("./src/Seguros.obj");
        // Esto es para recorrer el for y completar el array
        int numV = 0;
        ArrayList<Seguros> seguros = new ArrayList<>();
        try {
            //  Aquí leemos el archivo y lo ponemos en un array
            FileInputStream fi = new FileInputStream(fl);
            ObjectInputStream obi = new ObjectInputStream(fi);
            seguros = (ArrayList<Seguros>) obi.readObject();
            int Opoliza[] = new int[seguros.size()];
            String Ocliente[] = new String[seguros.size()];
            String Omatricula[] = new String[seguros.size()];
            Double Ocuota[] = new Double[seguros.size()];
            // Aquí leemos el array y ponemos los datos en variables para ahora crear los accesos aleatorios
            for (Seguros e : seguros) {
                System.out.println(e);
                Opoliza[numV] = e.getPoliza();
                Ocliente[numV] = e.getCliente();
                Omatricula[numV] = e.getMatricula();
                Ocuota[numV] = e.getCuota();
                numV++;
            }
            // Aquí pondré los tamaños de los aleatorios

            File f = new File("./src/SegurosDirecto.dat");
            try {
                RandomAccessFile alea = new RandomAccessFile(f, "rw");
                // A partir de aquí empezaremos a Escribir en el fichero Random
                for (int p = 0; p < Opoliza.length; p++) {
                    alea.writeInt(Opoliza[p]);

                    StringBuffer bufferCliente = new StringBuffer(Ocliente[p]);
                    bufferCliente.setLength((TamCliente));
                    for (int j = Ocliente[p].length(); j < TamCliente; j++) {
                        bufferCliente.setCharAt(j, ' ');
                    }
                    alea.writeChars(bufferCliente.toString());


                    StringBuffer bufferMatricula = new StringBuffer(Omatricula[p]);
                    bufferMatricula.setLength((TamMatricula));
                    for (int j = Ocliente[p].length(); j < TamMatricula; j++) {
                        bufferMatricula.setCharAt(j, ' ');
                    }
                    alea.writeChars(bufferMatricula.toString());


                    alea.writeDouble(Ocuota[p]);

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    // Aquí leeremos el archivo
    private static void LeerArchivoAsci() {
        File fr = new File("./src/SegurosDirecto.dat");
        int posTotal = TamPoliza + (TamCliente * 2) + (TamMatricula * 2) + TamCuota;
        ArrayList<Seguros> ArraySeguros = new ArrayList<>();
        try {
            RandomAccessFile aleat = new RandomAccessFile(fr, "rw");
            int cPoliza, pos = 0;
            char cCliente[] = new char[TamCliente], auxP;
            char cMatricula[] = new char[TamMatricula], auxN;
            while (true) {
                aleat.seek(pos);
                cPoliza = aleat.readInt();
                for (int i = 0; i < cCliente.length; i++) {
                    auxP = aleat.readChar();
                    cCliente[i] = auxP;
                }
                String vCliente = new String(cCliente).trim();

                for (int i = 0; i < cMatricula.length; i++) {
                    auxN = aleat.readChar();
                    cMatricula[i] = auxN;
                }
                String vMatricula = new String(cMatricula).trim();
                Double vCuota = aleat.readDouble();

                Seguros seguros = new Seguros(cPoliza, vCliente, vMatricula, vCuota);
                ArraySeguros.add(seguros);
                pos = pos + posTotal;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

        }
        for (Seguros e : ArraySeguros) {
            System.out.println(e);
        }
    }

    //Este método servirá para modificar el archivo y sobreescribirlo
    private static void CambiarCuota() {
        int posTotal = TamPoliza + (TamCliente * 2) + (TamMatricula * 2) + TamCuota;
        File fr = new File("./src/SegurosDirecto.dat");
        System.out.println("Por favor introduzca su matrícula para decrementar la Póliza");
        Scanner in = new Scanner(System.in);
        String matriculaConsola = in.nextLine();
        Double nuevoDouble;
        Double viejoDouble;
        Double comprobacion;
        char cMatricula[] = new char[TamMatricula], aux;
        try {
            RandomAccessFile aleat = new RandomAccessFile(fr, "rw");
            int pos = (TamPoliza + (TamCliente * 2));
            while (true) {
                aleat.seek(pos);
                for (int i = 0; i < TamMatricula; i++) {
                    aux = aleat.readChar();
                    cMatricula[i] = aux;
                }
                String matricula = new String(cMatricula);
                if ((matricula.trim().equalsIgnoreCase(matriculaConsola))) {
                    viejoDouble=aleat.readDouble();
                    nuevoDouble=(viejoDouble*0.85);
                    aleat.seek(pos + (TamMatricula * 2));
                    aleat.writeDouble(nuevoDouble);
                    // para comprobar que se ha hecho, lo leeremos de nuevo y lo guardaremos en una variable completamente nueva
                    aleat.seek(pos + (TamMatricula * 2));
                    comprobacion=aleat.readDouble();
                    System.out.println("La nueva poliza será de " + comprobacion);
                }
                pos = pos + posTotal;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {

        }
    }
}





