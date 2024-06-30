package proyectoed.NoDirigido.MatrizAdyacencia;


public class Demo {
	   public static void main(String[] args) {
/*   		
 *			//Ejemplo de Recorrido 1  			
 			GrafoMatriz grafo = new GrafoMatriz(10);

	        // Añadir vértices
	        grafo.nuevoVertice("1");
	        grafo.nuevoVertice("2");
	        grafo.nuevoVertice("3");
	        grafo.nuevoVertice("4");
	        grafo.nuevoVertice("5");
	        grafo.nuevoVertice("6");

	        // Añadir arcos
	        grafo.nuevoArco("1", "3", 1);
	        grafo.nuevoArco("1", "2", 2);
	        grafo.nuevoArco("1", "5", 3);
	        grafo.nuevoArco("2", "3", 3);
	        grafo.nuevoArco("2", "4", 2);
	        grafo.nuevoArco("3", "4", 4);
	        grafo.nuevoArco("5", "6", 4);
	        grafo.nuevoArco("6", "1", 6);
	        grafo.nuevoArco("6", "4", 5);
*/
			//Ejemplo de Recorrido 2 			

		   GrafoMatriz grafo = new GrafoMatriz(10);

	        // Añadir vértices
	        for (int i = 1; i <= 10; i++) {
	            grafo.nuevoVertice(String.valueOf(i));
	        }

	        // Añadir arcos
	        grafo.nuevoArco("1", "2", 5);
	        grafo.nuevoArco("1", "4", 8);
	        grafo.nuevoArco("1", "3", 10);
	        grafo.nuevoArco("2", "4", 6);
	        grafo.nuevoArco("2", "6", 5);
	        grafo.nuevoArco("3", "4", 7);
	        grafo.nuevoArco("3", "5", 8);
	        grafo.nuevoArco("3", "8", 15);
	        grafo.nuevoArco("4", "5", 5);
	        grafo.nuevoArco("4", "6", 11);
	        grafo.nuevoArco("5", "7", 4);
	        grafo.nuevoArco("5", "8", 3);
	        grafo.nuevoArco("6", "7", 9);
	        grafo.nuevoArco("6", "9", 7);
	        grafo.nuevoArco("7", "8", 12);
	        grafo.nuevoArco("7", "9", 4);
	        grafo.nuevoArco("7", "10", 6);
	        grafo.nuevoArco("8", "10", 12);
	        grafo.nuevoArco("9", "10", 7);
		   		   
	        // Imprimir el grafo
	        System.out.println("Grafo representado con matriz de pesos:");
	        grafo.imprimirConsolaGrafo();

	        // Encontrar el árbol de expansión mínima usando el algoritmo de Prim
	        ArbolExpansionMinimo prim = new ArbolExpansionMinimo(grafo);
	        System.out.println("Arbol de expansión mínima (algoritmo de Prim):");
	        int costoTotal = prim.arbolExpansionPrim();
	        System.out.println("Costo total del árbol de expansión mínima: " + costoTotal);
	    }
}
