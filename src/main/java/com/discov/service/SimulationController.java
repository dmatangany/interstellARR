package com.discov.service;

import com.discov.dataload.DirectLoader;
import com.discov.dataorm.Distance;
import com.discov.exception.ControllerException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import com.discov.logical.CustomAlgorithm;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SimulationController {

	@Autowired
	@Lazy
	private CustomAlgorithm customAlgorithm;

	@RequestMapping(method = RequestMethod.GET, value="/custom/{source}/{destination}")
	@ResponseBody
	public String getCustomShortestPath(@PathVariable("source") String source, @PathVariable("destination") String destination) throws ControllerException {
		return customAlgorithm.processNodes(source, destination).toString();
	}
}
