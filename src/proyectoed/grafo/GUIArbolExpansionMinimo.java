package proyectoed.grafo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUIArbolExpansionMinimo extends JFrame {

    private GrafoMatriz<String> grafo;
    private JPanel panelDibujo;
    private ArrayList<Point> nodos;

    public GUIArbolExpansionMinimo() {
        super("Árbol de Expansión Mínima - Prim");

        grafo = new GrafoMatriz<>();
        nodos = new ArrayList<>();

        // Agregar manualmente el nodo base con coordenadas fijas
        nodos.add(new Point(50, 50)); // Coordenadas fijas para el nodo base
        grafo.nuevoVertice("Base");

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        panelDibujo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarGrafo(g);
            }

            private void dibujarGrafo(Graphics g) {
                g.setColor(Color.BLACK);

                // Dibujar la tubería principal desde el nodo base
                Point nodoBase = nodos.get(0); // Nodo "Base"
                for (int i = 1; i < nodos.size(); i++) {
                    Point nodo = nodos.get(i);
                    g.drawLine(nodoBase.x, nodoBase.y, nodo.x, nodo.y);
                }

                // Dibujar las aristas entre los nodos
                for (int i = 1; i < nodos.size(); i++) {
                    Point nodo = nodos.get(i);
                    for (int j = i + 1; j < nodos.size(); j++) {
                        Point otroNodo = nodos.get(j);
                        g.drawLine(nodo.x, nodo.y, otroNodo.x, otroNodo.y);
                    }
                }

                // Dibujar los nodos como círculos
                for (Point punto : nodos) {
                    g.fillOval(punto.x - 10, punto.y - 10, 20, 20);
                    g.drawString(Integer.toString(nodos.indexOf(punto)), punto.x - 5, punto.y - 15);
                }
            }
        };

        panelDibujo.setPreferredSize(new Dimension(600, 400));
        panelDibujo.setBackground(Color.WHITE);
        panelPrincipal.add(panelDibujo, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton botonAgregarNodo = new JButton("Agregar Nodo");
        botonAgregarNodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNodo();
            }
        });

        JButton botonEjecutarPrim = new JButton("Ejecutar Prim");
        botonEjecutarPrim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarPrim();
            }
        });

        panelBotones.add(botonAgregarNodo);
        panelBotones.add(botonEjecutarPrim);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    private void agregarNodo() {
        String nombreNodo = JOptionPane.showInputDialog(this, "Ingrese el nombre del nodo:");
        if (nombreNodo != null && !nombreNodo.isEmpty()) {
            JTextField coordXField = new JTextField(5);
            JTextField coordYField = new JTextField(5);
            JPanel panelCoordenadas = new JPanel();
            panelCoordenadas.add(new JLabel("Coordenada X:"));
            panelCoordenadas.add(coordXField);
            panelCoordenadas.add(Box.createHorizontalStrut(15)); // a spacer
            panelCoordenadas.add(new JLabel("Coordenada Y:"));
            panelCoordenadas.add(coordYField);

            int result = JOptionPane.showConfirmDialog(this, panelCoordenadas,
                    "Ingrese las coordenadas X e Y del nodo", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    int x = Integer.parseInt(coordXField.getText());
                    int y = Integer.parseInt(coordYField.getText());
                    Point punto = new Point(x, y);
                    nodos.add(punto);
                    grafo.nuevoVertice(nombreNodo);
                    panelDibujo.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Las coordenadas deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void ejecutarPrim() {
        try {
            GrafMatPeso gp = new GrafMatPeso(grafo.getNumVerts());

            // Establecer los pesos en la matriz de pesos según las aristas del grafo
            for (int i = 0; i < grafo.getNumVerts(); i++) {
                for (int j = i + 1; j < grafo.getNumVerts(); j++) {
                    if (grafo.adyacente(i, j)) {
                        // Establecer el peso de la arista como 1 para simplificar
                        gp.setPeso(i, j, 1);
                    }
                }
            }

            ArbolExpansionMinimo aem = new ArbolExpansionMinimo(gp);
            int pesoArbol = Math.abs(aem.arbolExpansionPrim()); // Tomar el valor absoluto del peso

            System.out.println("Peso del árbol de expansión mínima: " + pesoArbol);
            System.out.println("Mejor recorrido (por consola):");
            aem.arbolExpansionPrim(); // Mostrar por consola el recorrido

            JOptionPane.showMessageDialog(this, "Árbol de Expansión Mínima (Prim)\nPeso total: " + pesoArbol,
                    "Resultado Prim", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUIArbolExpansionMinimo();
            }
        });
    }
}
