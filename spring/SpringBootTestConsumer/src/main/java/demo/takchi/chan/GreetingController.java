package demo.takchi.chan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class GreetingController {
	@Autowired
    private GreetingClient helloWorldClient;

	private static Logger logger = LogManager.getLogger(GreetingController.class);
	
    @GetMapping("/get-greeting")
	@HystrixCommand(fallbackMethod = "greetingFallBack")
    public String greeting() {
    	String msg = helloWorldClient.HelloWorld();
    	logger.error(msg);
        return msg;
    }
	
    public String greetingFallBack() {
        return "fall back msg";
    }
}