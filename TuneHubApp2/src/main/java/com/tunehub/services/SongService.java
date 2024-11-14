package com.tunehub.services;

import java.util.List;

import com.tunehub.entities.Songs;

public interface SongService {
	public String addSongs(Songs song);
	
	public boolean songExist(String name);
	
	public List<Songs> fetchAllSongs();

	public void updateSong(Songs song);
	
	// New method to delete a song by ID
	public void deleteSong(int id);
}
