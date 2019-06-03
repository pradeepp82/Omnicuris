package com.omnicuris.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omnicuris.dto.RequestDto;
import com.omnicuris.dto.ResponseDto;
import com.omnicuris.model.Items;
import com.omnicuris.repo.ItemRepository;

@Service
public class ItemServiceImpl implements ItemsService {

	@Autowired
	ItemRepository itemRepo;

	@Override
	public List<Items> findAll() {

		return itemRepo.findAll();
	}

	@Override
	public Items savedata(RequestDto requestdata) {
		Items item = dtoToEntity(requestdata);
		return itemRepo.save(item);
	}

	private Items dtoToEntity(RequestDto item) {
		Items itemdata = new Items();
		if (null != item) {
			if (null != item.getItemId()) {
				itemdata.setItemId(item.getItemId());
			}
			itemdata.setItemName(item.getItemName());
			itemdata.setItemDetails(item.getItemDetails());
		}

		return itemdata;
	}

	@Override
	public Items findById(String itemId) {
		Items itemdata = null;
		Optional<Items> item = itemRepo.findById(itemId);
		if (item.isPresent()) {
			return item.get();
		}
		return itemdata;

	}

	@Override
	public ResponseDto deleteById(String itemId) {
		ResponseDto response = new ResponseDto();
		try {
			Items note = findById(itemId);
			if (null != note) {
				itemRepo.delete(note);
				response.setResponseMsg("Success");
			} else {
				response.setResponseMsg("No Record present for the Id :" + itemId);
			}
		} catch (Exception e) {
			response.setResponseMsg("Failure");
			throw e;
		}

		return response;

	}

	@Override
	public Items save(Items item) {
		Items updatedItem = save(item);
		return updatedItem;
	}

	@Override
	public Items updateItems(String id, RequestDto itemDetails) {
		Items item = findById(id);
		if (null != item) {
			item = dtoToEntity(itemDetails);
			item.setItemId(id);
			itemRepo.save(item);
		}
		return null;
	}

	@Override
	public ResponseDto getBulk(String name, String quantity, String email) {
		List<Items> itemlist = itemRepo.findByItemName(name);
		ResponseDto Response = new ResponseDto();
		if (null != itemlist && !itemlist.isEmpty()) {

			if (itemlist.size() < Integer.parseInt(quantity)) {
				Response.setResponseMsg("Required Quantity Not Available");
				return Response;
			}
			Response.setResponseMsg("Success");
			return Response;
		}
		Response.setResponseMsg("item Not Present Currently");
		return Response;
	}

	@Override
	public ResponseDto getSingle(String name, String email) {
		List<Items> itemlist = itemRepo.findByItemName(name);
		ResponseDto Response = new ResponseDto();
		if (null != itemlist && !itemlist.isEmpty()) {
			Response.setResponseMsg("Success");
			return Response;
		}
		Response.setResponseMsg("item Not Present Currently");
		return Response;
	}

}
