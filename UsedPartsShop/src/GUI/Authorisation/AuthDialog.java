/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Authorisation;

import BusinessLogic.AuthoriseInfo;
import BusinessLogic.Customer;
import BusinessLogic.Seller;
import ConnectionManager.ConnectionManager;
import Exceptions.DBInteractionException;
import Service.ServerNumCars.Server;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HOME
 */
public class AuthDialog extends javax.swing.JDialog {

    /**
     * Creates new form AuthDialog
     */
    public AuthDialog(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	ConnectionManager.init("jdbc:derby://localhost:1527/D:/Files/10sem/Software Arch/repo/upsDB_ver2.0/upsDB_ver2.0","client", "12345");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextFieldPass = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldMail = new javax.swing.JTextField();
        jToggleButtonLogIn = new javax.swing.JToggleButton();
        jToggleButtonCreateAcc = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jRadioButtonCust = new javax.swing.JRadioButton();
        jRadioButtonSell = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextFieldPass.setText("12345");
        jTextFieldPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPassActionPerformed(evt);
            }
        });

        jLabel1.setText("Password:");

        jLabel2.setText("Email:");

        jTextFieldMail.setText("seller@mail.ru");

        jToggleButtonLogIn.setText("Log in");
        jToggleButtonLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonLogInActionPerformed(evt);
            }
        });

        jToggleButtonCreateAcc.setText("Create accaunt");
        jToggleButtonCreateAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCreateAccActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        jLabel3.setText("Hello! You'r using \"Used Part Shop\" application");

        buttonGroup1.add(jRadioButtonCust);
        jRadioButtonCust.setSelected(true);
        jRadioButtonCust.setText("As customer");

        buttonGroup1.add(jRadioButtonSell);
        jRadioButtonSell.setText("As seller");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToggleButtonCreateAcc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButtonLogIn, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                    .addComponent(jTextFieldMail)
                    .addComponent(jTextFieldPass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonCust)
                    .addComponent(jRadioButtonSell))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonCust))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonSell))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonCreateAcc)
                    .addComponent(jToggleButtonLogIn))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPassActionPerformed

    private void jToggleButtonCreateAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCreateAccActionPerformed
        new NewUserDialog(null, true).setVisible(true);
    }//GEN-LAST:event_jToggleButtonCreateAccActionPerformed

    private void jToggleButtonLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLogInActionPerformed
	try {
	    long ai_id = AuthoriseInfo.search(this.jTextFieldPass.getText(), this.jTextFieldMail.getText());
	    if(this.jRadioButtonCust.isSelected()){
		Customer cust = Customer.getByAI(ai_id);
		if (cust!=null){
		    this.dispose();
		    new MainCust(cust).setVisible(true);
		}
		else{
		    JOptionPane.showMessageDialog(this, "Wrong email or password" , "Error", JOptionPane.ERROR_MESSAGE);
		}
	    }
	    else{
		Seller sell = Seller.getByAI(ai_id);
		if(sell!=null){
		    this.dispose();
		    new MainSeller(sell).setVisible(true);
		}else{
		    JOptionPane.showMessageDialog(this, "Wrong email or password" , "Error", JOptionPane.ERROR_MESSAGE);
		}
	    }
	} catch (DBInteractionException ex) {
	    JOptionPane.showMessageDialog(this, "Wrong email or password\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    Logger.getLogger(AuthDialog.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
	}
    }//GEN-LAST:event_jToggleButtonLogInActionPerformed

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
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(AuthDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(AuthDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(AuthDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(AuthDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the dialog */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		AuthDialog dialog = new AuthDialog(new javax.swing.JFrame(), true);
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		    }
		});
		Server serv = new Server();
		serv.start();
		dialog.setVisible(true);
		System.out.print("After setVisible");
	    }
	});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButtonCust;
    private javax.swing.JRadioButton jRadioButtonSell;
    private javax.swing.JTextField jTextFieldMail;
    private javax.swing.JTextField jTextFieldPass;
    private javax.swing.JToggleButton jToggleButtonCreateAcc;
    private javax.swing.JToggleButton jToggleButtonLogIn;
    // End of variables declaration//GEN-END:variables
}