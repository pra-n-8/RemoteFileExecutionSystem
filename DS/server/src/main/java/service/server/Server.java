package service.server;

import java.util.ArrayList;
import java.util.List;


import models.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class Server {
	final static List<Response> URLList =new ArrayList();
	@RequestMapping(value ="/register",method =RequestMethod.POST)
	public ResponseEntity registerService(@RequestBody Response link){
		URLList.add(link);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers,HttpStatus.CREATED);
	}
	@RequestMapping(value = "/getresources/{type}", method = RequestMethod.GET)
	public ResponseEntity getResources(@PathVariable("type") String type) {
		System.out.println(type);
		List<String> res = new ArrayList<>();
		for( Response link : URLList){
			RestTemplate restTemplate = new RestTemplate();
			try {
				if(link.getExecType().toLowerCase().equals(type.toLowerCase())){
					Response response = restTemplate.getForObject(link.getUrl(), Response.class);
					res.add(response.getUrl());
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}

		}
		HttpHeaders headers = new HttpHeaders();
		if(res.size() ==0){
			res.add("No services available");
			return new ResponseEntity<>(res, headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(res, headers, HttpStatus.CREATED);

	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class NoSuchServerException extends RuntimeException {
		static final long serialVersionUID = -6516152229878843037L;
	}
}