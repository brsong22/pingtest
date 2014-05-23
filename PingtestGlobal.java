//package pingtest; comment out this line to be able to run from command line

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingtestGlobal {

	public static void main(String[] args) {
		double totalTime = 0;
		String count = "1";
		if(args.length == 2){
			count = args[1];
		}
		String url = args[0];
		String cmd = "ping -c " + count + " " + url;
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while((line = in.readLine()) != null){
				if(line.contains("timeout")){
					totalTime = 9999999;
					break;
				}
				else if(line.contains("time")){
					int startIndex = line.indexOf("time");
					int endIndex = line.indexOf("ms")-1;
					String timeString = line.substring(startIndex, endIndex);
					double time = Double.parseDouble(timeString.split("=")[1]);
					totalTime += time;
				}
			}
			in.close();
			System.out.println("Your average ping to <" + url + "> over " + count + " ping(s) is: " + totalTime/Double.parseDouble(count));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
