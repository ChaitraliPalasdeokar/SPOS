package Pass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Pass1 {

	
	public static void main(String[] args) throws Exception {
		
		// reading instructions from code 
		String data = readFileAsString("/home/ccoew/3476/Assign1/src/input");
		String[] s = data.split("[\\s,]");
		//split based on space
		 
		ArrayList<String> str = new ArrayList<String>();
		for(String e : s){
		    if(!e.isEmpty()){    
			 str.add(e);
		    }
		    
	    }
	
		System.out.println(str.toString());
		
		//read the mnemonics file
		
	    String mnemo = readFileAsString("/home/ccoew/3476/Assign1/src/mnemonics");
	    String[] mnem = mnemo.split("\\s");
	    
	    ArrayList<String> mnemonics = new ArrayList<String>();
		
	    for(String e : mnem){
		    
	    	if(!e.isEmpty()){
		    	 mnemonics.add(e);
		    }
		    
	    }
		
		System.out.println(mnemonics.toString());
		
		// wrapping the created ref array around object of class mnemonics
		
		Mnemonics m;
		ArrayList<Mnemonics> ref = new ArrayList<Mnemonics>();	    
		String n=null,op=null,t=null;
	    int l=0;
	    for(int i=0;i<mnemonics.size();i++) {
			if(i%4==0) {
				n=mnemonics.get(i);
				System.out.println("0 : " +mnemonics.get(i) );
				
			}
			else if(i%4==1) {
				t=mnemonics.get(i);
				System.out.println("1 : " +mnemonics.get(i) );
				
			}
			else if(i%4==2) {
				op=mnemonics.get(i);
				System.out.println("2: " +mnemonics.get(i) );
				
			}
			else if(i%4==3) {
				l=Integer.parseInt(mnemonics.get(i));
				System.out.println("3 : " +mnemonics.get(i) );
				m=new Mnemonics();
				m.name=n;
				m.len=l;
				m.opcode=op;
				m.type=t;
				ref.add(m);
			
			}
		}
	
	    System.out.println(ref.toString());
			
	    String opIn=null;
	    int lc=0;

		//stage one of parse one - compare with regs,start and so on..
	    for(int i=0;i<str.size();i++) {
		
	       if(true){
		        for(int j=0;j<ref.size();j++) {
		    		
		    		String st=null;
		    		if(str.get(i).equals(ref.get(j).name)){
		    			st=str.get(i)+" "+ ref.get(j).name+" ";
		    			
		    			if(lc!=0) {
		    			opIn=lc+" ("+ref.get(j).type+","+ref.get(j).opcode+")";
		    			}
		    			else {
			    			opIn="("+ref.get(j).type+","+ref.get(j).opcode+")";
			    		}
		    			lc=lc+ref.get(j).len;
		    			System.out.println(st+opIn);System.out.println(st+opIn);
		    			write("/home/ccoew/3476/Assign1/src/IC",opIn);
		    			
		    			break;			
		    		}
		    	}
	        }
	    	if(str.get(i).matches("\\d*")){
		   			
	    		opIn=" "+str.get(i)+"\n";
	   			lc=Integer.parseInt(str.get(i));
	    		System.out.println(opIn);
	   			
	   			write("/home/ccoew/3476/Assign1/src/IC",opIn);
	   		}
	    	
	    	if(str.get(i).matches(".REG")){
		   			
	            	
		    		opIn=" "+str.get(i)+",";
		   			System.out.println(opIn);
			   	    write("/home/ccoew/3476/Assign1/src/IC",opIn);
		    					
			}
            
	    	if(str.get(i).matches("=.*")){
		   			
            	
	    		opIn="(L,"+str.get(i).replaceAll("\\W","")+")\n";
	   			System.out.println(opIn);
		   	    write("/home/ccoew/3476/Assign1/src/IC",opIn);
	    					
		    }
	 
	    	else {
	    		opIn=str.get(i)+"\n";
	   			System.out.println(opIn);
		   	    write("/home/ccoew/3476/Assign1/src/IC",opIn);
	    	}
		}

	}


	
	public static String readFileAsString(String fileName)throws Exception
	{
	 String data = "";
	 data = new String(Files.readAllBytes(Paths.get(fileName)));
	 return data;
	}
	
	public static void write(String path,String update){
		try {
			FileWriter file = new FileWriter(path,true);
			file.write(update);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
