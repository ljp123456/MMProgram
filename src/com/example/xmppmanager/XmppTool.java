package com.example.xmppmanager;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import android.animation.AnimatorSet.Builder;

public class XmppTool {

	private static XMPPConnection connection = null;

	private static void openConnection() {
		try {
			ConnectionConfiguration connConfig = new ConnectionConfiguration(
					"192.168.0.114", 5222);
			connConfig.setSASLAuthenticationEnabled(false);

			connConfig.setSecurityMode(SecurityMode.disabled);// 设置为disabled
			connConfig.setSASLAuthenticationEnabled(false);// 设置为false
			connConfig.setCompressionEnabled(false);
			connection = new XMPPConnection(connConfig);
			connection.connect();
		} catch (XMPPException xe) {
			xe.printStackTrace();
		}
	}

	public static XMPPConnection getConnection() {
		if (connection == null) {
			openConnection();
		}
		return connection;
	}

	public static void closeConnection() {
		connection.disconnect();
		connection = null;
	}
}
