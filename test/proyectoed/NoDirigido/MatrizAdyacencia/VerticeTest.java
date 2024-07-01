package proyectoed.NoDirigido.MatrizAdyacencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VerticeTest {

	Vertice v = new Vertice("Nombre");

	@Test
	void testVertice() {
		// Crear vértices con diferentes nombres
        Vertice verticeA = new Vertice("A");
        Vertice verticeB = new Vertice("B");
        Vertice verticeC = new Vertice("C");
        Vertice verticeD = new Vertice("D");
        Vertice verticeE = new Vertice("E");

        // Verificar que los objetos no sean null
        assertNotNull(verticeA);
        assertNotNull(verticeB);
        assertNotNull(verticeC);
        assertNotNull(verticeD);
        assertNotNull(verticeE);

        // Verificar que los nombres de los vértices son los esperados
        assertEquals("A", verticeA.nomVertice());
        assertEquals("B", verticeB.nomVertice());
        assertEquals("C", verticeC.nomVertice());
        assertEquals("D", verticeD.nomVertice());
        assertEquals("E", verticeE.nomVertice());
	}
	
	@Test
	void testNomVertice() {
		assertEquals("Nombre", v.nomVertice());
	}

}
