package co.com.msuploadoffiles.objects;

/**
 * Class that is responsible for the calculation of time per nanosecond.
 * @author Migration Team
 */
public class MonitorTime {

	private long timeStart;

	public MonitorTime() {
		timeStart = System.nanoTime();
	}

	public String timeElapsed(){
		return Long.toString(System.nanoTime() - timeStart) + " nanoseconds";
	}
}
