package com.okapi.okapimanager.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wordlist {
	private List<String> words = new ArrayList<String>();
	
	public Wordlist(){
		
	}
	
	public void add(String str){
		words.add(str);
	}
	
	public void remove(String str){
		words.remove(str);
	}
	
	public boolean contains(String str){
		return words.contains(str);
	}
	
	public void clear(){
		words.clear();
	}
	
	public List<String> get(){
		return words;
	}
	
	public void Load(String location){
		BufferedReader reader = null;
		String line = null;
		File file = new File(location);
		
		if(!file.exists()){
			try{
				file.createNewFile();
			} catch(IOException e){
				return;
			}
		}
		
		try{
			reader = new BufferedReader(new FileReader(file));
			
			while((line = reader.readLine()) != null){
				add(line);
			}
			
			if(reader != null) reader.close();
		} catch(IOException e){
			return;
		}
	}
	
	public void Save(String location){
		BufferedWriter writer = null;
		File file = new File(location);
		
		if(!file.exists()){
			try{
				file.createNewFile();
			} catch(IOException e){
				return;
			}
		}
		
		try{
			writer = new BufferedWriter(new FileWriter(file));
			
			for(String str : words){
				writer.write(str);
			}
			
			if(writer != null) writer.close();
		} catch(IOException e){
			return;
		}
	}
}
