package com.xebia.payment.v2.repositories;

import com.xebia.payment.v2.domain.WebUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface WebUserRepository extends CrudRepository<WebUser, UUID>{
    List<WebUser> findByUsername(String username);
}