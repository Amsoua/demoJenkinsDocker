package com.tuto.docker.jenkins.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith( SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DemoJenkenDockerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getsAllAuthors() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/authors")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}


	@Test
	public void returnsNotFoundForInvalidSingleAuthor() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/author/4")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andReturn();
	}

	/*@Test
	public void addsNewRide() throws Exception {
		String newAuthor = "{\"name\":\"Monorail\",\"description\":\"Sedate travelling ride.\",\"thrillFactor\":2,\"vomitFactor\":1}";
		mockMvc.perform(MockMvcRequestBuilders.post("/author")
				.contentType(MediaType.APPLICATION_JSON)
				.content(newAuthor)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}*/


}
