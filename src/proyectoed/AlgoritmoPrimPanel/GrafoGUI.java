package proyectoed.AlgoritmoPrimPanel;

import javax.swing.*;

import proyectoed.NoDirigido.MatrizAdyacencia.ArbolExpansionMinimo;
import proyectoed.NoDirigido.MatrizAdyacencia.GrafoMatriz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GrafoGUI extends JFrame {
	private GrafoMatriz grafo;
	private List<int[]> arbolExpansionMinimo;
	private ArbolExpansionMinimoPanel arbolPanel;
	private GrafoMatrizPanel grafoPanel;
	private ResultadoPanel resultadoPanel;

	public GrafoGUI(GrafoMatriz grafo, List<int[]> arbolExpansionMinimo) {
		this.grafo = grafo;
		this.arbolExpansionMinimo = arbolExpansionMinimo;

		setTitle("Grafo y Árbol de Expansión Mínima");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Crear paneles para el grafo y el árbol de expansión mínima
		grafoPanel = new GrafoMatrizPanel(grafo);
		arbolPanel = new ArbolExpansionMinimoPanel(grafo, arbolExpansionMinimo);
		resultadoPanel = new ResultadoPanel();

		// Crear botones y panel de botones
		JPanel botonesPanel = new JPanel();
		botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		JButton agregarNodoBtn = new JButton("Agregar Nodo");
		JButton unirNodoBtn = new JButton("Unir Nodo");
		JButton eliminarNodoBtn = new JButton("Eliminar Nodo");
		JButton eliminarArcoBtn = new JButton("Eliminar Arco"); // Nuevo botón
		JButton actualizarGrafoBtn = new JButton("Actualizar Grafo");

		agregarNodoBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JTextField nombreNodoField = new JTextField();
		        JTextField nodoConectarField = new JTextField();
		        JTextField pesoField = new JTextField();

		        Object[] message = {
		            "Nombre del nuevo nodo:", nombreNodoField,
		            "Conectar a (nombre del nodo existente):", nodoConectarField,
		            "Peso de la conexión:", pesoField
		        };

		        int option = JOptionPane.showConfirmDialog(
		            GrafoGUI.this, message, "Agregar Nodo y Conectar", JOptionPane.OK_CANCEL_OPTION);

		        if (option == JOptionPane.OK_OPTION) {
		            String nombreNodo = nombreNodoField.getText().trim();
		            String nodoConectar = nodoConectarField.getText().trim();
		            String pesoStr = pesoField.getText().trim();

		            // Validar nombre del nuevo nodo
		            if (nombreNodo.isEmpty()) {
		                JOptionPane.showMessageDialog(GrafoGUI.this, "Debe ingresar un nombre válido para el nuevo nodo.");
		                return;
		            }
		            if (grafo.numVertice(nombreNodo) != -1) {
		                JOptionPane.showMessageDialog(GrafoGUI.this, "El nombre del nodo ya existe.");
		                return;
		            }

		            // Validar nodo a conectar
		            if (nodoConectar.isEmpty() || grafo.numVertice(nodoConectar) == -1) {
		                JOptionPane.showMessageDialog(GrafoGUI.this, "Debe conectar el nuevo nodo a un nodo existente.");
		                return;
		            }

		            // Validar peso
		            int peso;
		            try {
		                peso = Integer.parseInt(pesoStr);
		                if (peso <= 0) {
		                    JOptionPane.showMessageDialog(GrafoGUI.this, "El peso debe ser un número positivo mayor que cero.");
		                    return;
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(GrafoGUI.this, "Peso inválido. Debe ser un número.");
		                return;
		            }

		            // Agregar el nuevo nodo y conectarlo
		            grafo.nuevoVertice(nombreNodo);
		            grafo.nuevoArco(nombreNodo, nodoConectar, peso);

		            // Actualizar la interfaz y resultados
		            actualizarResultados("\n---------------------------------------\n");
		            grafo.imprimirConsolaGrafo();
		            actualizarResultados("Nodo agregado y conectado: " + nombreNodo + " -> " + nodoConectar + " con peso " + peso);
		            mostrarRecorridoString();
		            actualizarInterfaz();
		            actualizarResultados(grafo.imprimirStringGrafo());
		        }
		    }
		});


		unirNodoBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JTextField nodoInicioField = new JTextField();
		        JTextField nodoDestinoField = new JTextField();
		        JTextField pesoField = new JTextField();

		        Object[] message = {
		            "Nodo Inicio:", nodoInicioField,
		            "Nodo Destino:", nodoDestinoField,
		            "Peso:", pesoField
		        };

		        int option = JOptionPane.showConfirmDialog(GrafoGUI.this, message, "Unir Nodos",
		                JOptionPane.OK_CANCEL_OPTION);

		        if (option == JOptionPane.OK_OPTION) {
		            String nodoInicio = nodoInicioField.getText().trim();
		            String nodoDestino = nodoDestinoField.getText().trim();
		            String pesoStr = pesoField.getText().trim();

		            // Validar nodos y peso
		            if (nodoInicio.isEmpty() || nodoDestino.isEmpty() || pesoStr.isEmpty()) {
		                JOptionPane.showMessageDialog(GrafoGUI.this, "Todos los campos son requeridos.");
		                return;
		            }

		            int peso;
		            try {
		                peso = Integer.parseInt(pesoStr);
		                if (peso <= 0) {
		                    JOptionPane.showMessageDialog(GrafoGUI.this,
		                            "El peso debe ser un número positivo mayor que cero.");
		                    return;
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(GrafoGUI.this, "Peso inválido. Debe ser un número.");
		                return;
		            }

		            try {
		                grafo.nuevoArco(nodoInicio, nodoDestino, peso);
		            } catch (IllegalArgumentException ex) {
		                JOptionPane.showMessageDialog(GrafoGUI.this, ex.getMessage());
		                return;
		            }

		            actualizarResultados("\n---------------------------------------\n");
		            grafo.imprimirConsolaGrafo(); // Imprimir el estado del grafo
		            actualizarResultados("Arco agregado: " + nodoInicio + " -> " + nodoDestino + " con peso " + peso);
		            mostrarRecorridoString();
		            actualizarInterfaz();
		            actualizarResultados(grafo.imprimirStringGrafo());
		        }
		    }
		});


		eliminarNodoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreNodo = pedirNodo("Ingrese el nombre del nodo a eliminar:");
				if (nombreNodo == null || nombreNodo.trim().isEmpty()) {
					return; // Cancelled or empty input
				}

				int numVertice = grafo.numVertice(nombreNodo);
				if (numVertice == -1) {
					JOptionPane.showMessageDialog(GrafoGUI.this, "El nodo no existe.");
					return;
				}

				grafo.eliminarVertice(nombreNodo);
//************************************************************************************                
                actualizarResultados("\n---------------------------------------\n");
				grafo.imprimirConsolaGrafo(); // Imprimir el estado del grafo
                actualizarResultados("Nodo eliminado: " + nombreNodo);
                mostrarRecorridoString();
                actualizarInterfaz();
                actualizarResultados(grafo.imprimirStringGrafo());
//************************************************************************************                

			}
		});

		eliminarArcoBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JTextField nodoInicioField = new JTextField();
		        JTextField nodoDestinoField = new JTextField();

		        Object[] message = {
		            "Nodo de Inicio:", nodoInicioField,
		            "Nodo Destino:", nodoDestinoField
		        };

		        int option = JOptionPane.showConfirmDialog(GrafoGUI.this, message, "Eliminar Arco",
		                JOptionPane.OK_CANCEL_OPTION);

		        if (option == JOptionPane.OK_OPTION) {
		            String nodoInicio = nodoInicioField.getText().trim();
		            String nodoDestino = nodoDestinoField.getText().trim();

		            // Validar nodos
		            if (nodoInicio.isEmpty() || nodoDestino.isEmpty()) {
		                JOptionPane.showMessageDialog(GrafoGUI.this, "Ambos campos son requeridos.");
		                return;
		            }

		            try {
		                grafo.eliminarArco(nodoInicio, nodoDestino);
		            } catch (IllegalArgumentException ex) {
		                JOptionPane.showMessageDialog(GrafoGUI.this, ex.getMessage());
		                return;
		            }

		            actualizarResultados("\n---------------------------------------\n");
		            grafo.imprimirConsolaGrafo(); // Imprimir el estado del grafo
		            actualizarResultados("Arco eliminado: " + nodoInicio + " -> " + nodoDestino);
		            mostrarRecorridoString();
		            actualizarInterfaz();
		            actualizarResultados(grafo.imprimirStringGrafo());
		        }
		    }
		});

		actualizarGrafoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                actualizarResultados("\n---------------------------------------\n");
				mostrarRecorridoString();
				actualizarInterfaz();
                actualizarResultados(grafo.imprimirStringGrafo());
			}
		});

		botonesPanel.add(agregarNodoBtn);
		botonesPanel.add(unirNodoBtn);
		botonesPanel.add(eliminarNodoBtn);
		botonesPanel.add(eliminarArcoBtn); // Agregar el botón eliminar arco
		botonesPanel.add(actualizarGrafoBtn);

		// Añadir los paneles al JFrame
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 3));
		mainPanel.add(grafoPanel);
		mainPanel.add(arbolPanel);
		mainPanel.add(resultadoPanel);

		add(mainPanel, BorderLayout.CENTER);
		add(botonesPanel, BorderLayout.SOUTH);

		pack();
		setSize(1600, 800);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	
	private String pedirNodo(String mensaje) {
		return JOptionPane.showInputDialog(GrafoGUI.this, mensaje);
	}

	private String pedirPeso(String mensaje) {
		return JOptionPane.showInputDialog(GrafoGUI.this, mensaje);
	}

	public void mostrarRecorridoString() {
	    // Encontrar el árbol de expansión mínima usando el algoritmo de Prim
	    ArbolExpansionMinimo prim = new ArbolExpansionMinimo(grafo);
	    int costoTotal = prim.arbolExpansionPrim();
	    arbolExpansionMinimo = prim.getAristasArbolExpansionMinimo();

	    // Actualizar el panel de resultados
	    StringBuilder resultado = new StringBuilder();
	    resultado.append("Aristas del árbol de expansión mínima:\n");
	    for (int[] arista : arbolExpansionMinimo) {
	        String vertice1 = grafo.getVerts()[arista[0]].nomVertice();
	        String vertice2 = grafo.getVerts()[arista[1]].nomVertice();
	        resultado.append("V").append(vertice1).append(" -> V").append(vertice2).append("\n");
	    }
	    resultado.append("Costo total del árbol de expansión mínima: ").append(costoTotal).append("\n");
	    actualizarResultados(resultado.toString());
	}
	
	
	private void actualizarInterfaz() {
		// Encontrar el árbol de expansión mínima usando el algoritmo de Prim
		ArbolExpansionMinimo prim = new ArbolExpansionMinimo(grafo);
		int costoTotal = prim.arbolExpansionPrim();
		arbolExpansionMinimo = prim.getAristasArbolExpansionMinimo();

// *****************************************************************
		// Imprimir en consola las aristas del árbol de expansión mínima
		System.out.println("Aristas del árbol de expansión mínima:");
		for (int[] arista : arbolExpansionMinimo) {
			System.out.println("V" + arista[0] + " -> V" + arista[1]);
		}
		System.out.println("Costo total del árbol de expansión mínima: " + costoTotal);
// ***************************************************************

		// Actualizar los paneles
		grafoPanel.setGrafo(grafo);
		arbolPanel.setArbolExpansionMinimo(arbolExpansionMinimo);
		grafoPanel.repaint();
		arbolPanel.repaint();
// ***************************************************************		
		System.out.println("Grafo representado con matriz de pesos:");
		grafo.imprimirConsolaGrafo();
// ***************************************************************
		
	}
// ***************************************************************

// Método para actualizar los resultados en el panel de resultados
    public void actualizarResultados(String resultados) {
        resultadoPanel.actualizarResultados(resultados);
    }
 // ***************************************************************
	
	public static void main(String[] args) {
		// Crear el grafo y añadir vértices (nodos)
		GrafoMatriz grafo = new GrafoMatriz(15);
		// Añadir vértices
		for (int i = 1; i <= 10; i++) {
			grafo.nuevoVertice(String.valueOf(i));
		}

		// Llamar a un método para establecer las conexiones entre los nodos (arcos)
		establecerConexionesIniciales(grafo);

		// Encontrar el árbol de expansión mínima usando el algoritmo de Prim
		ArbolExpansionMinimo prim = new ArbolExpansionMinimo(grafo);
		int costoTotal = prim.arbolExpansionPrim();
		List<int[]> arbolExpansionMinimo = prim.getAristasArbolExpansionMinimo();

// ***************************************************************		
		// Imprimir en consola las aristas del árbol de expansión mínima
		System.out.println("Aristas del árbol de expansión mínima:");
		for (int[] arista : arbolExpansionMinimo) {
			System.out.println("V" + arista[0] + " -> V" + arista[1]);
		}
		System.out.println("Costo total del árbol de expansión mínima: " + costoTotal);
// ***************************************************************

		
		// Crear y mostrar la interfaz gráfica
		SwingUtilities.invokeLater(() -> new GrafoGUI(grafo, arbolExpansionMinimo));
	}

	private static void establecerConexionesIniciales(GrafoMatriz grafo) {
		// Añadir arcos iniciales
		grafo.nuevoArco("1", "2", 5);
		grafo.nuevoArco("1", "4", 8);
		grafo.nuevoArco("1", "3", 10);
		grafo.nuevoArco("2", "4", 6);
		grafo.nuevoArco("2", "6", 5);
		grafo.nuevoArco("3", "4", 7);
		grafo.nuevoArco("3", "5", 8);
		grafo.nuevoArco("3", "8", 15);
		grafo.nuevoArco("4", "5", 5);
		grafo.nuevoArco("4", "6", 11);
		grafo.nuevoArco("5", "7", 4);
		grafo.nuevoArco("5", "8", 3);
		grafo.nuevoArco("6", "7", 9);
		grafo.nuevoArco("6", "9", 7);
		grafo.nuevoArco("7", "8", 12);
		grafo.nuevoArco("7", "9", 4);
		grafo.nuevoArco("7", "10", 6);
		grafo.nuevoArco("8", "10", 12);
		grafo.nuevoArco("9", "10", 7);

	}
}
