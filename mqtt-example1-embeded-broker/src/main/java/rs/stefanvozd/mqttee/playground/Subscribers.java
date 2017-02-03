package rs.stefanvozd.mqttee.playground;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import rs.mqttserver.client.Subscribe;
import rs.mqttserver.client.Sync;

public class Subscribers {

	final static Logger log = Logger.getLogger(Subscribers.class);
	
	@Subscribe(topic="root/async/1",qos=1)
	public void asyncExample1(String topic, MqttMessage msg) throws InterruptedException{
        Thread.sleep(1500);
        log.info("Enter "+topic+ ", msg: "+msg);
	}

    @Subscribe(topic="root/async/2",qos=1)
    public void asyncExample2(String topic, MqttMessage msg) throws InterruptedException{
        Thread.sleep(1500);
        log.info("Enter "+topic+ ", msg: "+msg);
    }

    @Sync
    @Subscribe(topic="root/sync/1",qos=1)
    public void syncExample1(String topic, MqttMessage msg) throws InterruptedException{
	    Thread.sleep(1500);
        log.info("Enter "+topic+ ", msg: "+msg);
    }

    @Sync
    @Subscribe(topic="root/sync/2",qos=1)
    public void syncExample2(String topic, MqttMessage msg) throws InterruptedException{
        Thread.sleep(1500);
        log.info("Enter "+topic+ ", msg: "+msg);
    }

}
