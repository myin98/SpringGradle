package com.java.conroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.FileDTO;
import com.java.dto.JsonDTO;
import com.java.mapper.JsonMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/json")
@CrossOrigin(origins = "*")
public class JsonController {

	private final JsonMapper jsonMapper;
	private int KEY = 9;
	
	@GetMapping("/test")
	public JsonDTO test() {
		JsonDTO jsonDto = JsonDTO.builder().build();
		int no = jsonMapper.test(KEY);
		if(KEY == no) {
			Map map = new HashMap<>();
			map.put("no", no);
			
			jsonDto.setResult(map);
			jsonDto.setMessage("데이터베이스 접속 성공");
			jsonDto.setStatus(true);
		} else {
			jsonDto.setMessage("데이터베이스 접속 실패");
			jsonDto.setStatus(false);
		}
		return jsonDto;
	}
	
	@GetMapping("/type/{type:[0-9]+}")
	public JsonDTO type(@PathVariable("type") int type) {
		JsonDTO jsonDto = JsonDTO.builder().build();
		jsonDto.setMessage("파일 목록 실패");
		jsonDto.setStatus(false);

		log.info("type : {}", type);
		List<FileDTO> list = jsonMapper.findByType(type);
		if(list.size() > 0) {
			jsonDto.setResult(list);
			jsonDto.setMessage("파일 목록 성공");
			jsonDto.setStatus(true);
		}

		return jsonDto;
	}
	
	@GetMapping("/no/{no:[0-9]+}/type/{type:[0-9]+}")
	public JsonDTO delete(@PathVariable("no") int no, @PathVariable("type") int type) {
		JsonDTO jsonDto = JsonDTO.builder().build();
		jsonDto.setMessage("파일 삭제 실패");
		jsonDto.setStatus(false);
		
		log.info("NO : {}, type: {}" , no, type);
		if((jsonMapper.good(no)) == 1) {
			List<FileDTO> list = jsonMapper.findByType(type);
			//if(list.size() > 0) {
			jsonDto.setResult(list);
			jsonDto.setMessage("파일 목록 성공");
			jsonDto.setStatus(true);
			} else if (jsonMapper.bad(no) == 1){
				jsonDto.setStatus(true);
			}
		
		
		return jsonDto;
				
	}

	
}
