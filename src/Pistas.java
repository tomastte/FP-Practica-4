public class Pistas {
    private static final char PUNTO_BLANCO = '\u25CB';
    private static final char PUNTO_NEGRO = '\u25CF';

    private int aciertos;
    private int descolocados;

    public Pistas(int aciertos, int descolocados) {
        this.aciertos = aciertos;
        this.descolocados = descolocados;
    }

    // Visualiza las pistas correspondientes por jugada
    public void visualizar() {
        if (aciertos == 0 && descolocados == 0) {
            System.out.print("Fallo completo"); // Fallo
        } else {
            for (int i = 0; i < aciertos; i++) {
                System.out.print(PUNTO_NEGRO + " "); // Acierto de color y de posición
            }
            for (int i = 0; i < descolocados; i++) {
                System.out.print(PUNTO_BLANCO + " "); // Acierto de color pero no de posicion
            }
        }
    }

    public int getAciertos() {
        return aciertos;
    }

    @Override
    public String toString() {
        return this.getAciertos() + " " + descolocados;
    }
}
