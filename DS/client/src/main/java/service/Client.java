package service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Client {
    public static int ID = 0;

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public ResponseEntity getQuotations() {
        System.out.println("here");
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("test", headers, HttpStatus.CREATED);
    }

    //	To manage a runtime exception i.e. if an unknown URI path is passed
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class NoSuchQuotationException extends RuntimeException {
        static final long serialVersionUID = -6516152229878843037L;
    }
}
