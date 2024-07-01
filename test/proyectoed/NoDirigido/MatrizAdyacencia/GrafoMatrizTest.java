package proyectoed.NoDirigido.MatrizAdyacencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GrafoMatrizTest {

	@Test
	void testGrafoMatriz() {
		// Crear un grafo con un número máximo de vértices
        GrafoMatriz grafo = new GrafoMatriz(5);
        
        // Verificar que el número inicial de vértices es 0
        assertEquals(0, grafo.numeroDeVertices());
        
        // Verificar que la matriz de pesos se ha inicializado correctamente 
        int[][] matPeso = grafo.getMatPeso();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j) {
                    assertEquals(0, matPeso[i][j]);
                } else {
                    assertEquals(GrafoMatriz.getInfinito(), matPeso[i][j]);
                }
            }
        }
	}

	@Test
	void testNuevoVertice() {
		GrafoMatriz grafo = new GrafoMatriz(5);
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoVertice("D");
        grafo.nuevoVertice("E");
        
        // Verificar que el vértice ha sido añadido
        assertEquals(5, grafo.numeroDeVertices());
        assertEquals("A", grafo.getVerts()[0].nomVertice());
        assertEquals("B", grafo.getVerts()[1].nomVertice());
        assertEquals("C", grafo.getVerts()[2].nomVertice());
        assertEquals("D", grafo.getVerts()[3].nomVertice());
        assertEquals("E", grafo.getVerts()[4].nomVertice());
	}

	@Test
	void testNuevoArco() {
		GrafoMatriz grafo = new GrafoMatriz(5);
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoVertice("D");
        grafo.nuevoVertice("E");
        grafo.nuevoArco("A", "B", 10);
        grafo.nuevoArco("A", "C", 20);
        grafo.nuevoArco("D", "E", 30);
        grafo.nuevoArco("A", "D", 40);
        grafo.nuevoArco("D", "B", 50);
        
        // Verificar que el arco ha sido añadido
        assertEquals(10, grafo.getMatPeso()[0][1]);
        assertEquals(10, grafo.getMatPeso()[1][0]);
        assertEquals(20, grafo.getMatPeso()[0][2]);
        assertEquals(20, grafo.getMatPeso()[2][0]);
        assertEquals(30, grafo.getMatPeso()[3][4]);
        assertEquals(30, grafo.getMatPeso()[4][3]);
        assertEquals(40, grafo.getMatPeso()[0][3]);
        assertEquals(40, grafo.getMatPeso()[3][0]);
        assertEquals(50, grafo.getMatPeso()[3][1]);
        assertEquals(50, grafo.getMatPeso()[1][3]);
	}

	@Test
	void testEliminarArco() {
		GrafoMatriz grafo = new GrafoMatriz(5);
		grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoVertice("D");
        grafo.nuevoVertice("E");
        grafo.nuevoArco("A", "B", 10);
        grafo.nuevoArco("A", "C", 20);
        grafo.nuevoArco("D", "E", 30);
        grafo.nuevoArco("A", "D", 40);
        grafo.nuevoArco("D", "B", 50);
        grafo.eliminarArco("A", "B");
        grafo.eliminarArco("A", "C");
        grafo.eliminarArco("D", "E");
        grafo.eliminarArco("A", "D");
        grafo.eliminarArco("D", "B");
        
        // Verificar que el arco ha sido eliminado
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[0][1]);
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[1][0]);
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[0][2]);
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[2][0]);
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[3][4]);
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[4][3]);
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[0][3]);
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[3][0]);
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[3][1]);
        assertEquals(GrafoMatriz.getInfinito(), grafo.getMatPeso()[1][3]);
	}

	@Test
	void testNumVertice() {
		GrafoMatriz grafo = new GrafoMatriz(5);
		grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoVertice("D");
        grafo.nuevoVertice("E");
        
        // Verificar que los índices de los vértices son correctos
        assertEquals(0, grafo.numVertice("A"));
        assertEquals(1, grafo.numVertice("B"));
        assertEquals(2, grafo.numVertice("C"));
        assertEquals(3, grafo.numVertice("D"));
        assertEquals(4, grafo.numVertice("E"));
        assertEquals(-1, grafo.numVertice("F"));
	}

	@Test
	void testEliminarVertice() {
		GrafoMatriz grafo = new GrafoMatriz(5);
		grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoVertice("D");
        grafo.nuevoVertice("E");
        grafo.eliminarVertice("A");
        grafo.eliminarVertice("B");
        grafo.eliminarVertice("C");
        
        // Verificar que el vértice ha sido eliminado
        assertEquals(2, grafo.numeroDeVertices());
        assertEquals("D", grafo.getVerts()[0].nomVertice());
	}

	@Test
	void testImprimirConsolaGrafo() {
		GrafoMatriz grafo = new GrafoMatriz(3);
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoArco("A", "B", 5);
        grafo.nuevoArco("B", "C", 3);
        grafo.imprimirConsolaGrafo();
        
     // No se puede comprobar directamente la salida de consola, se debe verificar manualmente.
	}

	@Test
	void testImprimirStringGrafo() {
		GrafoMatriz grafo = new GrafoMatriz(3);
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoArco("A", "B", 5);
        grafo.nuevoArco("B", "C", 3);
        String grafoStr = grafo.imprimirStringGrafo();
        
        // Verificar que la salida es la esperada
        String expectedOutput = "\nMatriz de Adyacencia:\n0 5 ∞ \n5 0 3 \n∞ 3 0 \n\nVértices:\n\nA B C \n";
        assertEquals(expectedOutput, grafoStr);
	}

	@Test
	void testGetResultado() {
		GrafoMatriz grafo = new GrafoMatriz(3);
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoArco("A", "B", 5);
        grafo.nuevoArco("B", "C", 3);
        grafo.imprimirStringGrafo();
        
        // Verificar que getResultado devuelve la salida correcta
        String expectedOutput = "\nMatriz de Adyacencia:\n0 5 ∞ \n5 0 3 \n∞ 3 0 \n\nVértices:\n\nA B C \n";
        assertEquals(expectedOutput, grafo.getResultado());
	}

	@Test
	void testGetMatPeso() {
		GrafoMatriz grafo = new GrafoMatriz(3);
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoArco("A", "B", 5);
        grafo.nuevoArco("B", "C", 3);
        
        // Verificar que la matriz de pesos es correcta
        int[][] matPeso = grafo.getMatPeso();
        assertEquals(0, matPeso[0][0]);
        assertEquals(5, matPeso[0][1]);
        assertEquals(GrafoMatriz.getInfinito(), matPeso[0][2]);
        assertEquals(5, matPeso[1][0]);
        assertEquals(0, matPeso[1][1]);
        assertEquals(3, matPeso[1][2]);
        assertEquals(GrafoMatriz.getInfinito(), matPeso[2][0]);
        assertEquals(3, matPeso[2][1]);
        assertEquals(0, matPeso[2][2]);
	}

	@Test
	void testNumeroDeVertices() {
		GrafoMatriz grafo = new GrafoMatriz(6);
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoVertice("D");
        grafo.nuevoVertice("E");
        grafo.nuevoVertice("F");
        
        // Verificar que el número de vértices es correcto
        assertEquals(6, grafo.numeroDeVertices());
	}

	@Test
	void testGetVerts() {
		GrafoMatriz grafo = new GrafoMatriz(6);
		grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        grafo.nuevoVertice("C");
        grafo.nuevoVertice("D");
        grafo.nuevoVertice("E");
        grafo.nuevoVertice("F");
        
        
        // Verificar que los vértices son correctos
        Vertice[] verts = grafo.getVerts();
        assertEquals("A", verts[0].nomVertice());
        assertEquals("B", verts[1].nomVertice());
        assertEquals("C", verts[2].nomVertice());
        assertEquals("D", verts[3].nomVertice());
        assertEquals("E", verts[4].nomVertice());
        assertEquals("F", verts[5].nomVertice());
	}

	@Test
	void testGetInfinito() {
		// Verificar que el valor de INFINITO es Integer.MAX_VALUE
        assertEquals(Integer.MAX_VALUE, GrafoMatriz.getInfinito());
	}

}