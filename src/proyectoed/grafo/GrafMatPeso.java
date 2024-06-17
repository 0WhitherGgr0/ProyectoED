package proyectoed.grafo;

public class GrafMatPeso {
    public static final int INFINITO = Integer.MAX_VALUE;
    private int[][] matPeso;
    private int numVertices;

    public GrafMatPeso(int numVertices) {
        this.numVertices = numVertices;
        matPeso = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    matPeso[i][j] = 0;
                } else {
                    matPeso[i][j] = INFINITO;
                }
            }
        }
    }

    public int numeroDeVertices() {
        return numVertices;
    }

    public int[][] getMatPeso() {
        return matPeso;
    }

    public void setPeso(int origen, int destino, int peso) {
        matPeso[origen][destino] = peso;
        matPeso[destino][origen] = peso; // Si es un grafo no dirigido
    }
}
