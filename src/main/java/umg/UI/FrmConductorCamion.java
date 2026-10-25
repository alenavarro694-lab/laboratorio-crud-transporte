/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package umg.UI;

import umg.DAO.CamionDAO;
import umg.DAO.CamioneroDAO;
import umg.DAO.ConductorCamionDAO;
import umg.modelo.Camion;
import umg.modelo.Camionero;
import umg.modelo.ConductorCamion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author puchi
 */
public class FrmConductorCamion extends javax.swing.JFrame {
    private final ConductorCamionDAO asignacionDAO = new ConductorCamionDAO();
    private final CamioneroDAO camioneroDAO = new CamioneroDAO();
    private final CamionDAO camionDAO = new CamionDAO();
    private DefaultTableModel modeloTabla;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmConductorCamion.class.getName());

    public FrmConductorCamion() {
        initComponents();
        this.setLocationRelativeTo(null);
        configurarTabla();
        cargarCombos();
        cargarAsignaciones();
        txtFecha.setText(LocalDate.now().toString());
        txtId.setEnabled(false); // El ID no debe ser editable directamente
        TemaUI.aplicar(this, "Asignaciones", "Vincula a cada conductor con un camión y una fecha.");
    }

    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"ID Asignación", "Camionero", "Camión (Placa)", "Fecha Conducción"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblAsignaciones.setModel(modeloTabla);
        tblAsignaciones.getSelectionModel().addListSelectionListener(e -> {
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

        cmbCamion.removeAllItems();
        List<Camion> camiones = camionDAO.listar();
        for (Camion cam : camiones) {
            cmbCamion.addItem(cam.getIdCamion() + " - " + cam.getModelo() + " [" + cam.getNoPlaca() + "]");
        }
    }

    private void cargarAsignaciones() {
        modeloTabla.setRowCount(0);
        List<ConductorCamion> lista = asignacionDAO.listar();
        for (ConductorCamion cc : lista) {
            modeloTabla.addRow(new Object[]{
                cc.getIdConductorCamion(),
                cc.getConductor() != null ? cc.getConductor().getNombre() + " " + cc.getConductor().getApellidos() : "",
                cc.getCamion() != null ? cc.getCamion().getNoPlaca() : "",
                cc.getFecha()
            });
        }
    }

    private void seleccionarFila() {
        int fila = tblAsignaciones.getSelectedRow();
        if (fila >= 0) {
            txtId.setText(tblAsignaciones.getValueAt(fila, 0).toString());
            txtFecha.setText(tblAsignaciones.getValueAt(fila, 3).toString());
            
            String nombreCamioneroTabla = tblAsignaciones.getValueAt(fila, 1).toString();
            String placaCamionTabla = tblAsignaciones.getValueAt(fila, 2).toString();
            
            for (int i = 0; i < cmbCamionero.getItemCount(); i++) {
                String item = cmbCamionero.getItemAt(i);
                if (item.contains(nombreCamioneroTabla)) {
                    cmbCamionero.setSelectedIndex(i);
                    break;
                }
            }
            
            for (int i = 0; i < cmbCamion.getItemCount(); i++) {
                String item = cmbCamion.getItemAt(i);
                if (item.contains(placaCamionTabla)) {
                    cmbCamion.setSelectedIndex(i);
                    break;
                }
            }
            txtId.setEnabled(false);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtFecha.setText(LocalDate.now().toString());
        if (cmbCamionero.getItemCount() > 0) cmbCamionero.setSelectedIndex(0);
        if (cmbCamion.getItemCount() > 0) cmbCamion.setSelectedIndex(0);
        tblAsignaciones.clearSelection();
        txtId.setEnabled(false);
    }

    private boolean validarCampos() {
        if (txtFecha.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cmbCamionero.getSelectedItem() == null || cmbCamion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un camionero y un camión.", "Validación", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            LocalDate.parse(txtFecha.getText().trim());
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "El formato de fecha debe ser YYYY-MM-DD (ej: 2026-10-21).", "Validación", JOptionPane.ERROR_MESSAGE);
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
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        cmbCamionero = new javax.swing.JComboBox<>();
        cmbCamion = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsignaciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Id");

        txtFecha.setText("Fecha");

        cmbCamionero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbCamion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        tblAsignaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Asignacion", "Camionero", "Camion (Placa)", "Fecha Conduccion"
            }
        ));
        jScrollPane1.setViewportView(tblAsignaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(txtFecha))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtId)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbCamion, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbCamionero, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnGuardar)
                        .addGap(62, 62, 62)
                        .addComponent(btnEliminar)
                        .addGap(59, 59, 59)
                        .addComponent(btnLimpiar)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCamionero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCamion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      if (cmbCamionero.getSelectedItem() == null || cmbCamion.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un camionero y un camión.", "Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // 2. Extraer el objeto seleccionado o el ID de forma segura
        // Asumiendo que guardas objetos o cadenas formateadas, asegúrate de obtener el ID correcto:
        Camionero camioneroSeleccionado = (Camionero) cmbCamionero.getSelectedItem();
        Camion camionSeleccionado = (Camion) cmbCamion.getSelectedItem();

        ConductorCamion cc = new ConductorCamion();
cc.setConductor(camioneroSeleccionado);
cc.setCamion(camionSeleccionado);
        // Agrega la fecha u otros campos necesarios según tu modelo

        // 3. Ejecutar la inserción mediante el DAO
        if (asignacionDAO.insertar(cc)) {
            JOptionPane.showMessageDialog(this, "Asignación guardada exitosamente.");
            cargarAsignaciones();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error de conversión o selección: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una asignación de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int op = JOptionPane.showConfirmDialog(this, "¿Desea eliminar la asignación seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                if (asignacionDAO.eliminar(id)) {
                    JOptionPane.showMessageDialog(this, "Asignación eliminada correctamente.");
                    cargarAsignaciones();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la asignación.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El ID de la asignación no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new FrmConductorCamion().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbCamion;
    private javax.swing.JComboBox<String> cmbCamionero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblAsignaciones;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
