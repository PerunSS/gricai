package utils.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LogFactory {

	private static Map<String, Log> logs = new HashMap<String, Log>();
	
	static {
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream(new File("properties"+File.separator+"log.properties"));
			prop.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Map.Entry<Object, Object> entry : prop.entrySet()){
			logs.put(entry.getKey().toString(), new Log(entry.getValue().toString()));
		}
	}
	
	public static Log getLog(String alias){
		return logs.get(alias);
	}
	
	
}
