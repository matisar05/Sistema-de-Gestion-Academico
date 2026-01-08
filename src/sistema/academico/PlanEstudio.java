import java.util.*;

public class PlanEstudio {
    private TipoPlan tipo;
    private PlanState state;
    private List<Materia> materias = new ArrayList<>();

    public PlanEstudio(TipoPlan tipo) {
        this.tipo = tipo;
        this.state = tipo.createState();
    }

    public TipoPlan getTipo() {
        return tipo;
    }

    public PlanState getState() {
        return state;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void agregarMateria(Materia m) {
        materias.add(m);
    }

    public List<Materia> getMateriasObligatorias() {
        return materias.stream().filter(Materia::isObligatoria).toList();
    }

    public List<Materia> getMateriasOptativas() {
        return materias.stream().filter(m -> !m.isObligatoria()).toList();
    }

    public void setTipo(TipoPlan tipo) {
        this.tipo = tipo;
        this.state = tipo.createState();
    }

    public boolean puedeCursar(Alumno alumno, Materia materia) {
        return state.puedeCursar(alumno, materia);
    }

}
