package com.manavit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.manavit.entity.CitizenPlan;





@Repository
public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer>{
	
          //  select drop down funcationally in table
	
@Query(value="SELECT DISTINCT plan_name FROM citizen_plans_info",nativeQuery= true) 
	  List<String> getAllPlanNameType();
	  
	  @Query(value="select distinct plan_status from citizen_plans_info",nativeQuery = true) 
	  List<String> getAllPlanStatusType();
		
	
}
