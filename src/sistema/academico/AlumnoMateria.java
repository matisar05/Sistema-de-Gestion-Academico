public class AlumnoMateria {
    private Materia materia;
    private boolean parcialAprobado;
    private boolean finalAprobado;
    private boolean promovida;
    private int cuatrimestreCursado;

    public AlumnoMateria(Materia materia, int cuatrimestreCursado) {
        this.materia = materia;
        this.cuatrimestreCursado = cuatrimestreCursado;
        this.parcialAprobado = false;
        this.finalAprobado = false;
        this.promovida = false;
    }

    public Materia getMateria() {
        return materia;
    }

    public boolean isParcialAprobado() {
        return parcialAprobado;
    }

    public void aprobarParcial() {
        this.parcialAprobado = true;
    }

    public boolean isFinalAprobado() {
        return finalAprobado;
    }

    public void aprobarFinal() {
        this.finalAprobado = true;
    }

    public boolean isPromovida() {
        return promovida;
    }

    public void promocionar() {
        this.promovida = true;
        this.parcialAprobado = true;
        this.finalAprobado = true;
    }

    public int getCuatrimestreCursado() {
        return cuatrimestreCursado;
    }

    public boolean estaAprobada() {
        return finalAprobado || promovida;
    }

    public boolean cursadaAprobada() {
        return parcialAprobado || promovida;
    }
}