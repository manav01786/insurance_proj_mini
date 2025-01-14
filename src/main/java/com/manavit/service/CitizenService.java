package com.manavit.service;

import java.util.List;

import com.manavit.entity.CitizenPlan;
import com.manavit.formbinding.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;


public interface CitizenService {
       //	filter use here.....
	
//	List<CitizenPlan>getCitizenPlanByStatus(String status);
//	List<CitizenPlan>getCitizenPlanByPlanName( String planName);
//	List<CitizenPlan>getCitizenPlanByGender( String gender);
//	
	List<CitizenPlan>handleSearch(SearchRequest request);
	

	List<String>getAllPlanStatusType();
	List<String>getAllPlanNameType();	
	
	public Boolean exportPdf(HttpServletResponse response,SearchRequest searchRequest) throws Exception;
	public Boolean exportExcel(HttpServletResponse response, SearchRequest searchRequest) throws Exception;
	
	
}
