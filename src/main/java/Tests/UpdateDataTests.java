package Tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;

import BaseClass.BaseTest;
import Constants.FileConstants;
import TestData.AddUserData;
import TestData.UpdateUserData;
import Utils.DataUtils;
import Utils.FileUtils;
import Utils.RestUtils;
import Utils.Utils;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class UpdateDataTests extends BaseTest{
	
public static String token = null;
public static String userid="";
	
	@BeforeMethod
	public void setup1() {
		Response response = RestUtils.login(FileUtils.LoginPayload());
		token = RestUtils.getToken(response);
		userid = RestUtils.getUserid(response);
	}
	
	@Test()
	public void test_UpdateData() throws JsonProcessingException {
		//Add Data API
		AddUserData user = DataUtils.generateUserData();
		System.out.println("Account No: "+ user.getAccountNo());
		System.out.println("Department No: "+ user.getDepartmentNo());
		System.out.println("Salary: "+ user.getSalary());
		System.out.println("Pincode: "+ user.getPincode());
		String accno= user.getAccountNo();
		String deptno=user.getDepartmentNo();
		String salary = user.getSalary();
		String pincode =user.getPincode();
		//converting to POJO to JSON string
		String payload = Utils.serializeData(user);
		Response response = RestUtils.addData(payload,FileUtils.readData().getProperty("addData"),token);
		System.out.println(response.prettyPrint());
		assertThat("Stauscode is not 201", response.getStatusCode()==201);
		
		
		//Get Data API
		Response getresponse = RestUtils.getData(token);
		String getResp = getresponse.asString();
		String useridExpression = "$[?(@.userid ==  \"" + userid + "\")]";
		JSONArray recordsUSerids= JsonPath.parse(getResp).read(useridExpression);
		String getUserResponseid = "";
        for(Object record: recordsUSerids) {
			HashMap<String, Object> jsonObject = (HashMap<String, Object>) record;
        	String accnumber = (String) jsonObject.get("accountno");
        	if(accnumber.equals(user.getAccountNo())) {
        		getUserResponseid = jsonObject.get("id").toString();
        		System.out.println("getUserResponseid: "+getUserResponseid);
        	}
        }
		
		//Update data
        Faker faker = new Faker();
		UpdateUserData updateUserPayload = new UpdateUserData(accno,deptno,salary,pincode,userid,getUserResponseid);
		updateUserPayload.setDepartmentno(faker.number().digits(1));
		String updatedeptno = updateUserPayload.getDepartmentno();
		String updatePayload = Utils.serializeData(updateUserPayload);
		Response updateResponse = RestUtils.updateData(updatePayload, FileUtils.readData().getProperty("updateData"),token);
		assertEquals(updateResponse.statusCode(),200); // if invalid token given status code should not be 200
		Utils.validateSchema(updateResponse, FileConstants.UPDATE_DATA_SCHEMA_PATH);
		
		//Get Data API
		Response getrespons = RestUtils.getData(token);
		String getRespo = getrespons.asString();
		String useridExpression1 = "$[?(@.userid ==  \"" + userid + "\")]";
		JSONArray recordsUSerids1= JsonPath.parse(getRespo).read(useridExpression1);
        for(Object record: recordsUSerids1) {
			HashMap<String, Object> jsonObject = (HashMap<String, Object>) record;
        	String accnumber = (String) jsonObject.get("accountno");
        	String deno = (String) jsonObject.get("departmentno");
        	if(accnumber.equals(user.getAccountNo()) && updatedeptno.equals(deno)) {
        		System.out.println("Department number updated successfully");
        	}
        }
        
	}
}
