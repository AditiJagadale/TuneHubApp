package com.tunehub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Songs;
import com.tunehub.repository.SongRepository;
@Service
public class SongServiceImplementation implements SongService {
	@Autowired
	SongRepository srepo;
	
	@Override
	public String addSongs(Songs song) {
		srepo.save(song);
		return "song is added";
	}

	@Override
	public boolean songExist(String name) {
		if(srepo.findByName(name)==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public List<Songs> fetchAllSongs() {
		List<Songs> songlist=srepo.findAll();
		return songlist;
	}

	@Override
	public void updateSong(Songs song) {
		srepo.save(song);
		
	}
	
	// New method implementation to delete a song
		@Override
		public void deleteSong(int id) {
			srepo.deleteById(id);
		}

}
