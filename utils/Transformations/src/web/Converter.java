package web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Converter {

	public Set<String> printDistinctDrinks(String filename,Set<String> distinct) throws IOException{
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		if(distinct == null)
			distinct = new HashSet<String>();
		while(sc.hasNext()){
			String line = sc.nextLine();
			String drinks [] = line.split("\\$\\$\\$\\$");
			if(drinks.length>1){
				for(String drink:drinks){
					String name[] = drink.split("\\$\\$");
					if(name.length>1){
						distinct.add(name[1]);
					}
				}
			}
		}
		sc.close();
		return distinct;
	}
	
	public Map<String,Set<String>> getDrinksMap(String filename) throws FileNotFoundException{
		File error = new File("errors.txt");
		PrintWriter writer = new PrintWriter(error);
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		Map<String, Set<String>> result = new TreeMap<String, Set<String>>();
		while(sc.hasNext()){
			String line = sc.nextLine();
			String drinkData[] = line.split("\\|");
			if(drinkData.length>1){
				String drink[] = drinkData[1].split(";");
				if(drink.length>0){
					String key = drink[0];
					Set<String> value = result.get(key);
					if(value == null){
						value = new HashSet<String>();
					}
					String stringValue = "";
					for(int i=1;i<drink.length;i++){
						stringValue+=drink[i]+";";
					}
					value.add(stringValue.toLowerCase());
					result.put(key, value);
				}else{
					writer.println("error: "+drinkData[1]);
				}
				
			}else{
				writer.println("missign description: "+line);
			}
		}
		
		sc.close();
		writer.close();
		return result;
	}
	
	
	public static void main(String[] args) throws IOException {
//		Converter converter = new Converter();
//		Set<String> distinct = converter.printDistinctDrinks("beer_ale.csv", null);
//		System.out.println("beer_ale.csv");
//		distinct = converter.printDistinctDrinks("cocktails.csv", distinct);
//		System.out.println("cocktails.csv");
//		distinct = converter.printDistinctDrinks("coffee_tea.csv", distinct);
//		System.out.println("coffee_tea.csv");
//		distinct = converter.printDistinctDrinks("non_alc.csv", distinct);
//		System.out.println("non_alc.csv");
//		distinct = converter.printDistinctDrinks("punches.csv", distinct);
//		System.out.println("punches.csv");
//		distinct = converter.printDistinctDrinks("shots.csv", distinct);
//		System.out.println("shots.csv");
//		
//		File file = new File("distinct.txt");
//		PrintWriter writer = new PrintWriter(file);
//		for(String drink:distinct){
//			writer.println(drink);
//		}
//		writer.close();
		
		File result = new File("categories.txt");
		PrintWriter writer = new PrintWriter(result);
		Converter converter = new Converter();
		Map<String, Set<String>> categories = converter.getDrinksMap("drinks_classes.txt");
		for(Map.Entry<String, Set<String>> entry:categories.entrySet()){
			writer.println(entry.getKey()+":");
			for(String drink:entry.getValue()){
				if(drink.length()>0)
					writer.println("\t"+drink);
			}
		}
		writer.close();
	}
}
