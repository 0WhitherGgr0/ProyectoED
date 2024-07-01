package proyectoed.NoDirigido.MatrizAdyacencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArbolExpansionMinimoTest {

	ArbolExpansionMinimo aem1;
	ArbolExpansionMinimo aem2;
	GrafoMatriz gm1 = new GrafoMatriz(3);
	GrafoMatriz gm2 = new GrafoMatriz(5);
	
	@BeforeEach
	void init() {
		gm1.nuevoVertice("1");
		gm1.nuevoVertice("2");
		gm1.nuevoVertice("3");
		gm1.nuevoArco("1", "2", 1);
		gm1.nuevoArco("2", "3", 2);
		gm1.nuevoArco("1", "3", 3);
		aem1 = new ArbolExpansionMinimo(gm1);
		//
		gm2.nuevoVertice("1");
		gm2.nuevoVertice("2");
		gm2.nuevoVertice("3");
		gm2.nuevoVertice("4");
		gm2.nuevoVertice("5");
		gm2.nuevoArco("1", "2", 1);
		gm2.nuevoArco("2", "3", 2);
		gm2.nuevoArco("1", "3", 3);
		gm2.nuevoArco("2", "4", 3);
		gm2.nuevoArco("3", "5", 2);
		gm2.nuevoArco("4", "5", 1);
		gm2.nuevoArco("1", "4", 2);
		gm2.nuevoArco("1", "5", 3);
		aem2 = new ArbolExpansionMinimo(gm2);
	}
	
	@Test
	void testArbolExpansionPrim() {
		//Matriz de adyacencia de aem1
		// 0 1 3					V1--V2 = 1
		// 1 0 2			->		V2--V3 = 2
		// 3 2 0					Total  = 3
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 1 3 \n"
				+ "1 0 2 \n"
				+ "3 2 0 \n", 
				gm1.imprimirStringGrafo());
		assertEquals(3, aem1.arbolExpansionPrim());
		//Matriz de adyacencia de aem2
		// 0 1 3 2 3				V1--V2 = 1
		// 1 0 2 3 0				V1--V4 = 2
		// 3 2 0 0 2		->		V4--V5 = 1
		// 2 3 0 0 1				V3--V5 = 2
		// 3 0 2 1 0				Total  = 6
		assertEquals("\n"
				+ "Matriz de Adyacencia:\n"
				+ "0 1 3 2 3 \n"
				+ "1 0 2 3 ∞ \n"
				+ "3 2 0 ∞ 2 \n"
				+ "2 3 ∞ 0 1 \n"
				+ "3 ∞ 2 1 0 \n", 
				gm2.imprimirStringGrafo());
		assertEquals(6, aem2.arbolExpansionPrim());
	}

}
