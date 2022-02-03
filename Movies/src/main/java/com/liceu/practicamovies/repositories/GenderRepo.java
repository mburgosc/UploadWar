package com.liceu.practicamovies.repositories;

import com.liceu.practicamovies.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepo extends JpaRepository<Gender,Integer> {
}
