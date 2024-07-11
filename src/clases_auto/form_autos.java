package clases_auto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form_autos
{

    private JButton boton_registrar;
    private JButton boton_buscar;
    public JPanel mainPanel;

    public form_autos() {
        boton_registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("Registrar auto");
                frame.setContentPane(new registrar_autos().mainPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame form_autos = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                form_autos.dispose();

            }
        });
        boton_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        boton_buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    JFrame frame = new JFrame("Buscar auto");
                    frame.setContentPane(new buscar_auto().mainPanel);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    JFrame form_autos = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                    form_autos.dispose();
            }
        });
    }
}
