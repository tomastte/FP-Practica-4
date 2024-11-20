public class MasterMind {
    private Tablero tablero;
    private Jugada jugadaOculta;
    private int numFichas;

    public MasterMind(int numFichas) {
        this.numFichas=numFichas;
        this.tablero=new Tablero();
        this.jugadaOculta=new Jugada(numFichas);
    }

    public MasterMind(String nombreArchivo) {}

    public Tablero getTablero() {
        return tablero;
    }

    private void guardarPartida(String nombreArchivo) {}

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