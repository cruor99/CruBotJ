/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crubot.connect;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * @author KjetilAndre
 */
public class CrubotMain {
   
    
    public static void main(String args[]) throws IOException{
        CrubotCon c1 = new CrubotCon("irc.twitch.tv", "CruBot", "oauth:ca3muz1anz4eddy93v1f2a41lyotiqg", "#that_other_vidya_stream");
      try{
        c1.serverConnect();
     
} 
      catch(UnknownHostException e){
          System.err.println("No such host");
      }
      catch (IOException e){
          System.err.println("There was an error connecting to the host");
      }
      }
      }
      

