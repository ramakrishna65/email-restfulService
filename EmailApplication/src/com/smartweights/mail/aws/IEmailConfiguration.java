/**
 * 
 */
package com.smartweights.mail.aws;

import javax.mail.MessagingException;

import com.smartweights.mail.model.UserVO;

/**
 * @author Rama
 *
 */
public interface IEmailConfiguration {

	public void WeeklyWorkoutDetailsSendToUser(UserVO userVO) throws MessagingException;
	
	public void RecentWorkoutDetailsSendToUser(UserVO userVO) throws MessagingException;
	
	public void RegistrationDetailsSendToUser(String userEmail,String userName, String passWord) throws MessagingException;
	
	public void NFCTagDetailsSendToUser(String userEmail,String userName, String NFCTag, boolean status ) throws MessagingException;
}
