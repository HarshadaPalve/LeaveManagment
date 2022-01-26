package com.leave.management.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.leave.management.enums.Leaves;


@Service
public class LeaveService {

    private Map<Integer, Map<Leaves, Integer>> employeesLeavesMap;

    private Map<Integer, Map<Leaves, Integer>> getLeavesDataOfEmployees() {

        Map<Integer, Map<Leaves, Integer>> employeesLeavesMap = new HashMap<>();

        employeesLeavesMap.put(1, loadEmployeesLeave(5));
        employeesLeavesMap.put(2, loadEmployeesLeave(5));
        employeesLeavesMap.put(3, loadEmployeesLeave(5));
        employeesLeavesMap.put(4, loadEmployeesLeave(5));

        return employeesLeavesMap;
    }

    private Map<Leaves, Integer> loadEmployeesLeave(int noOfLeaves) {

        Map<Leaves, Integer> map = new HashMap<>();
        map.put(Leaves.SICK_LEAVE, noOfLeaves);
        map.put(Leaves.PAID_LEAVE, noOfLeaves);
        map.put(Leaves.CASUAL_LEAVE, noOfLeaves);
        return map;
    }

    public void setEmployeesLeavesMap() {
        employeesLeavesMap = getLeavesDataOfEmployees();
    }

    private boolean isLeavesAvailable(Leaves leave, Integer leaveCount, Integer employeeId) {

        return getEmployeesLeavesMap().get(employeeId).get(leave) >= leaveCount;
    }

    private void markLeaves(Leaves leave, Integer leaveCount, Integer employeeId) {
        Integer count = employeesLeavesMap.get(employeeId).get(leave);
        employeesLeavesMap.get(employeeId).put(leave, count - leaveCount);
    }

    private Map<Integer, Map<Leaves, Integer>> getEmployeesLeavesMap() {
        if (employeesLeavesMap == null)
            setEmployeesLeavesMap();
        return employeesLeavesMap;
    }

    public boolean isEmployeeNotAvailable(Integer employeeID) {
        return !getEmployeesLeavesMap().containsKey(employeeID);
    }

    public String applyLeave(Leaves leave, Integer leaveCount, Integer employeeId) {

        if (isLeavesAvailable(leave, leaveCount, employeeId)) {
            markLeaves(leave, leaveCount, employeeId);
            return "Leaves have been marked";
        } else {
            return "You don't have enough leaves in " + leave.leaveType;
        }
    }

    public String lapseYearEndLeaves(Calendar calendar) {

        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        if (month == 11 && date == 31) {
            getEmployeesLeavesMap().keySet().forEach(key -> employeesLeavesMap.put(key, loadEmployeesLeave(0)));
            return "All Leaves are lapsed";
        } else {
            return "Leaves will be lapsed on last day of the year";
        }
    }
}
