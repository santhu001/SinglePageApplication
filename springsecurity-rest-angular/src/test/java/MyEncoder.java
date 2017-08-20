import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyEncoder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
		System.out.println(encoder.encode("santhu123"));
	}

}
