/**
 * 
 */
package com.smartweights.mail.model;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Rama
 *
 */
public class ExerciseRecord extends CommonUtilsVO implements Comparable<ExerciseRecord> {

		
	@JsonProperty("id") 
	private String id;	

	@JsonProperty("unixTime")
	private float unixTime;
	
	@JsonProperty("exerciseDefinitionId")
	private String exerciseDefinitionId;
	
	@JsonProperty("smartWeightsUserId")
	private String smartWeightsUserId;
	
	@JsonProperty("exerciseMachineId")
	private String exerciseMachineId;
	
	@JsonProperty("fitnessCenterId")
	private String fitnessCenterId;
	
	@JsonProperty("exerciseDefinition")
	private ExerciseDefinition exerciseDefinition;
	
	@JsonProperty("sets") 
	private ArrayList<SetVO> set = new ArrayList<SetVO>();
		
	private String dispalyDate;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the unixTime
	 */
	public float getUnixTime() {
		return unixTime;
	}

	/**
	 * @param unixTime the unixTime to set
	 */
	public void setUnixTime(float unixTime) {
		this.unixTime = unixTime;
	}

	/**
	 * @return the exerciseDefinitionId
	 */
	public String getExerciseDefinitionId() {
		return exerciseDefinitionId;
	}

	/**
	 * @param exerciseDefinitionId the exerciseDefinitionId to set
	 */
	public void setExerciseDefinitionId(String exerciseDefinitionId) {
		this.exerciseDefinitionId = exerciseDefinitionId;
	}

	/**
	 * @return the smartWeightsUserId
	 */
	public String getSmartWeightsUserId() {
		return smartWeightsUserId;
	}

	/**
	 * @param smartWeightsUserId the smartWeightsUserId to set
	 */
	public void setSmartWeightsUserId(String smartWeightsUserId) {
		this.smartWeightsUserId = smartWeightsUserId;
	}

	/**
	 * @return the exerciseMachineId
	 */
	public String getExerciseMachineId() {
		return exerciseMachineId;
	}

	/**
	 * @param exerciseMachineId the exerciseMachineId to set
	 */
	public void setExerciseMachineId(String exerciseMachineId) {
		this.exerciseMachineId = exerciseMachineId;
	}

	/**
	 * @return the fitnessCenterId
	 */
	public String getFitnessCenterId() {
		return fitnessCenterId;
	}

	/**
	 * @param fitnessCenterId the fitnessCenterId to set
	 */
	public void setFitnessCenterId(String fitnessCenterId) {
		this.fitnessCenterId = fitnessCenterId;
	}

	/**
	 * @return the exerciseDefinition
	 */
	public ExerciseDefinition getExerciseDefinition() {
		return exerciseDefinition;
	}

	/**
	 * @param exerciseDefinition the exerciseDefinition to set
	 */
	public void setExerciseDefinition(ExerciseDefinition exerciseDefinition) {
		this.exerciseDefinition = exerciseDefinition;
	}

	/**
	 * @return the set
	 */
	public ArrayList<SetVO> getSet() {
		return set;
	}

	/**
	 * @param set the set to set
	 */
	public void setSet(ArrayList<SetVO> set) {
		this.set = set;
	}

	/**
	 * @return the dispalyDate
	 */
	public String getDispalyDate() {
		return dispalyDate;
	}

	/**
	 * @param dispalyDate the dispalyDate to set
	 */
	public void setDispalyDate(String dispalyDate) {
		this.dispalyDate = dispalyDate;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	 @Override
	public int compareTo(ExerciseRecord exrcise) {		
		Date dt = new java.util.Date((long) getUnixTime() * 1000);
		Date dt2 = new java.util.Date((long) exrcise.getUnixTime() * 1000);
		return dt.compareTo(dt2);
	} 
	

}
