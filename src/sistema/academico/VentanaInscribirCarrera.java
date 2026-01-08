import javax.swing.*;
import java.awt.*;

public class VentanaInscribirCarrera extends JFrame {

    public VentanaInscribirCarrera() {
        setTitle("Inscribir Alumno a Carrera");
        setSize(400, 200);
        setLayout(new BorderLayout());

        JComboBox<Alumno> comboAlumno = new JComboBox<>(DatosCompartidos.getAlumnos().toArray(new Alumno[0]));
        JComboBox<Carrera> comboCarrera = new JComboBox<>(DatosCompartidos.getCarreras().toArray(new Carrera[0]));
        JButton btnInscribir = new JButton("Inscribir");

        btnInscribir.addActionListener(e -> {
            Alumno a = (Alumno) comboAlumno.getSelectedItem();
            Carrera c = (Carrera) comboCarrera.getSelectedItem();
            if (a != null && c != null) {
                a.inscribirCarrera(c);
                JOptionPane.showMessageDialog(this, "Alumno inscripto a la carrera con éxito.");
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Alumno:"));
        panel.add(comboAlumno);
        panel.add(new JLabel("Carrera:"));
        panel.add(comboCarrera);
        panel.add(btnInscribir);

        add(panel, BorderLayout.CENTER);
    }
}
