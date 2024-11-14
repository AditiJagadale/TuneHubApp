package com.tunehub.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.entities.Playlist;
import com.tunehub.entities.Songs;
import com.tunehub.services.PlaylistService;
import com.tunehub.services.SongService;

@Controller
public class PlaylistController {
	@Autowired
	PlaylistService pserv;
	
	@Autowired
	SongService sserv;
	
	@GetMapping("/createplaylist")
	public String createPlayList(Model model)
	{
		List<Songs> songlist=sserv.fetchAllSongs();
		
		model.addAttribute("songlist", songlist);
		
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist)
	{	//adding playlist
		pserv.addPlaylist(playlist);
		
		//update the song table
		List<Songs> songList=playlist.getSongs();
		
		for(Songs song: songList)
		{
			song.getPlaylist().add(playlist);
			sserv.updateSong(song);
		}
		return "playlistsuccess";
		
	}
	
	@GetMapping("/viewplaylist")
	public String fetchPlaylist(Model model) {
		List<Playlist> plist=pserv.fetchPlaylist();
		System.out.println(plist);
		model.addAttribute("plist", plist);
		return "viewplaylist";
		
	}
		@GetMapping("/displayPlaylist")
		public String fetchPlaylist1(Model model) {
			List<Playlist> plist=pserv.fetchPlaylist();
			System.out.println(plist);
			model.addAttribute("plist", plist);
			return "displayPlaylist";
		
	}
}
