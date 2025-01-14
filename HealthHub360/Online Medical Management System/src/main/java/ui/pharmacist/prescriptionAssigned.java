/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.pharmacist;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import modelPharmacyEnterprise.Medicine;

import modelPharmacyEnterprise.Prescription;

import modelPharmacyEnterpriseDAO.PrescriptionDAO;
import modelPharmacyEnterpriseService.MedicineService;
import modelPharmacyEnterpriseService.PrescriptionMedicineService;
import modelPharmacyEnterpriseService.PrescriptionService;

/**
 *
 * @author keerthichandrakanth
 */
public class prescriptionAssigned extends javax.swing.JPanel {
     private int prescriptionId;
     JPanel mainWorkArea;
   private List<Medicine> medicines; // Declare medicines here as a class variable

    /**
     * Creates new form prescriptionAssigned
     */
    public prescriptionAssigned(JPanel mainWorkArea,int prescriptionId) {
        this.prescriptionId = prescriptionId;
        initComponents();
        populateMedicineTable();  // Populate the table

        }
    
      // Method to fetch medicines and populate JTable

        private void populateMedicineTable() {
                // Fetch medicines for the given prescription ID
                 PrescriptionMedicineService service = new PrescriptionMedicineService();
                 List<Medicine> medicines = service.getMedicinesForPrescription(prescriptionId);
                 txtPrescription.setText(String.valueOf(prescriptionId));
                 if (medicines != null) {
                    DefaultTableModel model = (DefaultTableModel) txtPrescriptionMedicine.getModel();
                    model.setRowCount(0);  // Clear any previous rows

                    // Add each medicine to the table
                    for (Medicine medicine : medicines) {
                        model.addRow(new Object[]{medicine.getName(), medicine.getQuantityInStock()});
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error fetching medicines", "Error", JOptionPane.ERROR_MESSAGE);
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

        txtPrescription = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPrescriptionMedicine = new javax.swing.JTable();
        btnMarkasDone = new javax.swing.JButton();


        txtPrescription.setEditable(false);
        txtPrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrescriptionActionPerformed(evt);
            }
        });

        jLabel1.setText("        Prescription ID");

        txtPrescriptionMedicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Medicine", "Quantity"
            }
        ));
        jScrollPane1.setViewportView(txtPrescriptionMedicine);

        btnMarkasDone.setText("Mark as Done");
        btnMarkasDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarkasDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrescription, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(btnMarkasDone)))

                .addGap(194, 194, 194))

        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, txtPrescription});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPrescription))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMarkasDone)

                .addGap(82, 82, 82))

        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, txtPrescription});

    }// </editor-fold>//GEN-END:initComponents


    private void btnMarkasDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarkasDoneActionPerformed
  

    // Create instances of MedicineService and PrescriptionService
    MedicineService medicineService = new MedicineService();
    PrescriptionService prescriptionService = new PrescriptionService(new PrescriptionDAO());
    

    // Fetch the prescription by ID
    Prescription prescription = prescriptionService.getPrescriptionById(prescriptionId);
     
    // Check if the prescription exists and its status
    if (prescription != null) {
        if ("Completed".equals(prescription.getStatus())) {
            JOptionPane.showMessageDialog(this, "This prescription has already been marked as complete.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if the prescription is already complete
        }
    } else {
        JOptionPane.showMessageDialog(this, "Prescription not found.", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Exit the method if the prescription is not found
    }

    // Fetch the medicine stock updates (assuming this is a valid method)
    List<Object[]> stockUpdates = medicineService.fetchMedicineStockUpdateData(prescriptionId);
    if (stockUpdates.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No medicines found for this prescription.", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Exit if no medicines found
    }

    // Check payment status
        if (!"Paid".equals(prescription.getPaymentStatus())) {
            JOptionPane.showMessageDialog(this, "Payment not made. Please complete the payment before proceeding.", 
                    "Payment Required", JOptionPane.WARNING_MESSAGE);
            return; // Exit if payment is not made
        }
    
    // Process each medicine stock update
    for (Object[] data : stockUpdates) {
        int medicineId = (int) data[0];
        int prescribedQuantity = (int) data[1];
        int currentStock = (int) data[2];
        int newStock = currentStock - prescribedQuantity;

        // Check if there's enough stock
        if (newStock < 0) {
            JOptionPane.showMessageDialog(this, "Insufficient stock for medicine ID: " + medicineId, "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if stock is insufficient
        }

        // Update the stock
        boolean updated = medicineService.updateMedicineStock(medicineId, newStock);
        if (!updated) {
            JOptionPane.showMessageDialog(this, "Failed to update stock for medicine ID: " + medicineId, "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if updating stock fails
        }
    }

    // Mark the prescription as "Completed"
    prescriptionService.updatePrescriptionStatus(prescriptionId, "Completed");
    JOptionPane.showMessageDialog(this, "Prescription marked as complete", "Success", JOptionPane.INFORMATION_MESSAGE);

    // Update the medicine table (populate with fresh data)
    populateMedicineTable();

    }//GEN-LAST:event_btnMarkasDoneActionPerformed

    private void txtPrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrescriptionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMarkasDone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtPrescription;
    private javax.swing.JTable txtPrescriptionMedicine;
    // End of variables declaration//GEN-END:variables
}


