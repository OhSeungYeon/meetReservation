package com.gbm.edu.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileLogging {

	@Value("${edu.location}")
	private String location;

    // 파일 기록
    public void log(String folder, String msg, String sourcepath)
    {
        String TracePath = location;
        TracePath += File.separator + folder;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();

        String fileName = sdf.format(now) + ".log";

        LogToFile(TracePath, fileName, msg);
    }

    //file 생성
    private static void LogToFile(String logFolder, String fileName, String msg)
    {
    	File directory = new File(logFolder);
        if (! directory.exists()){
            directory.mkdir();
        }

        try{
        	File file = new File(logFolder + File.separator + fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(msg);
            bw.newLine();
            bw.close();
        }
        catch (IOException e){
        	File file = new File(logFolder + File.separator + fileName + "Fail.log");
            FileWriter fw;
			try {
				fw = new FileWriter(file.getAbsoluteFile(), true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(msg);
	            bw.newLine();
	            bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

}
