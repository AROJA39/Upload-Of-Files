package co.com.msuploadoffiles.tracking;

import org.json.JSONObject;

import co.com.msuploadoffiles.constants.Constants;
import co.com.msuploadoffiles.objects.MonitorTime;

/**
 * Class defining the method for tracking consumed operations.
 * @author Migration Team
 */
public class TrackingUploadFiles {
	
	/**
     * Control method for monitoring consumed operations
     * 
     * @author Migration Team
     * @param JSONObject
     * @return JSONObject
     *
     */
	public JSONObject getInfoUpload(JSONObject infoUpload) {
		infoUpload.put(Constants.TIME, new MonitorTime().timeElapsed());
		infoUpload.put(Constants.ENDPOINT, Constants.ENDPOINTS);
		infoUpload.put("PACKAGE", Constants.PACKAGE);
		return infoUpload;
	}
}
