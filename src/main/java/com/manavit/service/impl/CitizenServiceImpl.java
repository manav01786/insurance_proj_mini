package com.manavit.service.impl;

import java.io.File;
//import java.io.File;
//
//import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.manavit.entity.CitizenPlan;
import com.manavit.formbinding.SearchRequest;
import com.manavit.repo.CitizenPlanRepo;
import com.manavit.service.CitizenService;
import com.manavit.utility.EmailUtils;
import com.manavit.utility.ExcelGenerator;
import com.manavit.utility.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenServiceImpl implements CitizenService {

	private CitizenPlanRepo citizenPlanRepo;
	private ExcelGenerator excelGenerator;
	private PdfGenerator pdfGenerator;
    private EmailUtils emailUtils;

	public CitizenServiceImpl(CitizenPlanRepo citizenPlanRepo, ExcelGenerator excelGenerator, PdfGenerator pdfGenerator,
			EmailUtils emailUtils) {
		super();
		this.citizenPlanRepo = citizenPlanRepo;
		this.excelGenerator = excelGenerator;
		this.pdfGenerator = pdfGenerator;
		this.emailUtils = emailUtils;
	}

	@Override
	public List<String> getAllPlanStatusType() {
		List<String> allPlanNameType = citizenPlanRepo.getAllPlanStatusType();
		return allPlanNameType;
	}

	@Override
	public List<String> getAllPlanNameType() {
		List<String> allPlanNameType = citizenPlanRepo.getAllPlanNameType();
		return allPlanNameType;
	}

	@Override
	public List<CitizenPlan> handleSearch(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
//		BeanUtils.copyProperties(request, entity);// is used to copy property values from one Java object to another if their property names and types match.

		if (request.getPlanName() != null && request.getPlanName() != "") {
			entity.setPlanName(request.getPlanName());
		}
		if (request.getGender() != null && request.getGender() != "") {
			entity.setGender(request.getGender());
		}
		if (request.getPlanStatus() != null && request.getPlanStatus() != "") {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (request.getPlanEndDate() != null && request.getPlanEndDate() != "") {
//			System.out.println("END DATE-----"+convertStringToDate(request.getPlanEndDate()));
			entity.setPlanEndDate(convertStringToDate(request.getPlanEndDate()));
		}
		if (request.getPlanStartDate() != null && request.getPlanStartDate() != "") {
//			System.out.println("START DATE-----"+convertStringToDate(request.getPlanStartDate()));
			entity.setPlanStartDate(convertStringToDate(request.getPlanStartDate()));
		}
		return citizenPlanRepo.findAll(Example.of(entity));
		

		

	}

	@Override
	public Boolean exportExcel(HttpServletResponse response, SearchRequest searchRequest) throws Exception {
		List<CitizenPlan> alldata = handleSearch(searchRequest);
		 // Generate a unique file name with a timestamp
	    String fileName = "plans_" + System.currentTimeMillis() + ".xls";
	    File file = new File("D:\\SBMS Spring practice\\email\\"  + fileName);

		Boolean isGenerated = excelGenerator.generate(alldata, response,file);
		String subject="Test Mail Subject";
		String body="<h1>HELLO there Excel.....</h1>";
		String to="manishverma01786@gmail.com";
		
		emailUtils.sendEmail(subject, body, to,file);
		if (isGenerated) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean exportPdf(HttpServletResponse response, SearchRequest searchRequest) throws Exception {

		List<CitizenPlan> alldata = handleSearch(searchRequest);
	    String fileName = "plans_" + System.currentTimeMillis() + ".pdf";
	    File file = new File("D:\\SBMS Spring practice\\email\\" + fileName);

		Boolean isGenerated = pdfGenerator.generate(alldata, response,file);
		String subject="Test Mail Subject";
		String body="<h1>HELLO PDF.....</h1>";
		String to="manishverma01786@gmail.com";
		
		emailUtils.sendEmail(subject, body, to,file);
		if (isGenerated) {
			return true;
		}
		return false;
	}

	public static LocalDate convertStringToDate(String stringDate) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(stringDate, format);
	}

}
