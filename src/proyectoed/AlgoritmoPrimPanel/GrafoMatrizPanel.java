package proyectoed.AlgoritmoPrimPanel;

import javax.swing.*;

import proyectoed.NoDirigido.MatrizAdyacencia.GrafoMatriz;

import java.awt.*;

public class GrafoMatrizPanel extends JPanel {
    private GrafoMatriz grafo;

    public GrafoMatrizPanel(GrafoMatriz grafo) {
        this.grafo = grafo;
    }

    public void setGrafo(GrafoMatriz grafo) {
        this.grafo = grafo;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int numVerts = grafo.numeroDeVertices();
        int[][] matPeso = grafo.getMatPeso();

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

        // Dibujar aristas
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.LIGHT_GRAY);
        g2.setFont(new Font("Arial", Font.BOLD, 16)); // Tamaño de la fuente para los pesos
        
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                if (matPeso[i][j] != GrafoMatriz.getInfinito() && i != j) {
                    g2.drawLine(coords[i].x, coords[i].y, coords[j].x, coords[j].y);
                    int xMiddle = (coords[i].x + coords[j].x) / 2;
                    int yMiddle = (coords[i].y + coords[j].y) / 2;
                    g2.drawString(String.valueOf(matPeso[i][j]), xMiddle, yMiddle);
                }
            }
        }

        // Dibujar vértices
        g2.setColor(Color.BLUE);
        for (int i = 0; i < numVerts; i++) {
            g2.fillOval(coords[i].x - 20, coords[i].y - 20, 40, 40);
            g2.setColor(Color.WHITE);
            g2.drawString(grafo.getVerts()[i].nomVertice(), coords[i].x - 10, coords[i].y + 5);
            g2.setColor(Color.BLUE);
        }
    }
}
