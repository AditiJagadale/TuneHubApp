package com.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Playlist;
import com.tunehub.repository.PlaylistRepository;

@Service
public class PlaylistServiceImplementation implements PlaylistService{
	@Autowired
	PlaylistRepository prepo;

	@Override
	public void addPlaylist(Playlist playlist) {
		prepo.save(playlist);
		
	}

	@Override
	public List<Playlist> fetchPlaylist() {
		prepo.findAll();
		return prepo.findAll();
	}

	

	

	
	
	

}
