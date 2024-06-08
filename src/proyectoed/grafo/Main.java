package proyectoed.grafo;

public class Main {
    public static void main(String[] args) {
        // Crear un grafo ponderado
        GrafMatPeso<String> grafo = new GrafMatPeso<>(5);

        // Agregar vértices
        grafo.setPeso(0, 1, 2); // Agregar aristas con pesos
        grafo.setPeso(0, 2, 3);
        grafo.setPeso(1, 2, 1);
        grafo.setPeso(1, 3, 4);
        grafo.setPeso(2, 3, 5);
        grafo.setPeso(2, 4, 6);
        grafo.setPeso(3, 4, 7);

        // Crear un árbol de expansión mínima usando el algoritmo de Prim
        ArbolExpansionMinimo<String> mst = new ArbolExpansionMinimo<>(grafo);
        int costoTotal = mst.arbolExpansionPrim();
        System.out.println("Costo total del MST: " + costoTotal);
    }
}
