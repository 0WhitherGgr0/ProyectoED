package proyectoed.AlgoritmoPrimPanel;

import javax.swing.*;

import proyectoed.NoDirigido.MatrizAdyacencia.GrafoMatriz;

import java.awt.*;
import java.util.List;

public class ArbolExpansionMinimoPanel extends JPanel {
    private GrafoMatriz grafo;
    private List<int[]> arbolExpansionMinimo;

    public ArbolExpansionMinimoPanel(GrafoMatriz grafo, List<int[]> arbolExpansionMinimo) {
        this.grafo = grafo;
        this.arbolExpansionMinimo = arbolExpansionMinimo;
    }

    public void setArbolExpansionMinimo(List<int[]> arbolExpansionMinimo) {
        this.arbolExpansionMinimo = arbolExpansionMinimo;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int numVerts = grafo.numeroDeVertices();

        // Coordenadas de los vértices (en un círculo)
        Point[] coords = new Point[numVerts];
        int radius = 200;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        for (int i = 0; i < numVerts; i++) {
            double angle = 2 * Math.PI * i / numVerts;
            coords[i] = new Point((int) (centerX + radius * Math.cos(angle)),
                    (int) (centerY + radius * Math.sin(angle)));
        }

        // Dibujar aristas del árbol de expansión mínima
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.RED);
        g2.setFont(new Font("Arial", Font.BOLD, 16)); // Tamaño de la fuente para los nombres de los vértices

        for (int[] arista : arbolExpansionMinimo) {
            int i = arista[0];
            int j = arista[1];
            g2.drawLine(coords[i].x, coords[i].y, coords[j].x, coords[j].y);
        }

        // Dibujar vértices
        g2.setColor(Color.GREEN);
        for (int i = 0; i < numVerts; i++) {
            g2.fillOval(coords[i].x - 20, coords[i].y - 20, 40, 40);
            g2.setColor(Color.WHITE);
            g2.drawString(grafo.getVerts()[i].nomVertice(), coords[i].x - 10, coords[i].y + 5);
            g2.setColor(Color.GREEN);
        }
    }
}
