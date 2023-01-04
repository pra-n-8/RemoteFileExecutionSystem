package ucd.distributed;

import messages.ServerResponseMessage;
import interfaces.FilesStorageService;

import models.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import services.FilesStorageServiceImpl;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JavaService {
    @PostConstruct
    public void registerService(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Response> request = new HttpEntity<>(new Response("Java","http://localhost:8083/getServer"));
        try {
            restTemplate.postForObject("http://localhost:8080/register", request, Response.class);
            System.out.println("Service Registered");
        }catch (Exception e){
            System.out.println("Service cannot be Registered");
        }
    }
    FilesStorageService storageService = new FilesStorageServiceImpl();

    @RequestMapping(value = "/getServer", method = RequestMethod.GET)
    public ResponseEntity getServer() {
        HttpHeaders headers = new HttpHeaders();
        Response res = new Response("Java","http://localhost:8083/runfile");
        return new ResponseEntity<>(res, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value ="/runfile", method = RequestMethod.POST)
    public List runFile(@RequestParam("file") MultipartFile file){
        List <String> out = new ArrayList<>();
        try {
            storageService.save(file);
            System.out.println(file.getOriginalFilename());
            System.out.println("**********");
            out = runProcess("java D:\\Pranit\\Project\\DS\\uploads\\"+file.getOriginalFilename());
            System.out.println("**********");

            storageService.delete(file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
    private List runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        List out = new ArrayList<>();
        out = printLines( pro.getInputStream());
        if(out.size()==0) {
            out= printLines(pro.getErrorStream());
        }
        pro.waitFor();
        return out;
    }
    private List<String> printLines(InputStream ins) throws Exception {
        String line = null;
        List<String> output = new ArrayList<>();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            output.add(line);
        }
        return output;
    }
}

