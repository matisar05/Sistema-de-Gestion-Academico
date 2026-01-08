import java.util.List;

public class Carrera {
    private String nombre;
    private PlanEstudio planEstudio;
    private int optativasRequeridas;

    public Carrera(String nombre, PlanEstudio planEstudio, int optativasRequeridas) {
        this.nombre = nombre;
        this.planEstudio = planEstudio;
        this.optativasRequeridas = optativasRequeridas;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setOptativasRequeridas(int cantidad) {
        this.optativasRequeridas = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public PlanEstudio getPlanEstudio() {
        return planEstudio;
    }

    public int getOptativasRequeridas() {
        return optativasRequeridas;
    }

    public List<Materia> getMateriasObligatorias() {
        return planEstudio.getMaterias().stream()
                .filter(Materia::isObligatoria)
                .toList();
    }

    public List<Materia> getMateriasOptativas() {
        return planEstudio.getMaterias().stream()
                .filter(m -> !m.isObligatoria())
                .toList();
    }

    public int getCantidadOptativasRequeridas() {
        return optativasRequeridas;
    }
}
