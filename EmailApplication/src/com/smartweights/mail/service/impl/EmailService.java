package com.smartweights.mail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;


import com.smartweights.mail.dao.interfaces.IEmailDao;
import com.smartweights.mail.model.ExerciseRecord;
import com.smartweights.mail.model.UserVO;
import com.smartweights.mail.service.interfaces.IEmailService;

@Service("emailService")
public class EmailService implements IEmailService {

	@Autowired    
    private IEmailDao emailDao;
	
	
	public UserVO getLiveWorkOutDetails()  {
		return emailDao.getLiveWorkOutDetails();
	}
	
	public UserVO getWeeklyWorkOutDetails()  {
		return emailDao.getWeeklyWorkOutDetails();
	}
	public List<ExerciseRecord> getExerciseRecordsFromRestFullService(String adminID, String REST_SERVICE_URI){
		return emailDao.getExerciseRecordsFromRestFullService(adminID,REST_SERVICE_URI);
	}
	
}
