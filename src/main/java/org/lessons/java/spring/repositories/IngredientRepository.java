package org.lessons.java.spring.repositories;

import org.lessons.java.spring.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
	Ingredient findByName(String name);
}
