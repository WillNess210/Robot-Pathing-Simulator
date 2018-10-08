package tools;

public class UpdateHandler{
	long lastTime, lastDuration;
	double duration;
	public UpdateHandler(int perSecond) {
		lastTime = System.currentTimeMillis();
		this.duration = (1000.0/((double)perSecond));
		this.lastDuration = 0;
	}
	public boolean shouldUpdate() {
		long time = System.currentTimeMillis();
		if(time - lastTime > this.duration) {
			lastDuration = time - lastTime;
			lastTime = time;
			return true;
		}
		return false;
	}
	public long timeSinceLastTick() {
		return lastDuration;
	}
}
