import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaEditarCarrera extends JFrame {

    private JComboBox<Carrera> comboCarreras;
    private JTextField campoNombre;
    private JComboBox<TipoPlan> comboTipo;
    private JTextField campoOptativas;

    private DefaultListModel<String> modeloMaterias = new DefaultListModel<>();
    private JList<String> listaMaterias = new JList<>(modeloMaterias);

    public VentanaEditarCarrera() {
        setTitle("Editar Carrera");
        setSize(700, 500);
        setLayout(new BorderLayout());

        comboCarreras = new JComboBox<>(DatosCompartidos.getCarreras().toArray(new Carrera[0]));
        comboCarreras.addActionListener(e -> cargarCarrera());

        JPanel panelSuperior = new JPanel();
        campoNombre = new JTextField(10);
        campoOptativas = new JTextField(3);
        comboTipo = new JComboBox<>(TipoPlan.values());

        panelSuperior.add(new JLabel("Carrera:"));
        panelSuperior.add(comboCarreras);
        panelSuperior.add(new JLabel("Nombre:"));
        panelSuperior.add(campoNombre);
        panelSuperior.add(new JLabel("Tipo:"));
        panelSuperior.add(comboTipo);
        panelSuperior.add(new JLabel("Optativas:"));
        panelSuperior.add(campoOptativas);

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());

        JButton btnEliminarMateria = new JButton("Eliminar Materia");
        btnEliminarMateria.addActionListener(e -> eliminarMateriaSeleccionada());

        JButton btnAgregarMateria = new JButton("Agregar Materia");
        btnAgregarMateria.addActionListener(e -> agregarNuevaMateria());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnAgregarMateria);
        panelBotones.add(btnEliminarMateria);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(listaMaterias), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        if (comboCarreras.getItemCount() > 0) {
            cargarCarrera();
        }
    }

    private void cargarCarrera() {
        Carrera c = (Carrera) comboCarreras.getSelectedItem();
        if (c == null)
            return;

        campoNombre.setText(c.getNombre());
        comboTipo.setSelectedItem(c.getPlanEstudio().getTipo());
        campoOptativas.setText(String.valueOf(c.getOptativasRequeridas()));

        modeloMaterias.clear();
        for (Materia m : c.getPlanEstudio().getMaterias()) {
            modeloMaterias.addElement(
                    m.getNombre() + " - C" + m.getCuatrimestre() +
                            (m.isObligatoria() ? " [Oblig.]" : " [Opt.]") +
                            (m.isTienePromocion() ? " [Promo]" : "") +
                            " Corr(" + m.getCorrelativas().size() + ")");
        }
    }

    private void guardarCambios() {
        Carrera c = (Carrera) comboCarreras.getSelectedItem();
        if (c == null)
            return;

        c.setNombre(campoNombre.getText().trim());
        c.setOptativasRequeridas(Integer.parseInt(campoOptativas.getText().trim()));
        c.getPlanEstudio().setTipo((TipoPlan) comboTipo.getSelectedItem());

        JOptionPane.showMessageDialog(this, "Carrera actualizada correctamente.");
        cargarCarrera();
    }

    private void eliminarMateriaSeleccionada() {
        Carrera c = (Carrera) comboCarreras.getSelectedItem();
        int index = listaMaterias.getSelectedIndex();
        if (c == null || index < 0)
            return;

        Materia materia = c.getPlanEstudio().getMaterias().get(index);
        c.getPlanEstudio().getMaterias().remove(materia);
        JOptionPane.showMessageDialog(this, "Materia eliminada.");
        cargarCarrera();
    }

    private void agregarNuevaMateria() {
        Carrera c = (Carrera) comboCarreras.getSelectedItem();
        if (c == null)
            return;

        JTextField campoNombre = new JTextField();
        JTextField campoCuatri = new JTextField();
        JCheckBox checkOblig = new JCheckBox("Obligatoria");
        JCheckBox checkPromo = new JCheckBox("Promocionable");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Cuatrimestre:"));
        panel.add(campoCuatri);
        panel.add(checkOblig);
        panel.add(checkPromo);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Materia", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = campoNombre.getText().trim();
                int cuatri = Integer.parseInt(campoCuatri.getText().trim());
                Materia m = new Materia(nombre, cuatri, checkOblig.isSelected(), checkPromo.isSelected());

                c.getPlanEstudio().agregarMateria(m);
                JOptionPane.showMessageDialog(this, "Materia agregada.");

                cargarCarrera();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar materia.");
            }
        }
    }
}
