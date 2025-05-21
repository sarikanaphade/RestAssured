package Utils;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import io.restassured.response.Response;

public class Utils {

	public static void validateSchema(Response response, String schemaFilePath) {
		response.then().assertThat().body(matchesJsonSchema(new File(schemaFilePath)));
	}
	
	//Jackson lib is used for searlzation and deserialization
	//POJO to JSON string
	public static String serializeData(Object obj) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD,Visibility.ANY);
		String value = om.writeValueAsString(obj);
		return value;
	}
	
	//JSON String to POJO
	public static Object deSerializeData(String jsonData) throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD,Visibility.ANY);
		Object val = om.readValue(jsonData, Object.class);
		return val;
	}
}
