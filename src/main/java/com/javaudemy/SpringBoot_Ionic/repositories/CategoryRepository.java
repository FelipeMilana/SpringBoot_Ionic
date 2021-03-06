package com.javaudemy.SpringBoot_Ionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javaudemy.SpringBoot_Ionic.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Transactional(readOnly=true)
	Category findByName(String name);
}
