package com.cts.synchs;

public class Vehicle extends Thread{
	private int regNo;
	private Bridge bridge;
	private TollBooth toll;
	
	public Vehicle(int no)
	{
		regNo=no;
		bridge=new Bridge();
		toll=new TollBooth();
	}
	@Override
	public synchronized void run()
	{
		         
	           bridge.bridgeMessage(regNo);
	           toll.tollBoothMessage(regNo);
	}
	void randomWait()
    {
        try {
           sleep((long)(3000*Math.random()));
        } catch (InterruptedException x) {
           System.out.println("Interrupted!");
        }
    }
}
