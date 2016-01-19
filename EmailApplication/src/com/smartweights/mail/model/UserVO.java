package com.smartweights.mail.model;

import java.util.ArrayList;
import java.util.List;

public class UserVO extends CommonUtilsVO {
	
	private Object userId;
	
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private String email;	
	
	private List<ExerciseRecord>  exerciseRecord;
		

	/**
	 * @return the userId
	 */
	public Object getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Object userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the exerciseRecord
	 */
	public List<ExerciseRecord> getExerciseRecord() {
		return exerciseRecord;
	}

	/**
	 * @param exerciseRecord the exerciseRecord to set
	 */
	public void setExerciseRecord(List<ExerciseRecord> exerciseRecord) {
		this.exerciseRecord = exerciseRecord;
	}

	

	
	
}
