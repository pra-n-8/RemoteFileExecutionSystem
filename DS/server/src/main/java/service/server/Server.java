package service.server;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import messages.ServerResponseMessage;
import models.Response;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class Server {
	final static List<String> URLList =new ArrayList<String>()
	{{
		add("http://localhost:8083/getServer");
		add("http://localhost:8085/getServer");
	}};

	@RequestMapping(value = "/getresources", method = RequestMethod.GET)
	public ResponseEntity getResources() {
		List<ServerResponseMessage> res = new ArrayList<>();
		for( String link : URLList){
			RestTemplate restTemplate = new RestTemplate();
			try {
//				System.out.println(restTemplate.getForObject(link, Response.class).getUrl());
				ServerResponseMessage response = restTemplate.getForObject(link, ServerResponseMessage.class);
				res.add(response);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		HttpHeaders headers = new HttpHeaders();
		// returning message testing
		return new ResponseEntity<>(res, headers, HttpStatus.CREATED);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class NoSuchServerException extends RuntimeException {
		static final long serialVersionUID = -6516152229878843037L;
	}
}