package com.discov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.discov.dataorm.Planet;

@RepositoryRestResource
public interface PlanetRepository extends JpaRepository<Planet, String> {

}
