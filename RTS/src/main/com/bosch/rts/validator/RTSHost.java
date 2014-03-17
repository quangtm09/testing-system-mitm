package com.bosch.rts.validator;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

/**
 * The Class RTSHost.
 */
public class RTSHost {

	/** The log. */
	@Logger
	private static Log log;

	/**
	 * Gets the host name.
	 *
	 * @return the host name
	 */
	public static String getHostName() {
		InetAddress addr = null;
		String hostname = null;
		try {
			addr = InetAddress.getLocalHost();

			// Hostname
			hostname = addr.getHostName();
		} catch (UnknownHostException e) {
			log.error("Error in getting host name. ", e);
		}

		return hostname;
	}
	
	/**
	 * Gets the rTS address.
	 *
	 * @return the rTS address
	 */
	public static String getRTSAddress(){
		String rtsAddress = "http://";
		String hostName = getHostName();
		if(hostName == null){
			hostName = "localhost";
		}		
		rtsAddress += hostName + ":8080" + "/" + "rts";			
		return rtsAddress;
	}	

}