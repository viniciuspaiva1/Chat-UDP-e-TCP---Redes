package javaapplication1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChatUDPServer extends javax.swing.JFrame {
  static  byte[] recebido;
  static int i = 0;
  static  byte[] enviados;
  static String nomeC;
    String msg;
    InetAddress endIP ;
   //static DatagramPacket enviaPacote;
   static DatagramSocket clienteSocket;
   static DatagramSocket pctR;
   
    public ChatUDPServer() throws SocketException {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enviar = new javax.swing.JButton();
        campoE = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoR = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        campoR.setEditable(false);
        campoR.setColumns(20);
        campoR.setRows(5);
        jScrollPane1.setViewportView(campoR);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(enviar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enviar)
                    .addComponent(campoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        try {
            msg = campoE.getText();
            enviados = msg.getBytes();
            endIP = InetAddress.getByName("127.0.0.1");
            DatagramPacket enviaPacote = new DatagramPacket(enviados, enviados.length, endIP, 5555); 
            clienteSocket.send(enviaPacote);
            campoR.append(msg+ "\n");
            campoE.setText("");
        } 
        catch (UnknownHostException ex) {
            Logger.getLogger(ChatUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatUDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }

   
    }//GEN-LAST:event_enviarActionPerformed
  
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
            java.util.logging.Logger.getLogger(ChatUDPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatUDPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatUDPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatUDPServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ChatUDPServer().setVisible(true);
                } catch (SocketException ex) {
                    Logger.getLogger(ChatUDPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
        try{
        pctR = new DatagramSocket(6666); 
        clienteSocket = new DatagramSocket();      
        recebido = new byte [1024];
        enviados = new byte [1024];
            String mess =" ";
                while (true) {
                    if(i==0){
                    DatagramPacket pacote = new DatagramPacket(recebido, recebido.length);
                    pctR.receive(pacote);
                    String msg_cliente = new String(pacote.getData());
                    mess = msg_cliente;
                    nomeC= msg_cliente;
                    campoR.append("Cliente conectado: "+nomeC+"\n");
                    i++;
                    
                     
                 }else{
                      
                    DatagramPacket pacote = new DatagramPacket(recebido, recebido.length);
                    pctR.receive(pacote);
                    String msg_cliente1 = new String(pacote.getData());
                    mess = msg_cliente1;
                    campoR.append(nomeC+" :" +"\n");
                    campoR.append(msg_cliente1+"\n");
                    }         
            }
   }catch(Exception e){}
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoE;
    private static javax.swing.JTextArea campoR;
    private javax.swing.JButton enviar;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
