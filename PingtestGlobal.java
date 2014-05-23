package pingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingtestGlobal {

	public static void main(String[] args) {
		double totalTime = 0;
		String count = "10";
		String url = args[0];
		String cmd = "ping -c " + count + " " + url;
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while((line = in.readLine()) != null){
				if(line.contains("time")){
					int startIndex = line.indexOf("time");
					int endIndex = line.indexOf("ms")-1;
					String timeString = line.substring(startIndex, endIndex);
					double time = Double.parseDouble(timeString.split("=")[1]);
					totalTime += time;
				}
			}
			in.close();
			System.out.println("Your average ping to <" + url + "> over 10 pings is: " + totalTime/(double)10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
