package havezzy.filetransfer.app.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/add")
	public Email addEmail(@RequestBody Email email) {
		return emailService.addEmail(email);
	}
	
	@RequestMapping("/get")
	public String getEmail() {
		return emailService.getEmail();
	}
}
