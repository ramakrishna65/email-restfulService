/**
 * 
 */
package com.smartweights.mail.pdf;


import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.smartweights.mail.model.ExerciseRecord;
import com.smartweights.mail.model.SetVO;
import com.smartweights.mail.model.UserVO;
import com.smartweights.mail.service.interfaces.IEmailService;
import com.smartweights.mail.utils.DateUtils;

/**
 * @author Rama
 *
 */
@Component("pdfGenaration")
public class GeneratePDF implements IGeneratePDF {

	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private RecordsProcessByType recordsProcessByType;
	
	@Autowired
	private PDFTableProperties pdfTableProperties;

	@Autowired
	private GenerateBarChart generateBarChart;
	
	@Autowired
	private DateUtils dateUtils;
	
		@Autowired
	private static UserVO userVO;
	
	
	private static Font EXERCISE_HEADING = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
	private static Font DATE_HEADING = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.WHITE);

	public void generateWeeklyExerciseRecordsPDF(OutputStream outputStream, UserVO userVO) throws Exception {
		 
		Document document = new Document();
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document, outputStream);

			//userVO = emailService.getLiveWorkOutDetails();

			Map<String, HashMap<Date, List<SetVO>>> UserVOByExercise = recordsProcessByType.getWorkoutDetailsByExericeis(userVO);
			
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
		    writer.setPageEvent(event);			

			document.open();
						
			pdfTableProperties.addMetaData(document ,userVO.getUserName());

			pdfTableProperties.addTitlePage(document);

			List<List<Integer>> recordList = getWeeklyRecords(userVO);
			
			createChartForWorkoutDetials(document, writer, recordList);
			
			document.newPage();
			
			createTableForWorkoutDetails(document, UserVOByExercise);
			
			
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void generateRecentWorkoutExerciseRecordsPDF(OutputStream outputStream, UserVO userVO) throws Exception {
		Document document = new Document();
		PdfWriter writer = null;
		try {
			writer = PdfWriter.getInstance(document, outputStream);

			//userVO = emailService.getLiveWorkOutDetails();

			Map<String, List<ExerciseRecord>> UserVOByExercise = recordsProcessByType.getLiveWorkoutDetailsByExericeis(userVO);

			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
			writer.setPageEvent(event);		
			
			document.open();

			pdfTableProperties.addCompanyLogo(document);
			
			pdfTableProperties.addMetaData(document, userVO.getUserName());

			pdfTableProperties.addTitlePageForLiveWorkout(document);

			createTableForRecentWorkouts(document, UserVOByExercise);

			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void createTableForWorkoutDetails(Document document, Map<String, HashMap<Date, List<SetVO>>> UserVOByExercise)
			throws DocumentException {

		Paragraph paragraph = new Paragraph();
		pdfTableProperties.creteEmptyLine(paragraph, 1);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(5);

		float[] columnWidths = new float[] { 15f, 15f, 15f, 25f, 25f };
		table.setWidths(columnWidths);

		for (Entry<String, HashMap<Date, List<SetVO>>> entry : UserVOByExercise.entrySet()) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell;
			cell = new PdfPCell(new Phrase(entry.getKey(), EXERCISE_HEADING));
			pdfTableProperties.exerciseHeaderStyle(cell);
			table.addCell(cell);

			HashMap<Date, List<SetVO>> setVOList = entry.getValue();
			TreeSet<Date> keys = new TreeSet<Date>(setVOList.keySet());
			TreeSet<Date> reverseKeys = new TreeSet<Date>();
			System.out.println("keys  = " + keys.descendingSet());
			reverseKeys=(TreeSet<Date>)keys.descendingSet();
			System.out.println("reverseKeys  = " + reverseKeys);
			
			for (Date key : reverseKeys) {				
			
				cell = new PdfPCell(new Phrase(dateUtils.getDispayDate(key), DATE_HEADING));
				pdfTableProperties.exerciseHeaderStyle(cell);
				table.addCell(cell);

				pdfTableProperties.createTableHeader(table);

				List<SetVO> ListSetVO = setVOList.get(key);
				int index = 1;
				for (SetVO set : ListSetVO) {
					table.addCell(pdfTableProperties.BodyCellStyle(String.valueOf(index)));
					table.addCell(pdfTableProperties.BodyCellStyle(String.valueOf(set.getWeight())));
					table.addCell(pdfTableProperties.BodyCellStyle(String.valueOf(set.getRepVO().size())));

					String Contract = new DecimalFormat("##.#")
							.format((double) set.getAverageContractionDuration() / 1000);
					String Extension = new DecimalFormat("##.#")
							.format((double) set.getAverageExtensionDuration() / 1000);
					table.addCell(pdfTableProperties.BodyCellStyle(Contract + " s"));
					table.addCell(pdfTableProperties.BodyCellStyle(Extension + " s"));
					index = index + 1;
				}
			}
		}
		document.add(table);
	}

	@SuppressWarnings("deprecation")
	private void createChartForWorkoutDetials(Document document , PdfWriter writer, List<List<Integer>> recordList ){
		try{
			float width = 500;
	        float height = 400;			
			Paragraph preface = new Paragraph();
			pdfTableProperties.creteEmptyLine(preface, 2);
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);		
			Graphics2D graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width, height);
			JFreeChart chart = generateBarChart.generateBarChart(recordList);
			chart.draw(graphics2d, rectangle2d);
	        
	        graphics2d.dispose();
	        contentByte.addTemplate(template, 0, height);
        
		}catch (Exception e) {
	        e.printStackTrace();
			
		}
        
	}
	
	
	private void createTableForRecentWorkouts(Document document, Map<String, List<ExerciseRecord>>  UserVOByExercise)
			throws DocumentException {

		Paragraph paragraph = new Paragraph();
		pdfTableProperties.creteEmptyLine(paragraph, 1);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(5);

		float[] columnWidths = new float[] { 15f, 15f, 15f, 25f, 25f };
		table.setWidths(columnWidths);
		
				
		for (Entry<String, List<ExerciseRecord>> entry : UserVOByExercise.entrySet()) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

			PdfPCell cell;
			cell = new PdfPCell(new Phrase(entry.getKey(), EXERCISE_HEADING));
			pdfTableProperties.exerciseHeaderStyle(cell);
			table.addCell(cell);
			
			pdfTableProperties.createTableHeader(table);
			int index =1;		
			List<ExerciseRecord> recordsList = entry.getValue();
			
			for(ExerciseRecord exercise : recordsList){							
				for(SetVO set: exercise.getSet()){
					table.addCell(pdfTableProperties.BodyCellStyle(String.valueOf(index)));
					table.addCell(pdfTableProperties.BodyCellStyle(String.valueOf(set.getWeight())));
					table.addCell(pdfTableProperties.BodyCellStyle(String.valueOf(set.getRepVO().size())));

					String Contract = new DecimalFormat("##.#")
							.format((double) set.getAverageContractionDuration() / 1000);
					String Extension = new DecimalFormat("##.#")
							.format((double) set.getAverageExtensionDuration() / 1000);
					table.addCell(pdfTableProperties.BodyCellStyle(Contract + " s"));
					table.addCell(pdfTableProperties.BodyCellStyle(Extension + " s"));
					index = index + 1;
				}
			}			
		}
		document.add(table);
	}

	private List<List<Integer>> getWeeklyRecords(UserVO userVO){
		List<List<Integer>> recordList = new ArrayList<List<Integer>>();
		List<ExerciseRecord> currentPeriodData = new ArrayList<ExerciseRecord>();
		List<ExerciseRecord> lastPeriodData = new ArrayList<ExerciseRecord>();
		Date sevenDaysAgo = dateUtils.getlastWeekDate();
		Date foutreenDaysAgo = dateUtils.getlast2WeekDate();
		for(ExerciseRecord exerciseRecord : userVO.getExerciseRecord()){
			Date unixDt = dateUtils.getStartofDay(exerciseRecord.getUnixTime());
					
			 if(sevenDaysAgo.before(unixDt) || sevenDaysAgo.equals(unixDt)){				
				 currentPeriodData.add(exerciseRecord);
			 }
			 			 
			 if((foutreenDaysAgo.before(unixDt) || foutreenDaysAgo.equals(unixDt)) && sevenDaysAgo.after(unixDt) ){				
				 lastPeriodData.add(exerciseRecord);
			 }			
		}
		List<Integer> lastPeriodWeightArray =  getWeightLiftedArrayByWeekdays(foutreenDaysAgo ,lastPeriodData);
		List<Integer> currentPeriodWeightArray =  getWeightLiftedArrayByWeekdays(sevenDaysAgo ,currentPeriodData);
		recordList.add(lastPeriodWeightArray);
		recordList.add(currentPeriodWeightArray);
		
		return  recordList;
	}
	
	
	private int getSelectedIndex(List dateArray, Date dt){
		int index=-1;
		for(int i=0; i< dateArray.size(); i++){	
			if(dateArray.get(i).equals(dt)){
				index =  i;
			}
		}
		return index;
	}
	
	private List<Integer> getWeightLiftedArrayByWeekdays(Date weekDates, List<ExerciseRecord> currentPeriodData){
		List<Date> arr= new ArrayList<Date>();
		List<Integer> dateArr = new ArrayList<Integer>();	
		for(int i=0; i <= 6; i++){
			Date nextDay = dateUtils.getNextDay(weekDates, i);
			arr.add(nextDay);
			dateArr.add(0);
		}		
		
		for(int i=0; i < currentPeriodData.size(); i++){
			ExerciseRecord exerciseRecord =  currentPeriodData.get(i);
			Date unixDt = dateUtils.getStartofDay(exerciseRecord.getUnixTime());
			int index = getSelectedIndex(arr ,unixDt);			
			if(index > -1 ){
				List<SetVO> SetList = exerciseRecord.getSet();
				for (int j = 0; j < SetList.size(); j++) {
					SetVO set = SetList.get(j);				
					int totalWeight = (set.getRepVO().size() * Integer.valueOf(set.getWeight()));				
					int oldValue = (Integer) dateArr.get(index);
					totalWeight = oldValue + totalWeight;
					dateArr.set(index, totalWeight);					 
				}
			}
		}
				
		return dateArr;
	}
	
}
