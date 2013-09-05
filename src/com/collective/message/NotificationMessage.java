package com.collective.message;

import java.util.ArrayList;
import java.util.List;

public class NotificationMessage {
	private int userId;
	private boolean globalMessage;
	private List<String> message = new ArrayList<String>();
	private int messageCount;

	public boolean isGlobalMessage() {
		return globalMessage;
	}
	public void setGlobalMessage(boolean globalMessage) {
		this.globalMessage = globalMessage;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
}
