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

import com.crud.locationlookup.dto.CountryDTO;
import com.crud.locationlookup.dto.StateDTO;
import com.crud.locationlookup.service.CountryService;
import com.crud.locationlookup.service.StateService;

@RestController
@RequestMapping("/api/")
public class StateController {

	@Autowired
	private StateService stateService;

	@GetMapping("/getAllStates")
	public ResponseEntity<?> getAllStates() {
		List<StateDTO> allStates = stateService.getAllStates();
		return ResponseEntity.ok(allStates);
	}

	@GetMapping("/getStateByName/{stateName}")
	public ResponseEntity<?> getStateByName(@PathVariable String stateName) {
		StateDTO stateByName = stateService.getStateByName(stateName);
		return ResponseEntity.ok(stateByName);
	}

	@PostMapping("/addState")
	public ResponseEntity<?> addState(@RequestBody StateDTO stateDTO) {
		StateDTO addState = stateService.addState(0, stateDTO);
		return ResponseEntity.ok(addState);
	}

	@PutMapping("/updateState/{id}")
	public ResponseEntity<?> updateState(@PathVariable int id, @RequestBody StateDTO stateDTO) {
		StateDTO updateState = stateService.addState(id, stateDTO);
		return ResponseEntity.ok(updateState);
	}

	@DeleteMapping("/deleteState/{id}")
	public ResponseEntity<?> deleteState(@PathVariable int id) {
		stateService.deleteState(id);
		return ResponseEntity.ok("Id is Deleted SucessFully: " + id);
	}

}
