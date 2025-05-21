package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;

import TestData.AddUserData;

public class DataUtils {
	Faker faker = new Faker();
	
	//read json file
	public static String readJSONToString(String jsonFilePath) throws IOException {
		byte[] data = Files.readAllBytes(Paths.get(jsonFilePath));
		return new String(data);
	}
	
	//extract data using jsonpath
	public static Object readTestDataFromJsonString(String jsonData, String jsonPath) {
		return JsonPath.read(jsonData,jsonPath);
	}
	
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
	
//	JsonPath Expression:
//
//		$.id: Extracts the root-level id.
//		$.address.street: Extracts street from the nested address object.
//		$.phones[0].number: Extracts the first phone number in the phones array.
	
//	JsonPath: This is a query language for JSON, and it is typically used with RestAssured or the json-path library for extracting data from JSON objects.
//
//	In RestAssured, you can use response.jsonPath().get(...) to extract values from a JSON response.
//
//	Jackson's ObjectMapper: This is used for converting Java objects to JSON and vice versa, but it does not provide built-in support for querying JSON data using JsonPath syntax.
	
	//read json file and extract data using key json Array and jsaon object
	public static String readJSONData(String jsonFilePath, String key) throws IOException {
		String jsonData = readJSONToString(jsonFilePath);
		return JsonPath.read(jsonData, key).toString();
	}

}
