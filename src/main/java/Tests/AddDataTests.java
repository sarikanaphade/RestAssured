package Tests;


import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import BaseClass.BaseTest;
import Constants.FileConstants;
import TestData.AddUserData;
import Utils.FileUtils;
import Utils.RestUtils;
import Utils.Utils;
import io.restassured.builder.ResponseBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddDataTests extends BaseTest{
	public static String token = null;
	public static String userid="";

	public static AddUserData generateUserData() {
		Faker faker = new Faker();
    
    // Creating a new User object with dynamic data
		AddUserData user = new AddUserData();
		user.setAccountNo("TA-"+faker.number().digits(7));
		user.setDepartmentNo(faker.number().digits(1));
		String salary = String.valueOf(faker.number().numberBetween(30000, 500000));
		user.setSalary(salary);
		user.setPincode(faker.address().zipCode());
		user.getAccountNo();
    return user;
}
	
	@BeforeMethod
	public void login() {
		Response response = RestUtils.login(FileUtils.LoginPayload());
		userid = response.jsonPath().getString("[0].userid");
		System.out.println(userid);
		token = RestUtils.getToken(response);
	}
	
	@Test
	public void test_AddData() throws JsonProcessingException {
		AddUserData user = generateUserData();
		System.out.println("Account No: "+ user.getAccountNo());
		System.out.println("Department No: "+ user.getDepartmentNo());
		System.out.println("Salary: "+ user.getSalary());
		System.out.println("Pincode: "+ user.getPincode());
		
		//converting to POJO to JSON string
		String payload = Utils.serializeData(user);
		
		//Response response = RestUtils.addData(FileUtils.addDataPayload(),FileUtils.readData().getProperty("addData"),token);
		Response response = RestUtils.addData(payload,FileUtils.readData().getProperty("addData"),token);
		System.out.println(response.prettyPrint());
		String responseText = response.getBody().asString();
		
		assertThat("Stauscode is not 201", response.getStatusCode()==201);
		//assertEquals(response.statusCode(),201); 
		assertTrue(response.time()<2500,"Response time is less than 20000ms");
		assertTrue(responseText.contains("success"),"Error while adding user");
		Utils.validateSchema(response, FileConstants.ADD_DATA_SCHEMA_PATH);
		
		//find all records for that user id
		Response getresponse = RestUtils.getData(token);
		String firstRecordJson = getresponse.jsonPath().getString("[0]");	
		
		//validate schema - getdata with only the first object
		given()
        .body(firstRecordJson)  // Validate only the first record
        .contentType("application/json")
        .when()
        .then()  // Apply assertions here, using .then() to validate the response
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GetDataSchema.json"));  // Validate using JSON schema

		
		
		String getresponseText = getresponse.asString();
//		String getResp = getresponse.asString();
//		String useridExpression = "$[?(@.userid ==  \"" + userid + "\")]";
//		JSONArray recordsUSerids= JsonPath.parse(getResp).read(useridExpression);
//		System.out.println(recordsUSerids);
//		int count = recordsUSerids.size(); // get all userid's with userid specified
//        System.out.println("Total records with userId " + userid + ": " + count);
//        
//        for(Object record: recordsUSerids) {
//			HashMap<String, Object> jsonObject = (HashMap<String, Object>) record;
//        	String accnumber = (String) jsonObject.get("accountno");
//        	if(accnumber.equals(user.getAccountNo())) {
//        		System.out.println("Record gets added successfully!!");
//        	}
//        }
		
        //graphQL api query
//		String accountID = "TA-1234567";
//		String getUserQuery = "{\n" +
//                "  \"query\": \"query { getUser(accountID: \\\"" + accountID + "\\\") { id userid }}\"\n" +
//                "}";
//		
//		Response getUserResponse = RestAssured.given()
//                .baseUri("http://your-graphql-api-url.com/graphql")  // Replace with your actual GraphQL API URL
//                .contentType("application/json")
//                .body(getUserQuery)
//                .post();  // For GraphQL queries, POST is typically used
		
	}

	//create response from output string
	public Response createMockResponse(String jsonBody) {
		ResponseBuilder rb = new ResponseBuilder();
		rb.setBody(jsonBody);
		return rb.build();
	}
	
	@Test
	public void checkPost() {
		HashMap map = new HashMap();
		map.put("name", "abc");
		map.put("job", "abc");
		String[] states = {"active", "inactive"};
		map.put("status", states);
		
//		given()
//			//.contentType("application/json")
//			.header("Authorization", "Bearer " + token)
//			.header("Content-Type", "application/json")
//			.body(map)
//		.when()
//		    .post("https://reqres.in/api/users")
//		.then()
//			.statusCode(201)
//			.body("name", equalTo("abc"))
//			.body("job", equalTo("abc"))
//			.body("status[0]", equalTo("active"))
//			.body("status[1]", equalTo("inactive"))
//			.body("status", hasSize(2))
//			.body("status", hasItems("active", "inactive"))
//			.body("status", everyItem(containsString("active")))
//			.body("status", everyItem(containsString("inactive")))
//			.body("status", everyItem(containsString("abc")))
//			.log().all();
		given()
		.header("Authorization", "Bearer " + token)
		.header("Content-Type", "application/json")
		.when()
		.get("https://reqres.in/api/users")
		.then()
		.statusCode(200)
		.body("data[0].id", equalTo(1))
		.body("data[0].first_name", equalTo("George"));
	}
	
	
	
	//How to verify if the user gets added, call getdata - but how to search for that user and fetch id and userid 
	
}
