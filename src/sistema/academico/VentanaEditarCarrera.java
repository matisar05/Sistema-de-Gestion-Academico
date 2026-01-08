import javax.swing.*;
import java.awt.*;

public class VentanaEditarCarrera extends JFrame {

    private JComboBox<Carrera> comboCarreras;
    private JTextField campoNombre;
    private JComboBox<TipoPlan> comboTipo;
    private JTextField campoOptativas;
    private DefaultListModel<String> modeloMaterias = new DefaultListModel<>();
    private JList<String> listaMaterias = new JList<>(modeloMaterias);

    public VentanaEditarCarrera() {
        setTitle("Editar Carrera");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = UIStyles.createTitlePanel("Editar Carrera");

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        UIStyles.stylePanelBackground(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelSuperior = createPanelSuperior();
        JPanel panelCentro = createPanelCentro();
        JPanel panelBotones = createPanelBotones();

        mainPanel.add(panelSuperior, BorderLayout.NORTH);
        mainPanel.add(panelCentro, BorderLayout.CENTER);
        mainPanel.add(panelBotones, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        if (comboCarreras.getItemCount() > 0) {
            cargarCarrera();
        }
    }

    private JPanel createPanelSuperior() {
        JPanel panel = new JPanel(new GridBagLayout());
        UIStyles.stylePanel(panel);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(UIStyles.PRIMARY_LIGHT, 1),
                "Seleccionar Carrera",
                0, 0, UIStyles.FONT_LABEL, UIStyles.TEXT_SECONDARY));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        comboCarreras = new JComboBox<>(DatosCompartidos.getCarreras().toArray(new Carrera[0]));
        comboCarreras.addActionListener(e -> cargarCarrera());
        UIStyles.styleComboBox(comboCarreras);

        campoNombre = new JTextField(15);
        campoOptativas = new JTextField(5);
        comboTipo = new JComboBox<>(TipoPlan.values());
        UIStyles.styleTextField(campoNombre);
        UIStyles.styleTextField(campoOptativas);
        UIStyles.styleComboBox(comboTipo);

        JLabel lblCarrera = new JLabel("Carrera:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblTipo = new JLabel("Tipo de Plan:");
        JLabel lblOptativas = new JLabel("Optativas:");
        UIStyles.styleLabel(lblCarrera);
        UIStyles.styleLabel(lblNombre);
        UIStyles.styleLabel(lblTipo);
        UIStyles.styleLabel(lblOptativas);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(lblCarrera, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        panel.add(comboCarreras, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panel.add(lblNombre, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.4;
        panel.add(campoNombre, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(lblTipo, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.2;
        panel.add(comboTipo, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        panel.add(lblOptativas, gbc);
        gbc.gridx = 5;
        gbc.weightx = 0.1;
        panel.add(campoOptativas, gbc);

        return panel;
    }

    private JPanel createPanelCentro() {
        JPanel panel = new JPanel(new BorderLayout());
        UIStyles.stylePanel(panel);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(UIStyles.PRIMARY_LIGHT, 1),
                "Materias del Plan",
                0, 0, UIStyles.FONT_LABEL, UIStyles.TEXT_SECONDARY));

        UIStyles.styleList(listaMaterias);
        panel.add(new JScrollPane(listaMaterias), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        UIStyles.stylePanel(panel);

        JButton btnAgregarMateria = new JButton("Agregar Materia");
        JButton btnEliminarMateria = new JButton("Eliminar Materia");
        JButton btnGuardar = new JButton("Guardar Cambios");

        UIStyles.styleButton(btnAgregarMateria);
        UIStyles.styleButtonAccent(btnEliminarMateria);
        UIStyles.styleButtonSuccess(btnGuardar);

        btnAgregarMateria.setPreferredSize(new Dimension(160, 35));
        btnEliminarMateria.setPreferredSize(new Dimension(160, 35));
        btnGuardar.setPreferredSize(new Dimension(160, 35));

        btnAgregarMateria.addActionListener(e -> agregarNuevaMateria());
        btnEliminarMateria.addActionListener(e -> eliminarMateriaSeleccionada());
        btnGuardar.addActionListener(e -> guardarCambios());

        panel.add(btnAgregarMateria);
        panel.add(btnEliminarMateria);
        panel.add(btnGuardar);

        return panel;
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

        JOptionPane.showMessageDialog(this, "Carrera actualizada correctamente.", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        cargarCarrera();
    }

    private void eliminarMateriaSeleccionada() {
        Carrera c = (Carrera) comboCarreras.getSelectedItem();
        int index = listaMaterias.getSelectedIndex();
        if (c == null || index < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una materia para eliminar.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar esta materia?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            Materia materia = c.getPlanEstudio().getMaterias().get(index);
            c.getPlanEstudio().getMaterias().remove(materia);
            JOptionPane.showMessageDialog(this, "Materia eliminada correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            cargarCarrera();
        }
    }

    private void agregarNuevaMateria() {
        Carrera c = (Carrera) comboCarreras.getSelectedItem();
        if (c == null)
            return;

        JTextField campoNombre = new JTextField();
        JTextField campoCuatri = new JTextField();
        JCheckBox checkOblig = new JCheckBox("Obligatoria");
        JCheckBox checkPromo = new JCheckBox("Promocionable");
        UIStyles.styleCheckBox(checkOblig);
        UIStyles.styleCheckBox(checkPromo);

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
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

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Materia m = new Materia(nombre, cuatri, checkOblig.isSelected(), checkPromo.isSelected());
                c.getPlanEstudio().agregarMateria(m);
                JOptionPane.showMessageDialog(this, "Materia agregada correctamente.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarCarrera();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar materia: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
