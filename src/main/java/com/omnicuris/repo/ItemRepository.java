package com.omnicuris.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omnicuris.model.Items;
@Repository
public interface ItemRepository extends JpaRepository<Items, String>{

	List<Items> findByItemName(String name);

}
