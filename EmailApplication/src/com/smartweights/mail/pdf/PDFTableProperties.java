/**
 * 
 */
package com.smartweights.mail.pdf;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * @author Rama
 *
 */

@Component("pdfTableProperties")
public class PDFTableProperties {

	
	 	public static final String COMPANY_LOGO = "../images/sw-logo.png";
	 
		private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
		private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		private static Font HEADER_WHITE_FONT = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.WHITE);
		private static Font TABLE_CELL_FONT = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL,
				new BaseColor(64, 64, 64));
		
		
		
		public void addCompanyLogo(Document document) throws DocumentException{
			try {
				 
				Image companyLogo = Image.getInstance(COMPANY_LOGO);
				document.add(companyLogo);
				Paragraph preface = new Paragraph();
				creteEmptyLine(preface, 1);
				preface.add(new Paragraph("SMART WEIGHTS", TIME_ROMAN));
				document.add(preface);
				
			} catch (BadElementException e) {			
				e.printStackTrace();
			} catch (MalformedURLException e) {			
				e.printStackTrace();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}

		public void addMetaData(Document document, String userName) {
			document.addTitle(userName + " PDF");
			document.addSubject("SmartWeight Records PDF");
			document.addKeywords("SmartWeight, exercise Records,  exercise sets, exercicse weight");
			document.addAuthor("SmartWeight Application");
			document.addCreator("SmartWeight Application");
		}

		public void addTitlePage(Document document) throws DocumentException {

			Paragraph preface = new Paragraph();
			creteEmptyLine(preface, 1);
			preface.add(new Paragraph("WORKOUT HISTORY BY EXERCISE", TIME_ROMAN));

			creteEmptyLine(preface, 1);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			preface.add(new Paragraph("Report created on " + simpleDateFormat.format(new Date()), TIME_ROMAN));
			document.add(preface);
		}

		public void addTitlePageForLiveWorkout(Document document) throws DocumentException {

			Paragraph preface = new Paragraph();
			creteEmptyLine(preface, 1);
			preface.add(new Paragraph("LIVE WORKOUT DETAILS", TIME_ROMAN));

			creteEmptyLine(preface, 1);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			preface.add(new Paragraph("Report created on " + simpleDateFormat.format(new Date()), TIME_ROMAN));
			document.add(preface);
		}

		
		public void creteEmptyLine(Paragraph paragraph, int number) {
			for (int i = 0; i < number; i++) {
				paragraph.add(new Paragraph(" "));
			}
		}
		
		public void createTableHeader(PdfPTable table) {
			
			PdfPCell c1 = new PdfPCell(new Phrase("SET", HEADER_WHITE_FONT));
			HeaderCellStyle(c1);
			table.addCell(c1);		

			c1 = new PdfPCell(new Phrase("WEIGHT", HEADER_WHITE_FONT));
			HeaderCellStyle(c1);
			table.addCell(c1);	

			c1 = new PdfPCell(new Phrase("REP", HEADER_WHITE_FONT));
			HeaderCellStyle(c1);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("CONTRACT TIME", HEADER_WHITE_FONT));
			HeaderCellStyle(c1);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("EXTEND TIME", HEADER_WHITE_FONT));
			HeaderCellStyle(c1);
			table.addCell(c1);
			// table.setHeaderRows(1);
		}

		public void HeaderCellStyle(PdfPCell cell) {
			cell.setPaddingTop(3f);
			cell.setPaddingBottom(5f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(new BaseColor(51, 114, 223));
			cell.setBorder(0);
		}

		public PdfPCell BodyCellStyle(String value) {
			PdfPCell cell = new PdfPCell(new Phrase(value, TABLE_CELL_FONT));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(new BaseColor(240, 240, 240));
			cell.setBorder(0);
			cell.setMinimumHeight(10f);
			return cell;
		}

		public void exerciseHeaderStyle(PdfPCell cell) {
			cell.setColspan(5);
			cell.setBackgroundColor(new BaseColor(0, 0, 0));
			cell.setBorderWidthBottom(1);
			cell.setBorderColorBottom(new BaseColor(255, 255, 255));
			cell.setPaddingTop(5f);
			cell.setPaddingBottom(10f);
			cell.setPaddingLeft(15f);

		}
}
