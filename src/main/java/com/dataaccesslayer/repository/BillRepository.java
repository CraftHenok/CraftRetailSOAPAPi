package com.dataaccesslayer.repository;

import org.springframework.data.repository.CrudRepository;
import com.dataaccesslayer.entity.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {
//	public Bill findOne(Long billId);
}
