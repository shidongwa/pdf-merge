package javaei.pdf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Logger {
	
	private PrintWriter logger = null;

	public Logger(String logFilepath) {
		File logfile = new File(logFilepath);
		if(!logfile.exists()){
			String fullpath = logfile.getAbsolutePath();
			String dir = fullpath.substring(0,fullpath.lastIndexOf("\\"));
			File parent = new File(dir);
			if(!parent.exists()){
				parent.mkdirs();
			}
			try {
				logfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			logger = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logfile,true), "UTF-8")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void log(String msg){
		logger.println(msg);
		logger.flush();
	}
	
	public void close(){
		logger.close();
	}
	

}
