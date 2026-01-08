import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaInscripcion extends JFrame {

    private JComboBox<Alumno> comboAlumno;
    private final DefaultListModel<String> modeloMateriasDisponibles = new DefaultListModel<>();
    private final DefaultListModel<String> modeloMateriasCursadas = new DefaultListModel<>();
    private final JList<String> listaMateriasDisponibles = new JList<>(modeloMateriasDisponibles);
    private final JList<String> listaMateriasCursadas = new JList<>(modeloMateriasCursadas);
    private JLabel lblResultado;

    public VentanaInscripcion() {
        setTitle("Gestión de Inscripciones");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = UIStyles.createTitlePanel("Gestión de Inscripciones");

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        UIStyles.stylePanelBackground(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelAlumno = createPanelAlumno();
        JPanel panelListas = createPanelListas();
        JPanel panelInferior = createPanelInferior();

        mainPanel.add(panelAlumno, BorderLayout.NORTH);
        mainPanel.add(panelListas, BorderLayout.CENTER);
        mainPanel.add(panelInferior, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createPanelAlumno() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        UIStyles.stylePanel(panel);

        JLabel lblAlumno = new JLabel("Seleccionar Alumno:");
        UIStyles.styleLabel(lblAlumno);

        comboAlumno = new JComboBox<>(DatosCompartidos.getAlumnos().toArray(new Alumno[0]));
        UIStyles.styleComboBox(comboAlumno);
        comboAlumno.setPreferredSize(new Dimension(300, 30));

        JButton btnActualizar = new JButton("Ver Materias Disponibles");
        UIStyles.styleButton(btnActualizar);
        btnActualizar.addActionListener(e -> actualizarListas());

        panel.add(lblAlumno);
        panel.add(comboAlumno);
        panel.add(btnActualizar);

        return panel;
    }

    private JPanel createPanelListas() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 0));
        panel.setBackground(UIStyles.BACKGROUND);

        JPanel panelDisponibles = createPanelDisponibles();
        JPanel panelCursadas = createPanelCursadas();

        panel.add(panelDisponibles);
        panel.add(panelCursadas);

        return panel;
    }

    private JPanel createPanelDisponibles() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        UIStyles.stylePanel(panel);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(UIStyles.SUCCESS, 2),
                "Materias Disponibles para Cursar",
                0, 0, UIStyles.FONT_LABEL, UIStyles.SUCCESS));

        UIStyles.styleList(listaMateriasDisponibles);
        JScrollPane scroll = new JScrollPane(listaMateriasDisponibles);

        JButton btnInscribir = new JButton("Inscribir en Materia");
        UIStyles.styleButtonSuccess(btnInscribir);
        btnInscribir.setPreferredSize(new Dimension(0, 40));
        btnInscribir.addActionListener(e -> inscribirEnMateria());

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnInscribir, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPanelCursadas() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        UIStyles.stylePanel(panel);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(UIStyles.PRIMARY, 2),
                "Materias Cursadas",
                0, 0, UIStyles.FONT_LABEL, UIStyles.PRIMARY));

        UIStyles.styleList(listaMateriasCursadas);
        JScrollPane scroll = new JScrollPane(listaMateriasCursadas);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 5, 5));
        panelBotones.setBackground(UIStyles.SURFACE);

        JButton btnPromocionar = new JButton("Promocionar");
        JButton btnAprobarCursada = new JButton("Aprobar Cursada");
        JButton btnAprobarFinal = new JButton("Aprobar Final");

        UIStyles.styleButtonSuccess(btnPromocionar);
        UIStyles.styleButton(btnAprobarCursada);
        UIStyles.styleButtonAccent(btnAprobarFinal);

        btnPromocionar.addActionListener(e -> promocionar());
        btnAprobarCursada.addActionListener(e -> aprobarCursada());
        btnAprobarFinal.addActionListener(e -> aprobarFinal());

        panelBotones.add(btnPromocionar);
        panelBotones.add(btnAprobarCursada);
        panelBotones.add(btnAprobarFinal);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPanelInferior() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        UIStyles.stylePanel(panel);

        JButton btnVerificar = new JButton("Verificar si Finalizó la Carrera");
        UIStyles.styleButton(btnVerificar);
        btnVerificar.setPreferredSize(new Dimension(250, 35));
        btnVerificar.addActionListener(e -> verificarGraduacion());

        lblResultado = new JLabel(" ", SwingConstants.CENTER);
        UIStyles.styleLabel(lblResultado);
        lblResultado.setFont(UIStyles.FONT_TITLE);

        JPanel wrapperBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapperBtn.setBackground(UIStyles.SURFACE);
        wrapperBtn.add(btnVerificar);

        panel.add(wrapperBtn, BorderLayout.NORTH);
        panel.add(lblResultado, BorderLayout.CENTER);

        return panel;
    }

    private void inscribirEnMateria() {
        Alumno a = (Alumno) comboAlumno.getSelectedItem();
        int index = listaMateriasDisponibles.getSelectedIndex();

        if (a == null || index < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un alumno y una materia.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Materia> disponibles = DatosCompartidos.getControlador().materiasDisponiblesParaCursar(a);
        if (index >= disponibles.size())
            return;

        Materia m = disponibles.get(index);

        boolean yaInscripto = a.getMaterias().stream()
                .anyMatch(am -> am.getMateria().equals(m));

        if (yaInscripto) {
            JOptionPane.showMessageDialog(this, "El alumno ya está inscripto en esta materia.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = DatosCompartidos.getControlador().inscribirEnMateria(a, m);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Inscripción exitosa a: " + m.getNombre(), "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            actualizarListas();
        } else {
            JOptionPane.showMessageDialog(this, "No cumple con las correlativas requeridas.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void promocionar() {
        Alumno a = (Alumno) comboAlumno.getSelectedItem();
        int index = listaMateriasCursadas.getSelectedIndex();

        if (a == null || index < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una materia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AlumnoMateria am = a.getMaterias().get(index);
        Materia m = am.getMateria();

        if (!m.isTienePromocion()) {
            JOptionPane.showMessageDialog(this, "La materia no cuenta con régimen de promoción.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String nota1Str = JOptionPane.showInputDialog(this, "Ingrese nota del 1° parcial:");
            String nota2Str = JOptionPane.showInputDialog(this, "Ingrese nota del 2° parcial:");
            if (nota1Str == null || nota2Str == null)
                return;

            double nota1 = Double.parseDouble(nota1Str);
            double nota2 = Double.parseDouble(nota2Str);

            if (nota1 < 1 || nota1 > 10 || nota2 < 1 || nota2 > 10) {
                JOptionPane.showMessageDialog(this, "Las notas deben estar entre 1 y 10.", "Validación",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            double promedio = (nota1 + nota2) / 2.0;

            if (promedio >= 7.0) {
                am.promocionar();
                JOptionPane.showMessageDialog(this,
                        "¡Materia promocionada con promedio: " + String.format("%.2f", promedio) + "!", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                am.aprobarParcial();
                JOptionPane.showMessageDialog(this, "Promedio insuficiente (" + String.format("%.2f", promedio)
                        + "). El alumno puede rendir final.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

            actualizarListas();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Notas inválidas. Debe ingresar números.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aprobarCursada() {
        Alumno a = (Alumno) comboAlumno.getSelectedItem();
        int index = listaMateriasCursadas.getSelectedIndex();

        if (a == null || index < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una materia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AlumnoMateria am = a.getMaterias().get(index);

        if (am.isParcialAprobado()) {
            JOptionPane.showMessageDialog(this, "La cursada ya fue aprobada.", "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        am.aprobarParcial();
        JOptionPane.showMessageDialog(this, "Cursada aprobada. El alumno puede rendir el final.", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        actualizarListas();
    }

    private void aprobarFinal() {
        Alumno a = (Alumno) comboAlumno.getSelectedItem();
        int index = listaMateriasCursadas.getSelectedIndex();

        if (a != null && index >= 0) {
            AlumnoMateria am = a.getMaterias().get(index);

            if (!am.isParcialAprobado()) {
                JOptionPane.showMessageDialog(this, "El alumno aún no aprobó la cursada. No puede rendir el final.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            am.aprobarFinal();
            JOptionPane.showMessageDialog(this, "Final aprobado exitosamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            actualizarListas();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una materia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void verificarGraduacion() {
        Alumno a = (Alumno) comboAlumno.getSelectedItem();

        if (a != null) {
            boolean termino = DatosCompartidos.getControlador().alumnoTerminoCarrera(a);
            if (termino) {
                lblResultado.setText("¡Felicitaciones! El alumno finalizó la carrera.");
                lblResultado.setForeground(UIStyles.SUCCESS);
            } else {
                lblResultado.setText("El alumno aún no ha completado la carrera.");
                lblResultado.setForeground(UIStyles.ERROR);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un alumno.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarListas() {
        Alumno a = (Alumno) comboAlumno.getSelectedItem();
        if (a == null)
            return;

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
            String estado = am.isPromovida() ? "[Promocionado]"
                    : am.isFinalAprobado() ? "[Aprobado con Final]"
                            : am.isParcialAprobado() ? "[Cursada Aprobada]" : "[En Curso]";

            modeloMateriasCursadas.addElement(am.getMateria().getNombre() + " " + estado);
        });

        lblResultado.setText(" ");
    }
}
