/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CatalogoController;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.CatalogoCuenta;

/**
 *
 * @author Zoila López
 */
public class consultas extends javax.swing.JPanel {

    /**
     * Creates new form consultas
     */
    public consultas() {
        initComponents();
        tb_load_catalogo();
    }

    CatalogoController catalogoCtrl = new CatalogoController();

    public void tb_load_catalogo() {
        List<String[]> listaCatalogo = catalogoCtrl.list();

        DefaultTableModel dt = (DefaultTableModel) catalogo_table.getModel();
        dt.setRowCount(0);

        for (String[] catalogo : listaCatalogo) {
            if (catalogo[2].equals("true")) {
                catalogo[2] = "General";
            } else {
                catalogo[2] = "Detalle";
            }
            if (catalogo[5].equals("1")) {
                catalogo[5] = "Débito";
            } else {
                catalogo[5] = "Crédito";
            }
            dt.addRow(catalogo);
        }
    }

    public void limpiarCampos(JTextField[] campos) {

        for (JTextField campo : campos) {
            campo.setText("");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTipoCuentaGroup = new javax.swing.ButtonGroup();
        jLabel6 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtNroCuenta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcionCuenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rdbGeneral = new javax.swing.JRadioButton();
        rdbDetalle = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtCuentaPadre = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cmbGrupoCuenta = new javax.swing.JComboBox<>();
        btnBuscarCatalogo = new javax.swing.JButton();
        btnLimpiarCatalogo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtNivelCuenta = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        catalogo_table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();

        jLabel6.setText("jLabel6");

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNroCuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNroCuentaKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Número de cuenta:");

        jLabel3.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Descripción:");

        jLabel4.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipo de cuenta:");

        btnTipoCuentaGroup.add(rdbGeneral);
        rdbGeneral.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        rdbGeneral.setText("General");

        btnTipoCuentaGroup.add(rdbDetalle);
        rdbDetalle.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        rdbDetalle.setText("Detalle");

        jLabel5.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Cuenta Padre:");

        txtCuentaPadre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCuentaPadreKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Grupo de Cuenta:");

        cmbGrupoCuenta.setFont(new java.awt.Font("Sylfaen", 0, 14)); // NOI18N
        cmbGrupoCuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Seleccione una opción---", "Débito", "Crédito" }));
        cmbGrupoCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGrupoCuentaActionPerformed(evt);
            }
        });

        btnBuscarCatalogo.setBackground(new java.awt.Color(153, 153, 255));
        btnBuscarCatalogo.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        btnBuscarCatalogo.setText("Buscar");
        btnBuscarCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCatalogoActionPerformed(evt);
            }
        });

        btnLimpiarCatalogo.setBackground(new java.awt.Color(0, 204, 204));
        btnLimpiarCatalogo.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        btnLimpiarCatalogo.setText("Limpiar");
        btnLimpiarCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCatalogoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nivel Cuenta:");

        txtNivelCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNivelCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnBuscarCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtCuentaPadre))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmbGrupoCuenta, 0, 220, Short.MAX_VALUE)
                                        .addComponent(txtNivelCuenta)))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(151, 151, 151)
                                    .addComponent(rdbDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addGap(0, 15, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDescripcionCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(189, 189, 189)
                            .addComponent(rdbGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBuscarCatalogo, btnLimpiarCatalogo});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNroCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescripcionCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdbGeneral)
                    .addComponent(rdbDetalle))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCuentaPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmbGrupoCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNivelCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbGrupoCuenta, jLabel14, jLabel2, jLabel3, jLabel4, jLabel5, jLabel7, rdbDetalle, rdbGeneral, txtCuentaPadre, txtDescripcionCuenta, txtNivelCuenta, txtNroCuenta});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBuscarCatalogo, btnLimpiarCatalogo});

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        catalogo_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro", "Descripcion", "Tipo", "Nivel", "Cuenta Padre", "Grupo", "Fecha Creacion", "Hora Creacion", "Debito Acumulado", "Credito Acumulado", "Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(catalogo_table);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Catalogo de Cuentas", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1084, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Transacciones", jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1084, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Balanza General", jPanel3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1084, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Balanza de comprobacion", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1084, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Resumen de gastos generales", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1084, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 602, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Estado de Ganancias y perdidas", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNivelCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNivelCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNivelCuentaActionPerformed

    private void btnLimpiarCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCatalogoActionPerformed
        // TODO add your handling code here:
        JTextField[] campos = {txtNroCuenta, txtDescripcionCuenta, txtCuentaPadre, txtNivelCuenta};
        cmbGrupoCuenta.setSelectedItem("---Seleccione una opción---");
        btnTipoCuentaGroup.clearSelection();
        limpiarCampos(campos);
        tb_load_catalogo();
    }//GEN-LAST:event_btnLimpiarCatalogoActionPerformed

    private void btnBuscarCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCatalogoActionPerformed
        // TODO add your handling code here:
        //if every field is empty, then do not apply search
        if (txtNroCuenta.getText().isEmpty() && txtDescripcionCuenta.getText().isEmpty() && txtNivelCuenta.getText().isEmpty() && cmbGrupoCuenta.getSelectedItem().equals("---Seleccione una opción---") && txtCuentaPadre.getText().isEmpty() && !(rdbGeneral.isSelected() || rdbDetalle.isSelected())) {
            JOptionPane.showMessageDialog(null, "Todos los campos están vacíos", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Crear un objeto CatalogoCuenta con los campos a filtrar
        CatalogoCuenta filtro = new CatalogoCuenta();
        if (!txtNroCuenta.getText().isEmpty()) {
            filtro.setNro_cta(Integer.parseInt(txtNroCuenta.getText()));
        }
        if (!txtDescripcionCuenta.getText().isEmpty()) {
            filtro.setDescripcion_cta(txtDescripcionCuenta.getText());
        }
        if (rdbGeneral.isSelected() || rdbDetalle.isSelected()) {
            boolean tipo_cta = false;
            if (rdbGeneral.isSelected()) {
                tipo_cta = true;
            }
            filtro.setTipo_cta(tipo_cta);
        }

        if (!txtNivelCuenta.getText().isEmpty()) {
            filtro.setNivel_cta(Integer.parseInt(txtNivelCuenta.getText()));
        }

        if (!txtCuentaPadre.getText().isEmpty()) {
            filtro.setCta_padre(Integer.parseInt(txtCuentaPadre.getText()));
        }

        if (!cmbGrupoCuenta.getSelectedItem().equals("---Seleccione una opción---")) {
            int grupo_cta = cmbGrupoCuenta.getSelectedItem().equals("Débito") ? 1 : 2;
            filtro.setGrupo_cta(grupo_cta);
        }

        List<CatalogoCuenta> catalogoCuentasFiltrado = catalogoCtrl.obtenerCatalogoCuentas(filtro);

        DefaultTableModel dt = (DefaultTableModel) catalogo_table.getModel();
        dt.setRowCount(0);

        for (CatalogoCuenta catalogo : catalogoCuentasFiltrado) {
            String tipoCta = catalogo.isTipo_cta() ? "General" : "Detalle";
            String grupoCta = (catalogo.getGrupo_cta() == 1) ? "Débito" : "Crédito";

            Object[] rowData = {
                catalogo.getNro_cta(),
                catalogo.getDescripcion_cta(),
                tipoCta,
                catalogo.getNivel_cta(),
                catalogo.getCta_padre(),
                grupoCta,
                catalogo.getFecha_creacion_cta(),
                catalogo.getHora_creacion_cta(),
                catalogo.getDebito_acum_cta(),
                catalogo.getCredito_acum_cta(),
                catalogo.getBalance_cta()
            };

            dt.addRow(rowData);
        }
    }//GEN-LAST:event_btnBuscarCatalogoActionPerformed

    private void cmbGrupoCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGrupoCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGrupoCuentaActionPerformed

    private void txtCuentaPadreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuentaPadreKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || Character.isWhitespace(c)) {
            txtCuentaPadre.setEditable(false);
        } else {
            txtCuentaPadre.setEditable(true);
        }
    }//GEN-LAST:event_txtCuentaPadreKeyPressed

    private void txtNroCuentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroCuentaKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || Character.isWhitespace(c)) {
            txtNroCuenta.setEditable(false);
        } else {
            txtNroCuenta.setEditable(true);
        }
    }//GEN-LAST:event_txtNroCuentaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCatalogo;
    private javax.swing.JButton btnLimpiarCatalogo;
    private javax.swing.ButtonGroup btnTipoCuentaGroup;
    private javax.swing.JTable catalogo_table;
    private javax.swing.JComboBox<String> cmbGrupoCuenta;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdbDetalle;
    private javax.swing.JRadioButton rdbGeneral;
    private javax.swing.JTextField txtCuentaPadre;
    private javax.swing.JTextField txtDescripcionCuenta;
    private javax.swing.JTextField txtNivelCuenta;
    private javax.swing.JTextField txtNroCuenta;
    // End of variables declaration//GEN-END:variables
}
