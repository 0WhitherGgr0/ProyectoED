package proyectoed.grafo;

public class GrafoMatriz<T> {

    private int numVerts;
    private static final int MAX_VERTS = 20;
    private Vertice<T>[] verts;
    private int[][] matAd;

    @SuppressWarnings("unchecked")
    public GrafoMatriz() {
        this(MAX_VERTS);
    }

    @SuppressWarnings("unchecked")
    public GrafoMatriz(int maxVerts) {
        matAd = new int[maxVerts][maxVerts];
        verts = (Vertice<T>[]) new Vertice[maxVerts];
        for (int i = 0; i < maxVerts; i++) {
            for (int j = 0; j < maxVerts; j++) {
                matAd[i][j] = 0;
            }
        }
        numVerts = 0;
    }

    public void nuevoVertice(T nom) {
        boolean esta = numVertice(nom) >= 0;
        if (!esta) {
            Vertice<T> v = new Vertice<>(nom);
            v.asigVert(numVerts);
            verts[numVerts++] = v;
        }
    }

    public int numVertice(T nom) {
        Vertice<T> v = new Vertice<>(nom);
        boolean encontrado = false;
        int i = 0;
        for (; (i < numVerts) && !encontrado;) {
            encontrado = verts[i].equals(v);
            if (!encontrado)
                i++;
        }
        return (i < numVerts) ? i : -1;
    }

    public void nuevoArco(int va, int vb) throws Exception {
        if (va < 0 || vb < 0 || va >= numVerts || vb >= numVerts)
            throw new Exception("Vértice no existe");
        matAd[va][vb] = 1;
    }

    public boolean adyacente(int va, int vb) throws Exception {
        if (va < 0 || vb < 0 || va >= numVerts || vb >= numVerts)
            throw new Exception("Vértice no existe");
        return matAd[va][vb] == 1;
    }
}