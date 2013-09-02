package com.collective.job;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.allconnect.message.JSonHelper;
import com.allconnect.message.Message;

public class NotificationProducer {

	private static final String QUEUE_HOST = "tcp://localhost:61616";
	private static final String QUEUE_NAME = "ActiveMQ";

	public void sendMessage(boolean globalMessageFlag) {
		Connection connection = null;
		Session session = null;

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(QUEUE_HOST);
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Message msg = new Message();
			int randomUserId = (int) (Math.random() * 3 + 1);
			msg.setUserId(randomUserId);
			msg.setGlobalMessage(globalMessageFlag);
			List<String> myMessages = new ArrayList<String>();
			if (globalMessageFlag) {
				myMessages.add("Global Message for all");
			} else {
				myMessages.add("Message One");
				myMessages.add("Message Two");
			}
			msg.setMessageCount(myMessages.size());
			msg.setMessage(myMessages);
			String jSonMessage = null;
			try {
				jSonMessage = JSonHelper.ObjectToJSonString(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Queue queue = session.createQueue(QUEUE_NAME);
			MessageProducer msgProducer = session.createProducer(queue);
			TextMessage message = session.createTextMessage(jSonMessage);
			msgProducer.send(message);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws JMSException {

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		final NotificationProducer nfnProducer = new NotificationProducer();

		final Runnable sendIndividualNotification = new Runnable() {
			public void run() {
				nfnProducer.sendMessage(false);
			}
		};
		final Runnable sendGlobalNotification = new Runnable() {
			public void run() {
				nfnProducer.sendMessage(true);
			}
		};
		scheduler.scheduleWithFixedDelay(sendIndividualNotification, 0, 60, SECONDS);
		scheduler.scheduleWithFixedDelay(sendGlobalNotification, 0, 90, SECONDS);
	}
}