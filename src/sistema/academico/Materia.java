import java.util.*;

public class Materia {
    private String nombre;
    private int cuatrimestre;
    private boolean esObligatoria;
    private boolean tienePromocion;
    private List<Materia> correlativas = new ArrayList<>();

    public Materia(String nombre, int cuatrimestre, boolean esObligatoria, boolean tienePromocion) {
        this.nombre = nombre;
        this.cuatrimestre = cuatrimestre;
        this.esObligatoria = esObligatoria;
        this.tienePromocion = tienePromocion;
    }

    public String getNombre() { return nombre; }
    public int getCuatrimestre() { return cuatrimestre; }
    public boolean isObligatoria() { return esObligatoria; }
    public boolean isTienePromocion() { return tienePromocion; }
    public List<Materia> getCorrelativas() { return correlativas; }

    public void agregarCorrelativa(Materia m) {
        correlativas.add(m);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
