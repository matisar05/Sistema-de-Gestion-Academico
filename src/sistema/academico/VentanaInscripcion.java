import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaInscripcion extends JFrame {

    private final JComboBox<Alumno> comboAlumno;
    private final DefaultListModel<String> modeloMateriasDisponibles = new DefaultListModel<>();
    private final DefaultListModel<String> modeloMateriasCursadas = new DefaultListModel<>();
    private final JList<String> listaMateriasDisponibles = new JList<>(modeloMateriasDisponibles);
    private final JList<String> listaMateriasCursadas = new JList<>(modeloMateriasCursadas);

    public VentanaInscripcion() {
        setTitle("Inscripción de Alumno a Materias");
        setSize(700, 500);
        setLayout(new BorderLayout());

        comboAlumno = new JComboBox<>(DatosCompartidos.getAlumnos().toArray(new Alumno[0]));
        JButton btnActualizar = new JButton("Ver Materias Disponibles");

        JPanel panelAlumno = new JPanel();
        panelAlumno.add(new JLabel("Seleccionar Alumno:"));
        panelAlumno.add(comboAlumno);
        panelAlumno.add(btnActualizar);

        JPanel panelListas = new JPanel(new GridLayout(1, 2));

        JPanel panelDisponibles = new JPanel(new BorderLayout());
        panelDisponibles.add(new JLabel("Materias disponibles"), BorderLayout.NORTH);
        panelDisponibles.add(new JScrollPane(listaMateriasDisponibles), BorderLayout.CENTER);
        JButton btnInscribir = new JButton("Inscribir");
        panelDisponibles.add(btnInscribir, BorderLayout.SOUTH);

        JPanel panelCursadas = new JPanel();
        panelCursadas.setLayout(new BoxLayout(panelCursadas, BoxLayout.Y_AXIS));
        panelCursadas.add(new JScrollPane(listaMateriasCursadas));
        JButton btnAprobar = new JButton("Aprobar Final");
        JButton btnPromocionar = new JButton("Promocionar");
        JButton btnAprobarCursada = new JButton("Aprobar Cursada");
        panelCursadas.add(btnPromocionar);
        panelCursadas.add(btnAprobarCursada);
        panelCursadas.add(btnAprobar);

        panelListas.add(panelDisponibles);
        panelListas.add(panelCursadas);

        JButton btnVerificar = new JButton("¿Finalizó la carrera?");
        JLabel lblResultado = new JLabel(" ");

        btnActualizar.addActionListener(e -> actualizarListas());

        btnPromocionar.addActionListener(e -> {
            Alumno a = (Alumno) comboAlumno.getSelectedItem();
            int index = listaMateriasCursadas.getSelectedIndex();
            if (a == null || index < 0) return;

            AlumnoMateria am = a.getMaterias().get(index);
            Materia m = am.getMateria();

            if (!m.isTienePromocion()) {
                JOptionPane.showMessageDialog(this, "La materia no cuenta con régimen de promoción.");
                return;
            }

            try {
                String nota1Str = JOptionPane.showInputDialog(this, "Nota 1° parcial:");
                String nota2Str = JOptionPane.showInputDialog(this, "Nota 2° parcial:");
                if (nota1Str == null || nota2Str == null) return;

                double nota1 = Double.parseDouble(nota1Str);
                double nota2 = Double.parseDouble(nota2Str);

                if (nota1 < 1 || nota1 > 10 || nota2 < 1 || nota2 > 10) {
                    JOptionPane.showMessageDialog(this, "Las notas deben estar entre 1 y 10.");
                    return;
                }

                double promedio = (nota1 + nota2) / 2.0;

                if (promedio >= 7.0) {
                    am.promocionar();
                    JOptionPane.showMessageDialog(this, "¡Materia promocionada con promedio: " + promedio + "!");
                } else {
                    am.aprobarParcial();
                    JOptionPane.showMessageDialog(this, "Promedio insuficiente (" + promedio + "). Podés rendir final.");
                }

                actualizarListas();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Notas inválidas.");
            }
        });

        btnInscribir.addActionListener(e -> {
            Alumno a = (Alumno) comboAlumno.getSelectedItem();
            int index = listaMateriasDisponibles.getSelectedIndex();
            if (a == null || index < 0) return;

            List<Materia> disponibles = DatosCompartidos.getControlador().materiasDisponiblesParaCursar(a);
            if (index >= disponibles.size()) return;

            Materia m = disponibles.get(index);

            boolean yaInscripto = a.getMaterias().stream()
                .anyMatch(am -> am.getMateria().equals(m));

            if (yaInscripto) {
                JOptionPane.showMessageDialog(this, "El alumno ya está inscripto en esta materia.");
                return;
            }

            boolean exito = DatosCompartidos.getControlador().inscribirEnMateria(a, m);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Inscripción exitosa a " + m.getNombre());
                actualizarListas();
            } else {
                JOptionPane.showMessageDialog(this, "No cumple con las correlativas.");
            }
        });

        btnAprobar.addActionListener(e -> {
            Alumno a = (Alumno) comboAlumno.getSelectedItem();
            int index = listaMateriasCursadas.getSelectedIndex();
            if (a != null && index >= 0) {
                AlumnoMateria am = a.getMaterias().get(index);
                if (!am.isParcialAprobado()) {
                    JOptionPane.showMessageDialog(this, "El alumno aún no aprobó la cursada. No puede rendir el final.");
                    return;
                }
                am.aprobarFinal();
                actualizarListas();
            }
        });

        btnVerificar.addActionListener(e -> {
            Alumno a = (Alumno) comboAlumno.getSelectedItem();
            if (a != null) {
                boolean termino = DatosCompartidos.getControlador().alumnoTerminoCarrera(a);
                lblResultado.setText(termino ? "✅ Alumno finalizó la carrera." : "❌ Aún no finalizó.");
            }
        });

        add(panelAlumno, BorderLayout.NORTH);
        add(panelListas, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(btnVerificar, BorderLayout.NORTH);
        panelInferior.add(lblResultado, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);

        btnAprobarCursada.addActionListener(e -> {
            Alumno a = (Alumno) comboAlumno.getSelectedItem();
            int index = listaMateriasCursadas.getSelectedIndex();

            if (a == null || index < 0) return;

            AlumnoMateria am = a.getMaterias().get(index);

            if (am.isParcialAprobado()) {
                JOptionPane.showMessageDialog(this, "La cursada ya fue aprobada.");
                return;
            }

            am.aprobarParcial();
            JOptionPane.showMessageDialog(this, "Cursada aprobada. El alumno ahora puede rendir el final.");
            actualizarListas();
        });
    }

    private void actualizarListas() {
        Alumno a = (Alumno) comboAlumno.getSelectedItem();
        if (a == null) return;

        if (a.getCarrera() == null) {
            JOptionPane.showMessageDialog(this,
                "El alumno no está inscripto en ninguna carrera.",
                "Carrera no asignada",
                JOptionPane.WARNING_MESSAGE);
            modeloMateriasDisponibles.clear();
            modeloMateriasCursadas.clear();
            return;
        }

        modeloMateriasDisponibles.clear();
        modeloMateriasCursadas.clear();

        List<Materia> disponibles = DatosCompartidos.getControlador().materiasDisponiblesParaCursar(a);
        disponibles.forEach(m -> modeloMateriasDisponibles.addElement(m.getNombre() + " - C" + m.getCuatrimestre()));

        a.getMaterias().forEach(am -> {
            String estado = am.isPromovida() ? "[Promocionado]" :
                am.isFinalAprobado() ? "[Aprobado con final]" :
                am.isParcialAprobado() ? "[Cursada aprobada]" : "[En Curso]";

            modeloMateriasCursadas.addElement(am.getMateria().getNombre() + " " + estado);
        });
    }
}
