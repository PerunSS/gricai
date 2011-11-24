package web;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

public class HTMLtoCSV {

	private void parseAndAppend(String sourceName, String destinationName) throws MalformedURLException, IOException {
		Source source = new Source(new URL("file:"+sourceName));
		PrintWriter writer = new PrintWriter(new FileWriter(new File(destinationName),true),true);
		writer.println("##");
		writer.println("Title$$");
		displaySegments(source.getAllStartTags("h1 class=\"fn recipe_title"),writer);
		writer.println("$$");
		writer.println("Preparation$$");
		displaySegments(source.getAllStartTags("div class=\"recipedirections instructions"),writer);
		writer.println("$$");
		writer.println("Ingredients$$");
		displaySegments(source.getAllStartTags("div class=\"ingredients\""),writer);
		writer.println();
		writer.close();
	}

	private static void displaySegments(List<? extends Segment> segments, PrintWriter writer) {
		for (Segment segment : segments) {
			for(Element element : segment.getChildElements()){
				printAllElements(element,writer);
			}
		}
	}
	
	private static void printAllElements(Element element, PrintWriter writer){
		if(element.getChildElements().size()>0){
			for(Element child:element.getChildElements()){
	//			System.out.println(child.getContent());
	//			System.out.println("*************");
	//			System.out.println(child.getAllElements().size());
				if(child.getAllElements().size()>1){
					printAllElements(child,writer);
					String text = child.getContent().toString().substring(0, child.getContent().toString().indexOf('<')).trim();
					if(text.length()>1)
						writer.print(text+"$$");
				}else{
					writer.print(child.getContent().toString().trim()+"$$");
				}
			}
		}else{
			writer.print(element.getRenderer());
		}
	}

	public static void main(String[] args) {
		HTMLtoCSV worker = new HTMLtoCSV();
		try {
			File start = new File("/Users/Perun/cerspri/shots/");
			File[] files = start.listFiles();
			for(File file:files){
				worker.parseAndAppend(file.getAbsolutePath(), "shots.csv");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
