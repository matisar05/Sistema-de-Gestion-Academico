import javax.swing.*;
import java.awt.*;

public class VentanaAlumno extends JFrame {
    private final DefaultListModel<String> modeloLista = new DefaultListModel<>();

    public VentanaAlumno() {
        setTitle("Alta de Alumno");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = UIStyles.createTitlePanel("Alta de Alumno");

        JPanel formPanel = new JPanel(new GridBagLayout());
        UIStyles.stylePanel(formPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNombre = new JLabel("Nombre completo:");
        JLabel lblLegajo = new JLabel("Legajo:");
        UIStyles.styleLabel(lblNombre);
        UIStyles.styleLabel(lblLegajo);

        JTextField campoNombre = new JTextField(20);
        JTextField campoLegajo = new JTextField(15);
        UIStyles.styleTextField(campoNombre);
        UIStyles.styleTextField(campoLegajo);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(campoNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(lblLegajo, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(campoLegajo, gbc);

        JButton btnAgregar = new JButton("Agregar Alumno");
        UIStyles.styleButtonSuccess(btnAgregar);
        btnAgregar.setPreferredSize(new Dimension(180, 35));

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(btnAgregar, gbc);

        JList<String> listaAlumnos = new JList<>(modeloLista);
        UIStyles.styleList(listaAlumnos);
        JScrollPane scrollPane = new JScrollPane(listaAlumnos);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(UIStyles.PRIMARY_LIGHT, 1),
                "Alumnos Registrados",
                0,
                0,
                UIStyles.FONT_LABEL,
                UIStyles.TEXT_SECONDARY));

        JPanel listPanel = new JPanel(new BorderLayout());
        UIStyles.stylePanel(listPanel);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String legajo = campoLegajo.getText().trim();

            if (!nombre.matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(this, "El nombre debe contener letras.", "Validación",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!legajo.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "El legajo debe ser numérico.", "Validación",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (nombre.isEmpty() || legajo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Validación",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (AlumnoRegistry.existeLegajo(legajo)) {
                JOptionPane.showMessageDialog(this, "Ya existe un alumno con ese legajo.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Alumno nuevo = AlumnoRegistry.crearAlumno(nombre, legajo);
                DatosCompartidos.getAlumnos().add(nuevo);
                modeloLista.addElement(nuevo.toString());
                campoNombre.setText("");
                campoLegajo.setText("");
                JOptionPane.showMessageDialog(this, "Alumno registrado exitosamente.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
    }
}
