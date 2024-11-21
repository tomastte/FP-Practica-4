public class Tablero {
    private final static int MAX_JUGADAS = 10;

    private Jugada[] jugadas;
    private Pistas[] pistas;
    private int numJugadas;

    public Tablero() {
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

    public void insertar(Jugada jugada, Pistas pistas) {
        this.jugadas[numJugadas]=jugada;
        this.pistas[numJugadas]=pistas;
        numJugadas++;
    }

    public boolean completo() {return numJugadas==MAX_JUGADAS;}

    public void visualizar() {
        for (int i=0;i<this.getNumJugadas();i++){
            System.out.println("Jugada " + (i+1) + "\t");
            this.getJugadas()[i].visualizar();
            this.getResultados()[i].visualizar();
            System.out.println();
        }
    }
}
