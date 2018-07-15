import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    private static final int PORT = 8080;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("MiniServer active " + PORT);
            while (true) {
                new ThreadedSocket(server.accept());
            }
        } catch (Exception e) {
        }
    }
}
class ThreadedSocket extends Thread {
    private Socket insocket;
    ThreadedSocket(Socket insocket) {
        this.insocket = insocket;
        this.start();
    }
    @Override
    public void run() {
        try {
            InputStream is = insocket.getInputStream();
            PrintWriter out = new PrintWriter(insocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line;
            line = in.readLine();
            String request_method = line;
            System.out.println(line);
            line = "";
            request_method = request_method.replaceAll("GET", "").replaceAll("/", "").trim().split(" ")[0];
//            int postDataI = -1;
////            while ((line = in.readLine()) != null && (line.length() != 0)) {
////                System.out.println(line);
////                if (line.indexOf("Content-Length:") > -1) {
////                    postDataI = new Integer(
////                            line.substring(
////                                    line.indexOf("Content-Length:") + 16,
////                                    line.length())).intValue();
////                }
////            }
////            String postData = "";
//  
//            if (postDataI > 0) {
//                char[] charArray = new char[postDataI];
//                in.read(charArray, 0, postDataI);
//                postData = new String(charArray);
//            }
            BufferedReader br;
    			if(request_method.equalsIgnoreCase("wave")) {
    				br = new BufferedReader(new FileReader("/Users/league/Desktop/wave.html"));
    				String line2 = "";
    				out.println("HTTP/1.0 200 OK");
    	            out.println("Content-Type: text/html; charset=utf-8");
    	            out.println("Server: MINISERVER");
    	            out.println("");
    				while((line2 = br.readLine()) != null) {
    					out.println(line2);
    				}
    			}
    			else if(request_method.equalsIgnoreCase("free")) {
    				br = new BufferedReader(new FileReader("/Users/league/Desktop/free.html"));
    				String line2 = "";
    				out.println("HTTP/1.0 200 OK");
    	            out.println("Content-Type: text/html; charset=utf-8");
    	            out.println("Server: MINISERVER");
    	            out.println("");
    				while((line2 = br.readLine()) != null) {
    					out.println(line2);
    				}
    			}
    			else {
    				out.println("HTTP/1.0 200 OK");
    	            out.println("Content-Type: text/html; charset=utf-8");
    	            out.println("Server: MINISERVER");
    	            out.println("");
    	            out.println("<h1>Welcome to the Web Site</h1>");
    	            out.println("Username: <input></input><br>Password: <input type='password'></input>");
    	            out.println("<script>function red() {window.location = \"https://www.google.com/\"; } </script>");
    	       
    	            out.println("<button onClick=\"red()\">Button</button>");
    	            
    			}
            out.close();
            insocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadPage(String request, PrintWriter printWriter) throws IOException {
    		BufferedReader br;
    		if(request.equalsIgnoreCase("wave")) {
    			br = new BufferedReader(new FileReader("/Users/league/Desktop/wave.html"));
    			String line = "";
    			while((line = br.readLine()) != null) {
    				printWriter.println(line);
    			}
    		}
    		else {
    			
    		}
    }
}