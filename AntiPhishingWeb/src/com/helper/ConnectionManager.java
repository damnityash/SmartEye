/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import com.constant.ServerConstants;

/**
 * 
 * @author Admin
 */
public class ConnectionManager extends DBUtils {

	public static Connection getDBConnection() {
		Connection conn = null;
		try {
			Class.forName(ServerConstants.db_driver);
			conn = DriverManager.getConnection(ServerConstants.db_url,
					ServerConstants.db_user, ServerConstants.db_pwd);
			System.out.println("Got Connection");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(
					null,
					"Please start the mysql Service from XAMPP Console.\n"
							+ ex.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static Boolean isBlackListed(String url) {

		WhiteListModel wl = new WhiteListModel();
		// String
		// query="SELECT * FROM useraccounts where (username like ? OR email like ?) and pass = ?";
		String query = "select * from phishtankurls where url like '"+url+"'";
		// UserModel um=null;
		List list = DBUtils.getBeanList(WhiteListModel.class, query);
		if (list.size() > 0) {

			return true;

		}
		return false;
	}
	public static Boolean isWhitelisted(String url) {

		// String
		// query="SELECT * FROM useraccounts where (username like ? OR email like ?) and pass = ?";
		String query = "select * from whitelisturl where whitelist ='true' and urldesc like '"+url+"'";
		// UserModel um=null;
		List list = DBUtils.getBeanList(WhiteListModel.class, query);
		if (list.size() > 0) {

			return true;

		}
		return false;
	}
	


	public static String callServer(String question){
		  StringBuilder responseString = new StringBuilder();

		     PrintWriter writer = null;
		     BufferedReader bufferedReader = null;
		     Socket clientSocket = null;

		     try {
		         try {
		    clientSocket = new Socket("localhost", 7813);
		   } catch (UnknownHostException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		         if (!clientSocket.isConnected())
		    try {
		     throw new SocketException("Could not connect to Socket");
		    } catch (SocketException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }

		         try {
		    clientSocket.setKeepAlive(true);
		   } catch (SocketException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }

		         try {
		    writer = new PrintWriter(clientSocket.getOutputStream(), true);
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		         writer.println(question);
		         writer.flush();

		         try {
		    bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		         String str;
		         try {
		    while ((str = bufferedReader.readLine()) != null) {
		        responseString.append(str);
		    }
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }

		     } finally {
		         if (writer != null)
		             writer.close();
		         if (bufferedReader != null)
		    try {
		     bufferedReader.close();
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		         if (clientSocket != null)
		    try {
		     clientSocket.close();
		    } catch (IOException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		     }
		     return responseString.toString(); 
		 }
	
	
	public static void sendTOServer(String sm){
		System.out.println("sending to server: " + sm);
		URL url = null;
		try {
			url = new URL("http://192.168.0.103:7813/" + URLEncoder.encode(sm, java.nio.charset.StandardCharsets.UTF_8.toString()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   System.out.println("URL to connect: " + url);
		   HttpURLConnection conn = null;
		try {
			System.out.println("11111");
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			System.out.println("3333");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   try {
			   System.out.println("2222");
			conn.setRequestMethod("GET");
		} catch (ProtocolException e) {
			System.out.println("4444");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 
	public static void main(String[] args) {
		boolean b = isBlackListed("http://2933517.com/js");
		System.out.println(b);
	}
}
