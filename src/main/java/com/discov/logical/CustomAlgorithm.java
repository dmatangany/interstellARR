//created from scratch by davison matanga dmatangany@gmail.com/0713628200 Jan-2021
package com.discov.logical;

import com.discov.dataload.DirectLoader;
import com.discov.dataorm.Route;
import com.discov.exception.ControllerException;
import com.discov.repository.PlanetRepository;
import com.discov.repository.RouteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomAlgorithm {
    static final Logger LOG = org.apache.log4j.Logger.getLogger(DirectLoader.class);
    Map dictionaryCurrent = new HashMap();
    Map dictionaryDestiny = new HashMap();
    Map dictionaryFinal = new HashMap();
    @Autowired
    PlanetRepository planetReposit;

    @Autowired
    RouteRepository routesReposit;

    private String mitigateCount(Map tokens) {
        int counter = 1000;
        String taketoken = "";
        for (Object val : tokens.values()) {
            String token = (String) val.toString().replace("^", "");
            if (token.length() < counter) {
                taketoken = token;
                counter = token.length();
            }
        }
        return taketoken;
    }

    private String coalesceValue(String token, String principal) {
        if (!principal.trim().contains(token)) {
            principal += token;
        }
        return principal;
    }

    public String processNodes(String startNode, String endNode) throws ControllerException {
        String pathvalue = startNode;
        try {
            List<Route> routeListing = routesReposit.findAll(Sort.by(Sort.Direction.ASC, "source"));
            int counter = 1;
            for (Route newroute : routeListing) {
                String currentNode = newroute.getSource().getPlanetName().trim();
                String currentLetter = newroute.getSource().getPlanetID().trim();
                String destinyNode = newroute.getDest().getPlanetName().trim();
                String destinyLetter = newroute.getDest().getPlanetID().trim();
                if (dictionaryDestiny.containsKey(currentLetter)) {
                    pathvalue = dictionaryDestiny.get(currentLetter).toString();
                }
                if (endNode.equals(destinyLetter)) {
                    pathvalue = coalesceValue(currentLetter, pathvalue);
                    pathvalue = coalesceValue(destinyLetter, pathvalue);
                    dictionaryCurrent.put(currentLetter, pathvalue);
                    dictionaryFinal.put(counter, pathvalue);
                    counter += 1;
                } else {
                    pathvalue = coalesceValue(currentLetter, pathvalue);
                    dictionaryCurrent.put(currentLetter, pathvalue);
                    dictionaryDestiny.put(destinyLetter, pathvalue);
                }
            }
            if(dictionaryFinal.isEmpty()){
                LOG.info("Route " + startNode.toString() + " to " + endNode.toString() + " not found");
                throw new ControllerException("Route not found");
            }
            return mitigateCount(dictionaryFinal);
        } catch (Exception ex) {
            LOG.info("Route " + startNode.toString() + " to " + endNode.toString() + " not found: " + ex.getMessage());
        } finally {
            return mitigateCount(dictionaryFinal);
        }
    }
}