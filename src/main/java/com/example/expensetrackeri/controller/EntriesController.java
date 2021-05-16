package com.example.expensetrackeri.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetrackeri.model.Entries;
import com.example.expensetrackeri.model.User;
import com.example.expensetrackeri.repository.EntriesRepository;
import com.example.expensetrackeri.repository.UserRepository;
import com.example.expensetrackeri.request.EditEntriesRequest;
import com.example.expensetrackeri.request.EntriesRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
@RequestMapping("/api")
public class EntriesController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntriesRepository entriesRepository;

	private static Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-DD").create();

	@GetMapping("/getEntries")
	public List<Entries> fetchAll() {
		User user = getCurrentUserObj();
		
		List<Entries> entries = new ArrayList<>();
	    Iterable<Entries> entriesAll = entriesRepository.findByUserId(user.getId());
	    for (Entries e : entriesAll) {
	        entries.add(e);
	    }
	    
		return entries;
	}
	
	@PostMapping("/editEntries")
	public void editEntry(@RequestBody String requestBody) throws Exception {
		EditEntriesRequest newEntry = gson.fromJson(requestBody, EditEntriesRequest.class);
		
		Long id = newEntry.getId();
		
		Optional<Entries> e = entriesRepository.findById(id);

		if(e.isPresent()) {
			Entries entry = e.get();
			entry.setDate(newEntry.getDate());
			entry.setCost(BigDecimal.valueOf(newEntry.getCost()));
			entry.setActivity(newEntry.getActivity());
			entriesRepository.save(entry);
		} else {
			throw new Exception("Entry not found with id " + id);
		}
		
	}
	
	@DeleteMapping("/deleteEntries/{id}")
	public void deleteProduct(@PathVariable Long id) throws Exception {
		Optional<Entries> e = entriesRepository.findById(id);
		
		if(e.isPresent()) {
			Entries entry = e.get();
			entriesRepository.delete(entry);
		} else {
			throw new Exception("Entry not found with id " + id);
		}
			
			

	}
	
	@PostMapping("/entries")
	public void createEntry(@RequestBody String requestBody) {
		
		EntriesRequest newEntry = gson.fromJson(requestBody, EntriesRequest.class);

		Entries entry = new Entries();
		entry.setActivity(newEntry.getActivity());
		entry.setCost(BigDecimal.valueOf(newEntry.getCost()));
		entry.setDate(newEntry.getDate());
		entry.setUser(getCurrentUserObj());
		
		entriesRepository.save(entry);

	}

	private User getCurrentUserObj() {
		String username = getCurrentUsername();
		return getUserObject(username);
	}

	private String getCurrentUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}

	private User getUserObject(String username) {
		return userRepository.findByUsername(username);
	}

}
