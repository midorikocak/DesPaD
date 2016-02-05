package AST;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.input.ReversedLinesFileReader;

public class OutputFilesFindOverlaps {
	
	static List<String> removeDuplicates(List<String> list) {

		// Store unique items in result.
		List<String> result = new ArrayList<>();

		// Record encountered Strings in HashSet.
		HashSet<String> set = new HashSet<>();

		// Loop over argument list.
		for (String item : list) {

		    // If String is not in set, add it to the list and the set.
		    if (!set.contains(item)) {
			result.add(item);
			set.add(item);
		    }
		}
		return result;
	    }
	
	public void ParseOutputs(String Title, File f) throws FileNotFoundException {
		
		final List<String> subgraph = new ArrayList<String>();		
		final List<Vertices> subgraph_concat = new ArrayList<Vertices>();
		final List<Vertices> subgraph_concat2 = new ArrayList<Vertices>();
		final List<String> subgraph_concat_ = new ArrayList<String>();
	    
		FilenameFilter textFilter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".dot");
	        }
	    };
	    
	    String fileName="";
	    
	    Integer i = 1;
	    File[] files2 = f.listFiles(textFilter);
	    
	    //dosyalari listeye atma islemi.
	    for (File file : files2) 
	    {
	    	subgraph.clear();
	        if (file.isDirectory()) 
	        {
	            System.out.print("directory:");
	        } 
	        else 
	        {
	            //System.out.println("File:"+file.getName());
	            fileName = f + "/" + file.getName();
  
	    		try{

	    			//Create object of FileReader
	    			FileReader inputFile = new FileReader(fileName);
	    			BufferedReader bufferReader = new BufferedReader(inputFile);

	    			String line;

	    			// Read file line by line and print on the console
	    			while ((line = bufferReader.readLine()) != null)   {
	    				//System.out.println(line);
	    				subgraph.add(line);
	    			}
	    			//Close the buffer reader
	    			bufferReader.close();
	    	       }
	    		catch(Exception e){
	    	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	    	       }
	    			    		
	    		String sentence="";String nodes ="";
	    		Integer newSG = 0;
	    		for (String s : subgraph)
	    		{			
	    			if(s.length()>=1 && (!s.contains("digraph")) && (!s.contains("}")))
	    			{				
	    				String ss = s.substring(2,s.length());
	    				
	    				if(newSG.equals(2) && (!ss.contains(" -> ")))
	    				{
	    					//subgraph_concat.add(new Vertices(i,sentence.trim()/*.substring(0,sentence.length()-1)*/,""));
	    					subgraph_concat_.add(sentence.trim());
	    					sentence = "";
	    				}
	    				
	    				if (!ss.contains(" -> "))				
	    				{
	    					nodes = ss;//.substring(0,ss.indexOf(" "));
	    					if (nodes.length()>=1)
	    						sentence = sentence + nodes + ";";
	    					newSG = 1;
	    				}
	    				else if (ss.contains(" -> "))
	    				{
	    					nodes = ss;//.substring(0,ss.indexOf(" ["));
	    					if (nodes.length()>=1)
	    						sentence = sentence + nodes + ";";
	    					newSG = 2;
	    				}
	    												
	    			}
	    			//System.out.println(sentence);
	    		}
	    		//subgraph_concat.add(new Vertices(i,sentence.trim()/*.substring(0,sentence.length()-1)*/,""));					    
	    		subgraph_concat_.add(sentence.trim());
	        }
	        i++;	           	        
	    }		    
	    
	    System.out.println("---- "+Title + " ----");
	    System.out.println();
	    //for (Vertices s : subgraph_concat)
	    	//System.out.println(s.getId() + ". " + s.getName() );
	    
	    int sayi =1;
	    String delims ="[;]";
	    i = 0;
	    for (String s : removeDuplicates(subgraph_concat_)) {
	    	
	    	subgraph_concat.add(new Vertices(i,s,""));
	    	System.out.println("subgraph_concat "+ i +"."+ s );
	    	i++;
	    } 
	    
	    final List<String> overlap = new ArrayList<String>();
	    
	    //Find overlaps by matching nodes and relations
		for (int j = 0; j < subgraph_concat.size(); j++) 
	    {
			String s = subgraph_concat.get(j).getName().replace(";;", ";");
			String[] s1 = s.split(delims);
			if(j != subgraph_concat.size()-1){
				for (int k = j+1; k < subgraph_concat.size(); k++)
				{
	
					String s_ = subgraph_concat.get(k).getName().replace(";;", ";");
					String[] s2 = s_.split(delims);
					
					int count=0;
					if (s1.length<=s2.length){
						for (int l = 0; l < s1.length; l++) 
						{
							for (int l2 = 0; l2 < s2.length; l2++) 
							{
								if(s1[l].equals(s2[l2]))
								{
									count++;
									break;
								}
							}
						}
					}
					else if (s1.length>s2.length){
						for (int l2 = 0; l2 < s2.length; l2++) 
						{
							for (int l = 0; l < s1.length; l++) 
							{
								if(s1[l].equals(s2[l2]))
								{
									count++;
									break;
								}
							}
						}
					}
					
					if (s1.length<=s2.length){
						if (count == s1.length)
						{
							String s_overlap = subgraph_concat.get(j).getName().replace(";;", ";");
							overlap.add(s_overlap);					
							System.out.println("Overlap : " +sayi+". " + s_overlap);
							sayi++;
							break;
						}
					}
					else if (s1.length>s2.length){
						if (count == s2.length)
						{
							String s_overlap = subgraph_concat.get(k).getName().replace(";;", ";");
							overlap.add(s_overlap);					
							System.out.println("Overlap : " +sayi+". " + s_overlap);
							sayi++;
							break;
						}
					}
				}
			}
//			else 
				if(j != 0){
				for (int k = j-1; k >= 0 ; k--)
				{
	
					String s_ = subgraph_concat.get(k).getName().replace(";;", ";");
					String[] s2 = s_.split(delims);
					
					int count=0;
					if (s1.length<=s2.length){
						for (int l = 0; l < s1.length; l++) 
						{
							for (int l2 = 0; l2 < s2.length; l2++) 
							{
								if(s1[l].equals(s2[l2]))
								{
									count++;
									break;
								}
							}
						}
					}
					else if (s1.length>s2.length){
						for (int l2 = 0; l2 < s2.length; l2++) 
						{
							for (int l = 0; l < s1.length; l++) 
							{
								if(s1[l].equals(s2[l2]))
								{
									count++;
									break;
								}
							}
						}
					}
					
					if (s1.length<=s2.length){
						if (count == s1.length)
						{
							String s_overlap = subgraph_concat.get(j).getName().replace(";;", ";");
							overlap.add(s_overlap);					
							System.out.println("Overlap : " +sayi+". " + s_overlap);
							sayi++;
							break;
						}
					}
					else if (s1.length>s2.length){
						if (count == s2.length)
						{
							String s_overlap = subgraph_concat.get(k).getName().replace(";;", ";");
							overlap.add(s_overlap);					
							System.out.println("Overlap : " +sayi+". " + s_overlap);
							sayi++;
							break;
						}
					}
				}
			}
		}
		
		
		//Only nodes 
		subgraph_concat_.clear();
		Integer newSG = 0;		
		for (File file : files2) 
	    {
	    	subgraph.clear();
	        if (file.isDirectory()) 
	        {
	            System.out.print("directory:");
	        } 
	        else 
	        {
	            //System.out.println("File:"+file.getName());
	            fileName = f + "/" + file.getName();
  
	    		try{

	    			//Create object of FileReader
	    			FileReader inputFile = new FileReader(fileName);
	    			BufferedReader bufferReader = new BufferedReader(inputFile);

	    			String line;

	    			// Read file line by line and print on the console
	    			while ((line = bufferReader.readLine()) != null)   {
	    				//System.out.println(line);
	    				subgraph.add(line);
	    			}
	    			//Close the buffer reader
	    			bufferReader.close();
	    	       }
	    		catch(Exception e){
	    	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	    	       }
	    			    		
	    		String sentence="";String nodes ="";

	    		for (String s : subgraph)
	    		{			
	    			if(s.length()>=1 && (!s.contains("digraph")) && (!s.contains("}")))
	    			{				
	    				String ss = s.substring(2,s.length());
	    				
	    				if(newSG.equals(2) && (!ss.contains(" -> ")))
	    				{
	    					//subgraph_concat.add(new Vertices(i,sentence.trim()/*.substring(0,sentence.length()-1)*/,""));
	    					if(sentence.trim().length()>1)
	    						subgraph_concat_.add(sentence.trim());
	    					sentence = "";
	    				}
	    				
	    				if (!ss.contains(" -> "))				
	    				{
	    					nodes = ss;//.substring(0,ss.indexOf(" "));
	    					if (nodes.length()>=1)
	    						sentence = sentence + nodes + ";";
	    					newSG = 1;
	    				}
	    				else if (ss.contains(" -> "))
	    				{
	    					nodes = ss;//.substring(0,ss.indexOf(" ["));
	    					newSG = 2;
	    				}
	    												
	    			}
	    		}   
	    		subgraph_concat_.add(sentence.trim());
	        }
	        i++;	           	        
	    }		  
		

		sayi =1;
	    delims ="[;]";
	    i = 0;
	    subgraph_concat2.clear();
	    for (String s : removeDuplicates(subgraph_concat_)) {
	    	
	    	subgraph_concat2.add(new Vertices(i,s,""));
	    	System.out.println("subgraph_concat2 "+ i +"."+ s );
	    	i++;
	    } 

		//Find overlaps by matching only nodes
		for (int j = 0; j < subgraph_concat2.size(); j++) 
	    {
			String s = subgraph_concat2.get(j).getName().replace(";;", ";");
			String[] s1 = s.split(delims);
			
			if(j != subgraph_concat2.size()-1){			
				for (int k = j+1; k < subgraph_concat2.size(); k++)
				{
	
					String s_ = subgraph_concat2.get(k).getName().replace(";;", ";");
					String[] s2 = s_.split(delims);
					
					int count=0;
					if (s1.length<=s2.length){
						for (int l = 0; l < s1.length; l++) 
						{
							for (int l2 = 0; l2 < s2.length; l2++) 
							{
								if(s1[l].equals(s2[l2]))
								{
									count++;
									break;
								}
							}
						}
					}
					else if (s1.length>s2.length){
						for (int l2 = 0; l2 < s2.length; l2++) 
						{
							for (int l = 0; l < s1.length; l++) 
							{
								if(s1[l].equals(s2[l2]))
								{
									count++;
									break;
								}
							}
						}
					}
					
					if (s1.length<=s2.length){
						if (count == s1.length)
						{
							String s_overlap = subgraph_concat.get(j).getName().replace(";;", ";");
							overlap.add(s_overlap);					
							System.out.println("Overlap_nodes : " +sayi+". " + s_overlap);
							sayi++;
							break;
						}
					}
					else if (s1.length>s2.length){
						if (count == s2.length)
						{
							String s_overlap = subgraph_concat.get(k).getName().replace(";;", ";");
							overlap.add(s_overlap);					
							System.out.println("Overlap_nodes : " +sayi+". " + s_overlap);
							sayi++;
							break;
						}
					}
				}
			}
//			else 
			if(j != 0){
				for (int k = j-1; k >= 0 ; k--)
				{
	
					String s_ = subgraph_concat2.get(k).getName().replace(";;", ";");
					String[] s2 = s_.split(delims);
					
					int count=0;
					if (s1.length<=s2.length){
						for (int l = 0; l < s1.length; l++) 
						{
							for (int l2 = 0; l2 < s2.length; l2++) 
							{
								if(s1[l].equals(s2[l2]))
								{
									count++;
									break;
								}
							}
						}
					}
					else if (s1.length>s2.length){
						for (int l2 = 0; l2 < s2.length; l2++) 
						{
							for (int l = 0; l < s1.length; l++) 
							{
								if(s1[l].equals(s2[l2]))
								{
									count++;
									break;
								}
							}
						}
					}
					
					if (s1.length<=s2.length){
						if (count == s1.length)
						{
							String s_overlap = subgraph_concat.get(j).getName().replace(";;", ";");
							overlap.add(s_overlap);					
							System.out.println("Overlap_nodes : " +sayi+". " + s_overlap);
							sayi++;
							break;
						}
					}
					else if (s1.length>s2.length){
						if (count == s2.length)
						{
							String s_overlap = subgraph_concat.get(k).getName().replace(";;", ";");
							overlap.add(s_overlap);					
							System.out.println("Overlap_nodes : " +sayi+". " + s_overlap);
							sayi++;
							break;
						}
					}
				}
			}
		}
		
		final List<String> finalList = new ArrayList<String>();
		for (Vertices v : subgraph_concat)
	    {
			Boolean exists = false;
			for (String s : overlap)
		    {
				if(v.getName().replace(";;", ";").equals(s.replace(";;", ";")))
				{
					exists = true;
					break;
				}
		    }
			if(exists==false)
				finalList.add(v.getName().replace(";;", ";"));
	    }
		
		sayi =1;
		for (String s : finalList)
	    {
			String[] final_s = s.split(delims);
			if(final_s.length>1){
				PrintWriter writer_final = new PrintWriter(f.toString()+"/final_out_"+sayi+".dot");
				writer_final.println("digraph SubdueGraph {");
				
				s = s.replace("Subdue 5.2.2 graph in dot format;", "");
				s = s.replace(";;", ";");
							
				for (int l = 0; l < final_s.length; l++) 
				{
					writer_final.println("  " + final_s[l] + ";");
				}
				writer_final.println("}");
				
				System.out.println(sayi+". "+ s);
				sayi++;
				writer_final.close();
			}
	    }
		sayi=0;
	}
	
	public void ClearOutputs(File f) throws FileNotFoundException {
		
		final List<String> subgraph = new ArrayList<String>();
	    subgraph.clear();
	    
		FilenameFilter textFilter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".g");
	        }
	    };
	    
	    String fileName="";
	    
	    File[] files3 = f.listFiles(textFilter);
	    	    
	    for (File file : files3) 
	    {
	    	if (file.isDirectory()) 
	        {
	            System.out.print("directory:");
	        } 
	        else 
	        {
	            //System.out.println("File:"+file.getName());
	            fileName = f + "/" + file.getName();
  
	    		try{

	    			//Create object of FileReader
	    			FileReader inputFile = new FileReader(fileName);
	    			BufferedReader bufferReader = new BufferedReader(inputFile);

	    			String line;
	    			Boolean isVertex = false;
	    			
	    			while ((line = bufferReader.readLine()) != null)   {
    					subgraph.add(line);
	    			}
	    			
	    			String strpath=fileName;
	    			ReversedLinesFileReader fr = new ReversedLinesFileReader(new File(strpath));
	    			String ch;

	    			do {
	    			    ch = fr.readLine();
	    			    
	    			    if(ch != null && ch.contains("fontcolor=black")){
	    					subgraph.remove(ch);
	    				}
	    			    
//	    			    if(ch.equals("}")){
//	    					continue;
//	    				}
//	    				if(ch.contains(" -> ") && isVertex.equals(false)){
//	    					subgraph.remove(ch);
//	    				}
//
//	    				if(ch.length()>10 && !ch.contains(" -> ") && (ch.contains("Class") || ch.contains("Interface") || ch.contains("Abstract")) ){
//	    					isVertex = true;
//	    					subgraph.remove(ch);
//	    				}	  
//	    				if(ch.contains(" -> ") && isVertex.equals(true)){
//	    					break;
//	    				}	    			    
	    			} while (ch != null);
	    			
	    			fr.close();
    				
    				PrintWriter writer_out = new PrintWriter(fileName.replace(".g", ".dot"));
    				for (String s : subgraph) {	
    					if (!s.contains("Subdue 5.2.2 graph in dot format")){
    						writer_out.println(s);
    					}
    				}
    				writer_out.close();
    				subgraph.clear();
    				
	    			}
		    	catch(Exception e){
		    	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
		    	       }
	        	}
	    			    		
	    	}
	    
	}

	public void DeleteRedundant(File f){
		String fileName="";

		final List<String> subgraph = new ArrayList<String>();		
		
	    FilenameFilter textFilter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	        	System.out.println(name.toString());
	            return name.toLowerCase().endsWith(".g");
	        }
	    };
	    
	    Integer i = 1;
	    File[] files = f.listFiles(textFilter);

	    //silme islemi. 0 instance ları siliyoruz.
	    for (File file : files) 
	    {
	    	System.out.println(file.toString());
	    	subgraph.clear();
	        if (file.isDirectory()) 
	        {
	            System.out.print("directory:");
	        } 
	        else 
	        {
	            //System.out.println("File:"+file.getName());
	            fileName = f + "/" + file.getName();
	    		try{

	    			//Create object of FileReader
	    			FileReader inputFile = new FileReader(fileName);
	    			
	    			BufferedReader bufferReader = new BufferedReader(inputFile);

	    			String line;

	    			// Read file line by line and print on the console
	    			while ((line = bufferReader.readLine()) != null)   {
	    				//System.out.println(line);
	    				subgraph.add(line);
	    			}
	    			//Close the buffer reader
	    			bufferReader.close();
	    	       }
	    		catch(Exception e){
	    	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	    	       }
	    		
	    		int sayi=1;
	    		for (String s : subgraph)
	    		{			
	    			if(s.length()>=1 && (!s.contains("digraph")) && (!s.contains("}")) && !s.substring(2,3).equals(" "))
	    			{
	    				//System.out.println(s.substring(2,3));
	    				if (Integer.parseInt(s.substring(2,3)) == sayi){
	    					//System.out.println(s.substring(2,3));
	    					//eser 1-9 arass node varsa o zaman bu output 0 instance'lsdsr. Sil!
	    					if(Integer.parseInt(s.substring(2,3)) == 9){
	    						file.delete();
	    						break;
	    					}
	    					sayi++;
	    				}
	    				else break;
	    			}
	    		}
	        }
	    }
	}
	

}

