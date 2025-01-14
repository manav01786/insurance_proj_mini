package com.manavit.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.manavit.entity.CitizenPlan;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator {

	public Boolean generate(List<CitizenPlan> alldata,HttpServletResponse response,File file) throws Exception{
		  // Creating the Object of Document
	    Document document = new Document(PageSize.A4);
	    // Getting instance of PdfWriter
	    PdfWriter.getInstance(document, response.getOutputStream());//for browser
	    PdfWriter.getInstance(document,new FileOutputStream(file));//for email
	    // Opening the created document to change it
	    document.open();
	    
	    
//	    --------------------------------write content part--------------
		/* 
		 * // Creating paragraph Paragraph paragraph=new
		 * Paragraph("this is content inside pdf"); // Adding the created paragraph in
		 * the document document.add(paragraph);
		 */
	    PdfPTable pdfTable=new PdfPTable(8);
	    pdfTable.addCell("ID");
	    pdfTable.addCell("Holder's name");
	    pdfTable.addCell("Gender");
	    pdfTable.addCell("Plan Name");
	    pdfTable.addCell("Plan Status");
	    pdfTable.addCell("Plan Start Date");
	    pdfTable.addCell("Plan End Date");
	    pdfTable.addCell("Benefits Amount");
	    
	    for (CitizenPlan citizenPlan : alldata) {
	    	pdfTable.addCell(String.valueOf(citizenPlan.getCitizenId()));
	    	pdfTable.addCell(citizenPlan.getCitizenName());
			pdfTable.addCell(citizenPlan.getPlanName());
			pdfTable.addCell(citizenPlan.getGender());
			
			pdfTable.addCell(citizenPlan.getPlanStatus());
			if (citizenPlan.getPlanStartDate()!=null) {
				pdfTable.addCell(citizenPlan.getPlanStartDate()+"");
			} else {
				pdfTable.addCell("N/A");
			}
			if (citizenPlan.getPlanStartDate()!=null) {
				pdfTable.addCell(citizenPlan.getPlanEndDate()+"");
			} else {
				pdfTable.addCell("N/A");
			}
			if (citizenPlan.getBenefitAmt()!=null) {
				pdfTable.addCell(citizenPlan.getBenefitAmt()+"");
			} else {
				pdfTable.addCell("N/A");
			}
			
			
		
		}
//	    --------------------------------write content part end here--------------
	 // Adding the created table in the document 
	 		 document.add(pdfTable);
	    
	    // Closing the document
	    document.close();

	    return true;
	}
}
