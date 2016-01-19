/**
 * 
 */
package com.smartweights.mail.html;

import java.io.OutputStream;

import org.springframework.stereotype.Component;

/**
 * @author Rama
 *
 */

@Component("generateHTMLContent")
public interface IGenerateHTMLContent {

	public void UserRegistrationMail() throws Exception;
	
	public void UserNFCTagDetailsMail() throws Exception;
	
}
