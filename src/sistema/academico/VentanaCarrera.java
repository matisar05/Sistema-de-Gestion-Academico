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
        setSize(600, 500);
        setLayout(new BorderLayout());

        JTextField campoNombreCarrera = new JTextField(15);
        JComboBox<TipoPlan> comboTipoPlan = new JComboBox<>(TipoPlan.values());
        JTextField campoOptativasRequeridas = new JTextField(3);

        JPanel panelCarrera = new JPanel();
        panelCarrera.add(new JLabel("Nombre Carrera:"));
        panelCarrera.add(campoNombreCarrera);
        panelCarrera.add(new JLabel("Tipo Plan:"));
        panelCarrera.add(comboTipoPlan);
        panelCarrera.add(new JLabel("Optativas necesarias:"));
        panelCarrera.add(campoOptativasRequeridas);

        JTextField campoNombreMateria = new JTextField(10);
        JTextField campoCuatri = new JTextField(2);
        JCheckBox checkObligatoria = new JCheckBox("Obligatoria", true);
        JCheckBox checkPromocion = new JCheckBox("Promocionable", false);
        JButton btnAgregarMateria = new JButton("Agregar Materia");

        JPanel panelMateria = new JPanel();
        panelMateria.setBorder(BorderFactory.createTitledBorder("Agregar Materia"));
        panelMateria.add(new JLabel("Nombre:"));
        panelMateria.add(campoNombreMateria);
        panelMateria.add(new JLabel("Cuatrimestre:"));
        panelMateria.add(campoCuatri);
        panelMateria.add(checkObligatoria);
        panelMateria.add(checkPromocion);
        panelMateria.add(btnAgregarMateria);

        JPanel panelCorrelativas = new JPanel(new BorderLayout());
        panelCorrelativas.setBorder(BorderFactory.createTitledBorder("Correlativas"));

        JButton btnAgregarCorrelativa = new JButton("Agregar Correlativa");
        btnAgregarCorrelativa.addActionListener(e -> {
            Materia seleccionada = (Materia) comboCorrelativas.getSelectedItem();
            if (seleccionada != null && !listaCorrelativasModelo.contains(seleccionada.getNombre())) {
                listaCorrelativasModelo.addElement(seleccionada.getNombre());
            }
        });

        panelCorrelativas.add(comboCorrelativas, BorderLayout.NORTH);
        panelCorrelativas.add(new JScrollPane(listaCorrelativas), BorderLayout.CENTER);
        panelCorrelativas.add(btnAgregarCorrelativa, BorderLayout.SOUTH);

        panelMateria.add(panelCorrelativas);

        JList<String> listaMaterias = new JList<>(modeloMaterias);

        btnAgregarMateria.addActionListener(e -> {
            try {
                String nombre = campoNombreMateria.getText().trim();
                String textoCuatri = campoCuatri.getText().trim();

                if (!nombre.matches(".*[a-zA-Z].*")) {
                    JOptionPane.showMessageDialog(this, "El nombre de la materia debe tener al menos una letra.");
                    return;
                }

                for (Materia existente : materiasCreadas) {
                    if (existente.getNombre().equalsIgnoreCase(nombre)) {
                        JOptionPane.showMessageDialog(this, "Ya existe una materia con ese nombre en esta carrera.");
                        return;
                    }
                }
                int cuatri = Integer.parseInt(textoCuatri);
                if (cuatri < 1 || cuatri > 10) {
                    JOptionPane.showMessageDialog(this, "El cuatrimestre debe estar entre 1 y 10.");
                    return;
                }
                boolean obligatoria = checkObligatoria.isSelected();
                boolean promo = checkPromocion.isSelected();

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
                campoNombreMateria.setText("");
                campoCuatri.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cuatri inválido.");
            }
        });

        JButton btnGuardarCarrera = new JButton("Guardar Carrera");
        btnGuardarCarrera.addActionListener(e -> {
            String nombreCarrera = campoNombreCarrera.getText().trim();
            if (!nombreCarrera.matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(this, "El nombre de la carrera debe contener letras.");
                return;
            }
            TipoPlan tipo = (TipoPlan) comboTipoPlan.getSelectedItem();

            try {
                int cantOptativas = Integer.parseInt(campoOptativasRequeridas.getText().trim());

                PlanEstudio plan = new PlanEstudio(tipo);
                materiasCreadas.forEach(plan::agregarMateria);
                Carrera carrera = new Carrera(nombreCarrera, plan, cantOptativas);
                DatosCompartidos.getCarreras().add(carrera);

                JOptionPane.showMessageDialog(this, "¡Carrera guardada!");
                campoNombreCarrera.setText("");
                campoOptativasRequeridas.setText("");
                modeloMaterias.clear();
                materiasCreadas.clear();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad de optativas inválida.");
            }
        });

        add(panelCarrera, BorderLayout.NORTH);
        add(panelMateria, BorderLayout.CENTER);
        add(new JScrollPane(listaMaterias), BorderLayout.EAST);
        add(btnGuardarCarrera, BorderLayout.SOUTH);
    }
}
