import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaCarrera extends JFrame {
    private final DefaultListModel<String> modeloMaterias = new DefaultListModel<>();
    private final List<Materia> materiasCreadas = new ArrayList<>();
    private final JComboBox<Materia> comboCorrelativas = new JComboBox<>();
    private final DefaultListModel<String> listaCorrelativasModelo = new DefaultListModel<>();
    private final JList<String> listaCorrelativas = new JList<>(listaCorrelativasModelo);

    public VentanaCarrera() {
        setTitle("Alta de Carrera");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel = UIStyles.createTitlePanel("Alta de Carrera");

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        UIStyles.stylePanelBackground(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelCarrera = createPanelCarrera();
        JPanel panelMateria = createPanelMateria();
        JPanel panelLista = createPanelListaMaterias();

        mainPanel.add(panelCarrera, BorderLayout.NORTH);
        mainPanel.add(panelMateria, BorderLayout.CENTER);
        mainPanel.add(panelLista, BorderLayout.EAST);

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createPanelCarrera() {
        JPanel panel = new JPanel(new GridBagLayout());
        UIStyles.stylePanel(panel);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(UIStyles.PRIMARY_LIGHT, 1),
                "Información de la Carrera",
                0, 0, UIStyles.FONT_LABEL, UIStyles.TEXT_SECONDARY));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNombre = new JLabel("Nombre de la Carrera:");
        JLabel lblTipo = new JLabel("Tipo de Plan:");
        JLabel lblOptativas = new JLabel("Optativas Requeridas:");
        UIStyles.styleLabel(lblNombre);
        UIStyles.styleLabel(lblTipo);
        UIStyles.styleLabel(lblOptativas);

        JTextField campoNombreCarrera = new JTextField(20);
        JComboBox<TipoPlan> comboTipoPlan = new JComboBox<>(TipoPlan.values());
        JTextField campoOptativasRequeridas = new JTextField(5);
        UIStyles.styleTextField(campoNombreCarrera);
        UIStyles.styleComboBox(comboTipoPlan);
        UIStyles.styleTextField(campoOptativasRequeridas);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panel.add(lblNombre, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.4;
        panel.add(campoNombreCarrera, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(lblTipo, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.2;
        panel.add(comboTipoPlan, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;
        panel.add(lblOptativas, gbc);
        gbc.gridx = 5;
        gbc.weightx = 0.1;
        panel.add(campoOptativasRequeridas, gbc);

        JButton btnGuardarCarrera = new JButton("Guardar Carrera");
        UIStyles.styleButtonSuccess(btnGuardarCarrera);
        btnGuardarCarrera.setPreferredSize(new Dimension(160, 35));

        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(btnGuardarCarrera, gbc);

        btnGuardarCarrera
                .addActionListener(e -> guardarCarrera(campoNombreCarrera, comboTipoPlan, campoOptativasRequeridas));

        return panel;
    }

    private JPanel createPanelMateria() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        UIStyles.stylePanel(panel);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(UIStyles.PRIMARY_LIGHT, 1),
                "Agregar Materia al Plan",
                0, 0, UIStyles.FONT_LABEL, UIStyles.TEXT_SECONDARY));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UIStyles.SURFACE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblCuatri = new JLabel("Cuatrimestre:");
        UIStyles.styleLabel(lblNombre);
        UIStyles.styleLabel(lblCuatri);

        JTextField campoNombreMateria = new JTextField(15);
        JTextField campoCuatri = new JTextField(5);
        UIStyles.styleTextField(campoNombreMateria);
        UIStyles.styleTextField(campoCuatri);

        JCheckBox checkObligatoria = new JCheckBox("Obligatoria", true);
        JCheckBox checkPromocion = new JCheckBox("Promocionable", false);
        UIStyles.styleCheckBox(checkObligatoria);
        UIStyles.styleCheckBox(checkPromocion);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(lblNombre, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.5;
        formPanel.add(campoNombreMateria, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        formPanel.add(lblCuatri, gbc);
        gbc.gridx = 3;
        gbc.weightx = 0.2;
        formPanel.add(campoCuatri, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(checkObligatoria, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        formPanel.add(checkPromocion, gbc);

        JPanel correlativasPanel = new JPanel(new BorderLayout(5, 5));
        correlativasPanel.setBackground(UIStyles.SURFACE);
        correlativasPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                "Correlativas",
                0, 0, UIStyles.FONT_LABEL, UIStyles.TEXT_SECONDARY));

        UIStyles.styleComboBox(comboCorrelativas);
        UIStyles.styleList(listaCorrelativas);

        JButton btnAgregarCorrelativa = new JButton("Agregar");
        UIStyles.styleButtonSecondary(btnAgregarCorrelativa);
        btnAgregarCorrelativa.addActionListener(e -> {
            Materia seleccionada = (Materia) comboCorrelativas.getSelectedItem();
            if (seleccionada != null && !listaCorrelativasModelo.contains(seleccionada.getNombre())) {
                listaCorrelativasModelo.addElement(seleccionada.getNombre());
            }
        });

        correlativasPanel.add(comboCorrelativas, BorderLayout.NORTH);
        correlativasPanel.add(new JScrollPane(listaCorrelativas), BorderLayout.CENTER);
        correlativasPanel.add(btnAgregarCorrelativa, BorderLayout.SOUTH);

        JButton btnAgregarMateria = new JButton("Agregar Materia");
        UIStyles.styleButton(btnAgregarMateria);
        btnAgregarMateria.addActionListener(
                e -> agregarMateria(campoNombreMateria, campoCuatri, checkObligatoria, checkPromocion));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(UIStyles.SURFACE);
        bottomPanel.add(btnAgregarMateria);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(correlativasPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPanelListaMaterias() {
        JPanel panel = new JPanel(new BorderLayout());
        UIStyles.stylePanel(panel);
        panel.setPreferredSize(new Dimension(280, 0));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(UIStyles.PRIMARY_LIGHT, 1),
                "Materias de la Carrera",
                0, 0, UIStyles.FONT_LABEL, UIStyles.TEXT_SECONDARY));

        JList<String> listaMaterias = new JList<>(modeloMaterias);
        UIStyles.styleList(listaMaterias);

        panel.add(new JScrollPane(listaMaterias), BorderLayout.CENTER);
        return panel;
    }

    private void agregarMateria(JTextField campoNombre, JTextField campoCuatri, JCheckBox checkOblig,
            JCheckBox checkPromo) {
        try {
            String nombre = campoNombre.getText().trim();
            String textoCuatri = campoCuatri.getText().trim();

            if (!nombre.matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(this, "El nombre de la materia debe tener al menos una letra.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            for (Materia existente : materiasCreadas) {
                if (existente.getNombre().equalsIgnoreCase(nombre)) {
                    JOptionPane.showMessageDialog(this, "Ya existe una materia con ese nombre.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            int cuatri = Integer.parseInt(textoCuatri);
            if (cuatri < 1 || cuatri > 10) {
                JOptionPane.showMessageDialog(this, "El cuatrimestre debe estar entre 1 y 10.", "Validación",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean obligatoria = checkOblig.isSelected();
            boolean promo = checkPromo.isSelected();

            Materia m = new Materia(nombre, cuatri, obligatoria, promo);
            for (int i = 0; i < listaCorrelativasModelo.getSize(); i++) {
                String nombreCorr = listaCorrelativasModelo.getElementAt(i);
                for (Materia existente : materiasCreadas) {
                    if (existente.getNombre().equalsIgnoreCase(nombreCorr)) {
                        m.agregarCorrelativa(existente);
                    }
                }
            }

            comboCorrelativas.addItem(m);
            listaCorrelativasModelo.clear();
            materiasCreadas.add(m);
            modeloMaterias.addElement(nombre + " - C" + cuatri + (obligatoria ? " [Oblig.]" : " [Opt.]")
                    + (m.getCorrelativas().isEmpty() ? "" : " (Corr: " + m.getCorrelativas().size() + ")"));
            campoNombre.setText("");
            campoCuatri.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El cuatrimestre debe ser un número válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCarrera(JTextField campoNombre, JComboBox<TipoPlan> comboTipo, JTextField campoOptativas) {
        String nombreCarrera = campoNombre.getText().trim();
        if (!nombreCarrera.matches(".*[a-zA-Z].*")) {
            JOptionPane.showMessageDialog(this, "El nombre de la carrera debe contener letras.", "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        TipoPlan tipo = (TipoPlan) comboTipo.getSelectedItem();

        try {
            int cantOptativas = Integer.parseInt(campoOptativas.getText().trim());

            PlanEstudio plan = new PlanEstudio(tipo);
            materiasCreadas.forEach(plan::agregarMateria);
            Carrera carrera = new Carrera(nombreCarrera, plan, cantOptativas);
            DatosCompartidos.getCarreras().add(carrera);

            JOptionPane.showMessageDialog(this, "Carrera guardada exitosamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            campoNombre.setText("");
            campoOptativas.setText("");
            modeloMaterias.clear();
            materiasCreadas.clear();
            comboCorrelativas.removeAllItems();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad de optativas debe ser un número válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
