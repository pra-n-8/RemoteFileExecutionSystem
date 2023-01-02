package service.server;

import java.util.ArrayList;
import java.util.List;


import models.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
class link{
	String type;
	String url;

	public link(String type, String url) {
		this.type = type;
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

}
@RestController
public class Server {
	final static List<link> URLList =new ArrayList()
	{{
		add(new link("Java","http://localhost:8083/getServer"));
		add(new link("Python","http://localhost:8085/getServer"));
	}};

	@RequestMapping(value = "/getresources", method = RequestMethod.GET)
	public ResponseEntity getResources(@RequestParam("type") String type) {
		System.out.println(type);
		List<String> res = new ArrayList<>();
		for( link link : URLList){
			RestTemplate restTemplate = new RestTemplate();
			try {
				if(link.getType().toLowerCase().equals(type.toLowerCase())){
					System.out.println("inside");
					Response response = restTemplate.getForObject(link.getUrl(), Response.class);

					res.add(response.getUrl());
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(res, headers, HttpStatus.CREATED);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class NoSuchServerException extends RuntimeException {
		static final long serialVersionUID = -6516152229878843037L;
	}
}