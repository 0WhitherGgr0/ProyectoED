package proyectoed.AlgoritmoPrimPanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ResultadoPanel extends JPanel {
    private JTextArea textArea;

    public ResultadoPanel() {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void actualizarResultados(String resultados) {
        textArea.append(resultados+"\n");
    }
}
