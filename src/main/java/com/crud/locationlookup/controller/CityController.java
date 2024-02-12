package com.crud.locationlookup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.locationlookup.dto.CityDTO;
import com.crud.locationlookup.service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {

	@Autowired
	private CityService cityService;
	
	

	@GetMapping("/getAllCity")
	public ResponseEntity<?> getAllStates() {
		List<CityDTO> allCity = cityService.getAllCity();
		return ResponseEntity.ok(allCity);
	}

	@GetMapping("/getCityByName/{cityName}")
	public ResponseEntity<?> getCityByName(@PathVariable String cityName) {
		CityDTO cityByName = cityService.getCityByName(cityName);
		return ResponseEntity.ok(cityByName);
	}

	@PostMapping("/addCity")
	public ResponseEntity<?> addState(@RequestBody CityDTO cityDTO) {
		CityDTO addState = cityService.addOrUpdateCity(0, cityDTO);
		return ResponseEntity.ok(addState);
	}

	@PutMapping("/updateCity/{id}")
	public ResponseEntity<?> updateState(@PathVariable int id, @RequestBody CityDTO cityDTO) {
		CityDTO updateCity = cityService.addOrUpdateCity(id, cityDTO);
		return ResponseEntity.ok(updateCity);
	}

	@DeleteMapping("/deleteCity/{id}")
	public ResponseEntity<?> deleteState(@PathVariable int id) {
		cityService.deleteCity(id);
		return ResponseEntity.ok("Id is Deleted SucessFully: " + id);
	}

}
