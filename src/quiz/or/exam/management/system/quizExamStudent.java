/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz.or.exam.management.system;

import project.ConnectionProvider;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author ekoya
 */
public class quizExamStudent extends javax.swing.JFrame {
    
    public String questionid="1";
    public String answer;
    public int menit=0;
    public int detik=0;
    public int marks=0;
    
    public void answerCheck(){
        String userAnswer="";
        if(fOptionA.isSelected()){
            userAnswer=fOptionA.getText();
        } else if(fOptionB.isSelected()){
            userAnswer=fOptionB.getText();
        } else if (fOptionC.isSelected()){
            userAnswer=fOptionC.getText();
        } else {
            userAnswer=fOptionD.getText();
        }
        
        if(userAnswer.equals(answer)){
            marks=marks+1;
            String marks1=String.valueOf(marks);
            fMark.setText(marks1);
        } else {
            marks=marks;
            String marks1=String.valueOf(marks);
        }
        
        //question number change
        int questionid1=Integer.parseInt(questionid);
        questionid1=questionid1+1;
        questionid=String.valueOf(questionid1);
        
        //clear option
        fOptionA.setSelected(false);
        fOptionB.setSelected(false);
        fOptionC.setSelected(false);
        fOptionD.setSelected(false);
        
        //last question
        if(questionid.equals("5")){
            bNext.setVisible(false);
        }
    }
    
    public void submit(){
        String rollNo=fRollNumber.getText();
        answerCheck();
        try{
            Connection con=ConnectionProvider.getCon();
            Statement st=con.createStatement();
            st.executeUpdate("UPDATE student SET marks='"+marks+"' WHERE rollNo='"+rollNo+"'");
            String marks1=String.valueOf(marks);
            setVisible(false);
            new successfullySubmited(marks1).setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void question(){
    try{
            Connection con=ConnectionProvider.getCon();
            Statement st = con.createStatement();
                 
            ResultSet rs1=st.executeQuery("select * from question where id='"+questionid+"'");
            
            while(rs1.next()){
                fQuestionNum.setText(rs1.getString(1));
                fQuestion.setText(rs1.getString(2));
                fOptionA.setText(rs1.getString(3));
                fOptionB.setText(rs1.getString(4));
                fOptionC.setText(rs1.getString(5));
                fOptionD.setText(rs1.getString(6));
                
                answer=rs1.getString(7);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    /**
     * Creates new form quizExamStudent
     */
    public quizExamStudent() {
        initComponents();
    }
    
    Timer time;
    public quizExamStudent(String rollNo){
        initComponents();
        fRollNumber.setText(rollNo);
        //date
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        fDate.setText(dFormat.format(date));
        
        
        //first question and students Details
        try{
            Connection con=ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select * from student where rollNo='"+rollNo+"'");
            while(rs.next()){
                fName.setText(rs.getString(2));
            }        
            ResultSet rs1=st.executeQuery("select * from question where id='"+questionid+"'");
            
            while(rs1.next()){
                fQuestionNum.setText(rs1.getString(1));
                fQuestion.setText(rs1.getString(2));
                fOptionA.setText(rs1.getString(3));
                fOptionB.setText(rs1.getString(4));
                fOptionC.setText(rs1.getString(5));
                fOptionD.setText(rs1.getString(6));
                
                answer=rs1.getString(7);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        //time program
        setLocationRelativeTo(this);
        
        time=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            fDetik.setText(String.valueOf(detik));
            fMenit.setText(String.valueOf(menit));
            
            if(detik==60){
                detik=0;
                menit++;
                if(menit==10){
                    time.stop();
                    answerCheck();
                    submit();
                }
            }
            detik++;
            }

        });
        time.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        fQuestionNum = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        fMark = new javax.swing.JLabel();
        fQuestion = new javax.swing.JLabel();
        fOptionA = new javax.swing.JRadioButton();
        fOptionB = new javax.swing.JRadioButton();
        fOptionC = new javax.swing.JRadioButton();
        fOptionD = new javax.swing.JRadioButton();
        bNext = new javax.swing.JButton();
        fSubmit = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        fDate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fMenit = new javax.swing.JLabel();
        fDetik = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fRollNumber = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel14.setText("Question Number : ");

        fQuestionNum.setText("jLabel15");

        jLabel16.setText("Your Marks : ");

        fMark.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(17, 17, 17)
                .addComponent(fQuestionNum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fMark)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(fQuestionNum)
                    .addComponent(jLabel16)
                    .addComponent(fMark))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        fQuestion.setText("Question Demo?");

        fOptionA.setText("jRadioButton1");
        fOptionA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fOptionAActionPerformed(evt);
            }
        });

        fOptionB.setText("jRadioButton2");
        fOptionB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fOptionBActionPerformed(evt);
            }
        });

        fOptionC.setText("jRadioButton3");
        fOptionC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fOptionCActionPerformed(evt);
            }
        });

        fOptionD.setText("jRadioButton4");
        fOptionD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fOptionDActionPerformed(evt);
            }
        });

        bNext.setText("Next");
        bNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextActionPerformed(evt);
            }
        });

        fSubmit.setText("Submit");
        fSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSubmitActionPerformed(evt);
            }
        });

        bClose.setText("X");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        jLabel1.setText("Date :");

        fDate.setText("jLabel2");

        jLabel2.setText("Total Time : ");

        jLabel3.setText("10 Min");

        jLabel4.setText("Time Taken : ");

        fMenit.setText("00");

        fDetik.setText("00");

        jLabel5.setText("Roll Number : ");

        fRollNumber.setText("jLabel6");

        jLabel6.setText("Name : ");

        fName.setText("jLabel7");

        jLabel7.setText("Total Question : ");

        jLabel8.setText("10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fQuestion)
                                    .addComponent(fOptionB)
                                    .addComponent(fOptionA)
                                    .addComponent(fOptionC)
                                    .addComponent(fOptionD))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bNext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fSubmit)
                                .addGap(20, 20, 20))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bClose))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fMenit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fDetik))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fRollNumber))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fName))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bClose)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(fDate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fMenit)
                    .addComponent(fDetik))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fRollNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(fQuestion)
                .addGap(31, 31, 31)
                .addComponent(fOptionA)
                .addGap(18, 18, 18)
                .addComponent(fOptionB)
                .addGap(18, 18, 18)
                .addComponent(fOptionC)
                .addGap(18, 18, 18)
                .addComponent(fOptionD)
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNext)
                    .addComponent(fSubmit))
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fOptionAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fOptionAActionPerformed
        // TODO add your handling code here:
        if(fOptionA.isSelected()){
            fOptionB.setSelected(false);
            fOptionC.setSelected(false);
            fOptionD.setSelected(false);
        }
    }//GEN-LAST:event_fOptionAActionPerformed

    private void fOptionBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fOptionBActionPerformed
        // TODO add your handling code here:
        if(fOptionB.isSelected()){
            fOptionC.setSelected(false);
            fOptionD.setSelected(false);
            fOptionA.setSelected(false);
        }
    }//GEN-LAST:event_fOptionBActionPerformed

    private void fOptionCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fOptionCActionPerformed
        // TODO add your handling code here:
        if(fOptionC.isSelected()){
            fOptionD.setSelected(false);
            fOptionA.setSelected(false);
            fOptionB.setSelected(false);
        }
    }//GEN-LAST:event_fOptionCActionPerformed

    private void fOptionDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fOptionDActionPerformed
        // TODO add your handling code here:
        if(fOptionD.isSelected()){
            fOptionA.setSelected(false);
            fOptionB.setSelected(false);
            fOptionC.setSelected(false);
        }
    }//GEN-LAST:event_fOptionDActionPerformed

    private void bNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextActionPerformed
        // TODO add your handling code here:
        answerCheck();
        question();
    }//GEN-LAST:event_bNextActionPerformed

    private void fSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSubmitActionPerformed
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(null, "Yakin?","Select",JOptionPane.YES_NO_OPTION);
        if(a==0){
            answerCheck();
            submit();
        }
    }//GEN-LAST:event_fSubmitActionPerformed

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        
    }//GEN-LAST:event_bCloseActionPerformed

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
            java.util.logging.Logger.getLogger(quizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(quizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(quizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(quizExamStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new quizExamStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bNext;
    private javax.swing.JLabel fDate;
    private javax.swing.JLabel fDetik;
    private javax.swing.JLabel fMark;
    private javax.swing.JLabel fMenit;
    private javax.swing.JLabel fName;
    private javax.swing.JRadioButton fOptionA;
    private javax.swing.JRadioButton fOptionB;
    private javax.swing.JRadioButton fOptionC;
    private javax.swing.JRadioButton fOptionD;
    private javax.swing.JLabel fQuestion;
    private javax.swing.JLabel fQuestionNum;
    private javax.swing.JLabel fRollNumber;
    private javax.swing.JButton fSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
