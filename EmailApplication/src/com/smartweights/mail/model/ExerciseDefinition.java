/**
 * 
 */
package com.smartweights.mail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Rama
 *
 */
public class ExerciseDefinition extends CommonUtilsVO {
	
	@JsonProperty("name") 
	private String exerciseName;
	
	@JsonProperty("description") 
	private String description;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("created")
	private String created;
	
	@JsonProperty("modified")
	private String modified;

	/**
	 * @return the exerciseName
	 */
	public String getExerciseName() {
		return exerciseName;
	}

	/**
	 * @param exerciseName the exerciseName to set
	 */
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

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
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(String modified) {
		this.modified = modified;
	}

	
	
}
