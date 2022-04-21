package com.example.employeeService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.employeeservice.controller.AddressController;
import com.example.employeeservice.domain.Address;
import com.example.employeeservice.service.AddressService;
import com.google.gson.Gson;

@WebMvcTest(AddressController.class)
@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
@AutoConfigureDataMongo
//@AutoConfigureMockMvc
//@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class AddressControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	AddressService addressService;
	
	Address mockaddress;
	
	@BeforeEach
	void init() {
		mockaddress = new Address("11-a", "1111 ravens crest dr", "", "plainsbor", "NJ", "08536");
	}
	
	@Test
	public void testAddAddress() throws Exception {
		when(addressService.addAddress(any())).thenReturn(mockaddress);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/Address/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(mockaddress))
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
		Address address = new Gson().fromJson(result.getResponse().getContentAsString(), Address.class);
		assertEquals(mockaddress.getId(), address.getId());
	
	}
	
	@Test
	public void testAddAddressByUser() throws Exception {
		when(addressService.addAddress(any())).thenReturn(mockaddress);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/Address/add/{userId}", "44")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(mockaddress))
				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
		Address address = new Gson().fromJson(result.getResponse().getContentAsString(), Address.class);
		assertEquals(mockaddress.getId(), address.getId());
	}
	
	
}
