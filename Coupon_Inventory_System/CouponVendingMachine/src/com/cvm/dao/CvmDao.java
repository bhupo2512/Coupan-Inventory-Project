package com.cvm.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.cvm.dto.CoupanDetails;
import com.cvm.dto.User;
import com.cvm.exception.CvmException;
import com.cvm.service.LinkedList;

public class CvmDao implements ICvmDao{

	private final String customerInfoFileName = "D:\\Workspace\\Mars_Workspace\\CS401\\CouponVendingMachine\\file\\CustomerInfo.txt";
	private final String coupanInfoFileName = "D:\\Workspace\\Mars_Workspace\\CS401\\CouponVendingMachine\\file\\CoupanData.txt";
	@Override
	public LinkedList getCustomerDB() throws CvmException {
		// TODO Auto-generated method stub
		String currentLineLoginDetails;
		FileReader loginInfoFileReader;
		LinkedList userLinkedList = new LinkedList();
		try {
			loginInfoFileReader = new FileReader(customerInfoFileName);
			BufferedReader loginInfoBufferReader = new BufferedReader(loginInfoFileReader);
			while((currentLineLoginDetails = loginInfoBufferReader.readLine())!=null)
			{
				String[] line=currentLineLoginDetails.split(" ");
				User user = new User(line[0], line[1], line[2], line[3]);
				userLinkedList.add(user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CvmException(e);
		}
		
		return userLinkedList;
	}
	@Override
	public LinkedList getCoupanDB() throws CvmException {
		// TODO Auto-generated method stub
		String currentLineLoginDetails;
		FileReader loginInfoFileReader = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		 // Now use today date.
		 // Adding 5 days
		String output = sdf.format(c.getTime());
		LinkedList coupanLinkedList = new LinkedList();
		try {
			loginInfoFileReader = new FileReader(coupanInfoFileName);
			BufferedReader loginInfoBufferReader = new BufferedReader(loginInfoFileReader);
			while((currentLineLoginDetails = loginInfoBufferReader.readLine())!=null)
			{
				String[] line=currentLineLoginDetails.split(" ");
				CoupanDetails coupanDetails = new CoupanDetails(line[0], line[1], line[2], line[3], Integer.parseInt(line[4].substring(1)), Integer.parseInt(line[5]), Integer.parseInt(line[6]), line[7]);
				coupanDetails.setFinalPrize(coupanDetails.getItemPrize()-coupanDetails.getItemPrize()*coupanDetails.getDiscountRate()/100);
				c.setTime(new Date());
				c.add(Calendar.DATE,coupanDetails.getNumberOfDaysForCoupanValidity());
				coupanDetails.setExpiryDate(c.getTime());
				coupanLinkedList.add(coupanDetails);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CvmException(e);
		}
		
		finally {
			try {
				if(loginInfoFileReader !=null)
				{
					loginInfoFileReader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new CvmException(e);
			}
			
		}
		return coupanLinkedList;
	}
	@Override
	public void writeDB(User details) throws CvmException {
		// TODO Auto-generated method stub
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try {
			fileWriter = new FileWriter(customerInfoFileName, true);
			printWriter = new PrintWriter(fileWriter);
			printWriter.println(details.getNameOfPerson()+" "+details.getUserName()+" "+details.getPassword()+" "+details.getStatus());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(fileWriter!=null)
				{
					fileWriter.close();
				}
				if(printWriter!=null)
				{
					printWriter.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new CvmException(e);
			}
			
		}
	}
	@Override
	public void writeDB(LinkedList linkedList) throws CvmException {
		// TODO Auto-generated method stub
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		//coupanInfoFileName = "abc.txt";
		//String coupanInfoFileName1 = "D:\\Workspace\\Mars_Workspace\\CS401\\CouponVendingMachine\\file\\abc.txt";
		try {
			fileWriter = new FileWriter(coupanInfoFileName);
			printWriter = new PrintWriter(fileWriter);
			for(int i=0; i<linkedList.getCount();i++)
			{
				CoupanDetails details = (CoupanDetails) linkedList.get(i);
				printWriter.println(details.getCoupanProvider()+" "+details.getCoupanCatagory()+" "+details.getCoupanAccesoryName()+" "+details.getCoupanName()+" $"+details.getItemPrize()+" "+details.getDiscountRate()+" "+details.getNumberOfDaysForCoupanValidity()+" "+details.getCoupanStatus());
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(fileWriter!=null)
				{
					fileWriter.close();
				}
				if(printWriter!=null)
				{
					printWriter.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new CvmException(e);
			}
			
		}
		
	}


	
}
