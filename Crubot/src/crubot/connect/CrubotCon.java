/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crubot.connect;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.regex.*;

/**
 *
 * @author KjetilAndre
 */
public class CrubotCon {
    String server = "";
    String nick = "";
    String login = "";
    String channel = "";
    int port = 6667;
    
    public CrubotCon(String servName, String nickName, String loginPass, String channelName){
       server = servName;
       nick = nickName;
       login = loginPass;
       channel = channelName;
        
    }
   public void serverConnect() throws IOException{
       //Server connection 
       Socket socket = new Socket(server, 6667);
       //output stream
       BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream()));
       //input stream
       BufferedReader reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
       
       
       //authenticate with the server
        writer.write("PASS " + login + "\n");
       writer.write("NICK " + nick + "\n");
       //Join the channel
       writer.write("JOIN " + channel + "\n");
       writer.write("PRIVMSG " + channel + " :Crubot up in this shit! \n");
       writer.flush();
       System.out.println("Successfully connected to the channel");
       
       String currLine = "";
       while ((currLine = reader.readLine()) !=null)
       {
           //checks for PING, returns PONG if found
           Pattern pingRegex = Pattern.compile("^PING", Pattern.CASE_INSENSITIVE);
           Matcher ping = pingRegex.matcher(currLine);
           if(ping.find())
           {
               writer.write("PONG " + channel + "\n");
               writer.flush();
           }
           }
       Pattern exitRegex = Pattern.compile("!exit", Pattern.CASE_INSENSITIVE);
       Matcher exit = exitRegex.matcher(currLine);
       if(exit.find())
       {
           writer.write("PRIVMSG " + channel + " :Bye Bye \n");
           writer.write("PART " + channel + "\n");
           writer.flush();
           socket.close();
       }
      
       }
    public Date dateTime(){
               Date d = new Date();
        return d;
                
    }
 }

     


