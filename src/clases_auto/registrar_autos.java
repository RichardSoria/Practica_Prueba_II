package clases_auto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class registrar_autos {
    private JButton registrar_boton;
    private JTextField campo_placa;
    private JTextField campo_marca;
    private JTextField campo_cilindraje;
    private JTextField campo_combustible;
    private JTextField campo_color;
    private JTextField campo_propietario;
    public JPanel mainPanel;
    private JButton boton_limpiar;
    private JButton boton_regresar;

    String url = "jdbc:mysql://localhost:3306/consecionaria";
    String user = "root";
    String password = "123456";
    String sql = "INSERT INTO vehiculos (placa, marca, cilindraje, tipo_combustible, color, propietario) VALUES (?, ?, ?, ?, ?, ?)";

    public registrar_autos() {
        registrar_boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                autos auto = new autos();

                if (campo_placa .getText().isEmpty() || campo_marca.getText().isEmpty() || campo_cilindraje.getText().isEmpty()
                        || campo_combustible.getText().isEmpty() || campo_color.getText().isEmpty() || campo_propietario.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
                    return;
                }

                if (campo_placa.getText().length() != 8)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese una placa válida.");
                    return;
                }

                if (Double.parseDouble(campo_cilindraje.getText()) < 0)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese un cilindraje válido.");
                    return;
                }

                if (campo_cilindraje.getText().contains(","))
                {
                    campo_cilindraje.setText(campo_cilindraje.getText().replace(",", "."));
                }

                auto.setPlaca(campo_placa.getText().toUpperCase());
                auto.setMarca(campo_marca.getText().toUpperCase());
                auto.setCilindraje(Double.parseDouble(campo_cilindraje.getText()));
                auto.setTipo_combustible(campo_combustible.getText().toUpperCase());
                auto.setColor(campo_color.getText().toUpperCase());
                auto.setPropietario(campo_propietario.getText().toUpperCase());

                try
                {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, auto.getPlaca());
                    statement.setString(2, auto.getMarca());
                    statement.setDouble(3, auto.getCilindraje());
                    statement.setString(4, auto.getTipo_combustible());
                    statement.setString(5, auto.getColor());
                    statement.setString(6, auto.getPropietario());
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Auto registrado con éxito.");
                    connection.close();
                }
                catch (SQLException throwables)
                {
                    throwables.printStackTrace();
                }

            }
        });
        boton_limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campo_placa.setText("");
                campo_marca.setText("");
                campo_cilindraje.setText("");
                campo_combustible.setText("");
                campo_color.setText("");
                campo_propietario.setText("");
            }
        });
        boton_regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Autos");
                frame.setContentPane(new form_autos().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame registrar_autos = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                registrar_autos.dispose();
            }
        });
    }
}
