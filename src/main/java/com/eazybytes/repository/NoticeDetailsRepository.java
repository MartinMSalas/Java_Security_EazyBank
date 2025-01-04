package com.eazybytes.repository;

import com.eazybytes.model.NoticeDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeDetailsRepository extends CrudRepository<NoticeDetails,Integer> {
}
