package distributed;

import messages.ServerResponseMessage;
import interfaces.FilesStorageService;
import models.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import services.FilesStorageServiceImpl;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PythonService {

    FilesStorageService storageService = new FilesStorageServiceImpl();

    @RequestMapping(value = "/getServer", method = RequestMethod.GET)
    public ResponseEntity getServer() {
        HttpHeaders headers = new HttpHeaders();
        Response res = new Response("Python", "https://localhost:8085/runfile");
        return new ResponseEntity<>(res, headers, HttpStatus.CREATED);
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
//
            out = runProcess("python D:\\Pranit\\Project\\DS\\uploads\\"+file.getOriginalFilename());
//            out=p;
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
        List out = printLines(pro.getInputStream());
        System.out.println(pro.getInputStream());
        pro.waitFor();
        return out;
    }
    private List<String> printLines(InputStream ins) throws Exception {
        String line = null;
        List<String> output = new ArrayList<>();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            output.add(line+'\n');
        }
        return output;
    }

}

