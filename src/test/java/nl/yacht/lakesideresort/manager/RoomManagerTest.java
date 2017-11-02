package nl.yacht.lakesideresort.manager;

import nl.yacht.lakesideresort.domain.Room;
import nl.yacht.lakesideresort.exception.NotFoundException;
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
	private Room r1, r2, r3, r4, r5;

	@Before
	public void setUp() throws Exception {
		RoomRepository roomRepository = mock(RoomRepository.class);
		roomManager = new RoomManager(roomRepository);

		r1 = new Room("101", Room.RoomType.BUDGET, Room.RoomSize.ONE_PERSON, LocalDate.now());
		r2 = new Room("201", Room.RoomType.NORMAL, Room.RoomSize.TWO_PERSON, LocalDate.now());
		r3 = new Room ("13", Room.RoomType.NORMAL, Room.RoomSize.ONE_PERSON, LocalDate.now());
		r4 = new Room ("4", Room.RoomType.NORMAL, Room.RoomSize.ONE_PERSON, LocalDate.now());
		r5 = new Room ("Sanders Kelder", Room.RoomType.NORMAL, Room.RoomSize.ONE_PERSON, LocalDate.now());
		r2.setId(1);

		listRooms = new ArrayList<>();
		listRooms.add(r1);
		listRooms.add(r2);
		when(roomRepository.findAll()).thenReturn(listRooms);
		long id = 1L;
		when(roomRepository.findOne(id)).thenReturn(r1);
		when(roomRepository.findOne(2L)).thenReturn(r2);
		when(roomRepository.findByRoomNumber("101")).thenReturn(r1);
		when(roomRepository.exists(1L)).thenReturn(true);
		when(roomRepository.exists(2L)).thenReturn(false);
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
		long id = roomManager.findRoomByRoomNumber("101");
		assertEquals(r1.getId(), id);
	}

	@Test
	public void updateRoom() throws Exception {
		roomManager.updateRoom(1L, r1);
	}

	@Test(expected = NotFoundException.class)
	public void updateRoom2() throws Exception {
		roomManager.updateRoom(2L, r2);
	}

	@Test
	public void insertExistingRoom() throws Exception {
		boolean result= roomManager.insertRoom(r1);
		assertFalse(result);
	}

	@Test
	public void insertRoom() throws Exception {
		boolean result = roomManager.insertRoom(r2);
		assertTrue(result);
	}

	@Test
	public void insertRoomWithNumber13() throws Exception{
		boolean result = roomManager.insertRoom(r3);
		assertFalse(result);
	}
	@Test
	public void insertRoomWithNumber4() throws Exception{
		boolean result = roomManager.insertRoom(r4);
		assertFalse(result);
	}
	@Test
	public void insertRoomWithString() throws Exception{
		boolean result = roomManager.insertRoom(r5);
		assertTrue(result);
	}


}