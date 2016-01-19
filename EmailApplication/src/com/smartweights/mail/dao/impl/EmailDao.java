package com.smartweights.mail.dao.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.smartweights.mail.dao.interfaces.IEmailDao;
import com.smartweights.mail.model.ExerciseDefinition;
import com.smartweights.mail.model.ExerciseRecord;
import com.smartweights.mail.model.RepVO;
import com.smartweights.mail.model.SetVO;
import com.smartweights.mail.model.UserVO;
import com.smartweights.mail.utils.DateUtils;

@Repository("emailDao")
public class EmailDao implements IEmailDao {
	
	 @Autowired
	 private DateUtils dateUtils;
	
	private String collectionName = "WorkoutNotificationQueue";
	private String password = "OQ6eIfi5y4Oan1udl3sC";
	private String database = "smartweights_prototype_staging";
	private String mongoDB = "mongodb://";
	private String colon = ":";
	private String symbol = "@";
	private String server = "52.89.99.179:27017";
	private String blackSlash = "/";
	
	private UserVO getExerciseRecords(UserVO uservo) {
		
		/*MongoClientURI  uri = new MongoClientURI (serverURI());
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient(uri);		
		MongoDatabase db = mongoClient.getDatabase(database);
		
		
		for (Document cur : db.getCollection(collectionName).find()) {
				System.out.println("Main Object = "+cur.toJson());
				uservo.setFirstName((String) cur.get("firstName"));
				uservo.setUserName((String) cur.get("username"));
				uservo.setUserId((Object) cur.get("userId"));
				uservo.setEmail((String) cur.get("email"));
				
				 ArrayList<?>  exerciseRecords = (ArrayList<?>) cur.get("exerciseRecords");
				 
				 uservo.setExerciseRecord(processExerciseRecords(exerciseRecords));				 
		}		
	
		mongoClient.close();*/
		return uservo;
		
	}
	
	private String serverURI(){
		String textURI = mongoDB.concat(collectionName).concat(colon).concat(password).concat(symbol).concat(server).concat(blackSlash).concat(database);
		return textURI;
		
	}
	/*
	
	private ArrayList<ExerciseRecord> processExerciseRecords(ArrayList<?> ExerRecords){
		ArrayList<ExerciseRecord> exerciseRecords = new ArrayList<ExerciseRecord>();
		
		for(int i=0; i < ExerRecords.size(); i++){	
    		Document records =(Document) ExerRecords.get(i);
    		ExerciseRecord exerciseRecordList = new ExerciseRecord();
    		
    		double unixTime = (Double) (records.get("unixTime"));    		
    		Date dt = new java.util.Date((long) unixTime * 1000);
    		
    		exerciseRecordList.setUnixTime(dateUtils.getStartofDay(dt));    		
    		exerciseRecordList.setDispalyDate(dateUtils.getDispayDate(dt));
    		
    		Document exerciseDefinition = (Document) records.get("exerciseDefinition");
    		
    		ExerciseDefinition exerciseDefination = new ExerciseDefinition();
    		exerciseDefination.setExerciseName((String)exerciseDefinition.get("name"));
    		exerciseDefination.setExerciseName((String)exerciseDefinition.get("description"));
    		exerciseRecordList.setExerciseDefinition(exerciseDefination);
    		
    		ArrayList<?> setsRecords = (ArrayList<?>) records.get("sets");    		
    		exerciseRecordList.setSet(processExerciseSets(setsRecords));
    		    		
    		exerciseRecords.add(exerciseRecordList);
		}		
		
		return exerciseRecords;
	}
	
	private ArrayList<SetVO> processExerciseSets(ArrayList<?> setRecords){
		ArrayList<SetVO> setsRecordList = new ArrayList<SetVO>();
		
		for(int i=0; i < setRecords.size(); i++){	
			Document set =(Document)setRecords.get(i);
			SetVO sets = new SetVO();
			
			sets.setAverageContractionDuration((Integer) set.get("averageContractionDuration"));
			sets.setAverageExtensionDuration((Integer) set.get("averageExtensionDuration"));
			sets.setRestDuration(String.valueOf(set.get("restDuration")));
			sets.setSequence(String.valueOf(set.get("sequence")));
			sets.setSetDuration(String.valueOf(set.get("setDuration")));
			sets.setWeight(String.valueOf(set.get("weight")));	
			
			
			ArrayList<?> reps = (ArrayList<?>) set.get("reps");
			
			sets.setRepVO(processExerciseReps(reps));
			
			setsRecordList.add(sets);
		}	
		
		return setsRecordList;
	}
	
	private ArrayList<RepVO> processExerciseReps(ArrayList<?> repRecords){
		
		ArrayList<RepVO> repRecordList = new ArrayList<RepVO>();
		
		for(int i=0; i < repRecords.size(); i++){	
			Document rep =(Document)repRecords.get(i);
			RepVO reps = new RepVO();
			reps.setContractionDuration((Object)(rep.get("contractionDuration")));
			reps.setExtensionDuration((Object)(rep.get("extensionDuration")));
			reps.setSequence((Object)(rep.get("sequence")));			
			
			repRecordList.add(reps);
		}
		
		return repRecordList;
	}
	*/
	public UserVO getLiveWorkOutDetails() {
		UserVO uservo = new UserVO();
		uservo = getExerciseRecords(uservo);		
		return uservo;
	}
	
	
	public UserVO getWeeklyWorkOutDetails(){
		UserVO uservo = new UserVO();
		uservo = getExerciseRecords(uservo);		
		return uservo;
	}
	
	
	public List<ExerciseRecord> getExerciseRecordsFromRestFullService(String adminID, String REST_SERVICE_URI){
		List<ExerciseRecord> exerciseRecordsList = new ArrayList<ExerciseRecord>();
		HttpHeaders headers = new HttpHeaders();
	 	headers.set("Accept",  "*/*");
	 	headers.set("Content-Type",  "application/json; charset=utf-8");
	 	headers.set("Accept",  "application/json");
	 	headers.add("Authorization", adminID);			 
	 	UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(REST_SERVICE_URI);
	 	
	 	HttpEntity<?> entity = new HttpEntity<ExerciseRecord[]>(headers);
	 	RestTemplate restTemplate = new RestTemplate();
	 	restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter()); 
	 	HttpEntity<ExerciseRecord[]> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, ExerciseRecord[].class);
	 	System.out.println("Response =  " + response);
	 	Arrays.sort(response.getBody(),Collections.reverseOrder());
	 	Date foutreenDaysAgo = dateUtils.getlast2WeekDate();
	 	Date toDay  = dateUtils.getTodayDate();
	 	
	 	for(ExerciseRecord exerciseRecord : response.getBody()){
	 		Date dt = dateUtils.getStartofDay(exerciseRecord.getUnixTime());
	 		if((foutreenDaysAgo.before(dt) || foutreenDaysAgo.equals(dt)) && toDay.after(dt) ){				
	 			exerciseRecordsList.add(exerciseRecord);
			 }
	 	}
	 	 	 	
	 	return exerciseRecordsList;
	}
}
