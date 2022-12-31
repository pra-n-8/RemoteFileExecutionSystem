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

@RestController
public class JavaService {

    FilesStorageService storageService = new FilesStorageServiceImpl();

    private final Path rootLocation = Path.of("src/JavaService/src/main/resources");
    @RequestMapping(value = "/getServer", method = RequestMethod.GET)
    public ResponseEntity getServer() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("http://localhost:8080/runfile", headers, HttpStatus.CREATED);
    }

    @RequestMapping(value ="/runfile", method = RequestMethod.POST)
    public void runFile(@RequestParam("file") MultipartFile file){
        try {
            storageService.save(file);
//            System.out.println(rootLocation);
            System.out.println(file.getOriginalFilename());
            runProcess("pwd");
            System.out.println("**********");
            runProcess("java /home/kroyooz/Desktop/Masters/Distributed Systems/Project/DS/uploads/"+file.getOriginalFilename());
            System.out.println("**********");
//            runProcess("java "+file);
//            System.out.println("filename is : "+filepath);r

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }
    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
    }

    private Path storeFile(MultipartFile file){
            try {
                if (file.isEmpty()) {
                    return null;
                }
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream,rootLocation,
                            StandardCopyOption.REPLACE_EXISTING);
                }
                return rootLocation;
            }
            catch (IOException e) {
                return null;
            }
        }

}

