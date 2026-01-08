import javax.swing.*;
import java.awt.*;

public class MainApp {
    public static void main(String[] args) {
        MainJavaTest.main(null);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de Gestión Académica");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 620);
            frame.setLocationRelativeTo(null);

            JPanel mainPanel = new JPanel(new BorderLayout());
            UIStyles.stylePanelBackground(mainPanel);

            JPanel titlePanel = UIStyles.createTitlePanel("Sistema de Gestión Académica");

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            centerPanel.setBackground(UIStyles.BACKGROUND);
            centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));

            JLabel subtitleLabel = new JLabel("Seleccione una opción para comenzar");
            UIStyles.styleLabelSecondary(subtitleLabel);
            subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            centerPanel.add(subtitleLabel);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 35)));

            Dimension btnSize = new Dimension(420, 48);
            int spacing = 14;

            // Botones Material Design con bordes redondeados y sombras
            JButton btnAlumno = UIStyles.createMaterialButton("Alta de Alumno", UIStyles.SURFACE);
            btnAlumno.setForeground(UIStyles.TEXT_PRIMARY);

            JButton btnCarrera = UIStyles.createMaterialButton("Alta de Carrera", UIStyles.SURFACE);
            btnCarrera.setForeground(UIStyles.TEXT_PRIMARY);

            JButton btnEditarCarrera = UIStyles.createMaterialButton("Editar Carrera", UIStyles.WARNING);

            JButton btnCarreraAlumno = UIStyles.createMaterialButton("Inscribir Alumno a Carrera", UIStyles.SURFACE);
            btnCarreraAlumno.setForeground(UIStyles.TEXT_PRIMARY);

            JButton btnInscripcion = UIStyles.createMaterialButton("Gestión de Inscripciones", UIStyles.ACCENT);

            btnAlumno.setMaximumSize(btnSize);
            btnCarrera.setMaximumSize(btnSize);
            btnEditarCarrera.setMaximumSize(btnSize);
            btnCarreraAlumno.setMaximumSize(btnSize);
            btnInscripcion.setMaximumSize(btnSize);

            btnAlumno.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCarrera.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnEditarCarrera.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCarreraAlumno.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnInscripcion.setAlignmentX(Component.CENTER_ALIGNMENT);

            btnAlumno.addActionListener(e -> new VentanaAlumno().setVisible(true));
            btnCarrera.addActionListener(e -> new VentanaCarrera().setVisible(true));
            btnInscripcion.addActionListener(e -> new VentanaInscripcion().setVisible(true));
            btnCarreraAlumno.addActionListener(e -> new VentanaInscribirCarrera().setVisible(true));
            btnEditarCarrera.addActionListener(e -> new VentanaEditarCarrera().setVisible(true));

            centerPanel.add(btnAlumno);
            centerPanel.add(Box.createRigidArea(new Dimension(0, spacing)));
            centerPanel.add(btnCarrera);
            centerPanel.add(Box.createRigidArea(new Dimension(0, spacing)));
            centerPanel.add(btnEditarCarrera);
            centerPanel.add(Box.createRigidArea(new Dimension(0, spacing)));
            centerPanel.add(btnCarreraAlumno);
            centerPanel.add(Box.createRigidArea(new Dimension(0, spacing)));
            centerPanel.add(btnInscripcion);

            mainPanel.add(titlePanel, BorderLayout.NORTH);
            mainPanel.add(centerPanel, BorderLayout.CENTER);

            frame.setContentPane(mainPanel);
            frame.setVisible(true);
        });
    }
}
