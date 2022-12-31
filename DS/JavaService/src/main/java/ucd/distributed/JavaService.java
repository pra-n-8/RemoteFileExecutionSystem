package ucd.distributed;

import interfaces.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.FilesStorageServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JavaService {

    FilesStorageService storageService = new FilesStorageServiceImpl();

    @RequestMapping(value = "/getServer", method = RequestMethod.GET)
    public ResponseEntity getServer() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("http://localhost:8080/runfile", headers, HttpStatus.CREATED);
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
            out = runProcess("java D:\\Pranit\\Project\\DS\\uploads\\"+file.getOriginalFilename());
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
        List out = printLines(command + " stdout:", pro.getInputStream());

        pro.waitFor();
        return out;
    }
    private List<String> printLines(String cmd, InputStream ins) throws Exception {
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

