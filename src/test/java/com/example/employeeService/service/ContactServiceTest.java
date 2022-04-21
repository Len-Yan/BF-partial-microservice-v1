package com.example.employeeService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.employeeservice.domain.Contact;
import com.example.employeeservice.repository.ContactRepository;
import com.example.employeeservice.service.ContactService;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {

	@InjectMocks
	ContactService contactService;
	
	@Mock
	ContactRepository contactRepository;
	
	Contact mockContact;
	Contact mockContact2;
	List<Contact> contacts;
	
	@BeforeEach
	void setup() {
		mockContact = new Contact("11-1", "someone", "emergency", 
				"911", "110", "no@email", "police", "?");
		mockContact2 = new Contact();
		contacts = new ArrayList<Contact>();
	}
	
	@Test
	public void testGetContactById() {
		when(contactRepository.findById("11-1")).thenReturn(Optional.of(mockContact));
		Contact c = contactService.getContactById("11-1");
		assertEquals(mockContact, c);
	}
	
	@Test
	public void testAddContact() {
		when(contactRepository.insert(mockContact)).thenReturn(mockContact);
		Contact c = contactService.addContact(mockContact);
		assertEquals(mockContact, c);
	}
	
	@Test
	public void testUpdateContact() {
		when(contactRepository.save(mockContact)).thenReturn(mockContact);
		Contact c = contactService.updateContact(mockContact);
		assertEquals(mockContact, c);
	}
	
	@Test
	public void testDeleteContactById() {
		when(contactRepository.deleteContactById("11-1")).thenReturn(mockContact);
		Contact c = contactService.deleteContactById("11-1");
		assertEquals(mockContact, c);
	}
	
	@Test
	public void testGetAllByUserId() {
		contacts.add(mockContact); contacts.add(mockContact2);
		List<Contact> ansContacts = new ArrayList<Contact>();
		ansContacts.add(mockContact);
		
		when(contactRepository.findByIdLike("11")).thenReturn(ansContacts);
		List<Contact> c = contactService.getAllByUserId("11");
		assertEquals(ansContacts, c);
	}
	
	@Test
	public void testGetAll() {
		contacts.add(mockContact); contacts.add(mockContact2);
		when(contactRepository.findAll()).thenReturn(contacts);
		List<Contact> c = contactService.getAll();
		assertEquals(contacts, c);
	}
}
