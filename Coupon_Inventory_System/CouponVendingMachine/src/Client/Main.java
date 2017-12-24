package Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.cvm.dto.CoupanDetails;
import com.cvm.dto.User;
import com.cvm.service.LinkedList;



public class Main {

	public static void main(String[] args) {
		// variable Declaration
		String userName;
		String password;
		String customerInfoFileName = System.getProperty("user.dir")+"\\files\\CustomerInfo.txt";
		String coupanInfoFileName = System.getProperty("user.dir")+"\\files\\CoupanData.txt";
		String currentLineLoginDetails;
		boolean loginStatus = false;
		boolean userNameAvailabilityStatus = false;
		boolean passwordMatchStatus = false;
		String selection = "N";
		String registrationSelection = "N";
		String nameOfUserInput;
		String userNameInput;
		String passwordInput;
		String confirmPasswordInput;
		String passwordIterationFlag = "N";
		String currentCoupanDetails;
		String purchaseSelection;
		int searchSelection;
		int coupanProviderSelection;
		int coupanCatagorySelection;
		int labelcount;
		int coupanPurchaseId;
		
		String searchCoupanProvider = "";
		String searchCoupanProviderChoice;
		String searchCoupanProviderCatagory = "";
		String searchCoupanProviderCatagoryAccesory = "";
		
		String searchCoupanCatagory ="";
		String searchCoupanAccesory = "";
		
		LinkedList userSelectionList = new LinkedList();
		LinkedList userSelectionSortedList = new LinkedList();
		LinkedList userPurchasedList = new LinkedList();
		// Taking input for sign in
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			do 
			{
				System.out.println("Enter UserName: ");
				userName = bufferedReader.readLine();
				System.out.println("Enter password: ");
				password = bufferedReader.readLine();
				
				// creating temporary user for login details;
				User logger = new User();
				logger.setUserName(userName);
				logger.setPassword(password);
				
				// verifying user is present in database or not
				FileReader loginInfoFileReader = new FileReader(customerInfoFileName);
				BufferedReader loginInfoBufferReader = new BufferedReader(loginInfoFileReader);
				while((currentLineLoginDetails = loginInfoBufferReader.readLine())!=null)
				{
					//System.out.println(currentLineLoginDetails);
					String[] line=currentLineLoginDetails.split(" ");
					//System.out.println("Length of line Array: "+line.length);
					User tempLogger = new User(line[0], line[1], line[2], line[3]);
					if(tempLogger.getUserName().equals(logger.getUserName()))
					{
						if(tempLogger.getPassword().equals(logger.getPassword()))
						{
							loginStatus = true;
							passwordMatchStatus = true;
							selection = "N";
							break;
						}
						else
						{
							userNameAvailabilityStatus = true;
							break;
						}
					}
				}
				
				if(loginStatus)
				{
					System.out.println("Logged in Successfully.");
				}
				else
				{
					if(userNameAvailabilityStatus)
					{
						System.out.println("Password did not matched");
						System.out.println("Do you want to enter details again: (Y/N)");
						selection = bufferedReader.readLine();
					}
					else
					{
						System.out.println("The user with this username is not registered.");
						System.out.println("Do you want to register: (Y/N)");
						registrationSelection = bufferedReader.readLine();
					}
				}
			}while(selection.toLowerCase().equals("y"));
			
			// Implemention Registration
			if(registrationSelection.toLowerCase().equals("y") && loginStatus==false)
			{
				do
				{
					System.out.println("Enter Name: ");
					nameOfUserInput = bufferedReader.readLine();
					System.out.println("Enter username: ");
					userNameInput = bufferedReader.readLine();
					
					System.out.println("Enter Password: ");
					passwordInput = bufferedReader.readLine();
					System.out.println("Enter Password again: ");
					confirmPasswordInput = bufferedReader.readLine();
					if(!(passwordInput.equals(confirmPasswordInput)))
					{
						System.out.println("Password and confirmed password do not match.");
						System.out.println("Do you want to enter details again: (Y/N)");
						passwordIterationFlag = bufferedReader.readLine();
					}
					else
					{
						passwordIterationFlag = "N";
						User details = new User(nameOfUserInput, userNameInput, passwordInput, "Customer");
						loginStatus = true;
						FileWriter fileWriter = new FileWriter(customerInfoFileName, true);
						PrintWriter printWriter = new PrintWriter(fileWriter);
						printWriter.println(details.getNameOfPerson()+" "+details.getUserName()+" "+details.getPassword()+" "+details.getStatus());
						printWriter.close();
					}
					// work from here. adding this login details to file is remained and work from here is remained. 
					// write file and allow to follow next code. no need for login again but code for that is need to be developed.
				}while(passwordIterationFlag.toLowerCase().equals("y"));
				
				if(passwordIterationFlag.toLowerCase().equals("n") && loginStatus==false)
				{
					System.exit(0);
				}
			}
			if((!(registrationSelection.toLowerCase().equals("y"))) && loginStatus==false)
			{
				System.exit(0);
			}
			System.out.println("Starts working from here");
			// Creating linkedlist for coupan data.
			LinkedList coupanList = new LinkedList();
			// Loading data from coupan database to linkedlist
			
			FileReader coupanReader = new FileReader(coupanInfoFileName);
			BufferedReader coupanReaderBufferReader = new BufferedReader(coupanReader);
			while((currentCoupanDetails = coupanReaderBufferReader.readLine())!= null)
			{
				String[] line=currentCoupanDetails.split(" ");
				System.out.println("Prize is: "+line[4]);
				System.out.println("Rate is: "+line[5]);
				System.out.println("Number of days: "+line[6]);
				CoupanDetails coupanDetails = new CoupanDetails(line[0], line[1], line[2], line[3], Integer.parseInt(line[4].substring(1)), Integer.parseInt(line[5]), Integer.parseInt(line[6]), line[7]);
				coupanDetails.setFinalPrize((coupanDetails.getItemPrize()-(coupanDetails.getItemPrize()*coupanDetails.getDiscountRate()/100)));
				coupanList.add(coupanDetails);
			}
			
			// printing data for reference purpose
			// coupanList.display();
			
			// Implementing Purchase Coupan Functionality -start
			System.out.println("Do you want to purchase any coupan(Y/N): ");
			purchaseSelection = bufferedReader.readLine();
			if(purchaseSelection.toLowerCase().equals("y"))
			{
				// Creating list for Coupan Providers
				LinkedList coupanProviders = new LinkedList();
				String tempCoupanProvider ="";
				int coupanProviderCount = 1;
				for(int i=0;i<coupanList.getCount();i++)
				{
					if(!(tempCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider())))
					{
						tempCoupanProvider = ((CoupanDetails)coupanList.get(i)).getCoupanProvider();
						coupanProviders.add(coupanProviderCount+"-"+tempCoupanProvider);
						coupanProviderCount++;
					}
				}
				coupanProviderCount--;
				// start working here..... 
				// print list for checking purpose
				System.out.println();
				System.out.println("Coupan Provider List: ");
				coupanProviders.display();
				
				// Creating list for Coupan Categories
				LinkedList coupanCatagory = new LinkedList();
				String tempCoupanCatagory = "";
				int coupanCatagoryCount = 1;
				for(int i=0;i<coupanList.getCount();i++)
				{
					if(!(tempCoupanCatagory.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory())))
					{
						tempCoupanCatagory = ((CoupanDetails)coupanList.get(i)).getCoupanCatagory();
						coupanCatagory.add(coupanCatagoryCount+"-"+tempCoupanCatagory);
						coupanCatagoryCount++;
					}
				}
				coupanCatagoryCount--;
				// print list for checking purpose
				System.out.println();
				System.out.println("Coupan Catagory List: ");
				coupanCatagory.display();
				
				// Creating list for Coupan Accesories.
				LinkedList coupanAccesoryName = new LinkedList();
				String tempCoupanAccesoryName = "";
				int coupanAccesoryCount = 1;
				for(int i=0;i<coupanList.getCount();i++)
				{
					if(!(tempCoupanAccesoryName.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName())))
					{
						tempCoupanAccesoryName = ((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName();
						coupanAccesoryName.add(coupanAccesoryCount+"-"+tempCoupanAccesoryName);
						coupanAccesoryCount++;
					}
				}
				coupanAccesoryCount--;
				// print list for checking
				System.out.println();
				System.out.println("Coupan Accesory Name List: ");
				coupanAccesoryName.display();
				
				// search method implementation
				System.out.println("How do you want to search coupan: ");
				System.out.println("1 - Coupan Provider");
				System.out.println("2 - Coupan Category");
				System.out.println("3 - Coupan Accesory Type");
				
				searchSelection = Integer.parseInt(bufferedReader.readLine());
				if(searchSelection==1)
				{
					System.out.println("Select One of The Coupan Provider from follow: ");
					coupanProviders.display();
					coupanProviderSelection = Integer.parseInt(bufferedReader.readLine());
					for(int i=0;i<coupanProviders.getCount();i++)
					{
						if(coupanProviderSelection == Integer.parseInt(((String)coupanProviders.get(i)).substring(0, 1)))
						{
							searchCoupanProvider = ((String)coupanProviders.get(i)).substring(2);
							break;
						}
								
					}
					System.out.println("Selected coupan Provider is: "+searchCoupanProvider);
					System.out.println("Do you want to select Coupan catagory under this provider: (Y/N)");
					searchCoupanProviderChoice = bufferedReader.readLine();
					if(searchCoupanProviderChoice.toLowerCase().equals("y"))
					{
						System.out.println("Select Coupan Category: ");
						int tc1 = 1;
						String coupanCatagoryUnderCoupanProvider ="";
						for(int i=0;i<coupanList.getCount();i++)
						{
							if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()))&&(!(coupanCatagoryUnderCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory()))))
							{
								coupanCatagoryUnderCoupanProvider = ((CoupanDetails)coupanList.get(i)).getCoupanCatagory();
								System.out.println(tc1+"-"+coupanCatagoryUnderCoupanProvider);
								tc1++;
							}
						}
						tc1=0;
						coupanCatagoryUnderCoupanProvider ="";
						int coupanCatagoryProviderSelection = Integer.parseInt(bufferedReader.readLine());
						for(int i=0;i<coupanList.getCount();i++)
						{
							if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()))&&(!(coupanCatagoryUnderCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory()))))
							{
								coupanCatagoryUnderCoupanProvider = ((CoupanDetails)coupanList.get(i)).getCoupanCatagory();
								tc1++;
								if(tc1==coupanCatagoryProviderSelection)
								{
									searchCoupanProviderCatagory = coupanCatagoryUnderCoupanProvider;
									break;
								}
							}
						}
						System.out.println("Selected Coupan Catagory Under Provider - "+searchCoupanProvider+" is: "+searchCoupanProviderCatagory);
						
						System.out.println("Do you want to select Coupan Accesory under this catagory and provider: (Y/N)");
						searchCoupanProviderChoice = bufferedReader.readLine();
						if(searchCoupanProviderChoice.toLowerCase().equals("y"))
						{
							System.out.println("Enter id for accesory which you want to choose: ");
							int tc2 =1;
							String coupanAccesoryunderCatagoryAndProvider = "";
							for(int i=0;i<coupanList.getCount();i++)
							{
								if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()) && (searchCoupanProviderCatagory.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory())))&&(!(coupanAccesoryunderCatagoryAndProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName()))))
								{
									coupanAccesoryunderCatagoryAndProvider = ((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName();
									System.out.println(tc2+"-"+coupanAccesoryunderCatagoryAndProvider);
									tc2++;
								}
							}
							tc2 = 0;
							coupanAccesoryunderCatagoryAndProvider = "";
							int coupanAccesoryUnderCatagoryAndProviderSelection = Integer.parseInt(bufferedReader.readLine());
							for(int i=0;i<coupanList.getCount();i++)
							{
								if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()) && (searchCoupanProviderCatagory.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory())))&&(!(coupanAccesoryunderCatagoryAndProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName()))))
								{
									coupanAccesoryunderCatagoryAndProvider = ((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName();
									tc2++;
									if(tc2==coupanAccesoryUnderCatagoryAndProviderSelection)
									{
										searchCoupanProviderCatagoryAccesory = coupanAccesoryunderCatagoryAndProvider;
										break;
									}
								}
							}
							System.out.println("Selected accesory under Provider - "+searchCoupanProvider+" and catagory - "+searchCoupanProviderCatagory+" is: "+searchCoupanProviderCatagoryAccesory);
							System.out.println("Displaying all coupan under this selection: ");
							for(int i=0;i<coupanList.getCount();i++)
							{
								if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()))&&(searchCoupanProviderCatagory.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory()))&&(searchCoupanProviderCatagoryAccesory.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName())))
								{
									userSelectionList.add(coupanList.get(i));
								}
							}
							System.out.println("Do you want to view all available coupans in sorted list of final prize: (Y/N)");
							searchCoupanProviderChoice = bufferedReader.readLine();
							if(searchCoupanProviderChoice.toLowerCase().equals("y"))
							{
								userSelectionSortedList = userSelectionList.sort();
								do
								{
									
									System.out.println("Diaplying all available coupans in sorted order: ");
									labelcount = 1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionSortedList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionSortedList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionSortedList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
							}
							else
							{
								//from here
								do
								{
									
									System.out.println("Diaplying all available coupans: ");
									labelcount = 1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
								// to here
							}
						}
						else
						{
							// only provider and catagory is present
							System.out.println("Selected accesory under Provider - "+searchCoupanProvider+" and catagory - "+searchCoupanProviderCatagory+" is: ");
							System.out.println("Displaying all coupan under this selection: ");
							for(int i=0;i<coupanList.getCount();i++)
							{
								if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()))&&(searchCoupanProviderCatagory.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory())))
								{
									userSelectionList.add(coupanList.get(i));
								}
							}
							System.out.println("Do you want to view all available coupans in sorted list of final prize: (Y/N)");
							searchCoupanProviderChoice = bufferedReader.readLine();
							if(searchCoupanProviderChoice.toLowerCase().equals("y"))
							{
								userSelectionSortedList = userSelectionList.sort();
								do
								{
									
									System.out.println("Diaplying all available coupans in sorted order: ");
									labelcount = 1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionSortedList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionSortedList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionSortedList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
							}
							else
							{
								//from here
								do
								{
									
									System.out.println("Diaplying all available coupans: ");
									labelcount = 1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
								// to here
							}
							
							
							
							
							
						}
					}
					else
					{
						System.out.println("Do you want to select Coupan Accesory under this provider: (Y/N)");
						searchCoupanProviderChoice = bufferedReader.readLine();
						if(searchCoupanProviderChoice.toLowerCase().equals("y"))
						{
							// provider and then accesory starts from here
							System.out.println("Enter id for accesory which you want to choose: ");
							int tc2 =1;
							String coupanAccesoryunderCatagoryAndProvider = "";
							for(int i=0;i<coupanList.getCount();i++)
							{
								if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()) &&(!(coupanAccesoryunderCatagoryAndProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName())))))
								{
									coupanAccesoryunderCatagoryAndProvider = ((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName();
									System.out.println(tc2+"-"+coupanAccesoryunderCatagoryAndProvider);
									tc2++;
								}
							}
							tc2 = 0;
							coupanAccesoryunderCatagoryAndProvider = "";
							int coupanAccesoryUnderCatagoryAndProviderSelection = Integer.parseInt(bufferedReader.readLine());
							for(int i=0;i<coupanList.getCount();i++)
							{
								if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()) &&(!(coupanAccesoryunderCatagoryAndProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName())))))
								{
									coupanAccesoryunderCatagoryAndProvider = ((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName();
									tc2++;
									if(tc2==coupanAccesoryUnderCatagoryAndProviderSelection)
									{
										searchCoupanProviderCatagoryAccesory = coupanAccesoryunderCatagoryAndProvider;
										break;
									}
								}
							}
							System.out.println("Selected accesory under Provider - "+searchCoupanProvider+" is: "+searchCoupanProviderCatagoryAccesory);
							System.out.println("Displaying all coupan under this selection: ");
							for(int i=0;i<coupanList.getCount();i++)
							{
								if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()))&&(searchCoupanProviderCatagoryAccesory.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName())))
								{
									userSelectionList.add(coupanList.get(i));
								}
							}
							System.out.println("Do you want to view all available coupans in sorted list of final prize: (Y/N)");
							searchCoupanProviderChoice = bufferedReader.readLine();
							if(searchCoupanProviderChoice.toLowerCase().equals("y"))
							{
								userSelectionSortedList = userSelectionList.sort();
								do
								{
									
									System.out.println("Diaplying all available coupans in sorted order: ");
									labelcount = 1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionSortedList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionSortedList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionSortedList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
							}
							else
							{
								//from here
								do
								{
									
									System.out.println("Diaplying all available coupans:");
									labelcount = 1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
								// to here
							}
							
							
							
							
						}
						else
						{
							System.out.println("Printing all coupans provided by this coupan provider: ");
							// only provider starts here 
							System.out.println("Displaying all coupan under this selection: ");
							for(int i=0;i<coupanList.getCount();i++)
							{
								if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider())))
								{
									userSelectionList.add(coupanList.get(i));
								}
							}
							System.out.println("Do you want to view all available coupans in sorted list of final prize: (Y/N)");
							searchCoupanProviderChoice = bufferedReader.readLine();
							if(searchCoupanProviderChoice.toLowerCase().equals("y"))
							{
								userSelectionSortedList = userSelectionList.sort();
								do
								{
									
									System.out.println("Diaplying all available coupans in sorted order: ");
									labelcount = 1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionSortedList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionSortedList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionSortedList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
							}
							else
							{
								//from here
								do
								{
									
									System.out.println("Diaplying all available coupans: ");
									labelcount = 1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
								// to here
							}
						}
					}
				}
				else
				{
					if(searchSelection==2)
					{
						// catagory and accesory
						System.out.println("Select One of The Coupan Catagory from follow: ");
						coupanCatagory.display();
						coupanCatagorySelection = Integer.parseInt(bufferedReader.readLine());
						for(int i=0;i<coupanProviders.getCount();i++)
						{
							if(coupanCatagorySelection == Integer.parseInt(((String)coupanProviders.get(i)).substring(0, 1)))
							{
								searchCoupanCatagory = ((String)coupanCatagory.get(i)).substring(2);
								break;
							}
									
						}
						System.out.println("Selected coupan Catagory is: "+searchCoupanCatagory);
						System.out.println("Do you want to select Coupan Accesory under this catagory and provider: (Y/N)");
						searchCoupanProviderChoice = bufferedReader.readLine();
						// adding accesory
						if(searchCoupanProviderChoice.toLowerCase().equals("y"))
						{
							System.out.println("Enter id for accesory which you want to choose: ");
							int tc2 =1;
							String coupanAccesoryunderCatagoryAndProvider = "";
							for(int i=0;i<coupanList.getCount();i++)
							{
								if(((searchCoupanProviderCatagory.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory())))&&(!(coupanAccesoryunderCatagoryAndProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName()))))
								{
									coupanAccesoryunderCatagoryAndProvider = ((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName();
									System.out.println(tc2+"-"+coupanAccesoryunderCatagoryAndProvider);
									tc2++;
								}
							}
							tc2 = 0;
							coupanAccesoryunderCatagoryAndProvider = "";
							int coupanAccesoryUnderCatagoryAndProviderSelection = Integer.parseInt(bufferedReader.readLine());
							for(int i=0;i<coupanList.getCount();i++)
							{
								if(((searchCoupanProviderCatagory.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory())))&&(!(coupanAccesoryunderCatagoryAndProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName()))))
								{
									coupanAccesoryunderCatagoryAndProvider = ((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName();
									tc2++;
									if(tc2==coupanAccesoryUnderCatagoryAndProviderSelection)
									{
										searchCoupanProviderCatagoryAccesory = coupanAccesoryunderCatagoryAndProvider;
										break;
									}
								}
							}
							System.out.println("Selected accesory under catagory - "+searchCoupanProviderCatagory+" is: "+searchCoupanProviderCatagoryAccesory);
							System.out.println("Displaying all coupan under this selection: ");
							for(int i=0;i<coupanList.getCount();i++)
							{
								if((searchCoupanProvider.equals(((CoupanDetails)coupanList.get(i)).getCoupanProvider()))&&(searchCoupanProviderCatagory.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory()))&&(searchCoupanProviderCatagoryAccesory.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName())))
								{
									userSelectionList.add(coupanList.get(i));
								}
							}
							System.out.println("Do you want to view all available coupans in sorted list of final prize: (Y/N)");
							searchCoupanProviderChoice = bufferedReader.readLine();
							if(searchCoupanProviderChoice.toLowerCase().equals("y"))
							{
								userSelectionSortedList = userSelectionList.sort();
								do
								{
									
									System.out.println("Diaplying all available coupans in sorted order: ");
									labelcount = 1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionSortedList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionSortedList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionSortedList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
							}
							else
							{
								//from here
								do
								{
									
									System.out.println("Diaplying all available coupans: ");
									labelcount = 1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
								// to here
							}
						}
						else
						{
							// only catagory
							System.out.println("Displaying all coupan under this selection: ");
							for(int i=0;i<coupanList.getCount();i++)
							{
								if(((searchCoupanProviderCatagory.equals(((CoupanDetails)coupanList.get(i)).getCoupanCatagory()))&&(searchCoupanProviderCatagoryAccesory.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName()))))
								{
									userSelectionList.add(coupanList.get(i));
								}
							}
							System.out.println("Do you want to view all available coupans in sorted list of final prize: (Y/N)");
							searchCoupanProviderChoice = bufferedReader.readLine();
							if(searchCoupanProviderChoice.toLowerCase().equals("y"))
							{
								userSelectionSortedList = userSelectionList.sort();
								do
								{
									
									System.out.println("Diaplying all available coupans in sorted order: ");
									labelcount = 1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionSortedList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionSortedList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionSortedList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
							}
							else
							{
								//from here
								do
								{
									
									System.out.println("Diaplying all available coupans: ");
									labelcount = 1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
								// to here
							}
							// only catagory ends
							
						}
					}
					else
					{
						if(searchSelection==3)
						{
							System.out.println("Select One of The Coupan Accesory from follow: ");
							coupanAccesoryName.display();
							coupanCatagorySelection = Integer.parseInt(bufferedReader.readLine());
							for(int i=0;i<coupanProviders.getCount();i++)
							{
								if(coupanCatagorySelection == Integer.parseInt(((String)coupanProviders.get(i)).substring(0, 1)))
								{
									searchCoupanAccesory = ((String)coupanCatagory.get(i)).substring(2);
									break;
								}
										
							}
							System.out.println("Selected coupan Accesory is: "+searchCoupanAccesory);
							// displaying all under accesory
							System.out.println("Displaying all coupan under this selection: ");
							for(int i=0;i<coupanList.getCount();i++)
							{
								if(((searchCoupanProviderCatagoryAccesory.equals(((CoupanDetails)coupanList.get(i)).getCoupanAccesoryName()))))
								{
									userSelectionList.add(coupanList.get(i));
								}
							}
							System.out.println("Do you want to view all available coupans in sorted list of final prize: (Y/N)");
							searchCoupanProviderChoice = bufferedReader.readLine();
							if(searchCoupanProviderChoice.toLowerCase().equals("y"))
							{
								userSelectionSortedList = userSelectionList.sort();
								do
								{
									
									System.out.println("Diaplying all available coupans in sorted order: ");
									labelcount = 1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionSortedList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionSortedList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionSortedList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionSortedList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionSortedList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
							}
							else
							{
								//from here
								do
								{
									
									System.out.println("Diaplying all available coupans: ");
									labelcount = 1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if(((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused"))
										{
											System.out.println(labelcount+" - "+((CoupanDetails)userSelectionList.get(i)).toString());
											labelcount++;
										}
									}
									System.out.println("Enter id of coupan you want to purchase: ");
									coupanPurchaseId =0;
									coupanPurchaseId = Integer.parseInt(bufferedReader.readLine());
									labelcount =1;
									for(int i=0;i<userSelectionList.getCount();i++)
									{
										if((((CoupanDetails)userSelectionList.get(i)).getCoupanStatus().equals("Unused")))
										{
											if(coupanPurchaseId == labelcount)
											{
												((CoupanDetails)userSelectionList.get(i)).setCoupanStatus("Redeemed");
												userPurchasedList.add(userSelectionList.get(i));
											}
										}
									}
									
									System.out.println("Do you want to purchase coupan again: (Y/N)");
									searchCoupanProviderChoice = bufferedReader.readLine();
								}while(searchCoupanProviderChoice.toLowerCase().equals("y"));
								// to here
							}
							// display  accesory ends
						}
						else
						{
							// implement list coupan here.
							// diaply all coupans
							// sort them
							// show purchased coupans
							// allow to purchase 
						}
					}
				}
			}
			else
			{
				System.exit(0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
