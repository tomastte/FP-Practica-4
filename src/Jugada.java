enum Color {
    ROJO, VERDE, AMARILLO, PURPURA
}

public class Jugada {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final char CUADRADO = '\u25A0';

    private Color[] fichas;

    public Jugada(String cadena) {
        fichas=new Color[cadena.length()];
        for (int i=0;i<cadena.length();i++){
            switch (cadena.charAt(i)){
                case 'R':
                    fichas[i]=Color.ROJO;
                    break;
                case 'V':
                    fichas[i]=Color.VERDE;
                    break;
                case 'A':
                    fichas[i]=Color.AMARILLO;
                    break;
                case 'P':
                    fichas[i]=Color.PURPURA;
                    break;
            }
        }
    }

    public Jugada(int numFichas) {
        fichas = new Color[numFichas];
        for (int i=0; i<fichas.length; i++){
            int random=(int) (Math.random()*3);
            fichas[i]=Color.values()[random];
        }
    }

    // Comprobar si la jugada corresponde (this) a la jugada oculta
    // Ofrece las pistas
    public Pistas comprobar(Jugada oculta) {
        Pistas pista;
        boolean[] utilizados=new boolean[fichas.length];
        boolean[] aciertados =new boolean[fichas.length];
        String jugadaOculta = oculta.toString();
        String jugada = this.toString();
        int aciertos=0;
        int descolocados=0;

        for (int i=0;i<jugada.length();i++) {
            if (jugada.charAt(i)==jugadaOculta.charAt(i)) {
                descolocados++;
                utilizados[i]=true;
            }
        }
        for (int j=0;j<jugada.length();j++) {
            if (jugada.charAt(j)==jugadaOculta.charAt(j)) {
                aciertos++;
                aciertados[j]=true;
            }
        }
        pista = new Pistas(aciertos, descolocados);
        return pista;
    }

    public void visualizar() {
        for (Color ficha : fichas) {
            switch (ficha) {
                case ROJO:
                    System.out.print(ANSI_RED + CUADRADO + " ");
                    break;
                case VERDE:
                    System.out.print(ANSI_GREEN + CUADRADO + " ");
                    break;
                case AMARILLO:
                    System.out.print(ANSI_YELLOW + CUADRADO + " ");
                    break;
                case PURPURA:
                    System.out.print(ANSI_PURPLE + CUADRADO + " ");
                    break;
            }
        }
        System.out.print(ANSI_BLACK);
    }

    public String toString() {
        String resultado = "";
        for (Color ficha : fichas) {
            switch (ficha) {
                case ROJO:
                    resultado += "R";
                    break;
                case VERDE:
                    resultado += "V";
                    break;
                case AMARILLO:
                    resultado += "A";
                    break;
                case PURPURA:
                    resultado += "P";
                    break;
            }
        }
        return resultado;
    }
}