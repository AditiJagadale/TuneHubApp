package com.tunehub.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.tunehub.entities.Users;
import com.tunehub.services.UsersService;

import jakarta.servlet.http.HttpSession;


@Controller
public class PaymentController {
	@Autowired
	UsersService service;
	Order order=null;
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder() {
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_LTBNwsDHz5Y41P", "UVd91SBOqsTdKx4MLhhFwW0P");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",50000);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1","Tea, Earl Grey, Hot");
			orderRequest.put("notes",notes);

			order = razorpay.orders.create(orderRequest);
		}
		catch(Exception e)
		{
			System.out.println("exception while creating order");
		}
		return order.toString();
	}
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId, @RequestParam String signature)
	{
	    try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_LTBNwsDHz5Y41P", "UVd91SBOqsTdKx4MLhhFwW0P");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "UVd91SBOqsTdKx4MLhhFwW0P");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//payment-success -> update to premium user
	@GetMapping("payment-success")
	public String paymentSuccess(HttpSession session)
	{//get the email id of user
		String email=(String)session.getAttribute("email");
		Users user=service.getUser(email);
		//set the status of the user to true 
		user.setPremium(true);
		//update the user to premium in database
		service.updateUser(user);
		
		return "login";
	}
	
	//payment-failure -> redirect to login page
	@GetMapping("payment-failure")
	public String paymentFailure()
	{
		//payment-error page
		return "login";
	}
}
