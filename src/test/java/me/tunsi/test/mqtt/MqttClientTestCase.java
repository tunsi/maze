package me.tunsi.test.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

public class MqttClientTestCase {
	
	public static void main(String[] args) {
		new MqttClientTestCase().testCase1();
	}

	public static String serverURI = "tcp://192.168.137.66:61613";
	
	@Test
	public void testCase1() {
		try {
			MemoryPersistence persistence = new MemoryPersistence();
			MqttClient mqttClient = new MqttClient(serverURI, "testCase1",persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			connOpts.setUserName("admin");
			connOpts.setPassword("password".toCharArray());
			System.out.println("Connecting to broker: " + serverURI);
			mqttClient.connect(connOpts);
			
			mqttClient.subscribe(MqttPublishSample.topic);
			
			mqttClient.setCallback(new MqttCallback() {
				
				@Override
				public void messageArrived(String arg0, MqttMessage message) throws Exception {
					System.out.println("Time:\t" +System.currentTimeMillis() +
			                           "  Topic:\t" + MqttPublishSample.topic +
			                           "  Message:\t" + new String(message.getPayload()) +
			                           "  QoS:\t" + message.getQos());
				}
				
				@Override
				public void deliveryComplete(IMqttDeliveryToken arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void connectionLost(Throwable arg0) {
					System.out.println("lost");
				}
			});
		} catch (MqttException e) {
			e.printStackTrace();
		}
		
	}
}
