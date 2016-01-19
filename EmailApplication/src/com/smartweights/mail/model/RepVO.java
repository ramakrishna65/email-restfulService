/**
 * 
 */
package com.smartweights.mail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Rama
 *
 */
public class RepVO extends CommonUtilsVO {
	
	
	@JsonProperty("extensionDuration") 
	private int extensionDuration;
	
	@JsonProperty("contractionDuration") 
	private int contractionDuration;
	
	@JsonProperty("sequence") 
	private int sequence;
	
	@JsonProperty("id") 
	private String repId;
	
	@JsonProperty("setId") 
	private String setId;

	/**
	 * @return the extensionDuration
	 */
	public int getExtensionDuration() {
		return extensionDuration;
	}

	/**
	 * @param extensionDuration the extensionDuration to set
	 */
	public void setExtensionDuration(int extensionDuration) {
		this.extensionDuration = extensionDuration;
	}

	/**
	 * @return the contractionDuration
	 */
	public int getContractionDuration() {
		return contractionDuration;
	}

	/**
	 * @param contractionDuration the contractionDuration to set
	 */
	public void setContractionDuration(int contractionDuration) {
		this.contractionDuration = contractionDuration;
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
	 * @return the repId
	 */
	public String getRepId() {
		return repId;
	}

	/**
	 * @param repId the repId to set
	 */
	public void setRepId(String repId) {
		this.repId = repId;
	}

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

	
	
	
	
}
