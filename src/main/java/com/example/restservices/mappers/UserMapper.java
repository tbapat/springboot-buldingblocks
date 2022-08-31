package com.example.restservices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.example.restservices.dtos.UserMsDto;
import com.example.restservices.entities.Users;

@Mapper(componentModel = "Spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);	
	
	@Mappings({
	@Mapping(source = "email", target="emailaddress"),
	@Mapping(source="role", target="rolename")
	})
	UserMsDto usertoUserMsDto(Users user);
	
	List<UserMsDto> usersToUserMsDtos(List<Users> users);

}
