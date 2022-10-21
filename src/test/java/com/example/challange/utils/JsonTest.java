package com.example.challange.utils;

import com.example.challange.entities.SWResultDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonTest extends Assertions {

	private SWResultDTO mockLoginBookDto() {
		SWResultDTO dto = new SWResultDTO();
		dto.setEdited("batata");
		return dto;
	}

	private String mockJson() {
		return "{\"edited\": \"batata\", \"eye_color\": \"batata\"}";
	}

	private List<SWResultDTO> mockList() {
		List<SWResultDTO> l = new ArrayList<>();
		SWResultDTO dto = mockLoginBookDto();
		l.add(dto);
		dto.setEye_color("batata");
		l.add(dto);
		return l;
	}

	@Test
	@DisplayName("deve serializar o DTO par a Json informando a classe como auxilio")
	void test01() {
		String json = Json.toJson(mockLoginBookDto(), SWResultDTO.class);
		assertNotNull(json);
		assertTrue(json.length() > 10);
	}

	@Test
	@DisplayName("deve serializar o DTO para Json SEM informar a classe como auxilio")
	void test02() {
		String json = Json.toJson(mockLoginBookDto());
		assertNotNull(json);
		assertTrue(json.length() > 10);
	}

	@Test
	@DisplayName("deve serializar um Json para DTO sem auxilio da classe")
	void test03() {
		Object o = Json.fromJson(mockJson());
		assertNotNull(o);
	}

	@Test
	@DisplayName("deve serializar um Json para DTO com auxilio da classe")
	void test04() {
		SWResultDTO dto = Json.fromJson(mockJson(), SWResultDTO.class);
		assertNotNull(dto);
		assertEquals("batata", dto.getEdited());
	}

	@Test
	@DisplayName("deve serializar uma lista em json")
	void test05() {
		String json = Json.listToJson(mockList());
		assertNotNull(json);
		assertTrue(json.length() > 10);
	}

	@Test
	@DisplayName("deve serializar um json para array da classe informada")
	void test06() {
		String json = Json.listToJson(mockList());
		SWResultDTO[] l = Json.jsonToArray(json, SWResultDTO[].class);
		assertEquals(2, l.length);
		assertEquals("batata", l[0].getEdited());
		assertEquals("batata", l[1].getEye_color());
		assertThrows(
			IndexOutOfBoundsException.class,
			() -> {
				SWResultDTO dto = l[3];
			}
		);
	}

	@Test
	@DisplayName("deve serializar um json para lista")
	void teste07() {
		List<SWResultDTO> l = Json.jsonArrayToList(Json.listToJson(mockList()), SWResultDTO[].class);
		assertNotNull(l);
		assertFalse(l.isEmpty());
		assertEquals(2, l.size());
		assertEquals("batata", l.get(0).getEdited());
		assertEquals("batata", l.get(1).getEye_color());
		assertThrows(
			IndexOutOfBoundsException.class,
			() -> {
				SWResultDTO loginBookDTO = l.get(3);
			}
		);
	}

	@Test
	@DisplayName("deve serializar um json para map")
	void test08() {
		Map<String, Object> map = new HashMap<>();
		map.put("ok", true);
		map.put("data", mockLoginBookDto());

		String json = Json.toJson(map);
		map = Json.jsonToMap(json);
		assertNotNull(map);
		assertTrue(map.containsKey("ok"));
		assertTrue(map.containsKey("data"));
		assertNotNull(map.get("ok"));
		assertNotNull(map.get("data"));
	}
}
