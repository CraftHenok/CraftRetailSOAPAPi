package com.craftsoftware.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.craftsoftware.users.CreateUser;
import com.craftsoftware.users.User;


public class UserService {
	private static List<User> users = new ArrayList<>();
	private static int userCount = 4;
		
	static {
		User user1 = new User();
		user1.setId(1);
		user1.setFist("Ted");
		user1.setLast("Bear");
		user1.setDepartment("iib");
		
		User user2 = new User();
		user2.setId(2);
		user2.setFist("Ted2");
		user2.setLast("Bear2");
		user2.setDepartment("iib");
		
		User user3 = new User();
		user3.setId(3);
		user3.setFist("Ted3");
		user3.setLast("Bear3");
		user3.setDepartment("iib");
		
		User user4 = new User();
		user4.setId(4);
		user4.setFist("Ted4");
		user4.setLast("Bear4");
		user4.setDepartment("iib");
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}
	
	public User deleteUser(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	public User findUser(int id) {
		for(User user: users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
		
	}
	
	public void udpateUser(User user2) {
		for(User user: users) {
			if(user.getId()==user2.getId()) {
				user.setFist(user2.getFist());
				user.setLast(user2.getLast());
				user.setDepartment(user2.getDepartment());
			}
		}
			
	}
	
	public void createUser(CreateUser user2) {
		User user = new User();
		user.setId(++userCount);
		user.setFist(user2.getFist());
		user.setLast(user2.getLast());
		user.setDepartment(user2.getDepartment());
		users.add(user);
	}
	
	public List<User> getAllUsers(){
		return users;
	}

}
