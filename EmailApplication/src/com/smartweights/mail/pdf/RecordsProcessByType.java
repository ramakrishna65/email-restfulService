/**
 * 
 */
package com.smartweights.mail.pdf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.smartweights.mail.model.ExerciseRecord;
import com.smartweights.mail.model.SetVO;
import com.smartweights.mail.model.UserVO;
import com.smartweights.mail.utils.DateUtils;

/**
 * @author Rama
 *
 */

@Controller("recordsProcessByType")
public class RecordsProcessByType {
	
	@Autowired
	private DateUtils dateUtils;
	
		public Map<String, HashMap<Date,  List<SetVO>>> getWorkoutDetailsByExericeis(UserVO userVO){
		
			Map<String, List<ExerciseRecord>> mapByExercise = mapRecordsByExerciseName(userVO);
			Map<String, HashMap<Date,  List<SetVO>>> mapByDate = mapRecordsByDate(mapByExercise);
			
			return mapByDate;
		}
		
		public Map<String, List<ExerciseRecord>> getLiveWorkoutDetailsByExericeis(UserVO userVO){
			
			Map<String, List<ExerciseRecord>> mapByExercise = mapRecordsByExerciseName(userVO);
						
			return mapByExercise;
		}
		
		// get Exercise Records by Name
		private Map<String, List<ExerciseRecord>> mapRecordsByExerciseName(UserVO uservo ){
			Map<String, List<ExerciseRecord>> mapObj = new HashMap<String, List<ExerciseRecord>>();
			
			for(ExerciseRecord exerciseRecord : uservo.getExerciseRecord()){
				
				if(mapObj.containsKey(exerciseRecord.getExerciseDefinition().getExerciseName())){
					
					List<ExerciseRecord> newExerciseRecordList = new ArrayList<ExerciseRecord>();
					newExerciseRecordList = mapObj.get(exerciseRecord.getExerciseDefinition().getExerciseName());
					newExerciseRecordList.add(exerciseRecord);			
					mapObj.put(exerciseRecord.getExerciseDefinition().getExerciseName(), newExerciseRecordList);
					
				}else{
					
					List<ExerciseRecord> exerciseRecordList = new ArrayList<ExerciseRecord>();
					exerciseRecordList.add(exerciseRecord);
					mapObj.put(exerciseRecord.getExerciseDefinition().getExerciseName(), exerciseRecordList);
				}			
				
			}
			
			return mapObj;
		}
		
		// get Exercise Records by Date followed by Exercise name
		private  Map<String, HashMap<Date, List<SetVO>>> mapRecordsByDate(Map<String, List<ExerciseRecord>> mapObj ){			
			HashMap<Date, List<SetVO>> mapSetVO = new HashMap<Date, List<SetVO>>();		
			
			Map<String, HashMap<Date,  List<SetVO>>> dateMap = new HashMap<String, HashMap<Date,  List<SetVO>>>();
			
			for(Map.Entry<String, List<ExerciseRecord>> entry : mapObj.entrySet() ){
				List<ExerciseRecord> recordsList = entry.getValue();
				
				for(ExerciseRecord exercise : recordsList){
					Date dt = dateUtils.getStartofDay(exercise.getUnixTime());
					if(mapSetVO.containsKey(dt)){						
						List<SetVO> setVOList = new ArrayList<SetVO>();						
						setVOList = mapSetVO.get(dt);					
						setVOList.addAll(exercise.getSet());
						mapSetVO.put(dt, setVOList);						
					}else{
						List<SetVO> setVOList = new ArrayList<SetVO>();					
						setVOList.addAll(exercise.getSet());
						mapSetVO.put(dt, setVOList);
					}
				}			
				dateMap.put(entry.getKey(), mapSetVO);
			}			
			 
			return dateMap;			
		}
		
		
		
		public UserVO getWorkoutDetailsByDate(UserVO userVO){
			UserVO userVOByDate = new UserVO();
			
			return userVOByDate;
		}
	
		
		
}
