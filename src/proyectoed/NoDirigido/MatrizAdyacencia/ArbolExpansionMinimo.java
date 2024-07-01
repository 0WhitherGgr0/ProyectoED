package proyectoed.NoDirigido.MatrizAdyacencia;

import java.util.ArrayList;
import java.util.List;

public class ArbolExpansionMinimo {
	// Atributos
	private int[][] Pesos;
	private int n; // vértice origen y número de vértices
	private List<int[]> aristasArbolExpansionMinimo;
	private StringBuilder resultado;

	// Contructor
	public ArbolExpansionMinimo(GrafoMatriz gp) {
		n = gp.numeroDeVertices();
		Pesos = gp.getMatPeso();
		aristasArbolExpansionMinimo = new ArrayList<>();
		resultado = new StringBuilder();
	}

	// Getters
	public int[][] getPesos() {
		return Pesos;
	}

	public String getResultado() {
		return resultado.toString();
	}
	
	public List<int[]> getAristasArbolExpansionMinimo() {
		return aristasArbolExpansionMinimo;
	}

	// ******************************Primer cambio**********************************
	// Métodos
	public int arbolExpansionPrim() {
		int longMin, menor;
		int z;
		int[] coste = new int[n];
		int[] masCerca = new int[n];
		boolean[] W = new boolean[n];
		for (int i = 0; i < n; i++) {
			W[i] = false; // conjunto vacío
		}
		longMin = 0;
		W[0] = true; // se parte del vértice 0
		// inicialmente, coste[i] es la arista (0,i)
		for (int i = 1; i < n; i++) {
			coste[i] = Pesos[0][i];
			masCerca[i] = 0;
		}
		for (int i = 1; i < n; i++) { // busca vértice z de V-W más cercano,
			// de menor longitud de arista, a algún vértice de W
			menor = coste[1];
			z = 1;
			for (int j = 2; j < n; j++) {
				if (coste[j] < menor) {
					menor = coste[j];
					z = j;
				}
			}
			longMin += menor;
			// se escribe el arco incorporado al árbol de expansión
			resultado.append("V").append(masCerca[z]).append(" -> ").append("V").append(z).append("\n");
			aristasArbolExpansionMinimo.add(new int[] { masCerca[z], z });
			W[z] = true; // vértice z se añade al conjunto W
			coste[z] = GrafoMatriz.getInfinito();
			// debido a la incorporación de z,
			// se ajusta coste[] para el resto de vértices
			for (int j = 1; j < n; j++) {
				if ((Pesos[z][j] < coste[j]) && !W[j]) {
					coste[j] = Pesos[z][j];
					masCerca[j] = z;
				}
			}
		}
		return longMin;
	}

}
