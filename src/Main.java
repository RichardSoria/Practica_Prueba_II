import javax.swing.*;
import clases_auto.form_autos;

public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Sistema de gestión de autos");
        frame.setContentPane(new form_autos().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}