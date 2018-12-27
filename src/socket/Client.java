package socket;

import java.io.*;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import gui.MainFrame;
import util.*;

public class Client {
	private Socket socket;
	DataOutputStream dos;
	private boolean isConnected;
	private String id;
	
	public Client(String id, String ip){
		this.id = id;
		connectServer(ip, Config.LocalServerPort);
	}
	
	public boolean connectServer(String ip, int port){
		if(isConnected) {
			return true;
		}
		try {
			socket = new Socket(ip, port);
			isConnected = true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean sendMsg(String message) {  
		if(isConnected){
			try {
				dos.writeUTF(id);
				dos.writeUTF(Config.TextPrefix);
				dos.writeInt(message.length());
				dos.writeUTF(message);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else
			return false;
	}
	
	public boolean sendImage(File file) {  
		if(isConnected){
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	        BufferedInputStream bis = new BufferedInputStream(fis);
	        byte[] byteArray = new byte[(int) file.length()];
	        try {
				bis.read(byteArray, 0, byteArray.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				dos.writeUTF(id);
				dos.writeUTF(Config.ImagePrefix);
				dos.writeInt((int) file.length());
				dos.writeUTF(file.getName());
				dos.write(byteArray);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else
			return false;
	}
	
	public boolean sendFile(File file) {  
		if(isConnected){
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	        BufferedInputStream bis = new BufferedInputStream(fis);
	        byte[] byteArray = new byte[(int) file.length()];
	        try {
				bis.read(byteArray, 0, byteArray.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				dos.writeUTF(id);
				dos.writeUTF(Config.FilePrefix);
				dos.writeInt((int) file.length());
				dos.writeUTF(file.getName());
				dos.write(byteArray);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else
			return false;
	}

	public boolean disConnect() {
		try {
			if (socket != null) {  
				socket.close();  
			}  
			isConnected = false;  
			return true;  
		} catch (IOException e) {  
			e.printStackTrace();
			isConnected = true;  
			return false;  
		}  
	}
}
