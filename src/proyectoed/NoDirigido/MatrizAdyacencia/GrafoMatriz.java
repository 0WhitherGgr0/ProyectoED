package proyectoed.NoDirigido.MatrizAdyacencia;


public class GrafoMatriz {
    private int numVerts;
    private int maxVerts;
    private Vertice[] verts;
    private int[][] matPeso;
    private static final int INFINITO = Integer.MAX_VALUE;
    private StringBuilder resultado;

    public GrafoMatriz(int maxVerts) {
        this.maxVerts = maxVerts;
        this.numVerts = 0;
        this.verts = new Vertice[maxVerts];
        this.matPeso = new int[maxVerts][maxVerts];
        resultado = new StringBuilder();

        // Inicializar la matriz de pesos con infinito
        for (int i = 0; i < maxVerts; i++) {
            for (int j = 0; j < maxVerts; j++) {
                if (i == j) {
                    matPeso[i][j] = 0;
                } else {
                    matPeso[i][j] = INFINITO;
                }
            }
        }
    }

    public void nuevoVertice(String nombre) {
        if (numVerts < maxVerts) {
            verts[numVerts] = new Vertice(nombre);
            numVerts++;
        } else {
            throw new IllegalArgumentException("Se ha alcanzado el número máximo de vértices.");
        }
    }

    public void nuevoArco(String inicio, String destino, int peso) {
        int vi = numVertice(inicio);
        int vd = numVertice(destino);

        if (vi == -1 || vd == -1) {
            throw new IllegalArgumentException("Uno o ambos nodos no existen.");
        }

        matPeso[vi][vd] = peso;
        matPeso[vd][vi] = peso; // Para grafo no dirigido
    }

    public void eliminarArco(String inicio, String destino) {
        int vi = numVertice(inicio);
        int vd = numVertice(destino);

        if (vi == -1 || vd == -1) {
            throw new IllegalArgumentException("Uno o ambos nodos no existen.");
        }

        matPeso[vi][vd] = INFINITO;
        matPeso[vd][vi] = INFINITO; // Para grafo no dirigido
    }

    public int numVertice(String nombre) {
        for (int i = 0; i < numVerts; i++) {
            if (verts[i].nomVertice().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public void eliminarVertice(String nombre) {
        int idx = numVertice(nombre);
        if (idx == -1) {
            throw new IllegalArgumentException("El vértice no existe.");
        }

        // Desplazar las filas de la matriz de adyacencia
        for (int i = idx; i < numVerts - 1; i++) {
            for (int j = 0; j < numVerts; j++) {
                matPeso[i][j] = matPeso[i + 1][j];
            }
        }
        // Desplazar las columnas de la matriz de adyacencia
        for (int j = idx; j < numVerts - 1; j++) {
            for (int i = 0; i < numVerts; i++) {
                matPeso[i][j] = matPeso[i][j + 1];
            }
        }

        // Restablecer la última fila y columna
        for (int i = 0; i < numVerts; i++) {
            matPeso[numVerts - 1][i] = INFINITO;
            matPeso[i][numVerts - 1] = INFINITO;
        }

        // Desplazar la lista de vértices
        for (int i = idx; i < numVerts - 1; i++) {
            verts[i] = verts[i + 1];
        }
        verts[numVerts - 1] = null;

        numVerts--;
    }

    public void imprimirConsolaGrafo() {
        System.out.println("Matriz de Adyacencia:");
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                if (matPeso[i][j] == INFINITO) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(matPeso[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Nombres de los Vértices:");
        for (int i = 0; i < numVerts; i++) {
            System.out.print(verts[i].nomVertice() + " ");
        }
        System.out.println();
    }

    // ******************************Segundo cambio**********************************

    public String imprimirStringGrafo() {
        resultado.setLength(0); // Clear previous results
        resultado.append("\nMatriz de Adyacencia:\n");
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                if (matPeso[i][j] == INFINITO) {
                    resultado.append("∞ ");
                } else {
                    resultado.append(matPeso[i][j]).append(" ");
                }
            }
            resultado.append("\n");
        }
        resultado.append("\nVértices:\n\n");
        for (int i = 0; i < numVerts; i++) {
            resultado.append(verts[i].nomVertice()).append(" ");
        }
        resultado.append("\n");
        return resultado.toString(); // Return the built string
    }

    public String getResultado() {
        return resultado.toString();
    }
    
    
    public int[][] getMatPeso() {
        return matPeso;
    }

    public int numeroDeVertices() {
        return numVerts;
    }

    public Vertice[] getVerts() {
        return verts;
    }

    public static int getInfinito() {
        return INFINITO;
    }

}

