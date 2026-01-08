import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        MainJavaTest.main(null);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema Académico");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
            
            JButton btnEditarCarrera = new JButton("Editar Carrera");
            JButton btnAlumno = new JButton("Alta de Alumno");
            JButton btnCarrera = new JButton("Alta de Carrera");
            JButton btnInscripcion = new JButton("Inscripción");
            JButton btnCarreraAlumno = new JButton("Inscribir Alumno a Carrera");
            
            btnAlumno.addActionListener(e -> new VentanaAlumno().setVisible(true));
            btnCarrera.addActionListener(e -> new VentanaCarrera().setVisible(true));
            btnInscripcion.addActionListener(e -> new VentanaInscripcion().setVisible(true));
            btnCarreraAlumno.addActionListener(e -> new VentanaInscribirCarrera().setVisible(true));
            btnEditarCarrera.addActionListener(e -> new VentanaEditarCarrera().setVisible(true));
            
            frame.add(btnEditarCarrera);
            frame.add(btnCarreraAlumno);
            frame.add(btnAlumno);
            frame.add(btnCarrera);
            frame.add(btnInscripcion);
            frame.setVisible(true);
        });
    }
}
