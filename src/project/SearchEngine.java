package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class SearchEngine {
			
	public static void main(String[] args) throws Exception {
		System.out.println("Web Search Engine developed by \n Nikhil Reddy \n Abhishek Kesiraju \n Pravallika Munukutla \n Nupur Badiani");
		System.out.println();
		
		try (Scanner sc = new Scanner(System.in)) {
					WebCrawler webCrawler = new WebCrawler();
					System.out.println("Enter the Link to Crawl and fetch sites:");
					String link = sc.nextLine();
					webCrawler.getPageLinks(link);
					HTMLtoText.generateTextFiles();
					System.out.println("----------------------");
					SearchEngine websearch = new SearchEngine();
					Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();		
					
					String option = "continue";
					while(option.equals("continue")) {	
						System.out.println("Enter Keyword to search ");
						String searchWord = sc.nextLine();
						int occur = 0;
						int number = 0; 
						try {
							File my_dir = new File("C:\\nikhil\\Project_ACC\\TextFiles");
							File[] fileArray = my_dir.listFiles();
							int i=0;
							while(i < fileArray.length) {
								occur = websearch.searchWord(fileArray[i], searchWord);
								hashtable.put(fileArray[i].getName(), occur); 
								if (occur != 0) {
									number++;
								}
								i++;
							}
							System.out.println("\n Number of files for the given input keyword " + searchWord + " are : " + number);
							if (number == 0) {
								System.out.println(" Word suggestions :");
								Dict.createDictionary();
								websearch.suggestions(searchWord);
							}
							PageRanking.rankFiles(hashtable, number);
							System.out.println("\n continue or exit? ");
							option = sc.nextLine();	
							if(option.equals("exit")) {
								sc.close();
								System.out.println("Closing Program");
							}
						} catch (Exception e) {
							System.out.println("Exception:" + e);
						}
					}	
		}
		
	}
	
	
	public int searchWord(File filePath, String s1) throws IOException {
		String my_data = "";
		try {
			BufferedReader my_Object = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = my_Object.readLine()) != null) {
				my_data = my_data + line;
			}
			my_Object.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		char txt[] = my_data.toCharArray();
		char pat[] = s1.toCharArray();
		int result = BoyerMoore.search(txt, pat);
		if (result != 0) {
			System.out.println("\n The given Keyword is present in the file " + filePath.getName());
		}
		return result;
	}
	
	public static void spellCheck(String pattern) throws IOException {
		String filename="dictionary.txt";
		File file = new File(filename);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			ArrayList<String> dictonarywords = new ArrayList<String>();
			String str=null;
			while((str= reader.readLine())!=null)
			{
				dictonarywords.add(str);
			}
			
			int ed,MinimumDistance=10, secondMinimumDist=10;
			int sugWordOne=0;
			for(int i = 0; i<dictonarywords.size();i++){
				String dw=dictonarywords.get(i);
				ed = EditDistance.minDistance(dw, pattern);
				if(ed<secondMinimumDist) {
					if(ed<MinimumDistance) {
						MinimumDistance=ed;
						sugWordOne=i;
					}
				
				}
			}
			System.out.println(" Entered Keyword is not present, Instead search for "+dictonarywords.get(sugWordOne));
		}	
	}
	
	public void suggestions(String pattern) {
		try {
			spellCheck(pattern);
		}catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
}
