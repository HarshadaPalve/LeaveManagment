package com.example.EmployeeLeavesManagement.controller;

import com.leave.management.controller.LeaveController;
import com.leave.management.service.LeaveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = LeaveController.class)
public class LeaveControllerTest {

    @MockBean
    private LeaveService leaveService;

    @Autowired
    LeaveController leaveController;

    @Test
    public void isEmployeeAvailableInSystemTestSuccess() {

        Integer employeeId = 1;
        String leaveType = "Casual Leave";
        Integer leaveCount = 2;
        String expectedResponse = "Leaves have been marked";

        when(leaveService.isEmployeeNotAvailable(employeeId)).thenReturn(false);
        when(leaveService.applyLeave(any(), any(), any())).thenReturn(expectedResponse);

        String response = leaveController.applyLeaveInSystem(employeeId, leaveType, leaveCount);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void isEmployeeNotAvailableInSystemTestSuccess() {

        Integer employeeId = 1;
        String leaveType = "Casual Leave";
        Integer leaveCount = 2;
        String expectedResponse = "Employee is not present in the System";

        when(leaveService.isEmployeeNotAvailable(employeeId)).thenReturn(true);

        String response = leaveController.applyLeaveInSystem(employeeId, leaveType, leaveCount);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void applyLeaveInSystemSuccess() {

        Integer employeeId = 1;
        String leaveType = "Casual Leave";
        Integer leaveCount = 2;
        String expectedResponse = "Leaves have been marked";

        when(leaveService.isEmployeeNotAvailable(employeeId)).thenReturn(false);
        when(leaveService.applyLeave(any(), any(), any())).thenReturn(expectedResponse);

        String response = leaveController.applyLeaveInSystem(employeeId, leaveType, leaveCount);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void leaveTypeIsNotAppropriate() {

        Integer employeeId = 1;
        String leaveType = "Earn Leave";
        Integer leaveCount = 2;
        String expectedResponse = "Leave Type is not appropriate";

        when(leaveService.isEmployeeNotAvailable(employeeId)).thenReturn(false);
        when(leaveService.applyLeave(any(), any(), any())).thenReturn(expectedResponse);

        String response = leaveController.applyLeaveInSystem(employeeId, leaveType, leaveCount);

        assertEquals(expectedResponse, response);
    }

    @Test
    public void lapseYearEndLeavesTest() {
        String expectedResponse = "All Leaves are lapsed";
        when(leaveService.lapseYearEndLeaves(any())).thenReturn(expectedResponse);
        String response = leaveService.lapseYearEndLeaves(Calendar.getInstance());
        assertEquals(expectedResponse, response);
    }

}
