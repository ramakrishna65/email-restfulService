/**
 * 
 */
package com.smartweights.mail.service.interfaces;

import java.util.List;

import org.springframework.http.HttpEntity;

import com.smartweights.mail.model.ExerciseRecord;
import com.smartweights.mail.model.UserVO;

/**
 * @author Rama
 *
 */
public interface IEmailService {
	
	public UserVO getLiveWorkOutDetails();
	
	public UserVO getWeeklyWorkOutDetails();
	
	public List<ExerciseRecord> getExerciseRecordsFromRestFullService(String adminID, String REST_SERVICE_URI);
}
