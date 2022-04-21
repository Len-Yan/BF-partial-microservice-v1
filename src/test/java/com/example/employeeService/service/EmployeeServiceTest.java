package com.example.employeeService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService employeeService;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	Employee mockEmployee;
	Employee mcokEmployee2;
	Date dob = new Date(1999, 1, 1);
	Date startDate = new Date();
	List<Employee> employees;
	
	@BeforeEach
	void setup() {
		mockEmployee = new Employee("emp1", 11, "Dummy", "Emp",
				"", "", "test@email.com", "1234567890", "", "M", 
				"987654321", dob, startDate, new Date(), "EMP**12", 
				new Date(), 15);
		employees = new ArrayList<Employee>();
	}
	
	@Test
	public void testGetEmployeeById() {
		when(employeeRepository.findById("emp1")).thenReturn(Optional.of(mockEmployee));
	
		Employee e = employeeService.getEmployeeById("emp1").get();
		assertEquals(mockEmployee, e);
	}
	
	@Test
	public void testGetEmployeeByUserId() {
		when(employeeRepository.findByUserID(11)).thenReturn(Optional.of(mockEmployee));
	
		Employee e = employeeService.getEmployeeByUserId(11);
		assertEquals(mockEmployee, e);
	}
	
	@Test
	public void testGetEmployeeByEmail() {
		when(employeeRepository.findByEmail("test@email.com")).thenReturn(Optional.of(mockEmployee));
	
		Employee e = employeeService.getEmployeeByEmail("test@email.com").get();
		assertEquals(mockEmployee, e);
	}
	
	@Test
	public void testAddEmployee() {
		when(employeeRepository.insert(mockEmployee)).thenReturn(mockEmployee);
	
		Employee e = employeeService.addEmployee(mockEmployee);
		assertEquals(mockEmployee, e);
	}
	
	@Test
	public void testUpdateEmployee() {
		when(employeeRepository.save(mockEmployee)).thenReturn(mockEmployee);
	
		Employee e = employeeService.updateEmplyee(mockEmployee);
		assertEquals(mockEmployee, e);
	}
	
	@Test
	public void testDeleteEmployee() {
		when(employeeRepository.deleteEmployeeByUserID(11)).thenReturn(mockEmployee);
	
		Employee e = employeeService.deleteEmployee(11);
		assertEquals(mockEmployee, e);
	}
	
	@Test
	public void testGetAllEmployee() {
		employees.add(mockEmployee); employees.add(mcokEmployee2);
		when(employeeRepository.findAll()).thenReturn(employees);
	
		List<Employee> e = employeeService.getAllEmployee();
		assertEquals(employees, e);
	}
	
	@Test
	public void testFindEmployeeByHouseID() {
		employees.add(mockEmployee); employees.add(mcokEmployee2);
		List<Employee> ansEmployees = new ArrayList<>();
		ansEmployees.add(mockEmployee);
		
		when(employeeRepository.findEmployeeByHouseID(15)).thenReturn(ansEmployees);
	
		List<Employee> e = employeeService.findEmployeeByHouseID(15);
		assertEquals(ansEmployees, e);
	}
}
