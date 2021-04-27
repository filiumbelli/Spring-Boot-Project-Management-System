package com.sofisticat.management.dao;

import com.sofisticat.management.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
