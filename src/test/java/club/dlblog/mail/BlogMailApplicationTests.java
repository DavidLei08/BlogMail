package club.dlblog.mail;

import club.dlblog.mail.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogMailApplicationTests {

	@Autowired
	private MailService mailService;

	@Test
	void contextLoads() {
		mailService.send("2686849744@qq.com", "testSubject", "tets text!!!!");
		mailService.send("2944394709@qq.com", "testSubject", "tets text!!!!\r\n huanghuang ");
	}


}
