package umg.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/** Pantalla inicial de navegación para los módulos del sistema. */
public class FrmPrincipal extends JFrame {
    public FrmPrincipal() {
        construirInterfaz();
    }

    private void construirInterfaz() {
        setTitle("Nexo Transporte | Panel principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(850, 620));

        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(TemaUI.FONDO);

        JPanel encabezado = new JPanel(new BorderLayout(18, 0));
        encabezado.setBackground(TemaUI.TINTA);
        encabezado.setBorder(new EmptyBorder(27, 32, 27, 32));

        JLabel marca = new JLabel("NT");
        marca.setOpaque(true);
        marca.setBackground(TemaUI.PRIMARIO);
        marca.setForeground(Color.WHITE);
        marca.setFont(new Font("SansSerif", Font.BOLD, 25));
        marca.setHorizontalAlignment(SwingConstants.CENTER);
        marca.setPreferredSize(new Dimension(68, 68));

        JPanel titulos = new JPanel(new BorderLayout(0, 7));
        titulos.setOpaque(false);
        JLabel titulo = new JLabel("NEXO TRANSPORTE");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 27));
        JLabel subtitulo = new JLabel("Panel de control · Operaciones y distribución");
        subtitulo.setForeground(new Color(203, 213, 225));
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        titulos.add(titulo, BorderLayout.CENTER);
        titulos.add(subtitulo, BorderLayout.SOUTH);
        encabezado.add(marca, BorderLayout.WEST);
        encabezado.add(titulos, BorderLayout.CENTER);

        JPanel contenido = new JPanel(new BorderLayout(0, 20));
        contenido.setBackground(TemaUI.FONDO);
        contenido.setBorder(new EmptyBorder(28, 32, 24, 32));

        JPanel introduccion = new JPanel(new BorderLayout(0, 7));
        introduccion.setOpaque(false);
        JLabel saludo = new JLabel("Administración del sistema");
        saludo.setFont(new Font("SansSerif", Font.BOLD, 22));
        saludo.setForeground(TemaUI.TINTA);
        JLabel ayuda = new JLabel("Elige un módulo para consultar o gestionar la información.");
        ayuda.setFont(new Font("SansSerif", Font.PLAIN, 14));
        ayuda.setForeground(TemaUI.SUAVE);
        introduccion.add(saludo, BorderLayout.CENTER);
        introduccion.add(ayuda, BorderLayout.SOUTH);

        JPanel tarjetas = new JPanel(new GridLayout(0, 2, 16, 16));
        tarjetas.setOpaque(false);
        tarjetas.add(crearTarjeta("01", "Camiones", "Modelos, tipo, potencia y placas.", () -> abrir(new FrmCamion())));
        tarjetas.add(crearTarjeta("02", "Camioneros", "Datos personales y registro de conductores.", () -> abrir(new FrmCamionero())));
        tarjetas.add(crearTarjeta("03", "Ciudades", "Catálogo de ciudades y destinos.", () -> abrir(new FrmCiudad())));
        tarjetas.add(crearTarjeta("04", "Paquetes", "Información de envíos, peso y destinatarios.", () -> abrir(new FrmPaquete())));
        tarjetas.add(crearTarjeta("05", "Asignaciones", "Relaciona conductores, camiones y fechas.", () -> abrir(new FrmConductorCamion())));

        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);
        parteSuperior.add(introduccion, BorderLayout.NORTH);
        parteSuperior.add(tarjetas, BorderLayout.CENTER);

        JLabel pie = new JLabel("SISTEMA DE GESTIÓN DE TRANSPORTE   ·   JAVA SWING / MYSQL");
        pie.setFont(new Font("SansSerif", Font.BOLD, 10));
        pie.setForeground(TemaUI.SUAVE);
        pie.setBorder(new EmptyBorder(8, 0, 0, 0));

        contenido.add(parteSuperior, BorderLayout.CENTER);
        contenido.add(pie, BorderLayout.SOUTH);
        raiz.add(encabezado, BorderLayout.NORTH);
        raiz.add(contenido, BorderLayout.CENTER);
        setContentPane(raiz);
        pack();
        setSize(Math.max(getWidth(), 930), Math.max(getHeight(), 680));
        setLocationRelativeTo(null);
    }

    private JPanel crearTarjeta(String numero, String titulo, String descripcion, Runnable accion) {
        JPanel tarjeta = new JPanel(new BorderLayout(14, 0));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TemaUI.BORDE),
                new EmptyBorder(16, 16, 16, 16)));

        JLabel insignia = new JLabel(numero);
        insignia.setOpaque(true);
        insignia.setBackground(new Color(219, 242, 238));
        insignia.setForeground(TemaUI.PRIMARIO);
        insignia.setFont(new Font("SansSerif", Font.BOLD, 17));
        insignia.setHorizontalAlignment(SwingConstants.CENTER);
        insignia.setPreferredSize(new Dimension(50, 50));

        JPanel detalle = new JPanel(new BorderLayout(0, 5));
        detalle.setOpaque(false);
        JLabel nombre = new JLabel(titulo);
        nombre.setFont(new Font("SansSerif", Font.BOLD, 16));
        nombre.setForeground(TemaUI.TINTA);
        JLabel descripcionLabel = new JLabel("<html><div style='width:240px'>" + descripcion + "</div></html>");
        descripcionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        descripcionLabel.setForeground(TemaUI.SUAVE);
        JButton boton = new JButton("Abrir módulo  →");
        boton.setFont(new Font("SansSerif", Font.BOLD, 12));
        boton.setForeground(TemaUI.PRIMARIO);
        boton.setBackground(Color.WHITE);
        boton.setBorder(new EmptyBorder(8, 0, 0, 0));
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setFocusPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.addActionListener(e -> accion.run());
        detalle.add(nombre, BorderLayout.NORTH);
        detalle.add(descripcionLabel, BorderLayout.CENTER);
        detalle.add(boton, BorderLayout.SOUTH);
        tarjeta.add(insignia, BorderLayout.WEST);
        tarjeta.add(detalle, BorderLayout.CENTER);
        tarjeta.setPreferredSize(new Dimension(390, 115));
        return tarjeta;
    }

    private void abrir(JFrame formulario) {
        formulario.setVisible(true);
    }
}
