package com.cvm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cvm.dto.User;
import com.cvm.exception.CvmException;
import com.cvm.service.CvmServiceImpl;
import com.cvm.service.ICvmService;
import com.cvm.service.LinkedList;

/**
 * Servlet implementation class CoupanVendingMachineCOntroller
 */
@WebServlet(name = "CoupanVendingMachineController", urlPatterns = { "/CoupanVendingMachineController" })
public class CouponVendingMachineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ICvmService cvmService = new CvmServiceImpl();
    LinkedList coupanProviderList;
    LinkedList coupanCatagoryList;
    LinkedList coupanAccesoryNameList;
    LinkedList availableCoupanList;
    LinkedList sortedList;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CouponVendingMachineController() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try {
    	
			cvmService.initializeDB();
		} catch (CvmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String uri=request.getRequestURI();
		String navigation = "";
		if(uri.contains("sortedCarrt.do"))
		{
			String selectionString = request.getParameter("so");
			if(selectionString.equals("1"))
			{
				try {
					availableCoupanList = cvmService.getSortedList("providername", availableCoupanList);
				} catch (CvmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(selectionString.equals("2"))
			{
				try {
					availableCoupanList = cvmService.getSortedList("product", availableCoupanList);
				} catch (CvmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(selectionString.equals("3"))
			{
				try {
					availableCoupanList = cvmService.getSortedList("price", availableCoupanList);
				} catch (CvmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(selectionString.equals("4"))
			{
				try {
					availableCoupanList = cvmService.getSortedList("discountrate", availableCoupanList);
				} catch (CvmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(selectionString.equals("5"))
			{
				try {
					availableCoupanList = cvmService.getSortedList("expirationperiod", availableCoupanList);
				} catch (CvmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(selectionString.equals("6"))
			{
				try {
					availableCoupanList = cvmService.getSortedList("status", availableCoupanList);
				} catch (CvmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.setAttribute("availableCoupanList", availableCoupanList);
			navigation = "PurchaseList.jsp";
			RequestDispatcher dispatcher=request.getRequestDispatcher(navigation);
			dispatcher.forward(request, response);
			
		}
		
		if(uri.contains("displaycoupan.do"))
		{
			String sortingFactor = (String) request.getAttribute("sortingFactor");
			LinkedList sortingList = availableCoupanList;
			try {
				sortedList = cvmService.getSortedList(sortingFactor, sortingList);
			} catch (CvmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// decidation remained from where request has come
		}
		if(uri.contains("done.do"))
		{
			try {
				cvmService.updateCoupanDb();
				navigation = "index.jsp";
				RequestDispatcher dispatcher=request.getRequestDispatcher(navigation);
				dispatcher.forward(request, response);
			} catch (CvmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String navigation = "";
		String uri=request.getRequestURI();
		if (uri.contains("login.do")){
			String userName=request.getParameter("username");
			String password=request.getParameter("password");
			
			User currentUser = new User();
			currentUser.setUserName(userName);
			currentUser.setPassword(password);
			
			try {
				currentUser = cvmService.validateUser(currentUser);
				if(currentUser!=null)
				{
					coupanProviderList = cvmService.getCoupanProvider();
					coupanCatagoryList = cvmService.getCoupanCatagory();
					coupanAccesoryNameList = cvmService.getCoupanAccesoryName();
					request.setAttribute("coupanProviderList", coupanProviderList);
					request.setAttribute("coupanCatagoryList", coupanCatagoryList);
					request.setAttribute("coupanAccesoryNameList", coupanAccesoryNameList);
					navigation = "Profile.jsp";
				}
				else
				{
					navigation = "Registration.jsp";
				}
				RequestDispatcher dispatcher=request.getRequestDispatcher(navigation);
				dispatcher.forward(request, response);
			} catch (CvmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(uri.contains("registration.do"))
		{
			String name =  request.getParameter("name");
			String userName =  request.getParameter("username");
			String password =  request.getParameter("password");
			User newUser;
			try {
				newUser = cvmService.addUser(name, userName, password);
				if(newUser!=null)
				{
					
					navigation = "index.jsp";
					
				}
				else
				{
					navigation = "Registration.jsp";
				}
				RequestDispatcher dispatcher=request.getRequestDispatcher(navigation);
				dispatcher.forward(request, response);
			} catch (CvmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(uri.contains("coupanselection.do"))// change it - from page where we select from dropdowns
		{
			try {
				availableCoupanList = cvmService.searchCoupan(request.getParameter("couponProvider"), request.getParameter("couponCategroy"), request.getParameter("couponProduct"));
				request.setAttribute("availableCoupanList", availableCoupanList);
				navigation = "PurchaseList.jsp";
				RequestDispatcher dispatcher=request.getRequestDispatcher(navigation);
				dispatcher.forward(request, response);
			} catch (CvmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(uri.contains("cart.do"))
		{
			String selection = request.getParameter("selectedList1");
			//System.out.println(selection);
			try {
				LinkedList purchasedList = cvmService.getPurchasedList(selection);
				request.setAttribute("purchasedList", purchasedList);
				navigation = "PurchasedCoupans.jsp";
				RequestDispatcher dispatcher=request.getRequestDispatcher(navigation);
				dispatcher.forward(request, response);
			} catch (CvmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
