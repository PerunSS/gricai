package old.utils.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	
	private File file = null;
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");

	protected Log(String filename){
		file = new File(filename);
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void logException(Exception e){
		try {
			FileWriter writer = new FileWriter(file);
			writer.write("EXCEPTION\t");
			writer.write(format.format(new Date())+"\t");
			writer.write(e.getMessage());
			writer.write("\n");
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
