import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String args[]) {
        // arguments supply filename and hostname
        Socket s = null;
        try {
            int serverPort = 7896;
            s = new Socket(args[1], serverPort);
            FileInputStream fileOutputStream = new FileInputStream(args[0]);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileOutputStream);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            byte[] b = new byte[6000];
            int l=0;
            while (l!=-1) {
                l = bufferedInputStream.read(b);
                if (l != -1) out.write(b, 0, l);
                out.flush();
            }
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            if (s != null) try {
                s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
}
