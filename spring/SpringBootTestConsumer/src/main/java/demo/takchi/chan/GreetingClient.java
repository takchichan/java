package demo.takchi.chan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@EnableFeignClients
@EnableDiscoveryClient
public class GreetingClient {
	@Autowired
    private TheClient theClient;

    @FeignClient(name = "SpringBootTest")
    interface TheClient {

        @RequestMapping(path = "/v1/greeting", method = RequestMethod.GET)
        @ResponseBody
        String HelloWorld();
    }

    public String HelloWorld() {
        return "rsp from SpringBootTest: " + theClient.HelloWorld();
    }
}
