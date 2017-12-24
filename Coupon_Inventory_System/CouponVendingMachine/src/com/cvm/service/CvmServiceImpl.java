package com.cvm.service;

import com.cvm.dao.CvmDao;
import com.cvm.dao.ICvmDao;
import com.cvm.dto.CoupanDetails;
import com.cvm.dto.User;
import com.cvm.exception.CvmException;

public class CvmServiceImpl<T> implements ICvmService{
	
	private ICvmDao cvmDao = new CvmDao();
	private LinkedList userLinkedList;
	private LinkedList coupanLinkedList;
	@Override
	public User validateUser(User user) throws CvmException {
		// TODO Auto-generated method stub
		User authorisedUser = null;
		System.out.println("Printing here");
		userLinkedList.display();
		for(int i=0;i<userLinkedList.getCount();i++)
		{
			if((user.getUserName().equals(((User)userLinkedList.get(i)).getUserName()))&&(user.getPassword().equals(((User)userLinkedList.get(i)).getPassword())))
			{
				authorisedUser =  (User) userLinkedList.get(i);
				System.out.println("authorisedUser "+authorisedUser.toString());
				break;
			}
		}
		return authorisedUser;
	}

	@Override
	public void initializeDB() throws CvmException {
		// TODO Auto-generated method stub
		try {
			userLinkedList = cvmDao.getCustomerDB();
			coupanLinkedList = cvmDao.getCoupanDB();
			userLinkedList.display();
			System.out.println();
			coupanLinkedList.display();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CvmException(e);
		}
	}

	@Override
	public LinkedList searchCoupan(String coupanProvider, String coupanCatagory, String coupanAccesoryName) {
		// TODO Auto-generated method stub
		LinkedList searchedCoupanList = new LinkedList<>();
		if(!(coupanProvider.equals("null")))
		{
			if(!(coupanCatagory.equals("null")))
			{
				if(!(coupanAccesoryName.equals("null")))
				{
					for(int i=0;i<coupanLinkedList.getCount();i++)
					{
						CoupanDetails tempCoupan = (CoupanDetails) coupanLinkedList.get(i);
						if((coupanProvider.equals(tempCoupan.getCoupanProvider()))&&(coupanCatagory.equals(tempCoupan.getCoupanCatagory()))&&(coupanAccesoryName.equals(tempCoupan.getCoupanAccesoryName()))&&((tempCoupan.getCoupanStatus()).equals("Unused")))
						{
							searchedCoupanList.add(tempCoupan);
						}
					}
				}
				else
				{
					for(int i=0;i<coupanLinkedList.getCount();i++)
					{
						CoupanDetails tempCoupan = (CoupanDetails) coupanLinkedList.get(i);
						if((coupanProvider.equals(tempCoupan.getCoupanProvider()))&&(coupanCatagory.equals(tempCoupan.getCoupanCatagory()))&&((tempCoupan.getCoupanStatus()).equals("Unused")))
						{
							searchedCoupanList.add(tempCoupan);
						}
					}
				}
			}
			else
			{
				if(!(coupanAccesoryName.equals("null")))
				{
					for(int i=0;i<coupanLinkedList.getCount();i++)
					{
						CoupanDetails tempCoupan = (CoupanDetails) coupanLinkedList.get(i);
						if((coupanProvider.equals(tempCoupan.getCoupanProvider()))&&(coupanAccesoryName.equals(tempCoupan.getCoupanAccesoryName()))&&((tempCoupan.getCoupanStatus()).equals("Unused")))
						{
							searchedCoupanList.add(tempCoupan);
						}
					}
				}
				else
				{
					for(int i=0;i<coupanLinkedList.getCount();i++)
					{
						CoupanDetails tempCoupan = (CoupanDetails) coupanLinkedList.get(i);
						if((coupanProvider.equals(tempCoupan.getCoupanProvider()))&&((tempCoupan.getCoupanStatus()).equals("Unused")))
						{
							searchedCoupanList.add(tempCoupan);
						}
					}
				}
			}
		}
		else
		{
			if(!(coupanCatagory.equals("null")))
			{
				if(!(coupanAccesoryName.equals("null")))
				{
					for(int i=0;i<coupanLinkedList.getCount();i++)
					{
						CoupanDetails tempCoupan = (CoupanDetails) coupanLinkedList.get(i);
						if((coupanCatagory.equals(tempCoupan.getCoupanCatagory()))&&(coupanAccesoryName.equals(tempCoupan.getCoupanAccesoryName()))&&((tempCoupan.getCoupanStatus()).equals("Unused")))
						{
							searchedCoupanList.add(tempCoupan);
						}
					}
				}
				else
				{
					for(int i=0;i<coupanLinkedList.getCount();i++)
					{
						CoupanDetails tempCoupan = (CoupanDetails) coupanLinkedList.get(i);
						if((coupanCatagory.equals(tempCoupan.getCoupanCatagory()))&&((tempCoupan.getCoupanStatus()).equals("Unused")))
						{
							searchedCoupanList.add(tempCoupan);
						}
					}
				}
			}
			else
			{
				if(!(coupanAccesoryName.equals("null")))
				{
					for(int i=0;i<coupanLinkedList.getCount();i++)
					{
						CoupanDetails tempCoupan = (CoupanDetails) coupanLinkedList.get(i);
						if((coupanAccesoryName.equals(tempCoupan.getCoupanAccesoryName()))&&((tempCoupan.getCoupanStatus()).equals("Unused")))
						{
							searchedCoupanList.add(tempCoupan);
						}
					}
				}
			}
		}
		return searchedCoupanList;
	}

	@Override
	public LinkedList getCoupanProvider() throws CvmException {
		// TODO Auto-generated method stub
		LinkedList coupanProviderLinkedList = new LinkedList<>();
		String tempCoupanProvider = "";
		for(int i=0;i<coupanLinkedList.getCount();i++)
		{
			CoupanDetails tempCoupanDetails = (CoupanDetails) coupanLinkedList.get(i);
			if(!(tempCoupanProvider.equals(tempCoupanDetails.getCoupanProvider())))
			{
				tempCoupanProvider = tempCoupanDetails.getCoupanProvider();
				coupanProviderLinkedList.add(tempCoupanProvider);
			}
		}
		return coupanProviderLinkedList;
	}

	@Override
	public LinkedList getCoupanCatagory() throws CvmException {
		// TODO Auto-generated method stub
		LinkedList coupanCatagoryLinkedList = new LinkedList<>();
		String tempCoupanCatagory = "";
		for(int i=0;i<coupanLinkedList.getCount();i++)
		{
			CoupanDetails tempCoupanDetails = (CoupanDetails) coupanLinkedList.get(i);
			if(!(tempCoupanCatagory.equals(tempCoupanDetails.getCoupanCatagory())))
			{
				tempCoupanCatagory = tempCoupanDetails.getCoupanCatagory();
				coupanCatagoryLinkedList.add(tempCoupanCatagory);
			}
		}
		return coupanCatagoryLinkedList;
	}

	@Override
	public LinkedList getCoupanAccesoryName() throws CvmException {
		// TODO Auto-generated method stub
		LinkedList coupanAccesoryNameLinkedList = new LinkedList<>();
		String tempCoupanAccesoryName = "";
		for(int i=0;i<coupanLinkedList.getCount();i++)
		{
			CoupanDetails tempCoupanDetails = (CoupanDetails) coupanLinkedList.get(i);
			if(!(tempCoupanAccesoryName.equals(tempCoupanDetails.getCoupanAccesoryName())))
			{
				tempCoupanAccesoryName = tempCoupanDetails.getCoupanAccesoryName();
				coupanAccesoryNameLinkedList.add(tempCoupanAccesoryName);
			}
		}
		return coupanAccesoryNameLinkedList;
	}

	@Override
	public User addUser(String name, String userName, String password) throws CvmException{
		// TODO Auto-generated method stub
		boolean userAddingFlag = true;
		User addUser = new User(name, userName, password, "Customer");
		for(int i=0;i<userLinkedList.getCount();i++)
		{
			if(userName.equals(((User)userLinkedList.get(i)).getUserName()))
			{
				userAddingFlag = false;
				break;
			}
		}
		if(userAddingFlag)
		{
			userLinkedList.add(addUser);
			cvmDao.writeDB(addUser);
		}
		else
		{
			addUser = null;
		}
		return addUser;
	}

	@Override
	public LinkedList getSortedList(String string, LinkedList linkedList) throws CvmException {
		// TODO Auto-generated method stub
		LinkedList sortedList = new LinkedList<>();
		if(string.toLowerCase().equals("providername"))
		{
			for (int i = 0; i < linkedList.getCount(); i++) {
		        for (int j = i + 1; j < linkedList.getCount(); j++) {
		            T tmp;
		            if ((((CoupanDetails)linkedList.get(i)).getCoupanProvider()).compareTo(((CoupanDetails)linkedList.get(j)).getCoupanProvider())>0) {
		                tmp = (T)linkedList.get(i);
		                linkedList.getLinkedListArray()[i] = linkedList.get(j);
		                linkedList.getLinkedListArray()[j] = tmp;
		            }
		        }
		    }
			sortedList = linkedList;
		}
		if(string.toLowerCase().equals("product"))
		{
			for (int i = 0; i < linkedList.getCount(); i++) {
		        for (int j = i + 1; j < linkedList.getCount(); j++) {
		            T tmp;
		            if ((((CoupanDetails)linkedList.get(i)).getCoupanAccesoryName()).compareTo(((CoupanDetails)linkedList.get(j)).getCoupanAccesoryName())>0) {
		                tmp = (T)linkedList.get(i);
		                linkedList.getLinkedListArray()[i] = linkedList.get(j);
		                linkedList.getLinkedListArray()[j] = tmp;
		            }
		        }
		    }
			sortedList = linkedList;
		}
		if(string.toLowerCase().equals("price"))
		{
			for (int i = 0; i < linkedList.getCount(); i++) {
		        for (int j = i + 1; j < linkedList.getCount(); j++) {
		            T tmp;
		            if ((((CoupanDetails)linkedList.get(i)).getFinalPrize())>(((CoupanDetails)linkedList.get(j)).getFinalPrize())) {
		                tmp = (T)linkedList.get(i);
		                linkedList.getLinkedListArray()[i] = linkedList.get(j);
		                linkedList.getLinkedListArray()[j] = tmp;
		            }
		        }
		    }
			sortedList = linkedList;
		}
		if(string.toLowerCase().equals("discountrate"))
		{
			for (int i = 0; i < linkedList.getCount(); i++) {
		        for (int j = i + 1; j < linkedList.getCount(); j++) {
		            T tmp;
		            if ((((CoupanDetails)linkedList.get(i)).getDiscountRate())>(((CoupanDetails)linkedList.get(j)).getDiscountRate())) {
		                tmp = (T)linkedList.get(i);
		                linkedList.getLinkedListArray()[i] = linkedList.get(j);
		                linkedList.getLinkedListArray()[j] = tmp;
		            }
		        }
		    }
			sortedList = linkedList;
		}
		if(string.toLowerCase().equals("expirationperiod"))
		{
			for (int i = 0; i < linkedList.getCount(); i++) {
		        for (int j = i + 1; j < linkedList.getCount(); j++) {
		            T tmp;
		            if ((((CoupanDetails)linkedList.get(i)).getNumberOfDaysForCoupanValidity())>(((CoupanDetails)linkedList.get(j)).getNumberOfDaysForCoupanValidity())) {
		                tmp = (T)linkedList.get(i);
		                linkedList.getLinkedListArray()[i] = linkedList.get(j);
		                linkedList.getLinkedListArray()[j] = tmp;
		            }
		        }
		    }
			sortedList = linkedList;
		}
		if(string.toLowerCase().equals("status"))
		{
			for(int i=0;i<linkedList.getCount();i++)
			{
				if(((CoupanDetails)linkedList.get(i)).getCoupanStatus().equals("Unused"))
				{
					sortedList.add(((CoupanDetails)linkedList.get(i)));
				}
			}
			for(int i=0;i<linkedList.getCount();i++)
			{
				if(((CoupanDetails)linkedList.get(i)).getCoupanStatus().equals("Redeemed"))
				{
					sortedList.add(((CoupanDetails)linkedList.get(i)));
				}
			}
		}
		return sortedList;
		
	}

	@Override
	public LinkedList getPurchasedList(String string) throws CvmException {
		// TODO Auto-generated method stub
		String dataArray [] = string.split(" ");
		LinkedList purchasedList = new LinkedList<>();
		int numberOfObjects = dataArray.length/7;
		for(int i=0;i<numberOfObjects;i++)
		{
			String tempArray [] = new String [7];
			for(int j=0;j<7;j++)
			{
				tempArray[j] = dataArray[(i*7)+j];
			}
			for(int k=0;k<coupanLinkedList.getCount();k++)
			{
				CoupanDetails temp = (CoupanDetails) coupanLinkedList.get(k);
				if((tempArray[0].equals(temp.getCoupanProvider()))&&(tempArray[1].equals(temp.getCoupanCatagory()))&&(tempArray[2].equals(temp.getCoupanAccesoryName()))&&(tempArray[3].equals(temp.getCoupanName()))&&(Integer.parseInt(tempArray[4])==temp.getDiscountRate())&&(Float.parseFloat(tempArray[5])==temp.getFinalPrize()))
				{
					temp.setCoupanStatus("Reedemed");
					purchasedList.add(temp);
				}
				
			}
		}
		return purchasedList;
	}

	@Override
	public void updateCoupanDb() throws CvmException {
		// TODO Auto-generated method stub
		cvmDao.writeDB(coupanLinkedList);
	}

	
	
	


}
