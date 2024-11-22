enum Color {
    ROJO, VERDE, AMARILLO, PURPURA
}

public class Jugada {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_BLACK = "\u001B[30m"; // Tiene un error y no cierra el color
    private static final String COLOR_DEFECTO = "\u001B[0m";
    private static final char CUADRADO = '\u25A0';

    private Color[] fichas;

    // Constructor de la jugada del usuario
    public Jugada(String cadena) {
        // A cada caracter del string le asignamos un color
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

    // Constructor de la jugada oculta
    public Jugada(int numFichas) {
        fichas = new Color[numFichas];
        for (int i=0; i<fichas.length; i++){
            int random=(int) (Math.random()*3);
            fichas[i]=Color.values()[random];
        }
    }

    // Comprueba la jugada con la jugada oculta y da las pistas correspondientes
    public Pistas comprobar(Jugada oculta) {
        Pistas pista;
        boolean[] utilizados=new boolean[fichas.length];
        boolean[] acertados=new boolean[fichas.length];
        String jugadaOculta = oculta.toString();
        String jugada = this.toString();
        boolean flag;
        int aciertos=0;
        int descolocados=0;

        for (int i=0;i<jugada.length();i++) {
            if (jugada.charAt(i)==jugadaOculta.charAt(i)) {
                aciertos++;
                acertados[i]=true;
                utilizados[i]=true;
            }
        }

        for (int i=0;i<jugada.length();i++) {
            if (!acertados[i]){
                flag=true;
                int j=0;
                while (flag && j<jugada.length()){
                    if (jugada.charAt(i)==jugadaOculta.charAt(j) && !utilizados[j]){
                        utilizados[j]=true;
                        descolocados++;
                        flag=false;
                    }
                    j++;
                }
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
        System.out.print(COLOR_DEFECTO); // Cierra el color
    }

    // Convert cada elemento del array fichas to String
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