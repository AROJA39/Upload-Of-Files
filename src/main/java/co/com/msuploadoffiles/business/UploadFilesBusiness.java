package co.com.msuploadoffiles.business;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;

import co.com.msuploadoffiles.constants.Constants;
import co.com.msuploadoffiles.tracking.TrackingUploadFiles;

/**
 * Class that defines the methods for reading and converting JSON files.
 * @author Migration Team
 */
public class UploadFilesBusiness {
	
	public static JSONObject jsonResponse = null;
	public static JSONObject tracking = null;
	
	/**
     * Control method for identification of operation required to be consumed, calling the information tracking method
     * 
     * @author Migration Team
     * @param HasMap<String, String>
     * @return String
     *
     */
	public String getOperation(String request) {
		if (!request.equals(Constants.INFOUPLOAD)) {
			jsonResponse = (JSONObject) getFile(request);
		}else if(request.equals(Constants.INFOUPLOAD)) {
			jsonResponse = new TrackingUploadFiles().getInfoUpload(tracking);
		}else {
			jsonResponse = new JSONObject();
			jsonResponse.put(Constants.ERROR, Constants.OPERATION_NOT_FOUND);
			jsonResponse.put(Constants.STATUS, Constants.FAILED);
			jsonResponse = new TrackingUploadFiles().getInfoUpload(jsonResponse);
			
		}
		return jsonResponse.toString();
	}
	
	/**
     * Control method for file upload, where the name of the file to be uploaded is received, 
     * the path is validated if found in the configuration JSON, the file is uploaded or an error is returned.
     * 
     * @author Migration Team
     * @param String
     * @return String
     * @throws Exception
     *
     */
	public static JSONObject getFile(String jsonName) {
		try {
			JSONObject jsonObject = (JSONObject) readAndConvertJson(Constants.PATHJSONS);
			if (!jsonObject.isNull(jsonName)) {
				tracking = new JSONObject();
				jsonResponse = (JSONObject) readAndConvertJson(jsonObject.getString(jsonName));
				tracking.put(Constants.STATUS, Constants.SUCCESS);
				return jsonResponse;
			}else {
				tracking = new JSONObject();
				tracking.put(Constants.ERROR, Constants.JSON_NOT_FOUND);
				tracking.put(Constants.STATUS, Constants.FAILED);
				tracking = new TrackingUploadFiles().getInfoUpload(tracking);
				return tracking;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
	
	/**
     * Control method for reading files, a conversion of the file to a String object is performed, 
     * a JSONArray is returned if the object complies with a regular expression, otherwise a JsonObject is returned.
     * 
     * @author Migration Team
     * @param String
     * @return Object
     * @throws Exception
     *
     */
	private static Object readAndConvertJson(String path) throws Exception {
		BufferedReader brBufferedReader = new BufferedReader(new FileReader(path));
		StringBuffer stringBuffer = new StringBuffer();
		String aux = null;
		while ((aux = brBufferedReader.readLine()) != null) {
			stringBuffer.append(aux);
		}
		String json = stringBuffer.toString();
		brBufferedReader.close();
		if (json.matches(Constants.REGEX)) {
			return new JSONArray(json);
		} else {
			return new JSONObject(json);
		}
	}
	
	
}