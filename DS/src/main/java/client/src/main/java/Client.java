import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client {
    public static int ID = 0;

    public Client() {
    }

    @RequestMapping(value = "/request/{machine_type}", method = RequestMethod.GET)
    public String getQuotations(@PathVariable(value = "machine_type", required = true) String machine_type) {
        // if (machine_type.length() > 0) {
        // System.out.println(machine_type);
        // } else {
        // // throw error
        // }
        return machine_type;

    }

}
