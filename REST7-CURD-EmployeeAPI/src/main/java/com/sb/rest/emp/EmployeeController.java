package com.sb.rest.emp;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	private final EmployeeService empSrvice;

	public EmployeeController(EmployeeService empSrvice) {
		this.empSrvice = empSrvice;
	}

	@GetMapping("/all")
	public List<Employee> getAllEmployee() {
		List<Employee> allEmployee = empSrvice.getAllEmployee();
		return allEmployee;

	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employeeById = empSrvice.getEmployeeById(id);
		if (employeeById == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(employeeById);

	}

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
		Employee employee = empSrvice.createEmployee(emp);

		return ResponseEntity.status(HttpStatus.CREATED).body(employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updateEmp) {
		Employee updateEmployee = empSrvice.updateEmployee(id, updateEmp);
		if (updateEmployee == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updateEmployee);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Employee> patchEmployee(@PathVariable Long id, @RequestBody Employee patchEmp) {
		Employee patchEmployee = empSrvice.patchEmployee(id, patchEmp);
		if (patchEmployee == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(patchEmployee);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		boolean deletedEmployee = empSrvice.deleteEmployee(id);
		if (!deletedEmployee) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}

}
