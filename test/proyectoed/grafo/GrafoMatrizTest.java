package proyectoed.grafo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GrafoMatrizTest {

    @Test
    void testGrafoMatriz() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        assertNotNull(grafo);
        assertEquals(0, grafo.getNumVerts());
    }

    @Test
    void testGrafoMatrizInt() {
        int maxVertices = 10;
        GrafoMatriz<String> grafo = new GrafoMatriz<>(maxVertices);
        assertNotNull(grafo);
        assertEquals(0, grafo.getNumVerts());
        assertEquals(maxVertices, grafo.getVerts().length);
    }
    
    @Test
    void testNuevoVertice() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        assertEquals(1, grafo.getNumVerts());
        assertEquals("A", grafo.getVerts()[0].nomVertice());
    }
    
    @Test
    void testNumVertice() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        assertEquals(0, grafo.numVertice("A"));
        assertEquals(1, grafo.numVertice("B"));
        assertEquals(-1, grafo.numVertice("C"));
    }
    
    @Test
    void testNuevoArcoStringString() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        assertDoesNotThrow(() -> grafo.nuevoArco("A", "B"));
        assertTrue(grafo.getMatAd()[0][1] == 1);
    }
	
    @Test
    void testNuevoArcoIntInt() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        assertDoesNotThrow(() -> grafo.nuevoArco(0, 1));
        assertTrue(grafo.getMatAd()[0][1] == 1);
    }
    
    @Test
    void testNuevoArcoExceptions() {
    	GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        assertThrows(Exception.class, () -> grafo.nuevoArco("C", "D"));
        assertThrows(Exception.class, () -> grafo.nuevoArco(2, 3));
    }
    
    @Test
    void testAdyacenteStringString() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        assertDoesNotThrow(() -> grafo.nuevoArco("A", "B"));
        assertDoesNotThrow(() -> {
            assertTrue(grafo.adyacente("A", "B"));
            assertFalse(grafo.adyacente("B", "A"));
        });
    }
    
    @Test
    void testAdyacenteIntInt() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("B");
        assertDoesNotThrow(() -> grafo.nuevoArco(0, 1));
        assertDoesNotThrow(() -> {
            assertTrue(grafo.adyacente(0, 1));
            assertFalse(grafo.adyacente(1, 0));
        });
    }

    @Test
    void testNoDuplicateVertices() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        grafo.nuevoVertice("A"); // Intentar añadir el mismo vértice nuevamente
        assertEquals(1, grafo.getNumVerts()); // Verificar que solo hay un vértice
        assertEquals("A", grafo.getVerts()[0].nomVertice());
    }

    @Test
    void testNuevoArcoStringStringException() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        Exception exception = assertThrows(Exception.class, () -> grafo.nuevoArco("A", "B"));
        assertEquals("Vértice no existe", exception.getMessage());
    }

    @Test
    void testNuevoArcoIntIntException() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        Exception exception = assertThrows(Exception.class, () -> grafo.nuevoArco(0, 1));
        assertEquals("Vértice no existe", exception.getMessage());
    }

    @Test
    void testAdyacenteStringStringException() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        Exception exception = assertThrows(Exception.class, () -> grafo.adyacente("A", "B"));
        assertEquals("Vértice no existe", exception.getMessage());
    }

    @Test
    void testAdyacenteIntIntException() {
        GrafoMatriz<String> grafo = new GrafoMatriz<>();
        grafo.nuevoVertice("A");
        Exception exception = assertThrows(Exception.class, () -> grafo.adyacente(0, 1));
        assertEquals("Vértice no existe", exception.getMessage());
    }
    
}
