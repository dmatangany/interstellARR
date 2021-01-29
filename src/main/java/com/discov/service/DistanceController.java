package com.discov.service;

import com.discov.dataorm.Distance;
import com.discov.exception.ControllerException;
import com.discov.exception.ResourceNotFoundException;
import com.discov.logical.CustomAlgorithm;
import org.apache.derby.iapi.services.daemon.DaemonFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/stellar")
public class DistanceController {

    static final Logger LOG = org.apache.log4j.Logger.getLogger(SimulationController.class);

    @Autowired
    @Lazy
    private CustomAlgorithm customAlgorithm;

    @RequestMapping(value = "/getDistance", method = RequestMethod.POST)
    public String getMyDistance(HttpServletRequest request, Model model) throws ResourceNotFoundException, ControllerException {
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        try {
            Distance distance = new Distance();
            distance.setSource(source);
            distance.setDestination(destination);
            String route = customAlgorithm.processNodes(source, destination).toString();
            if ((route == null) || (route == "")) {
                LOG.error("Route " + source + " to " + destination + " Not found!");
                throw new ResourceNotFoundException("Route " + source + " to " + destination + " Not found!");
            }
            distance.setRoute(route);
            System.out.println("Distance: " + source + " " + destination + " = " + route);
            model.addAttribute(distance);
            return "result";
        } catch (ControllerException e) {
            LOG.error("Route " + source + " to " + destination + " Not found!");
            throw new ControllerException("Internal Server Exception while getting route " + source + " to " + destination);
        }
    }
}