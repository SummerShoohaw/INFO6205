package edu.neu.coe.info6205.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileWriter {
	
	private String filepath = "";
	BufferedWriter br = null;
	
	public FileWriter(String filepath){
		this.filepath = filepath;
	}
	
	public FileWriter(){
	}
	
	public void setPath(String path) {
		filepath = path;
	}
	
	public void openFile() throws IOException {
		File file = new File(filepath);
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
        br = new BufferedWriter(writer);
	}
	
	public void writeLine(String s) throws IOException {
        String toWrite = s+'\n';
        System.out.println(toWrite);
        br.write(toWrite);
	}
	
//	public void writeLine(double distance) throws IOException {
//        String toWrite = String.valueOf(distance)+'\n';
//        System.out.println(toWrite);
//        br.write(toWrite);
//	}
	
	public void closeFile() throws IOException {
		br.close();
	}
}
