package umg.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

/** Estilos compartidos de la interfaz de gestión de transporte. */
public final class TemaUI {
    public static final Color TINTA = new Color(24, 39, 61);
    public static final Color PRIMARIO = new Color(15, 118, 110);
    public static final Color FONDO = new Color(245, 247, 250);
    public static final Color SUPERFICIE = Color.WHITE;
    public static final Color TEXTO = new Color(51, 65, 85);
    public static final Color SUAVE = new Color(100, 116, 139);
    public static final Color BORDE = new Color(218, 226, 235);

    private TemaUI() { }

    /** Aplica el tema sin reconstruir ni desconectar los componentes del formulario. */
    public static void aplicar(JFrame ventana, String titulo, String descripcion) {
        ventana.setTitle(titulo + " | Nexo Transporte");
        ventana.getContentPane().setBackground(FONDO);
        estilizarArbol(ventana.getContentPane());

        Container contenidoOriginal = ventana.getContentPane();
        JPanel encabezado = crearEncabezado(titulo, descripcion);
        JPanel cuerpo = new JPanel(new BorderLayout());
        cuerpo.setBackground(FONDO);
        cuerpo.setBorder(new EmptyBorder(16, 20, 20, 20));
        cuerpo.add(contenidoOriginal, BorderLayout.CENTER);

        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(FONDO);
        raiz.add(encabezado, BorderLayout.NORTH);
        raiz.add(cuerpo, BorderLayout.CENTER);
        ventana.setContentPane(raiz);
        ventana.setMinimumSize(new Dimension(680, 480));
        ventana.pack();
        ventana.setLocationRelativeTo(null);
    }

    private static JPanel crearEncabezado(String titulo, String descripcion) {
        JPanel panel = new JPanel(new BorderLayout(16, 0));
        panel.setBackground(TINTA);
        panel.setBorder(new EmptyBorder(19, 25, 19, 25));

        JLabel marca = new JLabel("NT");
        marca.setOpaque(true);
        marca.setBackground(PRIMARIO);
        marca.setForeground(Color.WHITE);
        marca.setFont(new Font("SansSerif", Font.BOLD, 20));
        marca.setHorizontalAlignment(SwingConstants.CENTER);
        marca.setPreferredSize(new Dimension(54, 54));

        JPanel textos = new JPanel(new BorderLayout(0, 5));
        textos.setOpaque(false);
        JLabel encabezado = new JLabel(titulo.toUpperCase());
        encabezado.setForeground(Color.WHITE);
        encabezado.setFont(new Font("SansSerif", Font.BOLD, 21));
        JLabel detalle = new JLabel(descripcion);
        detalle.setForeground(new Color(203, 213, 225));
        detalle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        textos.add(encabezado, BorderLayout.CENTER);
        textos.add(detalle, BorderLayout.SOUTH);

        panel.add(marca, BorderLayout.WEST);
        panel.add(textos, BorderLayout.CENTER);
        return panel;
    }

    private static void estilizarArbol(Container contenedor) {
        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof JPanel panel) {
                panel.setBackground(FONDO);
            }
            if (componente instanceof JLabel etiqueta) {
                etiqueta.setFont(new Font("SansSerif", Font.BOLD, 13));
                etiqueta.setForeground(TEXTO);
            } else if (componente instanceof JTextField campo) {
                campo.setFont(new Font("SansSerif", Font.PLAIN, 13));
                campo.setForeground(TEXTO);
                campo.setCaretColor(PRIMARIO);
                campo.setBackground(Color.WHITE);
                campo.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDE),
                        new EmptyBorder(7, 9, 7, 9)));
                campo.setPreferredSize(new Dimension(Math.max(150, campo.getPreferredSize().width), 34));
                if (!campo.isEnabled()) {
                    campo.setBackground(new Color(237, 242, 247));
                    campo.setForeground(SUAVE);
                }
            } else if (componente instanceof JComboBox<?> combo) {
                combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
                combo.setForeground(TEXTO);
                combo.setBackground(Color.WHITE);
                combo.setBorder(BorderFactory.createLineBorder(BORDE));
                combo.setPreferredSize(new Dimension(Math.max(180, combo.getPreferredSize().width), 34));
            } else if (componente instanceof JButton boton) {
                estilizarBoton(boton);
            } else if (componente instanceof JTable tabla) {
                estilizarTabla(tabla);
            } else if (componente instanceof JScrollPane scroll) {
                scroll.setBorder(BorderFactory.createLineBorder(BORDE));
                scroll.getViewport().setBackground(Color.WHITE);
            }
            if (componente instanceof Container hijo) {
                estilizarArbol(hijo);
            }
        }
    }

    private static void estilizarBoton(JButton boton) {
        String texto = boton.getText() == null ? "" : boton.getText().toLowerCase();
        Color fondo;
        if (texto.contains("eliminar")) {
            fondo = new Color(185, 55, 65);
        } else if (texto.contains("limpiar")) {
            fondo = new Color(226, 232, 240);
        } else if (texto.contains("actualizar")) {
            fondo = new Color(49, 92, 150);
        } else {
            fondo = PRIMARIO;
        }
        boton.setFont(new Font("SansSerif", Font.BOLD, 12));
        boton.setBackground(fondo);
        boton.setForeground(texto.contains("limpiar") ? TINTA : Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(9, 15, 9, 15));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);
    }

    private static void estilizarTabla(JTable tabla) {
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabla.setForeground(TEXTO);
        tabla.setBackground(Color.WHITE);
        tabla.setSelectionBackground(new Color(204, 242, 235));
        tabla.setSelectionForeground(TINTA);
        tabla.setGridColor(new Color(232, 237, 243));
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(true);
        tabla.setRowHeight(29);
        tabla.setFillsViewportHeight(true);
        tabla.setAutoCreateRowSorter(true);

        JTableHeader encabezado = tabla.getTableHeader();
        encabezado.setFont(new Font("SansSerif", Font.BOLD, 12));
        encabezado.setBackground(TINTA);
        encabezado.setForeground(Color.WHITE);
        encabezado.setPreferredSize(new Dimension(encabezado.getPreferredSize().width, 34));
        encabezado.setReorderingAllowed(false);
    }
}
