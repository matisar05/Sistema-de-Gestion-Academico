import javax.swing.*;
import java.awt.*;

public class VentanaInscribirCarrera extends JFrame {

    public VentanaInscribirCarrera() {
        setTitle("Inscribir Alumno a Carrera");
        setSize(550, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = UIStyles.createTitlePanel("Inscribir Alumno a Carrera");

        JPanel formPanel = new JPanel(new GridBagLayout());
        UIStyles.stylePanel(formPanel);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblAlumno = new JLabel("Seleccionar Alumno:");
        JLabel lblCarrera = new JLabel("Seleccionar Carrera:");
        UIStyles.styleLabel(lblAlumno);
        UIStyles.styleLabel(lblCarrera);

        JComboBox<Alumno> comboAlumno = new JComboBox<>(DatosCompartidos.getAlumnos().toArray(new Alumno[0]));
        JComboBox<Carrera> comboCarrera = new JComboBox<>(DatosCompartidos.getCarreras().toArray(new Carrera[0]));
        UIStyles.styleComboBox(comboAlumno);
        UIStyles.styleComboBox(comboCarrera);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(lblAlumno, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(comboAlumno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(lblCarrera, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        formPanel.add(comboCarrera, gbc);

        JButton btnInscribir = new JButton("Inscribir");
        UIStyles.styleButtonSuccess(btnInscribir);
        btnInscribir.setPreferredSize(new Dimension(150, 40));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(25, 0, 0, 0);
        formPanel.add(btnInscribir, gbc);

        btnInscribir.addActionListener(e -> {
            Alumno a = (Alumno) comboAlumno.getSelectedItem();
            Carrera c = (Carrera) comboCarrera.getSelectedItem();
            if (a != null && c != null) {
                a.inscribirCarrera(c);
                JOptionPane.showMessageDialog(this,
                        "Alumno inscripto exitosamente a la carrera:\n" + c.getNombre(),
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Debe seleccionar un alumno y una carrera.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }
}
