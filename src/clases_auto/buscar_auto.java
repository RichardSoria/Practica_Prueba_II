package clases_auto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class buscar_auto {
    private JTextField campo_placa;
    private JButton buscar_vehiculo;
    public JPanel mainPanel;
    private JButton boton_regresar;

    String url = "jdbc:mysql://localhost:3306/consecionaria";
    String user = "root";
    String password = "123456";
    String sql = "SELECT * FROM vehiculos WHERE placa = ?";

    public buscar_auto() {
        buscar_vehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autos auto = new autos();

                if (campo_placa.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Por favor, llene el campo de placa.");
                    return;
                }

                if (campo_placa.getText().length() != 8)
                {
                    JOptionPane.showMessageDialog(null, "Ingrese una placa válida.");
                    return;
                }

                try
                {
                    Connection conexion = DriverManager.getConnection(url, user, password);
                    PreparedStatement sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, campo_placa.getText());
                    ResultSet resultado = sentencia.executeQuery();
                    campo_placa.setText("");

                    if(resultado.next())
                    {
                        String placa = resultado.getString("placa");
                        String marca = resultado.getString("marca");
                        Double cilindraje = resultado.getDouble("cilindraje");
                        String tipo_combustible = resultado.getString("tipo_combustible");
                        String color = resultado.getString("color");
                        String propietario = resultado.getString("propietario");

                        JOptionPane.showMessageDialog(null, "Vehículo encontrado" +
                                "\n" + "Placa: " + placa + "\n" + "Marca: " + marca + "\n" + "Cilindraje: " +
                                "" + cilindraje + "\n" + "Tipo de combustible: " + tipo_combustible + "\n" + "Color: " + color + "\n" + "Propietario: " + propietario);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "No se encontraron registros.");
                    }
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "Error al buscar el vehículo.");
                }
            }

        });
        boton_regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Formulario de autos");
                frame.setContentPane(new form_autos().mainPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                JFrame buscar_auto = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                buscar_auto.dispose();
            }
        });
    }
}
