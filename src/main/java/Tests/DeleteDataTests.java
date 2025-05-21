package Tests;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;

import BaseClass.BaseTest;
import Constants.FileConstants;
import TestData.AddUserData;
import TestData.DeleteUserData;
import Utils.DataUtils;
import Utils.FileUtils;
import Utils.RestUtils;
import Utils.Utils;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class DeleteDataTests extends BaseTest{
	public static String token = null;
	public static String userid="";
		
		@BeforeMethod
		public void setup1() {
			Response response = RestUtils.login(FileUtils.LoginPayload());
			token = RestUtils.getToken(response);
			userid = RestUtils.getUserid(response);
		}
		
		@Test()
		public void test_DeleteData() throws JsonProcessingException {
			//Add Data API
			AddUserData user = DataUtils.generateUserData();
			System.out.println("Account No: "+ user.getAccountNo());
			System.out.println("Department No: "+ user.getDepartmentNo());
			System.out.println("Salary: "+ user.getSalary());
			System.out.println("Pincode: "+ user.getPincode());
			String accno= user.getAccountNo();
			System.out.println("accno: "+accno);
			//converting to POJO to JSON string
			String payload = Utils.serializeData(user);
			Response response = RestUtils.addData(payload,FileUtils.readData().getProperty("addData"),token);
			System.out.println(response.prettyPrint());
			assertThat("Stauscode is not 201", response.getStatusCode()==201);
			
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
	        
	        DeleteUserData data = new DeleteUserData(userid, getUserResponseid);
	        String deletePayload = Utils.serializeData(data);
	        System.out.println("Delete Payload: "+deletePayload);
	        logger.info("Delete API started");
	        Response deleteResponse = RestUtils.deleteData(deletePayload, FileUtils.readData().getProperty("deleteData"), token);
	        System.out.println(deleteResponse.prettyPrint());
	        assertThat("Stauscode is not 200", deleteResponse.getStatusCode()==200);
	        Utils.validateSchema(deleteResponse, FileConstants.DELETE_DATA_SCHEMA_PATH);
	        
	        Response checkresponse = RestUtils.getData(token);
			String chkResp = checkresponse.asString();
			String useridExpression1 = "$[?(@.userid ==  \"" + userid + "\")]";
			JSONArray recordsUSerids1= JsonPath.parse(chkResp).read(useridExpression1);
			for(Object record: recordsUSerids1) {
				HashMap<String, Object> jsonObject = (HashMap<String, Object>) record;
	        	String accnumber = (String) jsonObject.get("accountno");
	        	if(accnumber.equals(user.getAccountNo())) {
	        		BaseTest.threadLocalTest.get().info("Record not gets deleted successfully!!");
	        		System.out.println("Record not gets deleted successfully!!");
	        	}
	        }
			BaseTest.threadLocalTest.get().info("Record gets deleted successfully!!");
			System.out.println("Record gets deleted successfully!!");
		}

}
