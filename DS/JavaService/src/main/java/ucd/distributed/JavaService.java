package ucd.distributed;

import core.messages.ServerResponseMessage;
import interfaces.FilesStorageService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.FilesStorageServiceImpl;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JavaService {

    FilesStorageService storageService = new FilesStorageServiceImpl();

    @RequestMapping(value = "/getServer", method = RequestMethod.GET)
    public ResponseEntity getServer() {
        HttpHeaders headers = new HttpHeaders();
        ServerResponseMessage sm = new ServerResponseMessage();
        sm.setMessage("http://localhost:8080/runfile");
        return new ResponseEntity<>(sm, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value ="/runfile", method = RequestMethod.POST)
    public List runFile(@RequestParam("file") MultipartFile file){
        List <String> out = new ArrayList<>();
        try {
            storageService.save(file);
//            System.out.println(rootLocation);
            System.out.println(file.getOriginalFilename());

//            runProcess("pwd");
            System.out.println("**********");
            runProcess("javac /home/kroyooz/Desktop/Masters/Distributed Systems/Project/DS/uploads/"+file.getOriginalFilename());
            String filename= file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf(".java"));
            System.out.println(filename);
            out = runProcess("java /home/kroyooz/Desktop/Masters/Distributed Systems/Project/DS/uploads/"+filename);
            System.out.println("**********");
//            runProcess("java "+file);
//            System.out.println("filename is : "+filepath);r
            storageService.delete(file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
    private List runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        List out = printLines( pro.getInputStream());
        out.add( printLines(pro.getErrorStream()));
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

