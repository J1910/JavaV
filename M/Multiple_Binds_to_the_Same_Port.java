import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.http.HttpServletRequest;

class Multiple_Binds_to_the_Same_Port {

    // public void badMethodpre(HttpServletRequest req) {
    //     String iface = req.getParameter("arg0");
    //     badMethod(iface);
    // }

    // public void badMethodpre() {
    //     String iface = System.getProperty("key");
    //     badMethod(iface);
    // }

    public void badMethod(String iface) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(iface, 2020));
        // Do something
        ss.close();
    }

    public void goodMethod(String iface) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(iface, 0));
        // Do something
        ss.close();
    }

}
