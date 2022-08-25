package mandacaru;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Pdt {
	
	RestTemplate restTemplate = new RestTemplate();
	
	HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
	
	Gson gson = new Gson();
	
	// requisão de token da api da pdt sing
	
    public String pdtToken() throws ParseException{
    	
    	String uri = "https://h-auth.portaldedocumentos.com.br/auth/realms/assinador/protocol/openid-connect/token";
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	
    	HttpEntity<String> httpEntity = new HttpEntity<>("username=integracao.ufc@pd.tec.br&password=3gpB9d*n&client_id=assinador-app&client_secret=&grant_type=password&",headers);
    	
    	String result = restTemplate.postForObject(uri, httpEntity, String.class);
		
		JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
		
		String token = jsonObject.get("access_token").getAsString();
		 
        return token;
    }
    
    // requisão do id do processo da pdt sing
    
    public String pdtProcess(String token) throws ParseException{
    	String uri = "https://esign-api-pprd.portaldedocumentos.com.br/processes";
    	
    	String jsontext = 
    			"{\"title\":\"Valida\u00e7\u00e3o de An\u00fancio\","
    			+ "\"requester\":{\"id\":\"44afea47-2bfa-4380-9dae-e1e2ebe7a64d\"},"
    			+ "\"company\":{\"id\":\"036e8bc8-f964-4969-92c4-d255d258d941\"},"
    			+ "\"flow\":{\"defineOrderOfInvolves\":true,\"hasExpiration\":true,\"expiration\":\"2022-12-30\"}"
    			+ ",\"members\":"
    			// primeiro membro
    			+ "["
//    			+ "{\"name\":\"Gabriel Santiago\","
//    			+ "\"email\":\"gabrielsrmj@alu.ufc.br\","
//    			+ "\"documentType\":\"CPF\","
//    			+ "\"documentCode\":\"012.345.678-99\","
//    			+ "\"actionType\":{\"id\":\"510b226e-c705-4120-ad9d-4a19633ea3df\"},"
//    			+ "\"responsibility\":{\"id\":\"50a625b5-213a-4743-ae92-f3732d87f159\"},"
//    			+ "\"authenticationType\":{\"id\":\"841c8833-8566-4a9a-be5b-b30839ed138d\"},"
//    			+ "\"order\":1,"
//    			+ "\"type\":\"SUBSCRIBER\","
//    			+ "\"representation\":{\"willActAsPhysicalPerson\":true,\"willActRepresentingAnyCompany\":false}},"
    			// segundo membro
    			+ "{\"name\":\"Nicolas Caneiro\","
    			+ "\"email\":\"caneiroassado@gmail.com\","
    			+ "\"documentType\":\"CPF\","
    			+ "\"documentCode\":\"012.345.678-99\","
    			+ "\"actionType\":{\"id\":\"510b226e-c705-4120-ad9d-4a19633ea3df\"},"
    			+ "\"responsibility\":{\"id\":\"50a625b5-213a-4743-ae92-f3732d87f159\"},"
    			+ "\"authenticationType\":{\"id\":\"841c8833-8566-4a9a-be5b-b30839ed138d\"},"
    			+ "\"order\":1,"
    			+ "\"type\":\"SUBSCRIBER\","
    			+ "\"representation\":{\"willActAsPhysicalPerson\":true,\"willActRepresentingAnyCompany\":false}}]}";
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.add("Authorization", "Bearer " + token);
    	
    	HttpEntity<String> httpEntity = new HttpEntity<>(jsontext,headers);
    	
    	String result = restTemplate.postForObject(uri, httpEntity, String.class);
    	
    	JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
		
		String processId = jsonObject.get("id").getAsString();
		 
        return processId;
    }
    
    // requisão do id do documento da pdt sing
    
    public String pdtDocument(String token, String processId) throws ParseException{
    	
    	String uri = "https://esign-api-pprd.portaldedocumentos.com.br/processes/" + processId +"/documents";
    	
    	String jsontext = 
    			"{\"extension\":\"PDF\","
    			+ "\"isPendency\":false,"
    			+ "\"name\":\"meu-arquivo\","
    			+ "\"order\":1,"
    			+ "\"type\":\"SIGN\"}";
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.add("Authorization", "Bearer " + token);
    	
    	HttpEntity<String> httpEntity = new HttpEntity<>(jsontext,headers);
    	
    	String result = restTemplate.postForObject(uri, httpEntity, String.class);
    	
    	JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
		
		String documentId = jsonObject.get("id").getAsString();
		 
        return documentId;
    }
    
    // upload de documento do processo não esta funcionando
    
    public void pdtUpDocument(String token, String processId, String documentId) throws ParseException, IOException{
    	
    	String uri = "https://esign-api-pprd.portaldedocumentos.com.br/processes/" + processId + "/documents/" + documentId +"/upload";
    	
    	byte[] pdf = Files.readAllBytes(Paths.get("F:\\dummy.pdf"));
    	
    	MultiValueMap<String, byte[]> map= new LinkedMultiValueMap<String, byte[]>();
    	map.add("file", pdf);
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    	headers.add("Authorization", "Bearer " + token);
    	
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename("dummy.pdf")
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(pdf, fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);

        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                String.class);
        
        System.out.println(response);
    	
    }
    
    // bota o processo pra rodar, não testado
    
    public void patch(String token, String processId) throws ParseException{
    	
    	requestFactory.setConnectTimeout(0);
    	requestFactory.setReadTimeout(0);

    	restTemplate.setRequestFactory(requestFactory);
    	
    	String uri = "https://esign-api-pprd.portaldedocumentos.com.br/processes/" + processId +"/documents";
    	
    	String jsontext = 
    			"{"
    			+ "\"status\": \"RUNNING\""
    			+ "}";
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	headers.add("Authorization", "Bearer " + token);
    	
    	HttpEntity<String> httpEntity = new HttpEntity<>(jsontext,headers);
    	
    	ResponseEntity<String> responseEntity = restTemplate.exchange(
    			uri, 
    			HttpMethod.PATCH, 
    			httpEntity, 
    			String.class);
    	
    	System.out.println(responseEntity);
    		

    }
    
    // retorna o status do processo
    
    public String pdtCheckDocument(String token, String processId, String documentId) throws ParseException{
    	
    	String uri = "https://esign-api-pprd.portaldedocumentos.com.br/processes/" + processId +"/documents/" + documentId;
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	headers.add("Authorization", "Bearer " + token);

    	HttpEntity<String> httpEntity = new HttpEntity<>(headers);
    	
    	String result = restTemplate.exchange(
    			uri, 
    			HttpMethod.GET, 
    			httpEntity, 
    			String.class).getBody();
    	
    	JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
		
		String check = jsonObject.get("status").getAsString();
    	
    	return check;
    }
    
    public String pdtCheckProcess(String token, String processId) throws ParseException{
    	
    	String uri = "https://esign-api-pprd.portaldedocumentos.com.br/processes/" + processId ;
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	headers.add("Authorization", "Bearer " + token);

    	HttpEntity<String> httpEntity = new HttpEntity<>(headers);
    	
    	String result = restTemplate.exchange(
    			uri, 
    			HttpMethod.GET, 
    			httpEntity, 
    			String.class).getBody();
    	
    	JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
		
		String check = jsonObject.get("status").getAsString();
    	
    	return check;
    }
    
}
