package com.mmstechnology.repository;

import com.mmstechnology.model.ContactMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessagesRepository extends CrudRepository<ContactMessages,String> {
}
