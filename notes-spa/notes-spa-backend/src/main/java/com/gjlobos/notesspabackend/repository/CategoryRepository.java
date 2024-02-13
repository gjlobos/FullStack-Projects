package com.gjlobos.notesspabackend.repository;

import com.gjlobos.notesspabackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
