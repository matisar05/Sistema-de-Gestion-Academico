import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class UIStyles {

    // Paleta profesional Material Design
    public static final Color PRIMARY = new Color(52, 73, 94);
    public static final Color PRIMARY_LIGHT = new Color(127, 140, 141);
    public static final Color ACCENT = new Color(41, 128, 185);
    public static final Color SUCCESS = new Color(39, 174, 96);
    public static final Color WARNING = new Color(230, 126, 34);
    public static final Color ERROR = new Color(192, 57, 43);

    public static final Color BACKGROUND = new Color(245, 245, 245);
    public static final Color SURFACE = new Color(255, 255, 255);
    public static final Color BORDER = new Color(189, 195, 199);
    public static final Color BORDER_LIGHT = new Color(220, 221, 225);
    public static final Color SHADOW = new Color(0, 0, 0, 25);

    public static final Color TEXT_PRIMARY = new Color(44, 62, 80);
    public static final Color TEXT_SECONDARY = new Color(127, 140, 141);
    public static final Color TEXT_LIGHT = new Color(255, 255, 255);

    // Tipografía Material
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_LABEL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_TEXT = new Font("Segoe UI", Font.PLAIN, 12);

    // Border redondeado custom
    public static class RoundedBorder extends AbstractBorder {
        private Color color;
        private int radius;
        private int thickness;

        public RoundedBorder(Color color, int radius, int thickness) {
            this.color = color;
            this.radius = radius;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness + 2, thickness + 2, thickness + 2, thickness + 2);
        }
    }

    // Botón Material Design con bordes redondeados
    public static JButton createMaterialButton(String text, Color bgColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Sombra sutil
                g2.setColor(SHADOW);
                g2.fillRoundRect(2, 3, getWidth() - 4, getHeight() - 3, 8, 8);

                // Fondo del botón
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 4, 8, 8);

                g2.dispose();
                super.paintComponent(g);
            }
        };

        btn.setBackground(bgColor);
        btn.setForeground(TEXT_LIGHT);
        btn.setFont(FONT_BUTTON);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(12, 28, 12, 28));

        return btn;
    }

    // Estilos de botones tradicionales (backward compatibility)
    public static void styleButton(JButton btn) {
        btn.setBackground(PRIMARY);
        btn.setForeground(TEXT_LIGHT);
        btn.setFont(FONT_BUTTON);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(new EmptyBorder(10, 24, 10, 24));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }

    public static void styleButtonPrimary(JButton btn) {
        btn.setBackground(ACCENT);
        btn.setForeground(TEXT_LIGHT);
        btn.setFont(FONT_BUTTON);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(new EmptyBorder(10, 24, 10, 24));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }

    public static void styleButtonSuccess(JButton btn) {
        btn.setBackground(SUCCESS);
        btn.setForeground(TEXT_LIGHT);
        btn.setFont(FONT_BUTTON);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(new EmptyBorder(10, 24, 10, 24));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }

    public static void styleButtonAccent(JButton btn) {
        btn.setBackground(WARNING);
        btn.setForeground(TEXT_LIGHT);
        btn.setFont(FONT_BUTTON);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(new EmptyBorder(10, 24, 10, 24));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }

    public static void styleButtonSecondary(JButton btn) {
        btn.setBackground(SURFACE);
        btn.setForeground(PRIMARY);
        btn.setFont(FONT_BUTTON);
        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                new EmptyBorder(9, 23, 9, 23)));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }

    // Text fields Material
    public static void styleTextField(JTextField field) {
        field.setFont(FONT_TEXT);
        field.setBackground(SURFACE);
        field.setForeground(TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(BORDER, 4, 1),
                new EmptyBorder(6, 10, 6, 10)));
        field.setCaretColor(PRIMARY);
    }

    // Labels
    public static void styleLabel(JLabel label) {
        label.setFont(FONT_LABEL);
        label.setForeground(TEXT_PRIMARY);
    }

    public static void styleLabelSecondary(JLabel label) {
        label.setFont(FONT_SUBTITLE);
        label.setForeground(TEXT_SECONDARY);
    }

    public static void styleLabelBold(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(TEXT_PRIMARY);
    }

    // Panel de título Material
    public static JPanel createTitlePanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PRIMARY);
        panel.setBorder(new EmptyBorder(22, 28, 22, 28));

        JLabel label = new JLabel(title);
        label.setFont(FONT_TITLE);
        label.setForeground(TEXT_LIGHT);

        panel.add(label, BorderLayout.WEST);
        return panel;
    }

    // Paneles
    public static void stylePanel(JPanel panel) {
        panel.setBackground(SURFACE);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    public static void stylePanelWithBorder(JPanel panel, String title) {
        panel.setBackground(SURFACE);
        TitledBorder border = BorderFactory.createTitledBorder(
                new RoundedBorder(BORDER_LIGHT, 8, 1),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP,
                FONT_SUBTITLE,
                TEXT_SECONDARY);
        panel.setBorder(BorderFactory.createCompoundBorder(
                border,
                new EmptyBorder(10, 15, 15, 15)));
    }

    public static void stylePanelBackground(JPanel panel) {
        panel.setBackground(BACKGROUND);
    }

    // Listas
    public static void styleList(JList<?> list) {
        list.setFont(FONT_TEXT);
        list.setBackground(SURFACE);
        list.setForeground(TEXT_PRIMARY);
        list.setSelectionBackground(new Color(189, 195, 199));
        list.setSelectionForeground(TEXT_PRIMARY);
        list.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    // ComboBox
    public static void styleComboBox(JComboBox<?> combo) {
        combo.setFont(FONT_TEXT);
        combo.setBackground(SURFACE);
        combo.setForeground(TEXT_PRIMARY);
    }

    // CheckBox
    public static void styleCheckBox(JCheckBox check) {
        check.setFont(FONT_LABEL);
        check.setForeground(TEXT_PRIMARY);
        check.setBackground(SURFACE);
        check.setFocusPainted(false);
    }

    // ScrollPane Material
    public static JScrollPane createStyledScrollPane(JComponent component) {
        JScrollPane scroll = new JScrollPane(component);
        scroll.setBorder(new RoundedBorder(BORDER_LIGHT, 8, 1));
        scroll.getViewport().setBackground(SURFACE);
        return scroll;
    }
}
