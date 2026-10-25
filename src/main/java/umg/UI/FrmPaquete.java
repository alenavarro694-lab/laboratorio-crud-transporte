/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package umg.UI;

import umg.DAO.CuidadDAO;
import umg.DAO.CamioneroDAO;
import umg.DAO.PaqueteDAO;
import umg.modelo.Ciudad;
import umg.modelo.Camionero;
import umg.modelo.Paquete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author puchi
 */
public class FrmPaquete extends javax.swing.JFrame {
    private final PaqueteDAO paqueteDAO = new PaqueteDAO();
    private final CamioneroDAO camioneroDAO = new CamioneroDAO();
    private final CuidadDAO ciudadDAO = new CuidadDAO();
    private DefaultTableModel modeloTabla;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmPaquete.class.getName());

    public FrmPaquete() {
        initComponents();
        this.setLocationRelativeTo(null);
        configurarTabla();
        cargarCombos();
        cargarPaquetes();
        txtId.setEnabled(false); // El ID no debe ser editable directamente
        TemaUI.aplicar(this, "Paquetes", "Gestiona envíos, destinatarios y destinos de entrega.");
    }

    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Descripción", "Destinatario", "Peso", "Dirección", "Camionero", "Ciudad Destino"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPaquetes.setModel(modeloTabla);
        tblPaquetes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarFila();
            }
        });
    }

    private void cargarCombos() {
        cmbCamionero.removeAllItems();
        List<Camionero> camioneros = camioneroDAO.listar();
        for (Camionero c : camioneros) {
            cmbCamionero.addItem(c.getIdCamionero() + " - " + c.getNombre() + " " + c.getApellidos());
        }

        cmbCiudad.removeAllItems();
        List<Ciudad> ciudades = ciudadDAO.listar();
        for (Ciudad ci : ciudades) {
            cmbCiudad.addItem(ci.getIdCiudad() + " - " + ci.getNombre());
        }
    }

    private void cargarPaquetes() {
        modeloTabla.setRowCount(0);
        List<Paquete> lista = paqueteDAO.listar();
        for (Paquete p : lista) {
            modeloTabla.addRow(new Object[]{
                p.getIdPaquete(),
                p.getDescripcion(),
                p.getDestinatario(),
                p.getPeso(),
                p.getDireccion(),
                p.getCamionero() != null ? p.getCamionero().getNombre() : "",
                p.getCiudad() != null ? p.getCiudad().getNombre() : ""
            });
        }
    }

    private void seleccionarFila() {
        int fila = tblPaquetes.getSelectedRow();
        if (fila >= 0) {
            txtId.setText(tblPaquetes.getValueAt(fila, 0).toString());
            txtDescripcion.setText(tblPaquetes.getValueAt(fila, 1).toString());
            txtDestinatario.setText(tblPaquetes.getValueAt(fila, 2).toString());
            txtPeso.setText(tblPaquetes.getValueAt(fila, 3).toString());
            txtDireccion.setText(tblPaquetes.getValueAt(fila, 4).toString());
            
            String nombreCamioneroTabla = tblPaquetes.getValueAt(fila, 5).toString();
            String nombreCiudadTabla = tblPaquetes.getValueAt(fila, 6).toString();
            
            for (int i = 0; i < cmbCamionero.getItemCount(); i++) {
                String item = cmbCamionero.getItemAt(i);
                if (item.contains(nombreCamioneroTabla)) {
                    cmbCamionero.setSelectedIndex(i);
                    break;
                }
            }
            
            for (int i = 0; i < cmbCiudad.getItemCount(); i++) {
                String item = cmbCiudad.getItemAt(i);
                if (item.contains(nombreCiudadTabla)) {
                    cmbCiudad.setSelectedIndex(i);
                    break;
                }
            }
            txtId.setEnabled(false);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtDescripcion.setText("");
        txtDestinatario.setText("");
        txtPeso.setText("");
        txtDireccion.setText("");
        if (cmbCamionero.getItemCount() > 0) cmbCamionero.setSelectedIndex(0);
        if (cmbCiudad.getItemCount() > 0) cmbCiudad.setSelectedIndex(0);
        tblPaquetes.clearSelection();
        txtId.setEnabled(false);
    }

    private boolean validarCampos() {
        if (txtDescripcion.getText().trim().isEmpty() ||
            txtDestinatario.getText().trim().isEmpty() ||
            txtPeso.getText().trim().isEmpty() ||
            txtDireccion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cmbCamionero.getSelectedItem() == null || cmbCiudad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un camionero y una ciudad.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            BigDecimal peso = new BigDecimal(txtPeso.getText().trim());
            if (peso.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "El peso del paquete debe ser mayor a 0.", "Validación", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El peso debe ser un valor numérico válido.", "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private int obtenerIdCombo(JComboBox<String> combo) {
        String item = (String) combo.getSelectedItem();
        if (item == null || item.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(item.split(" - ")[0]);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtDestinatario = new javax.swing.JTextField();
        txtPeso = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        cmbCamionero = new javax.swing.JComboBox<>();
        cmbCiudad = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaquetes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Id");

        jLabel2.setText("Descripcion");

        jLabel3.setText("Destinatario");

        jLabel4.setText("Peso");

        jLabel5.setText("Direccion");

        cmbCamionero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCamionero.addActionListener(this::cmbCamioneroActionPerformed);

        cmbCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCiudad.addActionListener(this::cmbCiudadActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnEliminar.setText("Elimiar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        tblPaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Descripcion", "Destinatario", "Peso", "Direccion"
            }
        ));
        jScrollPane1.setViewportView(tblPaquetes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbCamionero, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(txtDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnGuardar)
                        .addGap(57, 57, 57)
                        .addComponent(btnActualizar)
                        .addGap(56, 56, 56)
                        .addComponent(btnEliminar)
                        .addGap(57, 57, 57)
                        .addComponent(btnLimpiar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addGap(89, 89, 89)
                .addComponent(jLabel2)
                .addGap(68, 68, 68)
                .addComponent(jLabel3)
                .addGap(86, 86, 86)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCamionero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      if (!validarCampos()) return;

        Camionero c = new Camionero();
        c.setIdCamionero(obtenerIdCombo(cmbCamionero));

        Ciudad ci = new Ciudad();
        ci.setIdCiudad(obtenerIdCombo(cmbCiudad));

        Paquete p = new Paquete();
        p.setDescripcion(txtDescripcion.getText().trim());
        p.setDestinatario(txtDestinatario.getText().trim());
        p.setPeso(new BigDecimal(txtPeso.getText().trim()));
        p.setDireccion(txtDireccion.getText().trim());
        p.setCamionero(c);
        p.setCiudad(ci);

        if (paqueteDAO.insertar(p)) {
            JOptionPane.showMessageDialog(this, "Paquete registrado exitosamente.");
            cargarPaquetes();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar paquete.", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un paquete de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!validarCampos()) return;

        try {
            Camionero c = new Camionero();
            c.setIdCamionero(obtenerIdCombo(cmbCamionero));

            Ciudad ci = new Ciudad();
            ci.setIdCiudad(obtenerIdCombo(cmbCiudad));

            Paquete p = new Paquete();
            p.setIdPaquete(Integer.parseInt(txtId.getText().trim()));
            p.setDescripcion(txtDescripcion.getText().trim());
            p.setDestinatario(txtDestinatario.getText().trim());
            p.setPeso(new BigDecimal(txtPeso.getText().trim()));
            p.setDireccion(txtDireccion.getText().trim());
            p.setCamionero(c);
            p.setCiudad(ci);

            if (paqueteDAO.actualizar(p)) {
                JOptionPane.showMessageDialog(this, "Paquete actualizado exitosamente.");
                cargarPaquetes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar paquete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID del paquete no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un paquete de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int op = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el paquete seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                if (paqueteDAO.eliminar(id)) {
                    JOptionPane.showMessageDialog(this, "Paquete eliminado exitosamente.");
                    cargarPaquetes();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar paquete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El ID del paquete no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cmbCamioneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCamioneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCamioneroActionPerformed

    private void cmbCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCiudadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new FrmPaquete().setVisible(true));
    }    
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbCamionero;
    private javax.swing.JComboBox<String> cmbCiudad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPaquetes;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDestinatario;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPeso;
    // End of variables declaration//GEN-END:variables
}
