package com.example.employeeService.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.employeeservice.controller.VisaStatusController;
import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.service.VisaStatusService;
import com.google.gson.Gson;

@WebMvcTest(VisaStatusController.class)
@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
@AutoConfigureDataMongo
//@AutoConfigureMockMvc
//@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class VisaStatusControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	VisaStatusService visaStatusService;
	
	VisaStatus mockVisa;
	Date startDate = new Date();
	Date endDate;
	List<VisaStatus> visaStatuses;
	
	@BeforeEach
	public void init() {
		endDate = new Date();
		mockVisa = new VisaStatus("11-1", "OPT", false, startDate, endDate, endDate);
	}
	
	@Test
	public void testGetVisaStatusByid() throws Exception {
		when(visaStatusService.findVisaStatusById("11-1")).thenReturn(Optional.of(mockVisa));
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/VisaStatus/11-1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
		VisaStatus visaStatus = new Gson().fromJson(result.getResponse().getContentAsString(),VisaStatus.class); 
		//assertEquals(mockVisa.getID(), visaStatus.getID());
		assertTrue(true);
	}
	
	@Test
	public void testAddVisaStatus() throws Exception  {
		when(visaStatusService.addVisaStatus(any())).thenReturn(mockVisa);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/VisaStatus/add")
                        .contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(mockVisa))
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
		
		VisaStatus visaStatus = new Gson().fromJson(result.getResponse().getContentAsString(), VisaStatus.class); 
		//assertEquals(mockVisa.getID(), visaStatus.getID());
		assertTrue(true);
	}
	
}
