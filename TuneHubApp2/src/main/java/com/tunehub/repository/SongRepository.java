package com.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entities.Songs;

public interface SongRepository extends JpaRepository<Songs,Integer>
{
	public Songs findByName(String name);

	public void deleteById(int id);
}
