package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tunehub.entities.Songs;
import com.tunehub.services.SongService;

import org.springframework.ui.Model;

@Controller
public class SongsController {
	@Autowired
	SongService sserv;
	
	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Songs song )
	{

		boolean songstatus=sserv.songExist(song.getName());
		
		if(songstatus==false)
		{
			sserv.addSongs(song);
			return "songsuccess";
		}
		else
		{
			return "songfail";
		}
		
	}
	
	@GetMapping("/map-viewsongs")
	public String viewSongs(Model model)
	{
		List<Songs> songlist=sserv.fetchAllSongs();
		model.addAttribute("songlist", songlist);
		return "displaysongs";
	}
	
	@GetMapping("/viewsongs")
	public String viewCustomerSongs(Model model)
	{
		boolean primeCustomerStatus=true;
		if(primeCustomerStatus==true)
		{
			List<Songs> songlist=sserv.fetchAllSongs();
			model.addAttribute("songlist", songlist);
			return "displaysongs";
		}
		else
		{
			return "samplepayment";
		}
	}
	
	// New method to delete a song
		@PostMapping("/deletesong")
		public String deleteSong(@RequestParam("id") int id, Model model) {
			sserv.deleteSong(id);
			return "/map-viewsongs";  // Redirect to refresh the list of songs
		}
}
