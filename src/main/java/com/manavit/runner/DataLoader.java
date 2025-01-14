package com.manavit.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.manavit.entity.CitizenPlan;
import com.manavit.repo.CitizenPlanRepo;



@Component
public class DataLoader implements ApplicationRunner{
	@Autowired
	private CitizenPlanRepo citizenPlanRepo;
	
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		citizenPlanRepo.deleteAll();
//		Cash plan-approved
		CitizenPlan c1=new CitizenPlan();
		c1.setCitizenName("Jone");
		c1.setPlanName("Cash");
		c1.setGender("Male");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setBenefitAmt(5000.00);
		
		
////		Cash plan-denial
		CitizenPlan c2=new CitizenPlan();
		c2.setCitizenName("smith");
		c2.setGender("Male");
		c2.setPlanName("Cash");
		c2.setPlanStatus("Denial");
		c2.setDenialReason("Rental income");
		
//		Cash plan-Terminated
		CitizenPlan c3=new CitizenPlan();
		c3.setCitizenName("joe");
		c3.setGender("Female");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
	c3.setTerminatedDate(LocalDate.now());
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setTerminationReason("Got a job");
	
//Food plan  approved

		CitizenPlan c4=new CitizenPlan();
		c4.setCitizenName("gini");
		c4.setPlanName("Food");
		c4.setGender("Female");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenefitAmt(4000.00);
//		
		
////		Cash plan-denial
		CitizenPlan c5=new CitizenPlan();
		c5.setCitizenName("robin");
		c5.setGender("Male");
		c5.setPlanName("Food");
		c5.setPlanStatus("Denial");
		c5.setDenialReason("Property income");
//		
//		Cash plan-Terminated
		CitizenPlan c6=new CitizenPlan();
		c6.setCitizenName("jssioe");
		c6.setGender("Female");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
	c6.setTerminatedDate(LocalDate.now());
		c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		c6.setTerminationReason("MisConduct");
//	
		//Medical plan  approved

		CitizenPlan c7=new CitizenPlan();
		c7.setCitizenName("Hemi");
		c7.setPlanName("Medical");
		c7.setGender("Female");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		c7.setBenefitAmt(14000.00);
//		

		CitizenPlan c8=new CitizenPlan();
		c8.setCitizenName("Bikas");
		c8.setGender("Male");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denial");
		c8.setDenialReason("Property income");
//		

		CitizenPlan c9=new CitizenPlan();
		c9.setCitizenName("Rob");
		c9.setGender("Male");
		c9.setPlanName("Medical");
		c9.setPlanStatus("Terminated");
	c9.setTerminatedDate(LocalDate.now());
		c9.setPlanStartDate(LocalDate.now().minusMonths(4));
		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
		c9.setTerminationReason("Employed");
		
		
		//Employement plan  approved

		CitizenPlan c10=new CitizenPlan();
		c10.setCitizenName("Sami");
		c10.setPlanName("Employement");
		c10.setGender("Female");
		c10.setPlanStatus("Approved");
		c10.setPlanStartDate(LocalDate.now());
		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		c10.setBenefitAmt(13000.00);
//		

		CitizenPlan c11=new CitizenPlan();
		c11.setCitizenName("rabas");
		c11.setGender("Male");
		c11.setPlanName("Employement");
		c11.setPlanStatus("Denial");
		c11.setDenialReason("Property income");
//		

		CitizenPlan c12=new CitizenPlan();
		c12.setCitizenName("Gabb");
		c12.setGender("Male");
		c12.setPlanName("Employement");
		c12.setPlanStatus("Terminated");
	c12.setTerminatedDate(LocalDate.now());
		c12.setPlanStartDate(LocalDate.now().minusMonths(4));
		c12.setPlanEndDate(LocalDate.now().plusMonths(6));
		c12.setTerminationReason("Employed");
		
		
		List<CitizenPlan>list=Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		citizenPlanRepo.saveAll(list);
	}

}
