/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import dao.impl.CategorieConsultationDAOImpl;
import dao.impl.ConsultationDAOImpl;
import dao.impl.PatientDAOImpl;
import dao.interfaces.CategorieConsultationDAO;
import dao.interfaces.ConsultationDAO;
import dao.interfaces.PatientDAO;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Consultation;
import model.Patient;
import model.Utilisateur;

/**
 *
 * @author idber
 */
public class DoctorPanel extends javax.swing.JFrame {
    
   // DAO utilisés par le médecin
    private final ConsultationDAO consultationDAO = new ConsultationDAOImpl();
    private final PatientDAO patientDAO = new PatientDAOImpl();
    private final CategorieConsultationDAO categorieDAO = new CategorieConsultationDAOImpl();

    // Le médecin connecté
    private final Utilisateur utilisateur;

     // Logger
    private static final Logger logger = Logger.getLogger(DoctorPanel.class.getName());
    
    // Constructeur principal appelé depuis LoginPanel
    public DoctorPanel(Utilisateur u) {
        this.utilisateur = u;
        initComponents();
        // afficher nom medecin
        lblDoctorName.setText(u.getNom() + " " + u.getPrenom());

        // config selection table -> charger details quand selection change
        tableDaily.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDaily.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    loadSelectedConsultation();
                }
            }
        });

         // charger toutes les consultations au démarrage
        loadAllConsultations();

    }
      private void loadAllConsultations() {
        try {
            LinkedList<Consultation> list = consultationDAO.findAllConsultations();
            DefaultTableModel model = (DefaultTableModel) tableDaily.getModel();
            model.setRowCount(0);

            for (Consultation c : list) {
                Patient p = patientDAO.findPatientById(c.getIdPatient());
                String patientFullName = p.getNom() + " " + p.getPrenom();
                String categorieName = categorieDAO.findCategorieConsultationById(c.getIdcategorie()).getCategorie();

                model.addRow(new Object[]{
                    c.getIdC(),                 // ID
                    c.getDateConsultation(),    // Date
                    patientFullName,            // Patient
                    categorieName,              // Catégorie
                    c.getStatus(),              // Statut
                    (c.isPaid() ? "OUI" : "NON"), // Payé
                    c.getPrix()                 // Prix
                });
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erreur chargement initial", ex);
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement initial.");
        }
    }
      /**
     * Charge et affiche les détails (zone notes) de la consultation sélectionnée.
     * Affiche : patient nom/prenom, catégorie, date, statut, notes.
     */
    private void loadSelectedConsultation() {
        int row = tableDaily.getSelectedRow();
        if (row == -1) return;

        try {
            int id = (int) tableDaily.getValueAt(row, 0);
            Consultation c = consultationDAO.findConsultationById(id);
            if (c == null) return;

            Patient p = patientDAO.findPatientById(c.getIdPatient());
            lblPatientName.setText(p.getNom() + " " + p.getPrenom());

            String categorieName = categorieDAO.findCategorieConsultationById(c.getIdcategorie()).getCategorie();
            lblCategory.setText(categorieName);

            lblDate.setText(c.getDateConsultation().toString());

            // statut et notes
            comboStatus.setSelectedItem(c.getStatus());
            txtDescription.setText(c.getDescription() == null ? "" : c.getDescription());

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erreur chargement consultation", ex);
            JOptionPane.showMessageDialog(this, "Erreur chargement consultation.");
        }
    }
    /**
     * Recherche par nom/prénom (si champ vide => affiche toutes les consultations).
     */
    private void searchByPatientKeyword(String keyword) {
        try {
            DefaultTableModel model = (DefaultTableModel) tableDaily.getModel();
            model.setRowCount(0);

            LinkedList<Patient> patients = patientDAO.findAllPatients();

            for (Patient p : patients) {
                String fullName = (p.getNom() + " " + p.getPrenom()).toLowerCase();

                if (!keyword.isEmpty()) {
                    if (!p.getNom().toLowerCase().contains(keyword)
                            && !p.getPrenom().toLowerCase().contains(keyword)
                            && !fullName.contains(keyword)) {
                        continue;
                    }
                }

                LinkedList<Consultation> list = consultationDAO.findByPatient(p.getId());

                for (Consultation c : list) {
                    String categorieName = categorieDAO.findCategorieConsultationById(c.getIdcategorie()).getCategorie();

                    model.addRow(new Object[]{
                        c.getIdC(),
                        c.getDateConsultation(),
                        p.getNom() + " " + p.getPrenom(),
                        categorieName,
                        c.getStatus(),
                        (c.isPaid() ? "OUI" : "NON"),
                        c.getPrix()
                    });
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erreur recherche patient", ex);
            JOptionPane.showMessageDialog(this, "Erreur recherche patient !");
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

        txtSearch = new javax.swing.JTextField();
        Rechercherpatient = new javax.swing.JLabel();
        btnLoad = new javax.swing.JButton();
        lblDoctorLabel = new javax.swing.JLabel();
        lblDoctorName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDaily = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblPatientName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCancelConsultation = new javax.swing.JButton();
        btnMarkDone = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        comboStatus = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 76, -1));

        Rechercherpatient.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rechercherpatient.setText("Rechercher patient");
        getContentPane().add(Rechercherpatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 130, -1));

        btnLoad.setBackground(new java.awt.Color(51, 153, 0));
        btnLoad.setForeground(new java.awt.Color(0, 0, 0));
        btnLoad.setText("Rechercher");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });
        getContentPane().add(btnLoad, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, -1));

        lblDoctorLabel.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblDoctorLabel.setText("Espace Dr");
        getContentPane().add(lblDoctorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 70, 20));

        lblDoctorName.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        getContentPane().add(lblDoctorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 150, 20));

        jLabel3.setText("───────────────────────────     Consultations  du jour  ──────────────────────────");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, -1));

        tableDaily.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Date", "Patient", "Catégorie", "Statut", "Payé", "Prix"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDaily.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tableDailyComponentAdded(evt);
            }
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                tableDailyComponentRemoved(evt);
            }
        });
        jScrollPane1.setViewportView(tableDaily);
        if (tableDaily.getColumnModel().getColumnCount() > 0) {
            tableDaily.getColumnModel().getColumn(0).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 563, 157));

        jLabel4.setText("───────────────────────────    Notes médicales     ────────────────────────────");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 600, -1));

        lblPatientName.setText("Patient ");
        getContentPane().add(lblPatientName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 159, -1));

        jLabel5.setText("Statut");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, -1, -1));

        lblCategory.setText("Catégorie");
        getContentPane().add(lblCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 159, -1));

        lblDate.setText("Date");
        getContentPane().add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 122, -1));

        jLabel8.setText("Notes");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 500, -1, -1));

        btnCancelConsultation.setForeground(new java.awt.Color(153, 51, 0));
        btnCancelConsultation.setText("ANNULEE");
        btnCancelConsultation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelConsultationActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelConsultation, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 600, -1, -1));

        btnMarkDone.setForeground(new java.awt.Color(0, 0, 153));
        btnMarkDone.setText("TERMINEE");
        btnMarkDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarkDoneActionPerformed(evt);
            }
        });
        getContentPane().add(btnMarkDone, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 600, -1, -1));

        btnSave.setForeground(new java.awt.Color(0, 153, 51));
        btnSave.setText("ENREGISTRER");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, -1, -1));

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PLANIFIEE ", "TERMINEE ", "ANNULEE" }));
        comboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusActionPerformed(evt);
            }
        });
        getContentPane().add(comboStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, -1, -1));

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, 249, -1));

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Raffraichir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, -1, -1));

        btnBack.setText("Retour");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 600, 75, -1));

        jLabel1.setText("──────────────────────────────────────────────────────────────────────");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 600, -1));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 0, 255));
        lblTitle.setText("CONSULTATIONS");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableDailyComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tableDailyComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDailyComponentAdded

    private void tableDailyComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tableDailyComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDailyComponentRemoved

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
           int row = tableDaily.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une consultation.");
            return;
        }
        try {
            int idConsultation = (int) tableDaily.getValueAt(row, 0);
            Consultation c = consultationDAO.findConsultationById(idConsultation);
            c.setDescription(txtDescription.getText());
            c.setStatus(comboStatus.getSelectedItem().toString());
            consultationDAO.updateConsultation(c);
            JOptionPane.showMessageDialog(this, "Notes médicales enregistrées.");
            loadAllConsultations();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erreur enregistrement", ex);
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement.");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
       String keyword = txtSearch.getText().trim().toLowerCase();
        searchByPatientKeyword(keyword);
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnMarkDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarkDoneActionPerformed
        // TODO add your handling code here:
         int row = tableDaily.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une consultation.");
            return;
        }
        try {
            int idConsultation = (int) tableDaily.getValueAt(row, 0);
            Consultation c = consultationDAO.findConsultationById(idConsultation);
            c.setStatus("TERMINEE");
            consultationDAO.updateConsultation(c);
            JOptionPane.showMessageDialog(this, "Consultation marquée comme terminée.");
            loadAllConsultations();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erreur MAJ statut", ex);
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour.");
        }
    }//GEN-LAST:event_btnMarkDoneActionPerformed

    private void btnCancelConsultationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelConsultationActionPerformed
        // TODO add your handling code here:
       int row = tableDaily.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionnez une consultation.");
            return;
        }
        try {
            int idConsultation = (int) tableDaily.getValueAt(row, 0);
            Consultation c = consultationDAO.findConsultationById(idConsultation);
            c.setStatus("ANNULEE");
            consultationDAO.updateConsultation(c);
            JOptionPane.showMessageDialog(this, "Consultation annulée.");
            loadAllConsultations();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erreur annulation", ex);
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour.");
        }
    }//GEN-LAST:event_btnCancelConsultationActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txtSearch.setText("");
        loadAllConsultations();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        btnLoadActionPerformed(evt);
    }//GEN-LAST:event_txtSearchActionPerformed

    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboStatusActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        // Retour au Dashboard médecin
    new DoctorDashboard(utilisateur).setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {JOptionPane.showMessageDialog(null,"Veuillez lancer l'application depuis LoginPanel.","Erreur", JOptionPane.ERROR_MESSAGE);});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Rechercherpatient;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancelConsultation;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnMarkDone;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDoctorLabel;
    private javax.swing.JLabel lblDoctorName;
    private javax.swing.JLabel lblPatientName;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tableDaily;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
