public class Pistas {
    private static final char PUNTO_BLANCO = '\u25CB';
    private static final char PUNTO_NEGRO = '\u25CF';

    private int aciertos;
    private int descolocados;

    public Pistas(int aciertos, int descolocados) {
        this.aciertos = aciertos;
        this.descolocados = descolocados;
    }

    public void visualizar() {
        if (aciertos == 0 && descolocados == 0) {
            System.out.print("Fallo completo");
        } else {
            for (int i = 0; i < aciertos; i++) {
                System.out.print(PUNTO_NEGRO + " ");
            }
            for (int i = 0; i < descolocados; i++) {
                System.out.print(PUNTO_BLANCO + " ");
            }
        }
    }

    public int getAciertos() {
        return aciertos;
    }

    @Override
    public String toString() {
        return aciertos + " " + descolocados;
    }
}
