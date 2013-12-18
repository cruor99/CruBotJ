/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crubot.connect;
import java.io.*;
import java.net.*;
/**
 *
 * @author KjetilAndre
 */
public class CrubotCon {
    String server = "";
    String nick = "";
    String login = "";
    String channel = "";
    public CrubotCon(String servName, String nickName, String loginPass, String channelName){
       server = servName;
       nick = nickName;
       login = loginPass;
       channel = channelName;
        
    }
   public void serverConnect() throws IOException{
       //Server connection 
       Socket socket = new Socket(server, 6667);
       BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream()));
       BufferedReader reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
       
       //Server logon
       writer.write("NICK " + nick + "\r\n");
       writer.write("USER" + login + " 8 * : Java IRC Bot Crubot \r\n");
       writer.flush();
       
       //Read lines from the server until we get connection confirmation
       String line = null;
       while ((line=reader.readLine()) != null){
           if (line.indexOf("004") >= 0){
               //we are now logged in.
               break;
           }
           else if (line.indexOf("433") >= 0){
               System.out.println("Nickname is alreay in use.");
               return;
           }
        }
       //join channel
       writer.write("JOIN " + channel + "\r\n");
       writer.flush();
       
       //keep reading lines from the server
       while((line = reader.readLine()) != null){
           if(line.toLowerCase().startsWith("PING ")){
               //Pings must be responded to, or we will be disconnected
               writer.write("PONG " + line.substring(5) + "\r\n");
               writer.write("PRIVMSG "+ channel + " PONG \r\n");
               writer.flush();
           }
           else{
               //print the raw line received by the bot
               System.out.println(line);
           }
           }
       }
}
     


