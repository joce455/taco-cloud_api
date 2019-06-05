package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Encodeador implements PasswordEncoder{

	
		@Override
		public boolean matches(CharSequence rawPassword, String encodedPassword) {
			if(rawPassword.toString().equals(encodedPassword)) {
				return true;
			}
			return false;
		}
		
		@Override
		public String encode(CharSequence rawPassword) {
			return rawPassword.toString();
			
		}
	
}
