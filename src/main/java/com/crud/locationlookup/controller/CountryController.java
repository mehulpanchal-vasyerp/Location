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
import com.crud.locationlookup.dto.CountryDTO;
import com.crud.locationlookup.dto.StateDTO;
import com.crud.locationlookup.model.Country;
import com.crud.locationlookup.service.CountryService;

import lombok.Delegate;

@RestController
@RequestMapping("/api/")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/getAllCountry")
	public ResponseEntity<?> getAllCountry() {
		List<CountryDTO> allCountries = countryService.getAllCountries();
		return ResponseEntity.ok(allCountries);
	}

	@GetMapping("/getCountryByName/{countryName}")
	public ResponseEntity<?> getCountryByName(@PathVariable String countryName) {
		CountryDTO countryByName = countryService.getCountryByName(countryName);
		return ResponseEntity.ok(countryByName);
	}

	@PostMapping("/addCountry")
	public ResponseEntity<?> addCountry(@RequestBody CountryDTO countryDTO) {
		CountryDTO addOrUpdate = countryService.addCountry(0, countryDTO);
		return ResponseEntity.ok(addOrUpdate);
	}

	@PutMapping("/updateCountry/{id}")
	public ResponseEntity<?> updateCountry(@PathVariable int id, @RequestBody CountryDTO countryDTO) {
		CountryDTO addOrUpdate = countryService.addCountry(id, countryDTO);
		return ResponseEntity.ok(addOrUpdate);
	}

	@DeleteMapping("/deleteCountry/{id}")
	public ResponseEntity<?> deleteCountry(@PathVariable int id) {
		countryService.deleteCountry(id);
		return ResponseEntity.ok("Id is Deleted SucessFully: " + id);
	}

	@GetMapping("/countryName/{countryName}")
	public ResponseEntity<?> getAllStatesByCountry(@PathVariable String countryName) {
		List<StateDTO> states = countryService.getAllStatesByCountry(countryName);
		return ResponseEntity.ok(states);
	}

	@GetMapping("/countryName/{countryName}/states/{stateName}")
	public ResponseEntity<?> getAllCitiesByState(@PathVariable String countryName, @PathVariable String stateName) {
		List<CityDTO> cities = countryService.getAllCitiesByState(countryName, stateName);
		return ResponseEntity.ok(cities);
	}

	@PostMapping("/countryId/{countryId}/assignState/{stateId}")
	public ResponseEntity<?> assignStateToCountry(@PathVariable int countryId, @PathVariable int stateId) {
		CountryDTO stateDTO = countryService.assignStateToCountry(countryId, stateId);
		return ResponseEntity.ok("State Assigned to Country");
	}

	@PostMapping("/stateId/{stateId}/assignCity/{cityId}")
	public ResponseEntity<?> assignCityToState(@PathVariable int stateId, @PathVariable int cityId) {
		StateDTO cityDTO = countryService.assignCityToState(stateId, cityId);
		return ResponseEntity.ok("City Assigned to State");
	}
}
