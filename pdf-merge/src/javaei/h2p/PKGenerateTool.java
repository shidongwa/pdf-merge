package javaei.h2p;

import java.util.Random;

public class PKGenerateTool {
	
	public String generatorPk(int count) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < count;i ++){
			sb.append(createRandomChar());
		}
		return sb.toString();
	}
	
	
	private char createRandomChar(){
		Random rd = new Random();
		int choice = rd.nextInt(3);
		char value;
		if(choice == 0){
			value = (char)createRandomInt(48,56);
		}else if(choice == 1){
			value = (char)createRandomInt(97,121);
		}else{
			value = (char)createRandomInt(65,89);
		}
		return value;
	}
	
	
	private int createRandomInt(int min,int max){
		Random random = new Random();
		int rdvalue = random.nextInt(max - min + 1);
		return min + rdvalue;
	}
	
	

}
