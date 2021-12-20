package com.thepolo49.backend.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails, Serializable {

	@Id
	private ObjectId id;

	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime modifiedAt;

	private boolean enabled = true;

	@Indexed(unique=true)
	private String username;
	private String password;
	private Set<Role> authorities = new HashSet<>();

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.enabled = true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

}
