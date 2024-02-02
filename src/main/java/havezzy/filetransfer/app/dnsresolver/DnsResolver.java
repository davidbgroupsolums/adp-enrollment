package havezzy.filetransfer.app.dnsresolver;

import org.springframework.stereotype.Service;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

@Service
public class DnsResolver {

	public String checkDns(String domainToCheck) {
		String[] mxHostnames = getMXRecords(domainToCheck);

		if (mxHostnames.length > 0) {
			for (String mxHostname : mxHostnames) {
				return mxHostname;
			}
		} 
		return "unknown";
	}

	public static String[] getMXRecords(String domain) {
		try {
			Lookup lookup = new Lookup(domain, Type.MX);
			Record[] records = lookup.run();

			if (records != null) {
				String[] mxHostnames = new String[records.length];
				for (int i = 0; i < records.length; i++) {
					if (records[i] instanceof MXRecord) {
						MXRecord mxRecord = (MXRecord) records[i];
						mxHostnames[i] = mxRecord.getTarget().toString();
					}
				}
				return mxHostnames;
			}
		} catch (TextParseException e) {
			e.printStackTrace();
		}

		return new String[0];
	}
}
