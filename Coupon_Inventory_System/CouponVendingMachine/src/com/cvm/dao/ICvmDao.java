package com.cvm.dao;

import com.cvm.dto.User;
import com.cvm.exception.CvmException;
import com.cvm.service.LinkedList;

public interface ICvmDao<T> {
	public LinkedList getCustomerDB()throws CvmException;
	public LinkedList getCoupanDB()throws CvmException;
	public void writeDB(User user) throws CvmException;
	public void writeDB( LinkedList<T> linkedList) throws CvmException;
}
