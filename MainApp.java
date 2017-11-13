package com.cts.synchs;

public class MainApp {

	public static void main(String[] args) {
		Vehicle[] vehicle =new Vehicle[5];
		
		for(int i=0;i<5;i++)
		{
			vehicle[i]=new Vehicle(i+1);
			vehicle[i].start();
			vehicle[i].randomWait();
			
		}
		
	}

}
