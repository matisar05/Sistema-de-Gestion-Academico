import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Alumno {
    private String nombre;
    private Carrera carrera;
    private String  legajo;
    private List<AlumnoMateria> materias = new ArrayList<>();

    public Alumno(String nombre, String legajo) {
        this.nombre = nombre;
        this.legajo = legajo;
    }

    public String getNombre() {
    return nombre;
}

    public String getLegajo() {
        return legajo;
    }
    
    public void inscribirCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void agregarMateria(AlumnoMateria am) {
        materias.add(am);
    }

    public List<AlumnoMateria> getMaterias() {
        return materias;
    }

    public boolean estaInscriptoEnCarrera() {
        return carrera != null;
    }

    public List<Materia> materiasAprobadas() {
        return materias.stream()
                .filter(AlumnoMateria::estaAprobada)
                .map(AlumnoMateria::getMateria)
                .collect(Collectors.toList());
    }

    public List<Materia> materiasCursadas() {
        return materias.stream()
                .filter(AlumnoMateria::cursadaAprobada)
                .map(AlumnoMateria::getMateria)
                .collect(Collectors.toList());
    }

    public boolean aproboFinalDe(Materia m) {
        return materias.stream().anyMatch(am ->
                am.getMateria().equals(m) && (am.isFinalAprobado() || am.isPromovida()));
    }

    public boolean aproboCursadaDe(Materia m) {
        return materias.stream().anyMatch(am ->
                am.getMateria().equals(m) && am.cursadaAprobada());
    }

    public AlumnoMateria buscarCursada(Materia m) {
        return materias.stream()
                .filter(am -> am.getMateria().equals(m))
                .findFirst().orElse(null);
    }

    public void registrarCursada(Materia materia, boolean parcial, boolean finalAprobado) {
        AlumnoMateria am = new AlumnoMateria(materia, materia.getCuatrimestre());
        if (parcial) am.aprobarParcial();
        if (finalAprobado) am.aprobarFinal();
        materias.add(am);
    }

    @Override
    public String toString() {
         return nombre + " (" + legajo + ")";
    }
}
