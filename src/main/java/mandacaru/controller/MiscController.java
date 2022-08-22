package mandacaru.controller;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(path = "/api") 
public class MiscController {
	
	// requisão de token da api do pdt sing
	
	@GetMapping(path = "/teste")
    public String teste() throws ParseException{
    	String uri = "https://h-auth.portaldedocumentos.com.br/auth/realms/assinador/protocol/openid-connect/token";
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	HttpEntity<String> httpEntity = new HttpEntity<>("username=integracao.ufc@pd.tec.br&password=3gpB9d*n&client_id=assinador-app&client_secret=&grant_type=password&",headers);
    	
    	String result = restTemplate.postForObject(uri, httpEntity, String.class);
		
		JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
		
		String token = jsonObject.get("access_token").getAsString();
		 
        return token;
    }

}
