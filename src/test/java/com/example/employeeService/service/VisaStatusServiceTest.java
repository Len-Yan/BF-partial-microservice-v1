package com.example.employeeService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.repository.VisaStatusRepository;
import com.example.employeeservice.service.VisaStatusService;

@ExtendWith(MockitoExtension.class)
public class VisaStatusServiceTest {

	@InjectMocks
	VisaStatusService visaStatusService;
	
	@Mock
	VisaStatusRepository visaStatusRepository;
	
	VisaStatus mockVisaStatus;
	VisaStatus mockVisaStatus2;
	Date startDate = new Date();
	Date endDate;
	List<VisaStatus> visaStatuses;
	
	@BeforeEach
	void setup() {
		endDate = new Date();
		mockVisaStatus = new VisaStatus("11-1", "OPT", false, startDate, endDate, endDate);
		mockVisaStatus2 = new VisaStatus();
		visaStatuses = new ArrayList<VisaStatus>();
	}
	
	@Test
	public void testFindVisaStatusById() {
		when(visaStatusRepository.findByID("11-1")).thenReturn(Optional.of(mockVisaStatus));
		
		VisaStatus v = visaStatusService.findVisaStatusById("11-1").get();
		assertEquals(mockVisaStatus, v);
	}
	
	@Test
	public void testAddVisaStatus() {
		when(visaStatusRepository.insert(mockVisaStatus)).thenReturn(mockVisaStatus);
		
		VisaStatus v = visaStatusService.addVisaStatus(mockVisaStatus);
		assertEquals(mockVisaStatus, v);
	}
	
	@Test
	public void testupdateVisaStatusById() {
		when(visaStatusRepository.save(mockVisaStatus)).thenReturn(mockVisaStatus);
		
		VisaStatus v = visaStatusService.updateVisaStatus(mockVisaStatus);
		assertEquals(mockVisaStatus, v);
	}
	
	@Test
	public void testDeleteVisaStatus() {
		when(visaStatusRepository.deleteVisaStatusBy("11-1")).thenReturn(mockVisaStatus);
		
		VisaStatus v = visaStatusService.deleteVisaStatus("11-1");
		assertEquals(mockVisaStatus, v);
	}
	
	@Test
	public void testGetAllByUserId() {
		visaStatuses.add(mockVisaStatus);
		mockVisaStatus2.setID("22-2");
		visaStatuses.add(mockVisaStatus2);
		
		List<VisaStatus> ans = new ArrayList<VisaStatus>();
		ans.add(mockVisaStatus);
		when(visaStatusRepository.findByIDLike("11")).thenReturn(ans);
		
		List<VisaStatus> v = visaStatusService.getAllByUserId("11");
		assertEquals(ans, v);
	}
	
	
}
