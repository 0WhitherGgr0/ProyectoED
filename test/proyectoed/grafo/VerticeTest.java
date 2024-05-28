package proyectoed.grafo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VerticeTest {

	Vertice n = new Vertice("V1");
	
	@Test
	void testNomVertice() {
		assertEquals("V1", n.nomVertice());
	}

	@Test
	void testEqualsVertice() {
		Vertice n1 = new Vertice("V1");
		Vertice n2 = new Vertice("V2");
		assertTrue(n.equals(n1));
		assertFalse(n.equals(n2));
	}

	@Test
	void testAsigVert() {
		n.asigVert(1);
		assertEquals("V1 (1)", n.toString());
	}

	@Test
	void testToString() {
		n.asigVert(1);
		assertEquals("V1 (1)", n.toString());
		Vertice n2 = new Vertice("V2");
		n2.asigVert(2);
		assertEquals("V2 (2)", n2.toString());
	}

}