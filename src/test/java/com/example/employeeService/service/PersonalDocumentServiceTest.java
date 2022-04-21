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

import com.example.employeeservice.domain.PersonalDocument;
import com.example.employeeservice.repository.PersonalDocumentRepository;
import com.example.employeeservice.service.PersonalDocumentService;

@ExtendWith(MockitoExtension.class)
public class PersonalDocumentServiceTest {

	@InjectMocks
	PersonalDocumentService personalDocumentService;
	
	@Mock
	PersonalDocumentRepository personalDocumentRepository;
	
	PersonalDocument mockDocument;
	PersonalDocument mockDocument2;
	List<PersonalDocument> personalDocuments;
	
	@BeforeEach
	void setUp() {
		Date enDate = new Date();
		mockDocument = new PersonalDocument("11-1", "c:d/e", "doc1", "no comment", enDate);
		mockDocument2 = new PersonalDocument();
		personalDocuments = new ArrayList<PersonalDocument>();
	}
	
	@Test
	public void testGetDocmentById() {
		when(personalDocumentRepository.findByID("11-1")).thenReturn(Optional.of(mockDocument));
		PersonalDocument p = personalDocumentService.getDocmentById("11-1").get();
		
		assertEquals(mockDocument, p);
	}
	
	@Test
	public void testAddDocument() {
		when(personalDocumentRepository.insert(mockDocument)).thenReturn(mockDocument);
		PersonalDocument p = personalDocumentService.addDocument(mockDocument);
		
		assertEquals(mockDocument, p);
	}
	
	@Test
	public void testUpdateDocment() {
		when(personalDocumentRepository.save(mockDocument)).thenReturn(mockDocument);
		PersonalDocument p = personalDocumentService.updateDocument(mockDocument);
		
		assertEquals(mockDocument, p);
	}
	
	@Test
	public void testDeleteDocment() {
		when(personalDocumentRepository.deletePersonalDocumentByID("11-1")).thenReturn(mockDocument);
		PersonalDocument p = personalDocumentService.deleteDocumentById("11-1");
		
		assertEquals(mockDocument, p);
	}
	
	@Test
	public void testGetAllByUserId() {
		personalDocuments.add(mockDocument);personalDocuments.add(mockDocument2);
		List<PersonalDocument> ansDocument = new ArrayList<PersonalDocument>();
		ansDocument.add(mockDocument);
		
		when(personalDocumentRepository.findByIDLike("11")).thenReturn(ansDocument);
		List<PersonalDocument> p = personalDocumentService.getAllByUserId("11");
		
		assertEquals(ansDocument, p);
	}
}
