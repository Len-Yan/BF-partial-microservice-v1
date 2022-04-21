package com.example.employeeService.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.employeeservice.controller.AddressController;
import com.example.employeeservice.domain.Address;
import com.example.employeeservice.domain.Contact;
import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.domain.PersonalDocument;
import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.service.AddressService;
import com.example.employeeservice.service.ContactService;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.service.PersonalDocumentService;
import com.example.employeeservice.service.VisaStatusService;

@WebMvcTest(AddressController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureDataMongo
public class EmployeeCombindResponseControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	EmployeeService employeeService;
	
	@MockBean
	AddressService addressService;
	
	@MockBean 
	ContactService contactService;
	
	@MockBean
	VisaStatusService visaStatusService;
	
	@MockBean
	PersonalDocumentService personalDocumentService;
	
	Address mockaddress1;
	Contact mockContact;
	Employee mockEmployee;
	PersonalDocument mockDocument;
	VisaStatus mockVisaStatus;
	
	Date dob = new Date(1999, 1, 1);
	Date startDate = new Date();
	
	@BeforeEach
	void init() {
		Date enDate = new Date();
		mockaddress1 = new Address("11-a", "1111 ravens crest dr", "", "plainsbor", "NJ", "08536");
		mockContact = new Contact("11-1", "someone", "emergency", 
				"911", "110", "no@email", "police", "?");
		mockEmployee = new Employee("emp1", 11, "Dummy", "Emp",
				"", "", "test@email.com", "1234567890", "", "M", 
				"987654321", dob, startDate, new Date(), "EMP**12", 
				new Date(), 15);
		mockDocument = new PersonalDocument("11-1", "c:d/e", "doc1", "no comment", enDate);
		mockVisaStatus = new VisaStatus("11-1", "OPT", false, startDate, enDate, enDate);
	}
	/*
	@Test
	public void testGetFullInfo() {
		when(employeeService.getEmployeeByUserId(any())).thenReturn(mockEmployee);
		when(contactService.getAllByUserId(any())).thenReturn(new List<Contact>(mockContact));
		when(addressService.addAddress(any())).thenReturn(mockaddress);
		when(addressService.addAddress(any())).thenReturn(mockaddress);
		when(addressService.addAddress(any())).thenReturn(mockaddress);
		
		
		
	}*/
	
	
}
