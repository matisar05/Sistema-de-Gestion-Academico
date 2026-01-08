import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaAlumno extends JFrame {
    private static final List<Alumno> alumnos = DatosCompartidos.getAlumnos();
    private final DefaultListModel<String> modeloLista = new DefaultListModel<>();

    public VentanaAlumno() {
        setTitle("Alta de Alumno");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JTextField campoNombre = new JTextField(15);
        JTextField campoLegajo = new JTextField(10);
        JButton btnAgregar = new JButton("Agregar");
        JList<String> listaAlumnos = new JList<>(modeloLista);

        btnAgregar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String legajo = campoLegajo.getText().trim();

            if (!nombre.matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(this, "Nombre inválido. Solo letras.");
                return;
            }

            if (!legajo.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "No es posible dejar el legajo vacío.");
                return;
            }
   
            if (nombre.isEmpty() || legajo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y legajo no pueden estar vacíos.");
                return;
            }

            if (AlumnoRegistry.existeLegajo(legajo)) {
                JOptionPane.showMessageDialog(this, "Ya existe un alumno con ese legajo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
    try {
        Alumno nuevo = AlumnoRegistry.crearAlumno(nombre, legajo);
        DatosCompartidos.getAlumnos().add(nuevo);
        modeloLista.addElement(nuevo.toString());
        campoNombre.setText("");
        campoLegajo.setText("");
    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(campoNombre);
        inputPanel.add(new JLabel("Legajo:"));
        inputPanel.add(campoLegajo);
        inputPanel.add(btnAgregar);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(listaAlumnos), BorderLayout.CENTER);
    }
}

