package tacos.security;

import lombok.Data;
import tacos.models.User;

@Data
public class RegistrationForm {
	private String username;
	private String password;
	private String fullname;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	public User toUser(Encodeador passwordEncoder) {
	return new User(
	username, passwordEncoder.encode(password),
	fullname, street, city, state, zip, phone);
	}
}
