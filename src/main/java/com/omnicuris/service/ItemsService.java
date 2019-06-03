package com.omnicuris.service;

import java.util.List;

import com.omnicuris.dto.RequestDto;
import com.omnicuris.dto.ResponseDto;
import com.omnicuris.model.Items;

public interface ItemsService {

	List<Items> findAll();

	Items savedata(RequestDto item);

	Items findById(String itemId);

	ResponseDto deleteById(String itemId);

	Items save(Items item);

	Items updateItems(String itemId, RequestDto itemDetails);

	ResponseDto getBulk(String name, String quantity, String email);

	ResponseDto getSingle(String name, String email);

}
