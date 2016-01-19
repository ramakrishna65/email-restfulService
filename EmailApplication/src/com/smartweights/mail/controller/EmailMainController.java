package com.smartweights.mail.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.smartweights.mail.aws.IEmailConfiguration;
import com.smartweights.mail.model.ExerciseRecord;
import com.smartweights.mail.model.UserVO;
import com.smartweights.mail.service.interfaces.IEmailService;

@Controller
public class EmailMainController {

	
	 @Autowired
	 private IEmailConfiguration emailConfiguration;
	 
	 @Autowired
	 private IEmailService eMailService;
	 
	 
	@RequestMapping(value="/sendmail",  method = RequestMethod.POST)    
	public ModelAndView SendWeeklyWorkoutDetailsMailToUser(@RequestParam("SITE_URL") String SITE_URL,@RequestParam("userEmail") String userEmail,@RequestParam("adminID") String adminID, @RequestParam("userName") String userName) throws JSONException{
		
		String message="";	
		System.out.print("\n ----------------");
		System.out.print(SITE_URL);
		System.out.print("\n ----------------");
		System.out.print(adminID);
		System.out.print("\n ----------------");
		System.out.print(userEmail);
		System.out.print("\n ----------------");
		System.out.print(userName);
				
		try {	
			UserVO userVO = new UserVO();
			userVO.setEmail(userEmail);
			userVO.setUserName(userName);
			List<ExerciseRecord> response  = eMailService.getExerciseRecordsFromRestFullService(adminID, SITE_URL);
			System.out.println(response);
			userVO.setExerciseRecord(response);
			/*System.out.print("\n ----------------");
			System.out.print(SITE_URL);
			System.out.print("\n ----------------");
			System.out.print(adminID);
			System.out.print("\n ----------------");
			System.out.print(userEmail);*/	
			
		 	
			emailConfiguration.WeeklyWorkoutDetailsSendToUser(userVO);
			message ="succfully send mail";
		} catch (Exception e) {			
			e.printStackTrace();
			message ="Error to send mail ";
		}
				
		return new ModelAndView("sendmail", "message", message);  
	}
	
	@RequestMapping("/recentWorkoutDetails")  
	public ModelAndView SendRecentWorkoutDetailsMailToUser(@RequestParam("SITE_URL") String SITE_URL,@RequestParam("userEmail") String userEmail,@RequestParam("adminID") String adminID, @RequestParam("userName") String userName) throws JSONException{
		
		String message="";		
		
		try {			
			UserVO userVO = new UserVO();
			userVO.setEmail(userEmail);
			userVO.setUserName(userName);
			List<ExerciseRecord> response  = eMailService.getExerciseRecordsFromRestFullService(adminID, SITE_URL);
			System.out.println(response);
			userVO.setExerciseRecord(response);
			emailConfiguration.RecentWorkoutDetailsSendToUser(userVO);
			message ="succfully send mail";
		} catch (Exception e) {			
			e.printStackTrace();
			message ="Error to send mail ";
		}
				
		return new ModelAndView("sendmail", "message", message);  
	}
	
	@RequestMapping("/registriaonSucess")  
	public ModelAndView SendRegistrationDetailsMailToUser(@RequestParam("userEmail") String userEmail, @RequestParam("userName") String userName, @RequestParam("passWord") String passWord) throws JSONException{
		
		String message="";		
		
		try {	
			emailConfiguration.RegistrationDetailsSendToUser(userEmail,userName, passWord );
			message ="succfully send mail";
		} catch (Exception e) {			
			e.printStackTrace();
			message ="Error to send mail ";
		}
				
		return new ModelAndView("sendmail", "message", message);  
	}
	
	@RequestMapping("/nfcTagDetails")  
	public ModelAndView NFCTagDetailsSendToUser(@RequestParam("userEmail") String userEmail, @RequestParam("userName") String userName, @RequestParam("NFCTag") String NFCTag,@RequestParam("status") boolean status) throws JSONException{
		
		String message="";		
		
		try {			
			emailConfiguration.NFCTagDetailsSendToUser(userEmail,userName, NFCTag, status);
			message ="succfully send mail";
		} catch (Exception e) {			
			e.printStackTrace();
			message ="Error to send mail ";
		}
				
		return new ModelAndView("sendmail", "message", message);  
	}
	
	
	@RequestMapping("/propertiestCall")  
	public ModelAndView PropertiesCall() {
		
		String message="";		
		
		
				
		return new ModelAndView("sendmail", "message", message);  
	}
	
	
	
}
