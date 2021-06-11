package com.eleveneleven.seoulapt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eleveneleven.seoulapt.model.dto.HouseDeal;
import com.eleveneleven.seoulapt.model.service.HouseService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "/house")
public class HouseController {

	@Autowired
	private HouseService service;

	@GetMapping("/addrToCode")
	@ApiOperation(value = "구 -> 코드", notes = "쿼리스트링으로 구이름을 입력받으면 그에 해당하는 코드로 반환")
	private ResponseEntity<String> getCode(@RequestParam String address) {
		try {
			String code = service.getCode(address);
			if (code == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<String>(code, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/gugun")
	@ApiOperation(value = "구군으로 아파트목록", notes = "구군을 전달받으면 해당하는 구의 아파트들의 최신1개 거래 목록을 이름순으로 정렬해서 반환")
	private ResponseEntity<List<HouseDeal>> getDeals(@RequestParam String gugun) {
		try {
			List<HouseDeal> deals = service.getDeals(gugun);
			return new ResponseEntity<List<HouseDeal>>(deals, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/gugun/apt")
	@ApiOperation(value = "해당 아파트 거래내역", notes = "구, 동, 아파트이름 전달받으면 해당 아파트의 거래내역을 최신순으로 정렬해서 반환")
	private ResponseEntity<List<HouseDeal>> getAptDeals(
			@RequestParam String gugun, @RequestParam String dong, @RequestParam String aptName) {
		
		HashMap<String, String> map = new HashMap<>();
		map.put("gugun", gugun);
		map.put("dong", dong);
		map.put("aptName", aptName);
		
		try {
			List<HouseDeal> deals = service.getAptDeals(map);
			return new ResponseEntity<List<HouseDeal>>(deals, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
//	통계 1.
	@GetMapping("/chart/gugun/price")
	@ApiOperation(value = "구에서 얼마나 비싼지", notes = "구, 동, 아파트이름 전달받으면 해당 구의 최신거래아파트의 가격과 해당하는 아파트 가격에만 check = 'T' 로 반환 {dealAmount, check}")
	private ResponseEntity<List<Map<String, String>>> satisticsOfGugunByPrice(
			@RequestParam String gugun, @RequestParam String dong, @RequestParam String aptName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("gugun", gugun);
		map.put("dong", dong);
		map.put("aptName", aptName);
		try {
			List<Map<String, String>> list = service.satisticsOfGugunByPrice(map);
			return new ResponseEntity<List<Map<String, String>>>(list, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
//	통계 2.
	@GetMapping("/chart/gugun/area")
	@ApiOperation(value = "구에서 얼마나 넓은지", notes = "구, 동, 아파트이름 전달받으면 해당 구의 최신거래아파트의 면적과 해당하는 아파트 면적에만 check = 'T' 로 반환 {area, check}")
	private ResponseEntity<List<Map<String, String>>> satisticsOfGugnByArea(
			@RequestParam String gugun, @RequestParam String dong, @RequestParam String aptName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("gugun", gugun);
		map.put("dong", dong);
		map.put("aptName", aptName);
		try {
			List<Map<String, String>> list = service.satisticsOfGugunByArea(map);
			return new ResponseEntity<List<Map<String, String>>>(list, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
//	통계 3.
	@GetMapping("/chart/apt/price")
	@ApiOperation(value = "해당 아파트 시세 변화", notes = "구, 동, 아파트이름 전달받으면 해당 가격, 거래 년 월 일 을 반환{dealAmount, dealYear, dealMonth, dealDay}")
	private ResponseEntity<List<Map<String, String>>> satisticsOfAptByPrice(
			@RequestParam String gugun, @RequestParam String dong, @RequestParam String aptName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("gugun", gugun);
		map.put("dong", dong);
		map.put("aptName", aptName);
		try {
			List<Map<String, String>> list = service.satisticsOfAptByPrice(map);
			return new ResponseEntity<List<Map<String, String>>>(list, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}