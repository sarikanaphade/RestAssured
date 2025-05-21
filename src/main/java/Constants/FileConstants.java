package Constants;

public class FileConstants {
	public static final String BASE_PATH= System.getProperty("user.dir");
	public static final String URI_FILE_PATH = BASE_PATH + "/src/main/java/InputData/URI.properties";
	public static final String ADD_PAYLOAD = BASE_PATH + "/src/main/java/InputData/AddData.json";
	public static final String UPDATE_PAYLOAD = BASE_PATH + "/src/main/java/InputData/UpdateData.json";
	public static final String LOGIN_PAYLOAD = BASE_PATH + "/src/main/java/InputData/Login.json";
	public static final String VALID_LOGIN_PAYLOAD = BASE_PATH + "/src/main/java/InputData/validLogin.json";
	public static final String LOGIN_EXCEL_FILE_PATH = BASE_PATH + "/src/main/java/InputData/login4.xlsx";
	public static final String LOGIN_SCHEMA_PATH = BASE_PATH + "/src/test/resources/LoginSchema.json";
	public static final String ADD_DATA_SCHEMA_PATH = BASE_PATH + "/src/test/resources/AddDataSchema.json";
	public static final String GET_DATA_SCHEMA_PATH = BASE_PATH + "/src/test/resources/GetDataSchema.json";
	public static final String UPDATE_DATA_SCHEMA_PATH = BASE_PATH + "/src/test/resources/UpdateDataSchema.json";
	public static final String DELETE_DATA_SCHEMA_PATH = BASE_PATH + "/src/test/resources/DeleteDataSchema.json";
	public static final String REPORT_PATH =  BASE_PATH + "/src/main/resources/Reports/"
			+"API report.html";

}
