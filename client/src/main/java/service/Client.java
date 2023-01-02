package service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

@RestController
public class Client {
    public static int ID = 0;

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    public ResponseEntity getQuotations(@RequestBody MultipartFile file) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultipartFile> request = new HttpEntity<>(file);
        HttpHeaders headers = new HttpHeaders();
        restTemplate.postForObject("http://localhost:8081/broker", request, MultipartFile.class);
        return new ResponseEntity<>("test", headers, HttpStatus.CREATED);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class NoSuchMachine extends RuntimeException {
        static final int error_id = -1;
    }
}
