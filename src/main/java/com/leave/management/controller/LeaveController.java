package com.leave.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leave.management.enums.Leaves;
import com.leave.management.service.LeaveService;



@RestController
public class LeaveController {
	
	
	  
	 

	@Autowired
	private LeaveService leaveService;

	//public String test(Integer employeeID, String leaveType, Integer leaveCount) {
	 @GetMapping(value = "/users") 
	public String applyLeave(@RequestParam("employeeID") Integer employeeID,
			  
			  @RequestParam("leaveType") String leaveType,
			  
			  @RequestParam("leaveCount") Integer leaveCount) {

		if (!leaveService.getEmployeesLeavesMap().containsKey(employeeID))
			return "Employee is not present in the System";

		if (leaveType.equalsIgnoreCase(Leaves.PAID_LEAVE.toString())) {

			if (leaveService.isLeavesAvailable(Leaves.PAID_LEAVE, leaveCount, employeeID)) {
				leaveService.markLeaves(Leaves.PAID_LEAVE, leaveCount, employeeID);
				return "Leaves have been marked";
			} else {
				return "You don't have enough leaves in " + Leaves.PAID_LEAVE;
			}

		} else if (leaveType.equalsIgnoreCase(Leaves.CASUAL_LEAVE.toString())) {

			if (leaveService.isLeavesAvailable(Leaves.CASUAL_LEAVE, leaveCount, employeeID)) {
				leaveService.markLeaves(Leaves.CASUAL_LEAVE, leaveCount, employeeID);
				return "Leaves have been marked";
			} else {
				return "You don't have enough leaves " + Leaves.CASUAL_LEAVE;
			}

		} else if (leaveType.equalsIgnoreCase(Leaves.SICK_LEAVE.toString())) {

			if (leaveService.isLeavesAvailable(Leaves.SICK_LEAVE, leaveCount, employeeID)) {
				leaveService.markLeaves(Leaves.SICK_LEAVE, leaveCount, employeeID);
				return "Leaves have been marked";
			} else {
				return "You don't have enough leaves " + Leaves.SICK_LEAVE;
			}

		} else {
			return "Leave Type is not appropriate";
		}
	}

	

}
