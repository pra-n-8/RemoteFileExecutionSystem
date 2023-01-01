package service.server;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class Server {
    @RequestMapping(value = "/resources", method = RequestMethod.GET)

	public ResponseEntity getResources() {
		
		String path = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/resources/";
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.setLocation(new URI(path));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		// returning message testing
		return new ResponseEntity<>("testing", headers, HttpStatus.CREATED);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public class NoSuchQuotationException extends RuntimeException {
		static final long serialVersionUID = -6516152229878843037L;
	}
}
