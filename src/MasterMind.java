import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MasterMind {
    private Tablero tablero;
    private Jugada jugadaOculta;
    private int numFichas;

    public MasterMind(int numFichas) {
        this.numFichas=numFichas;
        this.tablero=new Tablero();
        this.jugadaOculta=new Jugada(numFichas);
    }

    public MasterMind(String nombreArchivo) {
        String jugadaOculta;
        Jugada jugada;
        Pistas pistas;
        String[] aux;
        String linea;

        BufferedReader entrada = null;
        this.tablero=new Tablero(); // Tablero vacio
        try {
            entrada = new BufferedReader(new FileReader(nombreArchivo + ".txt"));
            jugadaOculta=entrada.readLine();
            this.jugadaOculta=new Jugada(jugadaOculta);
            this.numFichas=jugadaOculta.length();
            while ((linea=entrada.readLine())!=null){
                aux=linea.split(" ");
                jugada=new Jugada(aux[0]);
                pistas=new Pistas(Integer.parseInt(aux[1]),Integer.parseInt(aux[2]));
                getTablero().insertar(jugada,pistas);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("NO EXISTE EL FICHERO");
        } catch (IOException ex){
            System.out.println("ERROR AL RECUPERAR LA PARTIDA");
        } finally {
            try {
                if (entrada != null) { entrada.close();}
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    private void guardarPartida(String nombreArchivo) { // Opcion 'G'
        PrintWriter file = null;
        try {
            file = new PrintWriter(nombreArchivo + ".txt");
            file.println(jugadaOculta.toString()); // Escribe en la primera linea la jugada oculta
            for (int i=0;i<getTablero().getNumJugadas();i++) {
                Jugada jugadaActual=getTablero().getJugadas()[i];
                Pistas pistaActual=getTablero().getResultados()[i];
                // Cada jugada con su pista la escribe en el fichero
                file.println(jugadaActual.toString() + " " + pistaActual.toString());
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL GUARDAR LA PARTIDA");
        } finally {
            if (file != null) {file.close();}
        }
    }

    public void jugar() {
        boolean acierto=false, guardar=false, intento_fallado=false;
        do {
            // Pregunta la jugada
            String cadena=Teclado.leerJugadaGuardar(numFichas, "Intruduce jugada o G (guardar la partida).\nR (Rojo), V (Verde), A (Amarillo), P (purpura): ");
            if (cadena.equals("G")){ // Se eligio la opcion de guardar la partida
                this.guardarPartida(Teclado.leerString("Nombre del archivo: "));
                guardar=true;
            } else if (cadena.equals(jugadaOculta.toString())) { // Acertaste
                System.out.println("Has ganado");
                acierto=true;
            } else if (tablero.completo()){ // Superaste los intentos
                System.out.println("Has superado los intentos permitidos, la combinación secreta es " + jugadaOculta.toString());
                intento_fallado = true;
            } else { // La partida continua
                Jugada jugada = new Jugada(cadena); // Le pasa la jugada
                Pistas pistas = jugada.comprobar(jugadaOculta); // Verifica la jugada oculta con la del usuario
                tablero.insertar(jugada,pistas); // Inserta la jugada en el tablero
                // Visualiza la jugada
                System.out.print("Jugada " + tablero.getNumJugadas() + "\t");
                jugada.visualizar();
                pistas.visualizar();
            }
        } while (!acierto && !guardar && !intento_fallado);
    }

    public static void main(String[] args) {
        MasterMind masterMind;
        if (Teclado.leerSiNo("¿Quieres recuperar una partida? (S/N): ") == 'S') {
            String nombreArchivo = Teclado.leerString("Nombre del archivo: ");
            masterMind = new MasterMind(nombreArchivo);
            masterMind.getTablero().visualizar();
        } else {
            int fichas = Teclado.leerEntero(4, 6, "Número de fichas de las jugadas (4 - 6): ");
            masterMind = new MasterMind(fichas);
        }
        masterMind.jugar();
    }
}