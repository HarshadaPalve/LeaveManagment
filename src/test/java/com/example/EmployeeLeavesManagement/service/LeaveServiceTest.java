package com.example.EmployeeLeavesManagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.leave.management.enums.Leaves;
import com.leave.management.service.LeaveService;

@SpringBootTest(classes = LeaveService.class)
public class LeaveServiceTest {

	@Autowired
	private LeaveService leaveService;

	@Test
	public void isLeavesAvailableTest() {
		leaveService.getEmployeesLeavesMap();

		boolean result = leaveService.isLeavesAvailable(Leaves.SICK_LEAVE, 5, 1);
		Assertions.assertTrue(result);

	}

	@Test
	public void markLeavesTest() {
		leaveService.getEmployeesLeavesMap();

		leaveService.markLeaves(Leaves.PAID_LEAVE, 2, 1);
		Integer count = leaveService.getEmployeesLeavesMap().get(1).get(Leaves.PAID_LEAVE);
		Assertions.assertEquals(3, count);

	}

}
