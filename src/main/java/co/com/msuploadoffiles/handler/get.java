package co.com.msuploadoffiles.handler;

import co.com.msuploadoffiles.business.UploadFilesBusiness;
import co.com.msuploadoffiles.constants.Constants;
import io.javalin.http.ContentType;
import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Class of management of incoming and outgoing requests
 * @author Migration Team
 */
public class get implements Handler{

	/**
     * Method to control the handling of incoming and outgoing requests, where the get file method is invoked.
     * 
     * @author Migration Team
     * @param Context
     * @throws Exception
     *
     */
	public void handle(Context ctx) throws Exception {
		String request = ctx.queryParam(Constants.JSONNAME);
		ctx.contentType(ContentType.APPLICATION_JSON);
		ctx.result(new UploadFilesBusiness().getOperation(request));
	}

}
