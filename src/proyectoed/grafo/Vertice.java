package proyectoed.grafo;

public class Vertice<T> { //En esta clase si aparece T como tipo de dato

    private T nombre;
    private int numVertice;

    public Vertice(T nombre) {
        this.nombre = nombre;
        this.numVertice = -1;
    }

    public T nomVertice() {
        return nombre;
    }

    public boolean equals(Vertice<T> n) {
        return nombre.equals(n.nombre);
    }

    public void asigVert(int n) {
        numVertice = n;
    }

    public String toString() {
        return nombre + " (" + numVertice + ")";
    }
}