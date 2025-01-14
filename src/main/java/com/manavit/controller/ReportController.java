package com.manavit.controller;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.manavit.entity.CitizenPlan;
import com.manavit.formbinding.SearchRequest;
import com.manavit.service.CitizenService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ReportController {

	@Autowired
	private CitizenService citizenService;

	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response, HttpSession session,Model model) throws Exception {
		response.setContentType("application/octet-stream");
		 // Generate a dynamic file name
	    String fileName = "plans_" + System.currentTimeMillis() + ".xls";
	    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		// Retrieve SearchRequest from session
		SearchRequest searchRequest = (SearchRequest) session.getAttribute("searchRequest");
		Boolean exportExcel = citizenService.exportExcel(response, searchRequest);
		if (exportExcel) {
			model.addAttribute("msg", "Your pdf sent successfully 😀😀");
		} else {
			model.addAttribute("msg", "Something went wrong! try again");
		}
	}

	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response, HttpSession session,Model model) throws Exception {
		response.setContentType("application/pdf");
		 // Generate a dynamic file name
	    String fileName = "plans_" + System.currentTimeMillis() + ".pdf";
	    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		// Retrieve SearchRequest from session
		SearchRequest searchRequest = (SearchRequest) session.getAttribute("searchRequest");
		Boolean exportPdf = citizenService.exportPdf(response, searchRequest);
		if (exportPdf) {
			model.addAttribute("msg", "Your pdf sent successfully 😀😀");
		} else {
			model.addAttribute("msg", "Something went wrong! try again 🥺🥺");
		}
	  
	}

	@PostMapping("/handleSearch")
	public String handleSearch(@ModelAttribute("searchObj") SearchRequest searchRequest, Model model,
			HttpSession session) {
		System.out.print(searchRequest);

		init(model);
		// Store SearchRequest in session
		session.setAttribute("searchRequest", searchRequest);
		List<CitizenPlan> handleSearch = citizenService.handleSearch(searchRequest);
		model.addAttribute("handleSearchList", handleSearch);
		return "index";
	}

	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("searchObj", new SearchRequest());
		init(model);

		return "index";

	}

	private void init(Model model) {
		model.addAttribute("planNames", citizenService.getAllPlanNameType());
		model.addAttribute("status", citizenService.getAllPlanStatusType());
	}

}
