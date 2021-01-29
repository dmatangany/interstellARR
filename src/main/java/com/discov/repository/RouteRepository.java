package com.discov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.discov.dataorm.Route;

@RepositoryRestResource
public interface RouteRepository extends JpaRepository<Route, Short>
{

}
