package com.example.EmployeeLeavesManagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.leave.management.enums.Leaves;
import com.leave.management.service.LeaveService;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LeaveService.class)
public class LeaveServiceTest {

    @Autowired
    private LeaveService leaveService;

    @BeforeEach
    private void init() {
        leaveService.setEmployeesLeavesMap();
    }

    @Test
    public void leaveIsNotAvailableTest() {

        Integer employeeId = 1;
        String leaveType = "Casual Leave";
        Integer leaveCount = 7;
        String expectedResponse = "You don't have enough leaves in " + leaveType;
        Optional<Leaves> leavesOptional = Leaves.getLeave(leaveType);

        String response = leaveService.applyLeave(leavesOptional.get(), leaveCount, employeeId);
        assertEquals(expectedResponse, response);

    }

    @Test
    public void markCasualLeaveTest() {

        Integer employeeId = 1;
        String leaveType = "Casual Leave";
        Integer leaveCount = 2;
        String expectedResponse = "Leaves have been marked";
        Optional<Leaves> leavesOptional = Leaves.getLeave(leaveType);

        String response = leaveService.applyLeave(leavesOptional.get(), leaveCount, employeeId);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void markSickLeaveTest() {

        Integer employeeId = 1;
        String leaveType = "Sick Leave";
        Integer leaveCount = 2;
        String expectedResponse = "Leaves have been marked";
        Optional<Leaves> leavesOptional = Leaves.getLeave(leaveType);

        String response = leaveService.applyLeave(leavesOptional.get(), leaveCount, employeeId);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void markPaidLeaveTest() {

        Integer employeeId = 1;
        String leaveType = "Paid Leave";
        Integer leaveCount = 2;
        String expectedResponse = "Leaves have been marked";
        Optional<Leaves> leavesOptional = Leaves.getLeave(leaveType);

        String response = leaveService.applyLeave(leavesOptional.get(), leaveCount, employeeId);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void employeeIsNotAvailableTest() {

        Integer employeeId = 10;
        boolean employeeNotAvailable = leaveService.isEmployeeNotAvailable(employeeId);
        assertTrue(employeeNotAvailable);
    }

    @Test
    public void employeeIsAvailableTest() {

        Integer employeeId = 1;
        boolean employeeNotAvailable = leaveService.isEmployeeNotAvailable(employeeId);
        assertFalse(employeeNotAvailable);
    }

    @Test
    public void lapseYearEndLeavesForLastDayOfTheYearSuccess() {

        String expectedResponse = "All Leaves are lapsed";
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), 11, 31, 0, 0, 0);
        String response = leaveService.lapseYearEndLeaves(calendar);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void lapseYearEndLeavesForMiddleOfTheYearSuccess() {

        String expectedResponse = "Leaves will be lapsed on last day of the year";
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), 02, 20, 0, 0, 0);
        String response = leaveService.lapseYearEndLeaves(calendar);
        assertEquals(expectedResponse, response);
    }
}
