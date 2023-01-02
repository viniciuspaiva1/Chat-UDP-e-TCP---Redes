    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;



public class ChatUDPCliente extends javax.swing.JFrame {
    static byte [] recebido;
    static byte [] enviados;
    String msg_cliente;
    static DatagramSocket sendMsg;
    InetAddress endIP ;
   // static DatagramPacket enviaPacote;
    static DatagramPacket recebeDados ;
    static DatagramSocket pctR;
    static String mess;
    int i =0;
        public ChatUDPCliente() throws SocketException, UnknownHostException {
            initComponents();
        }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoE = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoR = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente UDP");
        setResizable(false);

        campoR.setEditable(false);
        campoR.setColumns(20);
        campoR.setRows(5);
        campoR.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(campoR);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoE, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(i == 0){
             String nomeC; 
               try{
                msg_cliente= campoE.getText();
                nomeC = msg_cliente;
                enviados = msg_cliente.getBytes();
                endIP = InetAddress.getByName("127.0.0.1");
                DatagramPacket enviaPacote = new DatagramPacket(enviados, enviados.length, endIP, 6666); 
                sendMsg.send(enviaPacote);
                campoE.setText("");
                i++;
               }catch (IOException ex) {
                } 
        }else {
               try {
                msg_cliente= campoE.getText();
                enviados = msg_cliente.getBytes();
                endIP = InetAddress.getByName("127.0.0.1");
                DatagramPacket enviaPacote = new DatagramPacket(enviados, enviados.length, endIP, 6666); 
                sendMsg.send(enviaPacote);
                campoE.setText("");
     
             }catch (IOException ex) {
              } 
        }
                
    }//GEN-LAST:event_jButton1ActionPerformed

   
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
            java.util.logging.Logger.getLogger(ChatUDPCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatUDPCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatUDPCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatUDPCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ChatUDPCliente().setVisible(true);
                    campoR.append("Por Favor Digite o Seu Nome:" + "\n");
                    
                } catch (SocketException ex) {
                    //Logger.getLogger(ChatUDPCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                   // Logger.getLogger(ChatUDPCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
         try{
        
        pctR = new DatagramSocket (5555);
        sendMsg = new DatagramSocket();
        recebido = new byte [2048];
        enviados = new byte [2048];
       
       
        while(true){
         DatagramPacket pacote = new DatagramPacket(recebido, recebido.length);
         pctR.receive(pacote);
         String msg_client = new String(pacote.getData());
         mess = msg_client;
         campoR.append("Servidor:"+mess + "\n");
         //recebido = null;
         //enviados = null;
        }
       
       
       } catch(Exception e){} 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoE;
    private static javax.swing.JTextArea campoR;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
