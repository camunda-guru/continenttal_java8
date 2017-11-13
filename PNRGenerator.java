package com.cts.threaddemos;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class PNRGenerator implements Runnable{

	private List<String> pnrList;
	private int count;
	//private Base64 base64Encoder;
	
	public PNRGenerator(int count)
	{
		pnrList=new ArrayList<String>();
		this.count=count;
	}
	
	public synchronized void createPNRArray()
	{
		System.out.println("Accessing Producer....");
		
		String temp=null;
		byte[] genData;
		while(true)
		{
		 	if(pnrList.isEmpty())
		 	{
		 		
				//for(int i=0;i<count;i++)
				//{
			       temp=String.valueOf(new Random().nextInt(10000));
			     //  genData=base64Encoder.getEncoder().encode(temp.getBytes());
					pnrList.add(temp);
					//System.out.println(genData.toString());
				//}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Generated all number....Ready to consume");
				notify();
		 	}
		 	else
		 	{
		 		try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	}
		}
		
	}
	
	
	public synchronized void consumePNRData()
	{
		System.out.println("Accessing Consumer....");
		while(true)
		{
		if(pnrList.size()==1)
		{
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for(int i=0;i<pnrList.size();i++)
		{
			
			System.out.println(pnrList.get(i));
			pnrList.remove(i);
			
		}
		notify();
		
		}
		else
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
		
	}


	@Override
	public void run() {
		// TODO Auto-generasuper.run();
		if(Thread.currentThread().getName().equals("Producer"))
			createPNRArray();
			else
				consumePNRData();
		
	}
	
	
	
	
	
}
