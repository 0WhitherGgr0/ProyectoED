package proyectoed.NoDirigido.MatrizAdyacencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VerticeTest {

	Vertice v = new Vertice("Nombre");

	@Test
	void testNomVertice() {
		assertEquals("Nombre", v.nomVertice());
	}

}
