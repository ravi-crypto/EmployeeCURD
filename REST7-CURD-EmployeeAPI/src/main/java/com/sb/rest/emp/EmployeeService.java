package com.sb.rest.emp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service

public class EmployeeService {
	private final Map<Long, Employee> employees = new LinkedHashMap<>();
	private long nextId = 3;

	public EmployeeService() {
		employees.put(1L, new Employee(1L, "Ravi", "ravi@gmail.com", "IT"));
		employees.put(2L, new Employee(2L, "John", "john@gmail.com", "HR"));
	}

	// READ: Get all employees
	public List<Employee> getAllEmployee() {

		return new ArrayList<Employee>(employees.values());

	}
	// READ: Get employee byID

	public Employee getEmployeeById(Long id) {
		if (employees.containsKey(id)) {
			Employee emp = employees.get(id);
			return emp;

		}
		return null;

	}

	// CREATE: Add employee
	public Employee createEmployee(Employee emp) {
		emp.setId(nextId++);
		employees.put(emp.getId(), emp);

		return emp;

	}

	// UPDATE: Replace complete employee
	public Employee updateEmployee(Long id, Employee updateEmp) {
		if (employees.containsKey(id)) {
			updateEmp.setId(id);
			employees.put(id, updateEmp);
			return updateEmp;
		}

		return null;
	}

	// PATCH: Update only supplied fields
	public Employee patchEmployee(Long id, Employee patchEmp) {
		Employee exitingEmp = employees.get(id);
		if (exitingEmp == null) {
			return null;
		}

		if (patchEmp.getName() != null) {
			exitingEmp.setName(patchEmp.getName());
		}
		if (patchEmp.getEmail() != null) {
			exitingEmp.setEmail(patchEmp.getEmail());
		}
		if (patchEmp.getDepartment() != null) {
			exitingEmp.setDepartment(patchEmp.getDepartment());
		}

		return exitingEmp;
	}

	// DELETE: Remove employee
	public boolean deleteEmployee(Long id) {
		return employees.remove(id) != null;
	}

}
