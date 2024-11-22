public class Tablero {
    private final static int MAX_JUGADAS = 10;

    private Jugada[] jugadas;
    private Pistas[] pistas;
    private int numJugadas;

    public Tablero() {
        // Arrays de tamaño MAX_JUGADAS
        jugadas = new Jugada[MAX_JUGADAS];
        pistas = new Pistas[MAX_JUGADAS];
    }

    public int getNumJugadas() {
        return numJugadas;
    }

    public Jugada[] getJugadas() {
        return jugadas;
    }

    public Pistas[] getResultados() {
        return pistas;
    }

    // Asigna una jugada y una pista al teblero
    public void insertar(Jugada jugada, Pistas pistas) {
        this.jugadas[numJugadas]=jugada;
        this.pistas[numJugadas]=pistas;
        numJugadas++;
    }

    // Comprueba que no se han superado los intentos maximos permitidos
    public boolean completo() {return numJugadas==MAX_JUGADAS;}

    // Visualiza el numero de jugada con la pista correspondiente
    public void visualizar() {
        for (int i=0;i<this.getNumJugadas();i++){
            System.out.println("Jugada " + (i+1) + "\t");
            this.getJugadas()[i].visualizar();
            this.getResultados()[i].visualizar();
            System.out.println();
        }
    }
}
