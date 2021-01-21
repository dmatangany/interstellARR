package com.discov.service;

import com.discov.logical.DijkstraAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.discov.logical.CustomAlgorithm;

/**
 * 
 * @author Philani Dlamini
 * This Restful controller delegates to the ShortestPathService stragegy to compute the shortest path
 */
@RestController
public class SimulationController {
	
	@Autowired
	@Lazy
	private CustomAlgorithm customAlgorithm;

	@Autowired
	@Lazy
	private DijkstraAlgorithm shortestPathService;

	@RequestMapping(method = RequestMethod.GET, value="/shortestpath/{source}/{destination}")
	@ResponseBody
	public String getShortestPath(@PathVariable("source") String source, @PathVariable("destination") String destination){
		return shortestPathService.findShortestPath(source, destination);
	}

	@RequestMapping(method = RequestMethod.GET, value="/custom/{source}/{destination}")
	@ResponseBody
	public String getCustomShortestPath(@PathVariable("source") String source, @PathVariable("destination") String destination){
		return customAlgorithm.processNodes(source, destination).toString();
	}}
