package co.com.msuploadoffiles;

import co.com.msuploadoffiles.constants.Constants;
import co.com.msuploadoffiles.handler.get;
import io.javalin.Javalin;

public class MsUploadOfFilesApplication {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(8086);//Constants.SERVER_PORT
		app.get("/get", new get());
	}

}
