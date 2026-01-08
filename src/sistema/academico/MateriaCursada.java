public class MateriaCursada {
    private Materia materia;
    private boolean parcialAprobado;
    private boolean finalAprobado;
    private boolean promocionada;

    public MateriaCursada(Materia materia) {
        this.materia = materia;
    }

    public void promocionar() {
        this.promocionada = true;
        this.parcialAprobado = true;
        this.finalAprobado = true;
    }

    public boolean isPromocionada() {
        return promocionada;
    }

    public Materia getMateria() {
        return materia;
    }

    public boolean isParcialAprobado() {
        return parcialAprobado;
    }

    public boolean isFinalAprobado() {
        return finalAprobado;
    }

    public void aprobarParcial() {
        parcialAprobado = true;
    }

    public void aprobarFinal() {
        finalAprobado = true;
    }
}
