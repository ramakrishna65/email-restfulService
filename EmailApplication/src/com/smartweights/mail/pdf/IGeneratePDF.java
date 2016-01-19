/**
 * 
 */
package com.smartweights.mail.pdf;

import java.io.OutputStream;

import com.smartweights.mail.model.UserVO;

/**
 * @author lenovo
 *
 */
public interface IGeneratePDF {

	public void generateWeeklyExerciseRecordsPDF(OutputStream outputStream, UserVO userVO) throws Exception;	
	
	public void generateRecentWorkoutExerciseRecordsPDF(OutputStream outputStream, UserVO userVO) throws Exception;	
	
}
