package co.com.msuploadoffiles.handler;

import org.json.JSONObject;

import co.com.msuploadoffiles.business.UploadFilesBusiness;
import co.com.msuploadoffiles.constants.Constants;
import co.com.msuploadoffiles.tracking.TrackingUploadFiles;
import io.javalin.http.ContentType;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

/**
 * Class of management of incoming and outgoing requests
 * 
 * @author Migration Team
 */
public class get implements Handler {

	/**
	 * Method to control the handling of incoming and outgoing requests, where the
	 * get file method is invoked.
	 * 
	 * @author Migration Team
	 * @param Context
	 * @throws Exception
	 *
	 */
	public void handle(Context ctx) throws Exception {
		try {
			String request = ctx.queryParam(Constants.JSONNAME);

			if (new UploadFilesBusiness().getOperation(request).contains(Constants.ERROR)) {// si aparece un error
				ctx.status(HttpCode.BAD_REQUEST).contentType(ContentType.APPLICATION_JSON)
						.result(new UploadFilesBusiness().getOperation(request));
			} else if (ctx.queryParamMap().size() != 1) { // si hay mas de un parametro pero esta bien UNO de los parametros
				ctx.status(HttpCode.NON_AUTHORITATIVE_INFORMATION).contentType(ContentType.APPLICATION_JSON)
						.result(Error(ctx.queryParamMap().size()) + new UploadFilesBusiness().getOperation(request));
			} else {// todo perfecto
				ctx.contentType(ContentType.APPLICATION_JSON);
				ctx.result(new UploadFilesBusiness().getOperation(request));
			}
		} catch (Exception e) { // alguna exception
			ctx.status(HttpCode.BAD_REQUEST).contentType(ContentType.APPLICATION_JSON)
					.result(Error(ctx.queryParamMap().size()));
		}

	}

	private String Error(Integer parametros) {
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put(Constants.ERROR, Constants.OPERATION_NOT_FOUND);
		jsonResponse.put(Constants.STATUS, Constants.FAILED);
		jsonResponse = new TrackingUploadFiles().getInfoUpload(jsonResponse);
		jsonResponse.put("Descripcion Error", "No se pueden enviar " + parametros + " parametros , "
				+ "Los parametros validos son : " + Constants.JSONNAME);
		return jsonResponse.toString();

	}

}
