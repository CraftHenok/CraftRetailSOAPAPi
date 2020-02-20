package com.craftsoftware.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.craftsoftware.soap.service.UserService;
import com.craftsoftware.users.CreateUserRequest;
import com.craftsoftware.users.CreateUserResponse;
import com.craftsoftware.users.DeleteUserRequest;
import com.craftsoftware.users.DeleteUserResponse;
import com.craftsoftware.users.GetAllUsersRequest;
import com.craftsoftware.users.GetAllUsersResponse;
import com.craftsoftware.users.GetUserRequest;
import com.craftsoftware.users.GetUserResponse;
import com.craftsoftware.users.UpdateUserRequest;
import com.craftsoftware.users.UpdateUserResponse;
import com.craftsoftware.users.User;

@Endpoint
public class UserEndpoint {
	
	UserService service = new UserService();

	@PayloadRoot(namespace = "http://www.craftsoftware.com/users", localPart = "GetUserRequest")
	@ResponsePayload
	public GetUserResponse processUserDetailsRequest(@RequestPayload GetUserRequest request) {
		GetUserResponse response = new GetUserResponse();	
		response.setUser(service.findUser(request.getId()));
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/users", localPart = "DeleteUserRequest")
	@ResponsePayload
	public DeleteUserResponse deleteUserRequest(@RequestPayload DeleteUserRequest request) {
		DeleteUserResponse response = new DeleteUserResponse();
		UserService user = new UserService();
		user.deleteUser(request.getId());
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/users", localPart = "GetAllUsersRequest")
	@ResponsePayload
	public GetAllUsersResponse getAllUsersRequest(@RequestPayload GetAllUsersRequest request) {
		List<User> users = service.getAllUsers();
		GetAllUsersResponse response = new GetAllUsersResponse();
		for (User user : users) {
			User mapUser = mapUser(user);
			response.getUsers().add(mapUser);
		}
		return response;
		
	}
	
	private User mapUser(User user) {
		User userDetails = new User();
		userDetails.setId(user.getId());
		userDetails.setFist(user.getFist());
		userDetails.setDepartment(user.getDepartment());
		return userDetails;
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/users", localPart = "UpdateUserRequest")
	@ResponsePayload
	public UpdateUserResponse updateUserRequest(@RequestPayload UpdateUserRequest request) {
		UpdateUserResponse response = new UpdateUserResponse();
		service.udpateUser(request.getUser());
		return response;
		
	}
	
	@PayloadRoot(namespace = "http://www.craftsoftware.com/users", localPart = "CreateUserRequest")
	@ResponsePayload
	public CreateUserResponse updateUserRequest(@RequestPayload CreateUserRequest request) {
		CreateUserResponse response = new CreateUserResponse();
		service.createUser(request.getCreateUser());
		return response;
		
	}
}
