package mandacaru.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(path = "/api") 
public class MiscController {
	
	// requisão de token da api da pdt sing
	
    public String pdtToken() throws ParseException{
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
    
    // requisão do id do processo da pdt sing
    
    @PostMapping(path = "/teste")
    public String pdtProcess() throws ParseException{
    	String uri = "https://esign-api-pprd.portaldedocumentos.com.br/processes";
    	
    	String jsontext = 
    			"{\"title\":\"Valida\u00e7\u00e3o de An\u00fancio\","
    			+ "\"requester\":{\"id\":\"44afea47-2bfa-4380-9dae-e1e2ebe7a64d\"},"
    			+ "\"company\":{\"id\":\"036e8bc8-f964-4969-92c4-d255d258d941\"},"
    			+ "\"flow\":{\"defineOrderOfInvolves\":true,\"hasExpiration\":true,\"expiration\":\"2022-12-30\"}"
    			+ ",\"members\":"
    			+ "[{\"name\":\"Gabriel Santiago\",\"email\":\"gabrielsrmj@alu.ufc.br\",\"documentType\":\"CPF\",\"documentCode\":\"012.345.678-99\",\"actionType\":{\"id\":\"510b226e-c705-4120-ad9d-4a19633ea3df\"},\"responsibility\":{\"id\":\"50a625b5-213a-4743-ae92-f3732d87f159\"},\"authenticationType\":{\"id\":\"841c8833-8566-4a9a-be5b-b30839ed138d\"},\"order\":1,\"type\":\"SUBSCRIBER\",\"representation\":{\"willActAsPhysicalPerson\":true,\"willActRepresentingAnyCompany\":false}},{\"name\":\"Nicolas Caneiro\",\"email\":\"caneiroassado@gmail.com\",\"documentType\":\"CPF\",\"documentCode\":\"012.345.678-99\",\"actionType\":{\"id\":\"510b226e-c705-4120-ad9d-4a19633ea3df\"},\"responsibility\":{\"id\":\"50a625b5-213a-4743-ae92-f3732d87f159\"},\"authenticationType\":{\"id\":\"841c8833-8566-4a9a-be5b-b30839ed138d\"},\"order\":2,\"type\":\"SUBSCRIBER\",\"representation\":{\"willActAsPhysicalPerson\":true,\"willActRepresentingAnyCompany\":false}}]}";
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.add("Authorization", "Bearer " + pdtToken());
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	HttpEntity<String> httpEntity = new HttpEntity<>(jsontext,headers);
    	
    	String result = restTemplate.postForObject(uri, httpEntity, String.class);
    	
    	JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
		
		String processId = jsonObject.get("id").getAsString();
		 
        return processId;
    }
    
    // requisão do id do documento da pdt sing
    
    public String pdtAddDocument() throws ParseException{
    	String url = "https://esign-api-pprd.portaldedocumentos.com.br/processes/:idProcess/documents";
    	
    	String jsontext = 
    			"{\"extension\":\"PDF\","
    			+ "\"isPendency\":false,"
    			+ "\"name\":\"meu-arquivo\","
    			+ "\"order\":1,"
    			+ "\"type\":\"SIGN\"}";
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.add("Authorization", "Bearer " + pdtToken());
    	
    	String uri = UriComponentsBuilder.fromHttpUrl(url)
    	        .queryParam("idProcess", "{idProcess}")
    	        .encode()
    	        .toUriString();

    	Map<String, String> params = new HashMap<>();
    	params.put("idProcess", pdtProcess());
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	HttpEntity<String> httpEntity = new HttpEntity<>(jsontext,headers);
    	
    	String result = restTemplate.postForObject(uri, httpEntity, String.class);
    	
    	JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);
		
		String documentId = jsonObject.get("id").getAsString();
		 
        return documentId;
    }
    

}
