package com.cts.threaddemos;

public class PNRApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 PNRGenerator pnrgen =new PNRGenerator(10);
Thread t1 =new Thread(pnrgen,"Producer");
Thread t2=new Thread(pnrgen,"Consumer");
t1.start();
t2.start();
		
		
		
	}

}
