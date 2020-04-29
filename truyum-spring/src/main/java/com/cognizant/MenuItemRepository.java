package com.cognizant;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.truyum.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {

	public List<MenuItem> findByActiveAndDateOfLaunchBefore(Boolean x,java.util.Date date);

}
