package eventlistener;

public interface TimerEventListener {
	void start();
	void stop();
	void reset();
	int getTimePassed();
}
