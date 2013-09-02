package com.collective.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;

import com.collective.manager.ConnectionManager;

public class WebsocketMessageInbound extends MessageInbound {

	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {

	}

	@Override
	protected void onTextMessage(CharBuffer arg0) throws IOException {

	}

	@Override
	protected void onClose(int status) {
		ConnectionManager.activeConnectionList.values().remove(this);
	}
}
