/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crubot.connect;

import java.io.IOException;

/**
 *
 * @author KjetilAndre
 */
public class CrubotMain {
   
    
    public static void main(String args[]) throws IOException{
        CrubotCon c1 = new CrubotCon("irc.no.quakenet.org", "CruBot", "oauth:ca3muz1anz4eddy93v1f2a41lyotiqg", "#otabttt");
      
        c1.serverConnect();
     
}
}
