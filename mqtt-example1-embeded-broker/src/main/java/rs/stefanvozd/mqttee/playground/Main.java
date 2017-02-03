package rs.stefanvozd.mqttee.playground;

import java.io.File;
import java.net.URI;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;

import org.eclipse.paho.client.mqttv3.MqttException;

import rs.mqttserver.client.MqttClientFactory;

public class Main {

	final static Logger log = Logger.getLogger(Main.class);

	private static void init() {
		String log4jConfigFile = System.getProperty("user.dir")
				+ File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		log.info("Logger initialized...");
	}

	public static void main(String[] args) throws Exception {
		initBroker();
		init();
		mqttReceive();
	}

	private static void initBroker() throws Exception {
		BrokerService broker = new BrokerService();
		broker.setBrokerName("localhost");
		TransportConnector connector = new TransportConnector();
		connector.setUri(new URI("tcp://localhost:61616"));

		/*MQTT Connector*/
		connector = new TransportConnector();
		connector.setUri(new URI("mqtt://localhost:1883"));
		connector.setName("MQTTConn");
		broker.addConnector(connector);

		/*Web socket connector*/
		/*connector = new TransportConnector();
		connector.setUri(new URI("ws://localhost:1884"));
		connector.setName("WSConn");
		broker.addConnector(connector);*/

		broker.addConnector(connector);
		broker.setUseJmx(false);
		broker.start();
		broker.waitUntilStarted();

	}

	public static void mqttReceive() {
		try {
			MqttClientFactory.setMainClass(Main.class);

			MqttAsyncClient pcli = MqttClientFactory
					.getConnectedMqttAsyncClient();

            clientStub(pcli);


		} catch (MqttException e) {
			log.error(e.getMessage(), e);
		}

	}

	public static void clientStub(MqttAsyncClient pcli) {
        try {
            pcli.publish("root/async/1", "hello async world 1".getBytes(), 1, false);
            pcli.publish("root/async/2", "hello async world 2".getBytes(), 1, false);

            Thread.sleep(5000);

            pcli.publish("root/sync/1", "hello sync world 1".getBytes(), 1, false);
            pcli.publish("root/sync/2", "hello sync world 2".getBytes(), 1, false);


        } catch (MqttException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
