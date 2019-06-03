package com.omnicuris.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omnicuris.model.CustomerData;

public interface CustomerDataRepo extends JpaRepository<CustomerData, String> {

	List<CustomerData> findByCustomerName(String itemName);

}
