package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;

import Constants.FileConstants;

public class FileUtils {
	static Properties prop = new Properties();

	public static Properties readData() {
		// Code to read login data from file
		FileInputStream fis = null;
		String filePath = Constants.FileConstants.URI_FILE_PATH;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String addDataPayload() {
		String addDataPayload = null;
		try {
			addDataPayload = new String(Files.readAllBytes(Paths.get(FileConstants.ADD_PAYLOAD)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addDataPayload;
	}

	public static String updateDataPayload() {
		String addDataPayload = null;
		try {
			addDataPayload = new String(Files.readAllBytes(Paths.get(FileConstants.UPDATE_PAYLOAD)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addDataPayload;
	}
	
	
	public static String LoginPayload() {
		String loginPayload = null;
		try {
			loginPayload = new String(Files.readAllBytes(Paths.get(FileConstants.VALID_LOGIN_PAYLOAD)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loginPayload;
	}
	
	public static String LoginPayload(String username,String password) {
		String loginPayload = null;
		try {
			loginPayload = new String(Files.readAllBytes(Paths.get(FileConstants.LOGIN_PAYLOAD)));
			loginPayload = loginPayload.replace("{{username}}", username);
			loginPayload = loginPayload.replace("{{password}}", password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loginPayload;
	}
	
	public List<Object[]> readExcelData(String excelFile) throws IOException{
		List<Object[]> data = new ArrayList<>();
		FileInputStream fis = new FileInputStream(new File(excelFile));
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		Sheet sheet1 = wb.getSheetAt(0);
		
		Iterator<Row> itr = sheet1.iterator();
		itr.next(); //skip header rowSS
		
		while(itr.hasNext()==true) {
			Row row = itr.next();
			//String username = row.getCell(0).getStringCellValue();
			String username = CellUtil.getCell(row, 0).getStringCellValue();
			//String password = row.getCell(1).getStringCellValue();
			String password = CellUtil.getCell(row, 1).getStringCellValue();
			int respcode = (int) CellUtil.getCell(row, 2).getNumericCellValue();
			String message = CellUtil.getCell(row, 3).getStringCellValue();
			data.add(new Object[] {username,password,respcode,message});
		}
		
		wb.close();
		return data;	
	}
	
	@DataProvider(name="loginData")
	public Object[][] loginData() throws IOException{
		List<Object[]> logindata = readExcelData(FileConstants.LOGIN_EXCEL_FILE_PATH);
		return logindata.toArray(new Object[0][0]);
	}
}
