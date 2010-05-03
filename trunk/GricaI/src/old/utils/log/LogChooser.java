package old.utils.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LogChooser {

	private static Map<String, Log> logs = new HashMap<String, Log>();
	
	static{
		Properties props = new Properties();
		try {
			FileInputStream in = new FileInputStream(new File("properties"+File.separator+"log.properties"));
			props.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Log getLog(String alias){
		return logs.get(alias);
	}
}
