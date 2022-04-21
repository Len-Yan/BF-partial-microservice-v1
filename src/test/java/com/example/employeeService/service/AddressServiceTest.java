package com.example.employeeService.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.employeeservice.domain.Address;
import com.example.employeeservice.repository.AddressRepository;
import com.example.employeeservice.service.AddressService;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	@InjectMocks
	AddressService addressService;
	
	@Mock
	AddressRepository addressRepository;
	
	Address mockaddress1;
	Address mockaddress2;
	List<Address> addresses;
	
	@BeforeEach
	void setUp() throws Exception {
		mockaddress1 = new Address("11-a", "1111 ravens crest dr", "", "plainsbor", "NJ", "08536");
		mockaddress2 = new Address();
		addresses = new ArrayList<Address>();
	}

	@Test
	void testGetAddressById() {
		when(addressRepository.findById("11-a")).thenReturn(Optional.of(mockaddress1));
		Address a = addressService.getAddressById("11-a");
		assertEquals(mockaddress1, a);
	}
	
	@Test
	void testAddAddress() {
		when(addressRepository.insert(mockaddress1)).thenReturn(mockaddress1);
		Address a = addressService.addAddress(mockaddress1);
		assertEquals(mockaddress1, a);
	}
	
	@Test
	void testUpdateAddress() {
		when(addressRepository.save(mockaddress1)).thenReturn(mockaddress1);
		Address a = addressService.updateAddress(mockaddress1);
		assertEquals(mockaddress1, a);
	}
	
	@Test
	void testDeleteAddress() {
		when(addressRepository.deleteAddressById("11-a")).thenReturn(mockaddress1);
		Address a = addressService.deleteAddress("11-a");
		assertEquals(mockaddress1, a);
	}
	
	
	@Test
	void testGetAllByUserId() {
		addresses.add(mockaddress1); addresses.add(mockaddress2);
		List<Address> ansAddresses = new ArrayList<Address>();
		ansAddresses.add(mockaddress1);
		
		when(addressRepository.findByIdLike("11")).thenReturn(ansAddresses);
		List<Address> a = addressService.getAllByUserId("11");
		assertEquals(ansAddresses, a);
	}
	
	@Test
	void testGetAllAddress() {
		addresses.add(mockaddress1); addresses.add(mockaddress2);
		when(addressRepository.findAll()).thenReturn(addresses);
		List<Address> a = addressService.getAllAddress();
		assertEquals(addresses, a);
	}
	
	

}
