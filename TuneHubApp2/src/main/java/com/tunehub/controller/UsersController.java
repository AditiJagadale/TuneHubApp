package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Songs;
import com.tunehub.entities.Users;
import com.tunehub.services.SongService;
import com.tunehub.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService userv;
	
	@Autowired
	SongService sserv;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user)
	{	
		boolean userstatus=userv.emailExists(user.getEmail());
		
		if(userstatus==false)
		{
			userv.addUser(user);
			return "login";
		}
		else
		{
			return "registerfail";
		}
		
	}
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email,@RequestParam String password, HttpSession session)
	{
		boolean loginstatus=userv.validateUser(email,password);
		if(loginstatus==true)
		{	
			session.setAttribute("email", email);
			
			// Extract the name from the email before the "@" symbol
            String name = email.split("@")[0];
            
            
            // Capitalize the first letter of the name
            name = capitalizeFirstLetter(name);
			
            session.setAttribute("name", name);
            
			//checking whether the user is admin or customer
			String role=userv.getRole(email);
			if(role.equals("admin"))
			{
			return "adminhome";
			}
			else
			{
			return "customerhome";
			}
		}
		else 
		{
		return "loginfail";
		}
		
		
		
	}
	
	@GetMapping("/exploresongs")
	public String exploreSongs(HttpSession session, Model model)
	{
		String email=(String) session.getAttribute("email");
		 if (email == null) {
		        return "redirect:/login"; // Redirect to login page if no session
		    }
	
		Users user=userv.getUser(email);
		boolean userStatus=user.isPremium();
		if(userStatus == true)
		{
			List<Songs> songlist=sserv.fetchAllSongs();
			model.addAttribute("songlist", songlist);
			return "viewSongs";
		}
		else
		{
			return "samplepayment";
		}
	}
	
	@PostMapping("/resetpassword")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword) {
		boolean passwordReset = userv.resetPassword(email, newPassword);
		if(passwordReset==true)
		{
			
			return "login";
		}
		else
		{
			return "resetpassword";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // Invalidate the session
	    return "redirect:/login"; // Redirect to the login page
	}
	
	// Helper method to capitalize the first letter
    private String capitalizeFirstLetter(String name) {
        if (name == null || name.isEmpty()) {
            return name;  // Return the original string if it's null or empty
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
