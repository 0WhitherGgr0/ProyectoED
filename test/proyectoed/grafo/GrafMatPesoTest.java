package proyectoed.grafo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GrafMatPesoTest {

	@Test
	void testGrafMatPeso() {
		 GrafMatPeso grafo = new GrafMatPeso(4);
		 assertNotNull(grafo);
	     assertEquals(0, grafo.matPeso[0][0]);
	     assertEquals(0, grafo.matPeso[1][1]);
	     assertEquals(0, grafo.matPeso[2][2]);
	     assertEquals(0, grafo.matPeso[3][3]);
	     assertEquals(Integer.MAX_VALUE, grafo.matPeso[0][1]);
	     assertEquals(Integer.MAX_VALUE, grafo.matPeso[1][2]);
	}

	@Test
	void testNumeroDeVertices() {
		GrafMatPeso grafo = new GrafMatPeso(4);
		assertEquals(4,grafo.numeroDeVertices());
	}

	@Test
	void testGetMatPeso() {
		GrafMatPeso grafo = new GrafMatPeso(4);
		assertNotNull(grafo.getMatPeso());
		assertTrue(grafo.matPeso==grafo.getMatPeso());
	}

	@Test
	void testSetPeso() {
		GrafMatPeso grafo = new GrafMatPeso(4);
		grafo.setPeso(2,3,8);
		assertEquals(8, grafo.matPeso[2][3]);
		grafo.setPeso(3,0,5);
		assertEquals(5, grafo.matPeso[3][0]);
		 
	}

}
