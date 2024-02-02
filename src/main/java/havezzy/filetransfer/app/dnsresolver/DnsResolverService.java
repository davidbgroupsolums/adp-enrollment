package havezzy.filetransfer.app.dnsresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnsResolverService {

	@Autowired
	private DnsResolver dnsResolver;
	
	public String checkDns(String domainToCheck) {
		return dnsResolver.checkDns(domainToCheck);
	}
}
