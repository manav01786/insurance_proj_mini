package com.manavit.utility;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.manavit.entity.CitizenPlan;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	public Boolean generate(List<CitizenPlan> alldata,HttpServletResponse response,File file) throws Exception {
//		create excel workbook
		Workbook workbook = new HSSFWorkbook();// for xls type
//		Workbook workbook=new XSSFWorkbook();//for xlsx type
//		create excel sheet
		Sheet sheet = workbook.createSheet("plan_data");
//		create header row
		Row headerRow = sheet.createRow(0);
//		create cell and set value
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Holder's name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Plan Start Date");
		headerRow.createCell(6).setCellValue("Plan End Date");
		headerRow.createCell(7).setCellValue("Benefits Amount");
		int datarowIndex = 1;
		for (CitizenPlan plan : alldata) {
			Row dataRow = sheet.createRow(datarowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());

			if (plan.getPlanStartDate() != null) {
				dataRow.createCell(5).setCellValue(plan.getPlanStartDate() + "");
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}

			if (plan.getPlanEndDate() != null) {
				dataRow.createCell(6).setCellValue(plan.getPlanEndDate() + "");
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}

			if (plan.getBenefitAmt() != null) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmt());
			} else {
				dataRow.createCell(7).setCellValue("N/A");
			}

			datarowIndex++;
		}

//		for storing into local file
		FileOutputStream fos=new FileOutputStream(file);
		workbook.write(fos);

//
		ServletOutputStream outputStream = response.getOutputStream();// ServletOutputStream is used in Java Servlets to
																		// write binary data directly to the HTTP
																		// response, such as serving files, images, or
																		// downloadable content.
		workbook.write(outputStream);
		workbook.close();
		return true;
	}
}
