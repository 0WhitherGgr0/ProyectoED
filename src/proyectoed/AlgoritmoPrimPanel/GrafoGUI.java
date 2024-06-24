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

    public GrafoGUI(GrafoMatriz grafo, List<int[]> arbolExpansionMinimo) {
        this.grafo = grafo;
        this.arbolExpansionMinimo = arbolExpansionMinimo;

        setTitle("Grafo y Árbol de Expansión Mínima");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear paneles para el grafo y el árbol de expansión mínima
        grafoPanel = new GrafoMatrizPanel(grafo);
        arbolPanel = new ArbolExpansionMinimoPanel(grafo, arbolExpansionMinimo);

        // Crear botones y panel de botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton agregarNodoBtn = new JButton("Agregar Nodo");
        JButton unirNodoBtn = new JButton("Unir Nodo");
        JButton eliminarNodoBtn = new JButton("Eliminar Nodo");
        JButton eliminarArcoBtn = new JButton("Eliminar Arco"); // Nuevo botón
        JButton actualizarGrafoBtn = new JButton("Actualizar Grafo");

        // Añadir ActionListeners para los botones
        agregarNodoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreNodo = pedirNodo("Ingrese el nombre del nuevo nodo:");
                if (nombreNodo == null || nombreNodo.trim().isEmpty()) {
                    return; // Cancelled or empty input
                }

                if (grafo.numVertice(nombreNodo) != -1) {
                    JOptionPane.showMessageDialog(GrafoGUI.this, "El nombre del nodo ya existe.");
                    return;
                }

                grafo.nuevoVertice(nombreNodo);
                grafo.imprimirGrafo(); // Imprimir el estado del grafo
                actualizarInterfaz();
            }
            
        });

        unirNodoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nodoInicio = pedirNodo("Nodo Inicio:");
                if (nodoInicio == null) return;

                String nodoDestino = pedirNodo("Nodo destino:");
                if (nodoDestino == null) return;

                String pesoStr = pedirPeso("Peso:");
                if (pesoStr == null) return;

                int peso;
                try {
                    peso = Integer.parseInt(pesoStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GrafoGUI.this, "Peso inválido.");
                    return;
                }

                try {
                    grafo.nuevoArco(nodoInicio, nodoDestino, peso);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(GrafoGUI.this, ex.getMessage());
                }

                grafo.imprimirGrafo(); // Imprimir el estado del grafo
                actualizarInterfaz();
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
                grafo.imprimirGrafo(); // Imprimir el estado del grafo
                actualizarInterfaz();
            }
        });

        eliminarArcoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nodoInicio = pedirNodo("Nodo de Inicio:");
                if (nodoInicio == null) return;

                String nodoDestino = pedirNodo("Nodo final:");
                if (nodoDestino == null) return;

                try {
                    grafo.eliminarArco(nodoInicio, nodoDestino);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(GrafoGUI.this, ex.getMessage());
                }

                grafo.imprimirGrafo(); // Imprimir el estado del grafo
                actualizarInterfaz();
            }
        });

        actualizarGrafoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarInterfaz();
            }
        });

        botonesPanel.add(agregarNodoBtn);
        botonesPanel.add(unirNodoBtn);
        botonesPanel.add(eliminarNodoBtn);
        botonesPanel.add(eliminarArcoBtn); // Agregar el botón eliminar arco
        botonesPanel.add(actualizarGrafoBtn);

        // Añadir los paneles al JFrame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(grafoPanel);
        mainPanel.add(arbolPanel);

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

    private void actualizarInterfaz() {
        // Encontrar el árbol de expansión mínima usando el algoritmo de Prim
        ArbolExpansionMinimo prim = new ArbolExpansionMinimo(grafo);
        int costoTotal = prim.arbolExpansionPrim();
        arbolExpansionMinimo = prim.getAristasArbolExpansionMinimo();

        // Imprimir en consola las aristas del árbol de expansión mínima
        System.out.println("Aristas del árbol de expansión mínima:");
        for (int[] arista : arbolExpansionMinimo) {
            System.out.println("V" + arista[0] + " -> V" + arista[1]);
        }
        System.out.println("Costo total del árbol de expansión mínima: " + costoTotal);

        // Actualizar los paneles
        grafoPanel.setGrafo(grafo);
        arbolPanel.setArbolExpansionMinimo(arbolExpansionMinimo);
        grafoPanel.repaint();
        arbolPanel.repaint();
        System.out.println("Grafo representado con matriz de pesos:");
        grafo.imprimirGrafo();
    }

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

        // Imprimir en consola las aristas del árbol de expansión mínima
        System.out.println("Aristas del árbol de expansión mínima:");
        for (int[] arista : arbolExpansionMinimo) {
            System.out.println("V" + arista[0] + " -> V" + arista[1]);
        }
        System.out.println("Costo total del árbol de expansión mínima: " + costoTotal);

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
