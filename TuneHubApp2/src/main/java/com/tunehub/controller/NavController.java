package com.tunehub.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@EnableAutoConfiguration
public class NavController {
	@GetMapping("/map-register")
	public String registerMapping()
	{
		return "register";
	}
	
	@GetMapping("/map-login")
	public String loginMapping()
	{
		return "login";
	}
	
	@GetMapping("/map-songs")
	public String songMapping()
	{
		return "addsongs";
	}
	
	@GetMapping("/samplepayment")
	public String samplePayment()
	{
		return "samplepayment";
	}
	
	
	@GetMapping("/map-deleteSongs")
	public String deleteSongs()
	{
		return "deleteSong";
	}
	
	@GetMapping("/resetpassword")
	public String resetPassword()
	{
		return "resetpassword";
	}
	
}
