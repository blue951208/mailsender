package goodee.three.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HomeworkBoardApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(HomeworkBoardApplication.class, args);
	}
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HomeworkBoardApplication.class);
	}
}
