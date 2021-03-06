/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BusinessLogic.Bid;
import BusinessLogic.Car.Car;
import BusinessLogic.Customer;
import BusinessLogic.Tender;
import Exceptions.DBInteractionException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HOME
 */
public class MainCust extends javax.swing.JFrame {

    /**
     * Creates new form MainCust
     */
    public MainCust(Customer c) {
	initComponents();
	cust = c;
	this.jLabel1.setText("Hello, " + cust.getNickname());
	try {
	    List<Car> car_list = c.getCarsList();
	    this.jListCars.setListData(car_list.toArray());
	} catch (DBInteractionException ex) {
	    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    Logger.getLogger(MainCust.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
	}
    }
    
    Customer cust;
    
    private void carListReload(){
	this.jListCars.removeAll();
	try {
	    this.jListCars.setListData(cust.getCarsList().toArray());
	} catch (DBInteractionException ex) {
	    Logger.getLogger(MainCust.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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

        jPanelCars = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCars = new javax.swing.JList();
        jButtonAddCar = new javax.swing.JButton();
        jButtonRemoveCar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanelTenders = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTenders = new javax.swing.JList();
        jButtonAdd = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jPanelBids = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListBids = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jButtonShowBids = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelCars.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("My cars");

        jListCars.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListCars.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListCarsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListCars);

        jButtonAddCar.setText("Add car");
        jButtonAddCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCarActionPerformed(evt);
            }
        });

        jButtonRemoveCar.setText("Remove");
        jButtonRemoveCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveCarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCarsLayout = new javax.swing.GroupLayout(jPanelCars);
        jPanelCars.setLayout(jPanelCarsLayout);
        jPanelCarsLayout.setHorizontalGroup(
            jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCarsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonRemoveCar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAddCar)))
                .addContainerGap())
        );
        jPanelCarsLayout.setVerticalGroup(
            jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCarsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddCar)
                    .addComponent(jButtonRemoveCar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Hello, ");

        jPanelTenders.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Car's tenders");

        jListTenders.setToolTipText("");
        jListTenders.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListTendersValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListTenders);

        jButtonAdd.setText("New tender");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonClose.setText("Close tender");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTendersLayout = new javax.swing.GroupLayout(jPanelTenders);
        jPanelTenders.setLayout(jPanelTendersLayout);
        jPanelTendersLayout.setHorizontalGroup(
            jPanelTendersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTendersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTendersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTendersLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelTendersLayout.setVerticalGroup(
            jPanelTendersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTendersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTendersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonClose)
                    .addComponent(jButtonAdd))
                .addContainerGap())
        );

        jPanelBids.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane3.setViewportView(jListBids);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Bids");

        jButtonShowBids.setText("Show seller");
        jButtonShowBids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowBidsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBidsLayout = new javax.swing.GroupLayout(jPanelBids);
        jPanelBids.setLayout(jPanelBidsLayout);
        jPanelBidsLayout.setHorizontalGroup(
            jPanelBidsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBidsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBidsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBidsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonShowBids)))
                .addContainerGap())
        );
        jPanelBidsLayout.setVerticalGroup(
            jPanelBidsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBidsLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonShowBids)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelTenders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCars, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBids, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelCars, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelTenders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanelBids, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCarActionPerformed
        CarAdding nc = new CarAdding(this, true, this.cust);
	nc.setVisible(true);
	if(nc.isAdded){
	    this.carListReload();
	}
    }//GEN-LAST:event_jButtonAddCarActionPerformed

    private void jButtonRemoveCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveCarActionPerformed
	Car c;
	if(this.jListCars.getSelectedValue()!=null){
	    c = (Car)this.jListCars.getSelectedValue();
	    try {
		cust.removeCar(c);
		this.carListReload();
		JOptionPane.showMessageDialog(this, "Car deleted" , "Info", JOptionPane.INFORMATION_MESSAGE);
	    } catch (DBInteractionException ex){
		JOptionPane.showMessageDialog(this, "Can't remove car:\n" , "Error", JOptionPane.ERROR_MESSAGE);
		Logger.getLogger(MainCust.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
	    }
	}
	else{
	    JOptionPane.showMessageDialog(this, "Please, select car first", "Warning", JOptionPane.WARNING_MESSAGE);
	}
    }//GEN-LAST:event_jButtonRemoveCarActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
       if((Car)this.jListCars.getSelectedValue()==null){
	   JOptionPane.showMessageDialog(this, "Select car first", "Warning", JOptionPane.WARNING_MESSAGE);
       }
       else{
	   new TenderAdding(this, rootPaneCheckingEnabled,  (Car)this.jListCars.getSelectedValue(), this.cust).setVisible(true);
	   this.tenderListReload();
       }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jListCarsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListCarsValueChanged
       if (evt.getValueIsAdjusting()){
	   this.tenderListReload();
       }
    }//GEN-LAST:event_jListCarsValueChanged

    private void jListTendersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListTendersValueChanged
        if(evt.getValueIsAdjusting()){
	    this.jListBids.removeAll();
	    try {
		this.jListBids.setListData(((Tender)this.jListTenders.getSelectedValue()).getBidsList().toArray());
	    } catch (DBInteractionException ex) {
		Logger.getLogger(MainCust.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
	    }
	}
    }//GEN-LAST:event_jListTendersValueChanged

    private void jButtonShowBidsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowBidsActionPerformed
        if((Bid)this.jListBids.getSelectedValue()!=null){
	    new BidShow(this, rootPaneCheckingEnabled, (Bid)this.jListBids.getSelectedValue()).setVisible(true);
	}else{
	    JOptionPane.showMessageDialog(this, "Select bid first", "Warning", JOptionPane.WARNING_MESSAGE);
	}
    }//GEN-LAST:event_jButtonShowBidsActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        Tender t;
	if(this.jListTenders.getSelectedValue()!=null){
	    t = (Tender)this.jListTenders.getSelectedValue();
	    try {
		cust.removeTender(t);
		this.tenderListReload();
		JOptionPane.showMessageDialog(this, "Tender deleted" , "Info", JOptionPane.INFORMATION_MESSAGE);
	    } catch (DBInteractionException ex){
		JOptionPane.showMessageDialog(this, "Can't remove tender:\n" , "Error", JOptionPane.ERROR_MESSAGE);
		Logger.getLogger(MainCust.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
	    }
	}
	else{
	    JOptionPane.showMessageDialog(this, "Please, select tender first", "Warning", JOptionPane.WARNING_MESSAGE);
	}
    }//GEN-LAST:event_jButtonCloseActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddCar;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonRemoveCar;
    private javax.swing.JButton jButtonShowBids;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jListBids;
    private javax.swing.JList jListCars;
    private javax.swing.JList jListTenders;
    private javax.swing.JPanel jPanelBids;
    private javax.swing.JPanel jPanelCars;
    private javax.swing.JPanel jPanelTenders;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    private void tenderListReload() {
	this.jListTenders.removeAll();
	try {
	    this.jListTenders.setListData(cust.getTendersForCar((Car)this.jListCars.getSelectedValue()).toArray());
	} catch (DBInteractionException ex) {
	    Logger.getLogger(MainCust.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
	}
    }
}
