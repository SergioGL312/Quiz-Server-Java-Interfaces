/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.InetAddress;
import javax.swing.JOptionPane;
import cuestionario.Jugador;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sergio
 */
public class Lobby extends javax.swing.JFrame implements PropertyChangeListener {

    Servidor s;

    public Lobby(Servidor servidor) {
        this.s = servidor;
        initComponents();
        setIP();
        setPin();
        String[] cabezera = new String[]{"Nombre Jugador"};
        dtm.setColumnIdentifiers(cabezera);
        tbl.setModel(dtm);
        servidor.addPropertyChangeListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        backgroundPregunta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblPIN = new javax.swing.JLabel();
        lblIpHost = new javax.swing.JLabel();
        btnPin = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        background.setBackground(new java.awt.Color(240, 244, 249));
        background.setForeground(new java.awt.Color(153, 153, 153));
        background.setMinimumSize(new java.awt.Dimension(509, 258));
        background.setPreferredSize(new java.awt.Dimension(800, 509));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backgroundPregunta.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("PIN del Juego");

        lblPIN.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        lblPIN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPIN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblIpHost.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        lblIpHost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIpHost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout backgroundPreguntaLayout = new javax.swing.GroupLayout(backgroundPregunta);
        backgroundPregunta.setLayout(backgroundPreguntaLayout);
        backgroundPreguntaLayout.setHorizontalGroup(
            backgroundPreguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPreguntaLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(lblIpHost, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addGroup(backgroundPreguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(285, 285, 285))
        );
        backgroundPreguntaLayout.setVerticalGroup(
            backgroundPreguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPreguntaLayout.createSequentialGroup()
                .addGroup(backgroundPreguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPreguntaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblPIN, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundPreguntaLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblIpHost, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        background.add(backgroundPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1060, 120));

        btnPin.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnPin.setText("Pin");
        btnPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPinActionPerformed(evt);
            }
        });
        background.add(btnPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 460, -1, -1));

        btnSiguiente.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        background.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 470, -1, -1));

        tbl.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl);

        background.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 390, 290));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPinActionPerformed
        btnPin.setEnabled(false);
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                s.iniciarServidor();
            }
        });
        hilo.start();
    }//GEN-LAST:event_btnPinActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
       bandera = true;
    }//GEN-LAST:event_btnSiguienteActionPerformed

    public boolean getBandera() {
        return bandera;
    }
    
    public void actualizarTabla() {
        // listaJugadores = s.getJugadores();
        dtm.setRowCount(0);
        for (Jugador jugador : listaJugadores) {
            String[] fila = new String[]{jugador.getNombreJugador()};
            dtm.addRow(fila);
        }
    }

    private void setIP() {
        try {
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            lblIpHost.setText(ipAddress);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "[-] Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setPin() {
        lblPIN.setText(Integer.toString(s.getQuiz().getPin()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("jugadores")) {
            listaJugadores = (List<Jugador>) evt.getNewValue();
            actualizarTabla();
        }
    }

    /**
     * @param args the command line arguments
     */
    /*  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         *
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lobby.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lobby().setVisible(true);
            }
        });
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel backgroundPregunta;
    private javax.swing.JButton btnPin;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIpHost;
    private javax.swing.JLabel lblPIN;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
    private List<Jugador> listaJugadores;
    DefaultTableModel dtm = new DefaultTableModel();
    private static boolean bandera = false;
}
