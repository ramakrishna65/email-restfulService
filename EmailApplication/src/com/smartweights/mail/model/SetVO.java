/**
 * 
 */
package com.smartweights.mail.model;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Rama
 *
 */
public class SetVO extends CommonUtilsVO {
	
	@JsonProperty("id") 
	private String setId;
	
	@JsonProperty("exerciseRecordId") 
	private String exerciseRecordId;
	
	@JsonProperty("weight") 
	private int weight;
	
	@JsonProperty("setDuration") 
	private int setDuration;
	
	@JsonProperty("sequence") 
	private int sequence;
	
	@JsonProperty("restDuration") 
	private int restDuration;
	
	@JsonProperty("averageExtensionDuration") 
	private int averageExtensionDuration;
	
	@JsonProperty("averageContractionDuration") 
	private int averageContractionDuration;
	
	@JsonProperty("reps") 
	private List<RepVO> repVO = new ArrayList<RepVO>();

	/**
	 * @return the setId
	 */
	public String getSetId() {
		return setId;
	}

	/**
	 * @param setId the setId to set
	 */
	public void setSetId(String setId) {
		this.setId = setId;
	}

	/**
	 * @return the exerciseRecordId
	 */
	public String getExerciseRecordId() {
		return exerciseRecordId;
	}

	/**
	 * @param exerciseRecordId the exerciseRecordId to set
	 */
	public void setExerciseRecordId(String exerciseRecordId) {
		this.exerciseRecordId = exerciseRecordId;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the setDuration
	 */
	public int getSetDuration() {
		return setDuration;
	}

	/**
	 * @param setDuration the setDuration to set
	 */
	public void setSetDuration(int setDuration) {
		this.setDuration = setDuration;
	}

	/**
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the restDuration
	 */
	public int getRestDuration() {
		return restDuration;
	}

	/**
	 * @param restDuration the restDuration to set
	 */
	public void setRestDuration(int restDuration) {
		this.restDuration = restDuration;
	}

	/**
	 * @return the averageExtensionDuration
	 */
	public int getAverageExtensionDuration() {
		return averageExtensionDuration;
	}

	/**
	 * @param averageExtensionDuration the averageExtensionDuration to set
	 */
	public void setAverageExtensionDuration(int averageExtensionDuration) {
		this.averageExtensionDuration = averageExtensionDuration;
	}

	/**
	 * @return the averageContractionDuration
	 */
	public int getAverageContractionDuration() {
		return averageContractionDuration;
	}

	/**
	 * @param averageContractionDuration the averageContractionDuration to set
	 */
	public void setAverageContractionDuration(int averageContractionDuration) {
		this.averageContractionDuration = averageContractionDuration;
	}

	/**
	 * @return the repVO
	 */
	public List<RepVO> getRepVO() {
		return repVO;
	}

	/**
	 * @param repVO the repVO to set
	 */
	public void setRepVO(List<RepVO> repVO) {
		this.repVO = repVO;
	}

	
	
	
}
