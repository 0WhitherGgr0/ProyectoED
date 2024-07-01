package proyectoed.NoDirigido.MatrizAdyacencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GrafoMatrizTest {

	GrafoMatriz gm = new GrafoMatriz(4);
	
	@Test
	void testNuevoVertice() {
		assertEquals(0, gm.numeroDeVertices());
		gm.nuevoVertice("1");
		assertEquals(1, gm.numeroDeVertices());
		gm.nuevoVertice("2");
		assertEquals(2, gm.numeroDeVertices());
		gm.nuevoVertice("3");
		assertEquals(3, gm.numeroDeVertices());
		gm.nuevoVertice("4");
		assertEquals(4, gm.numeroDeVertices());
		assertThrows(IndexOutOfBoundsException.class, () -> gm.nuevoVertice("5"));
	}

	@Test
	void testNuevoArco() {
		gm.nuevoVertice("1");
		gm.nuevoVertice("2");
		gm.nuevoVertice("3");
		gm.nuevoVertice("4");
		System.out.println(gm.imprimirStringGrafo());
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 ∞ ∞ ∞ \n"
				+ "∞ 0 ∞ ∞ \n"
				+ "∞ ∞ 0 ∞ \n"
				+ "∞ ∞ ∞ 0 \n"
				+ "\nVertices:\n\n"
				+ "1 2 3 4 \n", 
				gm.imprimirStringGrafo());
		gm.nuevoArco("1", "2", 1);
		gm.nuevoArco("2", "3", 2);
		gm.nuevoArco("3", "4", 3);
		gm.nuevoArco("4", "1", 4);
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 1 ∞ 4 \n"
				+ "1 0 2 ∞ \n"
				+ "∞ 2 0 3 \n"
				+ "4 ∞ 3 0 \n"
				+ "\nVertices:\n\n"
				+ "1 2 3 4 \n", 
				gm.imprimirStringGrafo());
		assertThrows(IndexOutOfBoundsException.class, () -> gm.nuevoArco("10", "3", 1));
		assertThrows(IndexOutOfBoundsException.class, () -> gm.nuevoArco("2", "10", 1));
	}

	@Test
	void testEliminarArco() {
		gm.nuevoVertice("1");
		gm.nuevoVertice("2");
		gm.nuevoVertice("3");
		gm.nuevoVertice("4");
		gm.nuevoArco("1", "2", 1);
		gm.nuevoArco("2", "3", 2);
		gm.nuevoArco("3", "4", 3);
		gm.nuevoArco("4", "1", 4);
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 1 ∞ 4 \n"
				+ "1 0 2 ∞ \n"
				+ "∞ 2 0 3 \n"
				+ "4 ∞ 3 0 \n"
				+ "\nVertices:\n\n"
				+ "1 2 3 4 \n",
				gm.imprimirStringGrafo());
		gm.eliminarArco("1", "2");
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 ∞ ∞ 4 \n"
				+ "∞ 0 2 ∞ \n"
				+ "∞ 2 0 3 \n"
				+ "4 ∞ 3 0 \n"
				+ "\nVertices:\n\n"
				+ "1 2 3 4 \n", 
				gm.imprimirStringGrafo());
		gm.eliminarArco("2", "3");
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 ∞ ∞ 4 \n"
				+ "∞ 0 ∞ ∞ \n"
				+ "∞ ∞ 0 3 \n"
				+ "4 ∞ 3 0 \n"
				+ "\nVertices:\n\n"
				+ "1 2 3 4 \n", 
				gm.imprimirStringGrafo());
		gm.eliminarArco("3", "4");
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 ∞ ∞ 4 \n"
				+ "∞ 0 ∞ ∞ \n"
				+ "∞ ∞ 0 ∞ \n"
				+ "4 ∞ ∞ 0 \n"
				+ "\nVertices:\n\n"
				+ "1 2 3 4 \n", 
				gm.imprimirStringGrafo());
		assertThrows(RuntimeException.class, () -> gm.eliminarArco("10", "2"));
		assertThrows(RuntimeException.class, () -> gm.eliminarArco("1", "10"));
	}

	@Test
	void testNumVertice() {
		gm.nuevoVertice("1");
		gm.nuevoVertice("2");
		gm.nuevoVertice("3");
		gm.nuevoVertice("4");
		assertEquals(3, gm.numVertice("4"));
		assertEquals(2, gm.numVertice("3"));
		assertEquals(1, gm.numVertice("2"));
		assertEquals(0, gm.numVertice("1"));
		assertEquals(-1, gm.numVertice("1000"));
	}

	@Test
	void testEliminarVertice() {
		gm.nuevoVertice("1");
		gm.nuevoVertice("2");
		gm.nuevoVertice("3");
		gm.nuevoVertice("4");
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n" //Para cuatro nodos
				+ "0 ∞ ∞ ∞ \n"
				+ "∞ 0 ∞ ∞ \n"
				+ "∞ ∞ 0 ∞ \n"
				+ "∞ ∞ ∞ 0 \n"
				+ "\nVertices:\n\n"
				+ "1 2 3 4 \n", 
				gm.imprimirStringGrafo());
		gm.eliminarVertice("2");
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n" //Para tres nodos
				+ "0 ∞ ∞ \n"
				+ "∞ 0 ∞ \n"
				+ "∞ ∞ 0 \n"
				+ "\nVertices:\n\n"
				+ "1 3 4 \n", 
				gm.imprimirStringGrafo());
		gm.eliminarVertice("4");
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n" //Para dos nodos
				+ "0 ∞ \n"
				+ "∞ 0 \n"
				+ "\nVertices:\n\n"
				+ "1 3 \n", 
				gm.imprimirStringGrafo());
		gm.eliminarVertice("1");
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n" //Para un nodo
				+ "0 \n"
				+ "\nVertices:\n\n"
				+ "3 \n", 
				gm.imprimirStringGrafo());
		assertThrows(RuntimeException.class, () -> gm.eliminarVertice("10"));
	}

	@Test
	void testImprimirStringGrafo() {
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "\nVertices:\n\n"
				+ "\n",
				gm.imprimirStringGrafo());
		gm.nuevoVertice("1");
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 \n"
				+ "\nVertices:\n\n"
				+ "1 \n",
				gm.imprimirStringGrafo());
		gm.nuevoVertice("2");
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 ∞ \n"
				+ "∞ 0 \n"
				+ "\nVertices:\n\n"
				+ "1 2 \n",
				gm.imprimirStringGrafo());
		gm.nuevoArco("1", "2", 3);
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n" //Para dos nodos
				+ "0 3 \n"
				+ "3 0 \n"
				+ "\nVertices:\n\n"
				+ "1 2 \n",
				gm.imprimirStringGrafo());
	}
	
}