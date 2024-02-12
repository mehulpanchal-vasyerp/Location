package com.crud.locationlookup.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.locationlookup.dto.CountryDTO;
import com.crud.locationlookup.dto.StateDTO;
import com.crud.locationlookup.exception.CountryNotFoundException;
import com.crud.locationlookup.model.Country;
import com.crud.locationlookup.model.State;
import com.crud.locationlookup.repository.CountryRepository;
import com.crud.locationlookup.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<StateDTO> getAllStates() {
		List<StateDTO> list = new ArrayList<>();
		List<State> states = stateRepository.findAll();
		for (State state : states) {
			StateDTO dto = new StateDTO();
			dto.setId(state.getId());
			dto.setStateName(state.getStateName());

//			Country country = states.get();
//			if (country != null) {
//				CountryDTO countryDTO = new CountryDTO();
//				countryDTO.setId(states.getId());
//				countryDTO.setCountryName(states.getCountryName());
//				dto.setCountry(country);
//			}
			list.add(dto);
		}
		return list;
	}

	@Override
	public StateDTO getStateByName(String stateName) {
		
		State state = stateRepository.findByName(stateName);
		if (state != null) {
			StateDTO countryDTO = new StateDTO();
			countryDTO.setId(state.getId());
			countryDTO.setStateName(state.getStateName());
			return countryDTO;
		} else {
			throw new CountryNotFoundException("State not found with name: " + stateName);
		}
	}

	@Override
	public StateDTO addState(int id, StateDTO stateDTO) {
		if (id == 0) {
			// Create a new country
			Country country=null;
			State newstate = new State();

			newstate.setId(stateDTO.getId());
			newstate.setStateName(stateDTO.getStateName());
//			Country country = null;
//			if (stateDTO.getCountry().getCountryName() != null) {
//				country = countryRepository.findById(stateDTO.getCountry().getId())
//						.orElseThrow(() -> new CountryNotFoundException("Country Not found: " + id));
//				country.setCountryName(stateDTO.getCountry().getCountryName());
//				newstate.setCountry(country);
//			}
			newstate.setCountry(country);
			// Set other fields as needed
			stateRepository.save(newstate);

			// Convert and return the new country as DTO
			return new StateDTO(newstate.getId(), newstate.getStateName()
			// Add other fields as needed
			);

		} else {
			State optionalCountry = stateRepository.findById(id)
					.orElseThrow(() -> new CountryNotFoundException("Country id is not found"));
			State countryToUpdate = optionalCountry;
			// Update the country entity with the data from DTO
			if (stateDTO.getStateName() != null) {
				countryToUpdate.setStateName(stateDTO.getStateName());
			}

			// Save the updated or new country
			stateRepository.save(countryToUpdate);
			return new StateDTO(countryToUpdate.getId(), countryToUpdate.getStateName());
		}

	}

	@Override
	public void deleteState(int id) {
		stateRepository.findById(id).orElseThrow(()-> new CountryNotFoundException("State Id not Found: "+id));
		stateRepository.deleteById(id);
	}

}
