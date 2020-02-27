package com.dataaccesslayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dataaccesslayer.entity.Bill;

 
public interface BillRepository extends CrudRepository<Bill, Long> {
//	public Bill findOne(Long billId);
}
