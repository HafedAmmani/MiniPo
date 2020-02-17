/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;
import com.minipo.Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*; 
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sinda
 */
public class JavaEmailUtil {
    public static void sendEmail(String recepient) throws Exception{
        System.out.println("Preparing to send email");
           Properties props = new Properties();
           
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.starttls.enable","true");
           props.put("mail.smtp.host", "smtp.gmail.com");
           props.put("mail.smtp.port", "25" );
           
             String MyAccountEmail="projetminipo@gmail.com"; 
             String password="projetminipo2020";
             
             Session session = Session.getInstance(props, new Authenticator() {
               @Override
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(MyAccountEmail, password);
               }
                 
});
             
             Message message = prepareMesssage(session,MyAccountEmail, recepient);
             Transport.send(message);
             System.out.println("Message sent sucessfully");
    }

     
    
     
    private static Message prepareMesssage(Session session, String MyAccountEmail, String recepient) {
        try {
            Connection con;
            Statement ste;
            con = DataBase.getInstance().getConnection();
            ste=con.createStatement();
            ResultSet rs=ste.executeQuery("select * from livreur");
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MyAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My First email from Java App");
            message.setText("Hey there \n");        
//message.setContent(rs, recepient);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaEmailUtil.class.getName()).log(Level.SEVERE, null, ex);
     
       }
        return null;
    }

}
