import java.util.*;

public class DatosCompartidos {
    private static final List<Alumno> alumnos = new ArrayList<>();
    private static final List<Carrera> carreras = new ArrayList<>();
    private static final ControladorInscripciones controlador = new ControladorInscripciones();

    public static List<Alumno> getAlumnos() {
        return alumnos;
    }

    public static List<Carrera> getCarreras() {
        return carreras;
    }

    public static ControladorInscripciones getControlador() {
        return controlador;
    }
}

