package com.collective.manager;

import java.util.HashMap;
import java.util.Map;
import com.collective.server.WebsocketMessageInbound;

public class ConnectionManager {

	public static Map<Integer, WebsocketMessageInbound> activeConnectionList = new HashMap<Integer, WebsocketMessageInbound>();

}
