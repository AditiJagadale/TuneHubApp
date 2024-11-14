package com.tunehub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Users;
import com.tunehub.repository.UsersRepository;
@Service
public class UsersServiceImplementation implements UsersService
{	@Autowired
	UsersRepository repo;
	
	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "User is created and saved";
	}

	@Override
	public boolean emailExists(String email) {
		if(repo.findByEmail(email)==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user=repo.findByEmail(email);
		String db_password=user.getPassword();
		if(db_password.equals(password))
		{
			return true;
		}		
		else
		{
			return false;
		}
	}

	@Override
	public String getRole(String email) {
		Users user=repo.findByEmail(email);
		String role=user.getRole();
		return role;
	}

	@Override
	public Users getUser(String email) {
		
		return repo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		repo.save(user);
		
		
	}

	@Override
	public boolean resetPassword(String email, String newPassword) {
	    // Fetch the user by email
	    Users user = repo.findByEmail(email);
	    
	    // Check if user exists
	    if (user == null) {
	        // If user does not exist, return false
	        return false;
	    }
	    
	    // Optionally, you could add more checks here like ensuring the new password meets certain criteria
	    if (newPassword == null || newPassword.isEmpty()) {
	        // If new password is invalid, return false
	        return false;
	    }

	    // Update the user's password
	    user.setPassword(newPassword);

	    // Save the updated user back to the repository
	    repo.save(user);

	    // If everything is successful, return true
	    return true;
	}
	
	
	


	

}
