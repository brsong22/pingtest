package pingtest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Pingtest {
	public static void main(String args[]){
		try {
			String url = args[0];
			InetAddress address = InetAddress.getByName(url);
			String ip = address.getHostAddress();
			System.out.println(url +"'s ip: " + ip);
			long startTime = System.currentTimeMillis();
			boolean isReachable = false;
			try {
				isReachable = address.isReachable(10000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//3 second timeout
			if(isReachable){
				long pingTime = System.currentTimeMillis() - startTime;
				System.out.println("ping: " + pingTime/1000 + " seconds.");
				return;
			}
			else{
				System.out.println("ping: TIMEOUT.");
				return;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
