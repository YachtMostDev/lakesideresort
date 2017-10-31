package nl.yacht.lakesideresort.controller;

import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.manager.RoomManager;
import nl.yacht.lakesideresort.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(RoomController.class)
public class RoomControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RoomManager roomManager;

	@Test
	public void getRooms() throws Exception {
		mockMvc.perform(get("/api/room"))
			.andExpect(status().isOk());
	}

	@Test
	public void findRoomByRoomNumber() throws Exception {
		mockMvc.perform(get("/api/room/search/42"))
			.andExpect(status().isOk());
	}

	@Test
	public void getSingleRoom() throws Exception {
		mockMvc.perform(get("/api/room/42"))
				.andExpect(status().isOk());
	}

	@Test
	public void insertRoom() throws Exception {
		mockMvc.perform(post("/api/room/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"roomNumber\":99,\"roomSize\":\"ONE_PERSON\",\"roomType\":\"BUDGET\",\"availableFrom\":\"2017-02-03\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void changeRoom() throws Exception {
		mockMvc.perform(put("/api/room/42")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"roomNumber\":99,\"roomSize\":\"ONE_PERSON\",\"roomType\":\"BUDGET\",\"availableFrom\":\"2017-02-03\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteRoom() throws Exception {
		mockMvc.perform(delete("/api/room/42"))
				.andExpect(status().isOk());
	}



}