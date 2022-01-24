package com.leave.management.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.leave.management.enums.Leaves;

@Service
public class LeaveService {

	private Map<Integer, Map<Leaves, Integer>> employeesLeavesMap;

	private Map<Integer, Map<Leaves, Integer>> getLeavesDataOfEmployees() {

		Map<Integer, Map<Leaves, Integer>> employeesLeavesMap = new HashMap<>();

		employeesLeavesMap.put(1, loadEmployeesLeave());
		employeesLeavesMap.put(2, loadEmployeesLeave());
		employeesLeavesMap.put(3, loadEmployeesLeave());
		employeesLeavesMap.put(4, loadEmployeesLeave());

		return employeesLeavesMap;
	}

	private Map<Leaves, Integer> loadEmployeesLeave() {

		Map<Leaves, Integer> map = new HashMap<>();
		map.put(Leaves.SICK_LEAVE, 5);
		map.put(Leaves.PAID_LEAVE, 5);
		map.put(Leaves.CASUAL_LEAVE, 5);
		return map;

	}

	public boolean isLeavesAvailable(Leaves leave, Integer leaveCount, Integer employeeId) {

		return employeesLeavesMap.get(employeeId).get(leave) >= leaveCount;

	}

	public void markLeaves(Leaves leave, Integer leaveCount, Integer employeeId) {
		Integer count = employeesLeavesMap.get(employeeId).get(leave);
		employeesLeavesMap.get(employeeId).put(leave, count - leaveCount);
	}

	public Map<Integer, Map<Leaves, Integer>> getEmployeesLeavesMap() {
		if (employeesLeavesMap == null)
			employeesLeavesMap = getLeavesDataOfEmployees();
		return employeesLeavesMap;
	}

}
