package ucd.distributed;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class JavaService {

    @RequestMapping(value = "/getServer", method = RequestMethod.GET)
    public ResponseEntity getServer() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("http://localhost:8081/runfile", headers, HttpStatus.CREATED);
    }

    @RequestMapping(value ="/runfile", method = RequestMethod.POST)
    public void runFile(@RequestParam("file") MultipartFile file){
        try {
            System.out.println(file.toString());
//            runProcess("pwd");
//            System.out.println("**********");
//            runProcess("javac -cp src src/com/journaldev/files/Test.java");
//            System.out.println("**********");
//            runProcess("java -cp src com/journaldev/files/Test Hi Pankaj");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }
}
