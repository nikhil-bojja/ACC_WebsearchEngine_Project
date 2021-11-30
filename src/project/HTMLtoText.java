package project;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.*;

public class HTMLtoText{

	public static void textFileCreator(String fileName) throws IOException {

		File myfile = new File("C:\\nikhil\\Project_ACC\\URL_Html_files\\" + fileName);
		org.jsoup.nodes.Document document = Jsoup.parse(myfile, "UTF-8");
		String string = document.text();

		String fileNameWithOutExt = fileName.replaceFirst("[.][^.]+$", "");
		PrintWriter out = new PrintWriter("C:\\nikhil\\Project_ACC\\TextFiles\\" + fileNameWithOutExt + ".txt");
		out.println(string);
		out.close();
	}
	
	public static void generateTextFiles() throws IOException {
		File folder = new File("C:\\nikhil\\Project_ACC\\URL_Html_files\\");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				textFileCreator(listOfFiles[i].getName());
			}
		}
		System.out.println("\n HTML Files are converted and Text Files are generated.");
	}

	public static void main(String[] args) throws IOException {
		generateTextFiles();
	}
}
