package com.collective.server;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import com.collective.jms.NotificationConsumer;

/**
 * Servlet implementation class NotificatonServlet
 */
@WebServlet(description = "Notification Servlet to push realtime notification", urlPatterns = {"/nserver"})
public class NotificatonServlet extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
	private NotificationConsumer notificationConsumer = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NotificatonServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		notificationConsumer = NotificationConsumer.getInstance();
	}

	@Override
	protected StreamInbound createWebSocketInbound(String stringArg, HttpServletRequest request) {
		WebsocketMessageInbound newActiveConnection = new WebsocketMessageInbound();
		notificationConsumer.addConnection(Integer.parseInt(request.getParameter("t")),
				newActiveConnection);
		return newActiveConnection;
	}
}