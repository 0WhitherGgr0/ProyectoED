package proyectoed.grafo;

public class GrafoMatriz<T> { //Acá si se usa T
	//Agregué varios getters
    private int numVerts;
    private static final int MAX_VERTS = 20;
    private Vertice<T>[] verts;
    private int[][] matAd;

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
    
    public int getNumVerts() {
    	return numVerts;
    }
    
    public Vertice<T>[] getVerts() {
    	return verts;
    }
    
    public int[][] getMatAd(){
    	return matAd;
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

    public void nuevoArco(T va, T vb) throws Exception { //Lo cambie a T ya que ese el el tipo de dato que usan los vertices
    	int na = numVertice(va);
    	int nb = numVertice(vb);
    	nuevoArco(na, nb);
    }
    
    public void nuevoArco(int va, int vb) throws Exception {
    	if (va < 0 || vb < 0 || va >= numVerts || vb >= numVerts)
            throw new Exception("Vértice no existe");
        matAd[va][vb] = 1;
    }

    public boolean adyacente(T va, T vb) throws Exception {
    	int na = numVertice(va);
    	int nb = numVertice(vb);
    	return adyacente(na, nb);
    }
    
    public boolean adyacente(int va, int vb) throws Exception {
        if (va < 0 || vb < 0 || va >= numVerts || vb >= numVerts)
            throw new Exception("Vértice no existe");
        return matAd[va][vb] == 1;
    }
}