package com.discov;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.discov.service.SimulationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
	@LocalServerPort
	private int port;

	private URL base;

    @Autowired
    private SimulationController simulationController;

	@Autowired
	private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;

    @Test
    public void restfulRouteHasSpecificStartTagAndEndTag(String startTag, String endTag) throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" +  port + "/custom/" + startTag + "/" + endTag,
                String.class)).hasSizeGreaterThan(2).startsWith(startTag).endsWith(endTag);
    }
}
