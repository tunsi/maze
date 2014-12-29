package me.tunsi.test.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.BeforeClass;
import org.junit.Test;

public class MqttPublishSample {

	public static String topic = "MQTT Examples";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void testCase1() {
		String content = "Message from MqttPublishSample";
		int qos = 2;
		String broker = "tcp://192.168.137.66:61613";
		String clientId = "JavaSample";
		MemoryPersistence persistence = new MemoryPersistence();

		try {
			MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setUserName("admin");
			connOpts.setPassword("password".toCharArray());
			System.out.println("Connecting to broker: " + broker);
			mqttClient.connect(connOpts);
			System.out.println("Publishing message: " + content);
			MqttMessage message = new MqttMessage(content.getBytes());
			message.setQos(qos);
			mqttClient.publish(topic, message);
			System.out.println("Message published");
			mqttClient.disconnect();
			System.out.println("Disconnected");
			System.exit(0);
		} catch (MqttException e) {
			e.printStackTrace();
		}

	}
}
