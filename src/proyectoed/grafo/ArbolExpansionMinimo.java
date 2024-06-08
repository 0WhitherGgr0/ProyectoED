package proyectoed.grafo;

public class ArbolExpansionMinimo { //Aquí tambien retiré T
    private int[][] pesos;
    private int n;

    public ArbolExpansionMinimo(GrafMatPeso gp) {
        n = gp.numeroDeVertices();
        pesos = gp.getMatPeso();
    }

    public int[][] getPesos() {
    	return pesos;
    }
    
    public int arbolExpansionPrim() {
        int longMin, menor;
        int z;
        int[] coste = new int[n];
        int[] masCerca = new int[n];
        boolean[] W = new boolean[n];

        for (int i = 0; i < n; i++)
            W[i] = false;

        longMin = 0;
        W[0] = true;

        for (int i = 1; i < n; i++) {
            coste[i] = pesos[0][i];
            masCerca[i] = 0;
        }

        for (int i = 1; i < n; i++) {
            menor = coste[1];
            z = 1;

            for (int j = 2; j < n; j++)
                if (coste[j] < menor) {
                    menor = coste[j];
                    z = j;
                }

            longMin += menor;
            System.out.println("V" + masCerca[z] + " -> " + "V" + z);
            W[z] = true;
            coste[z] = GrafMatPeso.INFINITO;

            for (int j = 1; j < n; j++)
                if ((pesos[z][j] < coste[j]) && !W[j]) {
                    coste[j] = pesos[z][j];
                    masCerca[j] = z;
                }
        }

        return longMin;
    }
}
