package proyectoed.grafo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArbolExpansionMinimoTest {
	@Test
    void testArbolExpansionMinimo() {
        GrafMatPeso grafMatPeso = new GrafMatPeso(4); 
        grafMatPeso.setPeso(0, 1, 10);
        grafMatPeso.setPeso(0, 2, 6);
        grafMatPeso.setPeso(0, 3, 5);
        grafMatPeso.setPeso(1, 3, 15);
        grafMatPeso.setPeso(2, 3, 4);

        ArbolExpansionMinimo arbol = new ArbolExpansionMinimo(grafMatPeso);
         
        // Validar que el constructor inicializa correctamente los atributos
        assertNotNull(arbol);
        assertEquals(4, arbol.getPesos().length);
        assertEquals(4, arbol.getPesos()[0].length);
    }
   
    @Test
    void testArbolExpansionPrimCaso1() {
        // Caso 1: Grafo simple
        GrafMatPeso grafMatPeso = new GrafMatPeso(4);
        grafMatPeso.setPeso(0, 1, 10);
        grafMatPeso.setPeso(0, 2, 6);
        grafMatPeso.setPeso(0, 3, 5);
        grafMatPeso.setPeso(1, 3, 15);
        grafMatPeso.setPeso(2, 3, 4);

        ArbolExpansionMinimo arbol = new ArbolExpansionMinimo(grafMatPeso);
        int pesoEsperado = 19; // La suma de los pesos de las aristas en el MST 
        assertEquals(pesoEsperado, arbol.arbolExpansionPrim());
    }
    
    @Test
    void testArbolExpansionPrimCaso2() {
        // Caso 2: Grafo con todos los pesos iguales
        GrafMatPeso grafMatPeso = new GrafMatPeso(3);
        grafMatPeso.setPeso(0, 1, 1);
        grafMatPeso.setPeso(0, 2, 1);
        grafMatPeso.setPeso(1, 2, 1);

        ArbolExpansionMinimo arbol = new ArbolExpansionMinimo(grafMatPeso);
        int pesoEsperado = 2; // La suma de los pesos de las aristas en el MST
        assertEquals(pesoEsperado, arbol.arbolExpansionPrim());
    }

    @Test
    void testArbolExpansionPrimCaso3() {
        // Caso 3: Grafo con vértices desconectados (infinito)
        GrafMatPeso grafMatPeso = new GrafMatPeso(4);
        grafMatPeso.setPeso(0, 1, 1);
        grafMatPeso.setPeso(0, 2, 5);
        grafMatPeso.setPeso(1, 2, 2);
        grafMatPeso.setPeso(1, 3, GrafMatPeso.INFINITO);
        grafMatPeso.setPeso(2, 3, 1);

        ArbolExpansionMinimo arbol = new ArbolExpansionMinimo(grafMatPeso);
        int pesoEsperado = 4; // La suma de los pesos de las aristas en el MST, sin incluir infinito
        assertEquals(pesoEsperado, arbol.arbolExpansionPrim());
    }

    @Test
    void testArbolExpansionPrimCaso4() {
        // Caso 4: Grafo más grande
        GrafMatPeso grafMatPeso = new GrafMatPeso(5);
        grafMatPeso.setPeso(0, 1, 2);
        grafMatPeso.setPeso(0, 3, 6);
        grafMatPeso.setPeso(1, 2, 3);
        grafMatPeso.setPeso(1, 3, 8);
        grafMatPeso.setPeso(1, 4, 5);
        grafMatPeso.setPeso(2, 4, 7);
        grafMatPeso.setPeso(3, 4, 9);

        ArbolExpansionMinimo arbol = new ArbolExpansionMinimo(grafMatPeso);
        int pesoEsperado = 16; // La suma de los pesos de las aristas en el MST
        assertEquals(pesoEsperado, arbol.arbolExpansionPrim());
    }
}