package com.bytesCode.spring.datajpa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.bytesCode.spring.datajpa.controller.TutorialController;
import com.bytesCode.spring.datajpa.model.Tutorial;
import com.bytesCode.spring.datajpa.repository.TutorialRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = TutorialController.class)
class TutorialControllerTest {

	@Autowired
	MockMvc mockMvc;

	private static ObjectMapper objectMapper = new ObjectMapper();

	@MockBean
	TutorialRepository tutorialRepository;

	/*
	 * @Test void getTutorials_returnJsonContent() throws Exception { Tutorial
	 * tutorial = new Tutorial("connecting Thoughts10", "desc10", true);
	 * tutorial.setId(10L); String json = objectMapper.writeValueAsString(tutorial);
	 * when(tutorialRepository.findById(10L)).thenReturn(Optional.of(tutorial));
	 * 
	 * this.mockMvc.perform(get("/api/tutorials/{id}",tutorial.getId()))
	 * .andExpect(status().isOk());
	 * .andExpect(content().string(containsString(json))) .andDo(print()); }
	 */

	@Test
	public void addNewCustomerTest() throws Exception {
		Tutorial tutorial = new Tutorial("connecting Thoughts10", "desc10", true);
		String json = objectMapper.writeValueAsString(tutorial);

		// when(tutorialRepository.save(tutorial)).thenReturn(tutorial);
		MvcResult mvcResult = mockMvc
				.perform(post("/api/tutorials").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is2xxSuccessful()).andReturn();

	}

	@Test
	public void updateCustomerTest() throws Exception {
		Tutorial tutorial = new Tutorial("connecting Thoughts10", "desc10", true);
		tutorial.setId(10L);
		String json = objectMapper.writeValueAsString(tutorial);
		when(tutorialRepository.findById(10L)).thenReturn(Optional.of(tutorial));
		when(tutorialRepository.save(tutorial)).thenReturn(tutorial);
		MvcResult mvcResult = mockMvc
				.perform(put("/api/tutorials/{id}", tutorial.getId()).content(json)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is2xxSuccessful()).andReturn();

	}
}
