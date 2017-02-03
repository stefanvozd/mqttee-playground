package rs.stefanvozd.mqttee.playground;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import rs.mqttserver.client.Subscribe;
import rs.mqttserver.client.Sync;

import java.nio.charset.StandardCharsets;

public class Subscribers {

	final static Logger log = Logger.getLogger(Subscribers.class);
	
	@Subscribe(topic="root/async/+",qos=1)
	public void asyncExample(String topic, MqttMessage msg) throws InterruptedException{
        Thread.sleep(2000);
        log.info("Enter "+topic+ ", msg: "+msg);
	}

    @Sync
    @Subscribe(topic="root/sync/+",qos=1)
    public void syncExample(String topic, MqttMessage msg) throws InterruptedException{
	    Thread.sleep(2000);
        log.info("Enter "+topic+ ", msg: "+msg);
    }


    @Sync
    @Subscribe(topic="room/living/light",qos=1)
    public void iotLightExample(String topic, MqttMessage msg) throws InterruptedException{
        if(new String(msg.getPayload(), StandardCharsets.UTF_8).equals("on")) {
            log.info("\n" +
                    "         |\n" +
                    " \\     _____     /\n" +
                    "     /       \\\n" +
                    "    (         )\n" +
                    "-   ( ))))))) )   -\n" +
                    "     \\ \\   / /\n" +
                    "      \\|___|/\n" +
                    "  /    |___|    \\\n" +
                    "       |___|    \n" +
                    "       |___|");
        } else {
            log.info("\n" +
                    "     _____  \n" +
                    "   .'     `.\n" +
                    "  /         \\\n" +
                    " |           | \n" +
                    " '.  +^^^+  .'\n" +
                    "   `. \\./ .'\n" +
                    "     |_|_|  \n" +
                    "     (___)    \n" +
                    "     (___)\n" +
                    "     `---'");
        }
    }

    @Subscribe(topic="configurabile_topic",qos=1)
    public void propTopic(String topic, MqttMessage msg) throws InterruptedException{
        log.info("configurabile topic from prop files!!!");
    }

}
