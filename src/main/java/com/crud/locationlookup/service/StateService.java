package com.crud.locationlookup.service;

import java.util.List;

import com.crud.locationlookup.dto.StateDTO;

public interface StateService {

	List<StateDTO> getAllStates();

	StateDTO getStateByName(String stateName);

	StateDTO addState(int id, StateDTO stateDTO);

	void deleteState(int id);

}
