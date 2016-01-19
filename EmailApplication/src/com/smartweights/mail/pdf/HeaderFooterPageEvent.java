/**
 * 
 */
package com.smartweights.mail.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.events.PdfPageEventForwarder;

/**
 * @author Rama
 *
 */
public class HeaderFooterPageEvent extends PdfPageEventForwarder {

	Font headerFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
    Font lineStyle = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.ORANGE);
    Font FooterFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
    String companyName ="SmartWeights";
    String lineBorder = "____________________________________________________________";
    String copyRight ="Copyright SmartWeights, 2016., All Rights Reserved | Terms and Conditions | Privacy Policy ";
    String contactUS="Email: info@thesmartweights.com:  Tele No : 949-682-9094";

    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        Rectangle rect = new Rectangle(10, 10, 585, 832);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(2); 
       
        rect.setBorderColor(BaseColor.LIGHT_GRAY);
        rect.setUseVariableBorders(true);    
        
        cb.rectangle(rect);          
        Phrase header = new Phrase(companyName, headerFont);
        Phrase line = new Phrase(lineBorder, lineStyle);            
       
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, header,  document.left(),  document.top()+5 , 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, line,  document.right(),  document.top() , 0);        
        
        Phrase copyRights = new Phrase(copyRight, FooterFont);
        Phrase address = new Phrase(contactUS, FooterFont);        
        
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, line,  document.right(),  document.bottom() +15 , 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, copyRights,  document.left(), document.bottom() , 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, address,  document.left(), document.bottom()-15 , 0);
        
    }
}
