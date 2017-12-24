package com.cvm.service;

import com.cvm.dto.User;
import com.cvm.exception.CvmException;

public interface ICvmService {
	public User validateUser(User user) throws CvmException;
	public void initializeDB ()throws CvmException;
	public LinkedList searchCoupan(String coupanProvider, String coupanCatagory, String coupanAccesoryName) throws CvmException;
	public LinkedList getCoupanProvider() throws CvmException;
	public LinkedList getCoupanCatagory() throws CvmException;
	public LinkedList getCoupanAccesoryName() throws CvmException;
	public User addUser(String name, String userName, String password)throws CvmException;
	public LinkedList getSortedList(String string, LinkedList linkedList) throws CvmException;
	public LinkedList getPurchasedList(String string) throws CvmException;
	public void updateCoupanDb() throws CvmException;
}
