package nl.yacht.lakesideresort.manager;

import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.repository.RoomRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class RoomManagerTest {
	private RoomManager roomManager;
	private List<Room> listRooms;
	private Room r1;

	@Before
	public void setUp() throws Exception {
		RoomRepository roomRepository = mock(RoomRepository.class);
		roomManager = new RoomManager(roomRepository);

		r1 = new Room(101, Room.RoomType.BUDGET, Room.RoomSize.ONE_PERSON, LocalDate.now());
		Room r2 = new Room(201, Room.RoomType.NORMAL, Room.RoomSize.TWO_PERSON, LocalDate.now());


		listRooms = new ArrayList<>();
		listRooms.add(r1);
		listRooms.add(r2);
		when(roomRepository.findAll()).thenReturn(listRooms);
		long id = 1L;
		when(roomRepository.findOne(id)).thenReturn(r1);
		when(roomRepository.findByRoomNumber(101)).thenReturn(r1);
		when(roomRepository.exists(1L)).thenReturn(true);
	}

	@Test
	public void getRooms() throws Exception {
		Iterable<Room> rooms = roomManager.getRooms();
		assertEquals(listRooms, rooms);
	}

	@Test
	public void getSingleRoom() throws Exception {
		Room foundRoom = roomManager.getSingleRoom(1L);
		assertEquals(r1, foundRoom);
	}

	@Test
	public void deleteRoom() throws Exception {
		roomManager.deleteRoom(1L);
	}

	@Test
	public void findRoomByRoomNumber() throws Exception {
		long id = roomManager.findRoomByRoomNumber(101);
		assertEquals(r1.getId(), id);
	}

	@Test
	public void updateRoom() throws Exception {
		roomManager.updateRoom(1L, r1);
	}

}