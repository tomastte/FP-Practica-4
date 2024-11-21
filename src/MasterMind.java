import java.io.*;

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
        int numFichas;
        BufferedReader entrada = null;
        this.tablero=new Tablero();
        try {
            entrada = new BufferedReader(new FileReader(nombreArchivo + ".txt"));
            jugadaOculta=entrada.readLine();
            this.jugadaOculta=new Jugada(jugadaOculta);
            numFichas=jugadaOculta.length();
            this.numFichas=numFichas;
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

    private void guardarPartida(String nombreArchivo) {
        PrintWriter file=null;
        try {
            file = new PrintWriter(nombreArchivo + ".txt");
            file.print(jugadaOculta.toString());
            file.println(this.getTablero());
        } catch (IOException ex) {
            System.out.println("ERROR AL GUARDAR LA PARTIDA");
        } finally {
            if (file!=null) {file.close();}
        }
    }

    public void jugar() {
        boolean acierto=false, guardar=false, intento_fallado=false;
        // Eliminar 2 lineas
        String cadenaOculta=jugadaOculta.toString();
        System.out.println(cadenaOculta);
        do {
            String cadena=Teclado.leerJugadaGuardar(numFichas, "Intruduce jugada o G (guardar la partida).\nR (Rojo), V (Verde), A (Amarillo), P (purpura): ");
            if (cadena.equals("G")){
                this.guardarPartida(Teclado.leerString("Nombre del archivo: "));
                guardar=true;
            } else if (cadena.equals(jugadaOculta.toString())) {
                System.out.println("Has ganado");
                acierto=true;
            } else if (tablero.completo()){
                System.out.println("Has superado los intentos permitidos, la combinación secreta es " + jugadaOculta.toString());
                intento_fallado = true;
            } else {
                Jugada jugada = new Jugada(cadena);
                Pistas pistas = jugada.comprobar(jugadaOculta);

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