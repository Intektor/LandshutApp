package de.intektor.landshut_app.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * @author Intektor
 */
public class UpdateRequester {

    public void requestUpdate(final String currentVersion) {
        new Thread("Update Request Thread") {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1", 27206);
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    out.writeUTF(currentVersion);

                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    in.readBoolean()
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
