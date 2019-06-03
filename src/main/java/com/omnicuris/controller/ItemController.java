package com.omnicuris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnicuris.dto.RequestDto;
import com.omnicuris.dto.ResponseDto;
import com.omnicuris.model.Items;
import com.omnicuris.service.ItemsService;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemsService itemservice;

	@GetMapping("/items")
	public List<Items> getAllItems() {
		return itemservice.findAll();
	}

	@PostMapping("/addItems")
	public Items createItem(@RequestBody RequestDto item) {
		return itemservice.savedata(item);
	}

	@GetMapping("/items/{id}")
	public Items getItemById(@PathVariable(value = "id") String itemId) {
		return itemservice.findById(itemId);

	}

	@GetMapping("/bulk/{name}/{quantity}/{email}")
	public ResponseEntity<?> getBulk(@PathVariable(value = "name") String name,
			@PathVariable(value = "quantity") String quantity, @PathVariable(value = "email") String email) {
		ResponseDto response = itemservice.getBulk(name, quantity, email);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/single/{name}/{email}")
	public ResponseEntity<?> getsingle(@PathVariable(value = "name") String name,
			@PathVariable(value = "email") String email) {
		ResponseDto response = itemservice.getSingle(name, email);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public Items updateItem(@PathVariable(value = "id") String itemId, @RequestBody RequestDto itemDetails) {
		return itemservice.updateItems(itemId, itemDetails);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable(value = "id") String itemId) {
		ResponseDto response = itemservice.deleteById(itemId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
