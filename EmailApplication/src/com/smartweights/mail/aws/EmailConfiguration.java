/**
 * 
 */
package com.smartweights.mail.aws;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.smartweights.mail.html.IGenerateHTMLContent;
import com.smartweights.mail.model.UserVO;
import com.smartweights.mail.pdf.IGeneratePDF;

/**
 * @author Rama
 *
 */
@Component("emailConfiguration")
public class EmailConfiguration implements IEmailConfiguration {
	
	String toAddress = "praveenkashyap@gmail.com"; // sender email 
	String BCCAddress = "designerkrishna@gmail.com"; // sender email 	
	String fromAddress = "rama@nelluriitsolutions.com"; // receiver email 
	String liveWorkOutSubject ="Current Wokout Detail from SmartWeights ";	
	String weeklyWorkOutSubject ="Weekly Wokout Detail from SmartWeights ";	
	String emailMessage= "This email was sent through the SmartWeight Application";
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private IGeneratePDF pdfGenaration;
	
		
	public void WeeklyWorkoutDetailsSendToUser(UserVO userVO) throws MessagingException{
		final UserVO  uservo = userVO;
		mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {				
				ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
				pdfGenaration.generateWeeklyExerciseRecordsPDF(outputStream, uservo);					
				mailConfigurationWithAttachment(outputStream,  mimeMessage, weeklyWorkOutSubject);	
				
				outputStream.close();
			}
		});			
	}
	
	public void RecentWorkoutDetailsSendToUser(UserVO userVO) throws MessagingException{
		final UserVO  uservo = userVO;
		mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {				
				ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
				pdfGenaration.generateRecentWorkoutExerciseRecordsPDF(outputStream, uservo);					
				mailConfigurationWithAttachment(outputStream,  mimeMessage, liveWorkOutSubject);					
				outputStream.close();
			}
		});			
	}
	
	public void RegistrationDetailsSendToUser(String userEmail,String userName, String passWord) throws MessagingException{	
		String subject = "Thank you for registering on	SmartWeights";
		String conent ="<h3>Dear"+" "+ userName+"</h3> <br/>Thank you for registering with <a href='http://smartweights.nelluriitsolutions.com/'>Smart Weights</a> <br/><br/><br/> ";
		conent += "<b>User Name : </b> "+ userName+"<br/> <b>Passowrd : </b> "+passWord+"<br/>";
		
		mailConfigurationWithBodyText(subject,conent,userEmail);	
	}
	
	public void NFCTagDetailsSendToUser(String userEmail, String userName, String NFCTag, boolean status) throws MessagingException{		
		String subject = "NFC Tag Details";
		String conent="";
		if(status){
			conent ="<h3>Dear"+" "+  userName +"</h3> <br/>Your NFC Tag is created sucessfully. Please find the below details <br/> ";
		}else {
			conent ="<h3>Dear"+" "+  userName +"</h3> <br/>Your NFC Tag is Updated sucessfully. Please find the below details <br/> ";
		}
		conent += "<b>NFC Tag : </b>"+ NFCTag + "<br/> ";
		
		mailConfigurationWithBodyText(subject,conent,userEmail);
	}
	
	
	
	private void mailConfigurationWithAttachment(ByteArrayOutputStream outputStream,   MimeMessage mimeMessage, String subject) throws MessagingException {
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");		
		byte[] bytes = outputStream.toByteArray();
		DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
		
		messageHelper.setFrom(fromAddress); 
		messageHelper.setTo(toAddress); // pdfGenaration.getCurrentUserEmail();
		messageHelper.setCc(BCCAddress);
		messageHelper.setSubject(subject); // 
		messageHelper.setText(emailMessage);
		String attachName = "weekly.pdf";
		
		messageHelper.addAttachment(attachName, dataSource);
	}
	
	private void mailConfigurationWithBodyText(String subject, String content, String userEmail){
		try{
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			messageHelper.setFrom(fromAddress); 
			mimeMessage.setContent(content,"text/html");
			messageHelper.setTo(toAddress); // pdfGenaration.getCurrentUserEmail();
			messageHelper.setCc(BCCAddress);
			//messageHelper.setTo(userEmail);
			//messageHelper.setCc(BCCAddress);
			messageHelper.setSubject(subject); // 
			
			mailSender.send(mimeMessage);
		}catch(MessagingException ex){
			 Logger.getLogger(EmailConfiguration.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	
}
