
package CypressSys;
import java.net.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.util.*;

/**
 * To Use LiveMap, simply instantiate it with an ArrayList of String[] arrays.
 * Each String array should contain two elements:
 *  -Concern type
 *  -Address/Coordinate/Location of the Concern
 * For example:
 * {"Potholes","350 Victoria Street Toronto"}
 * 
 * It is better to be specific while specifying location, or else Google Map's
 * API will not be able to locate the marker.
 * @author Philip L
 */


public class LiveMap {
    private JFrame frame;
    private Image map;
    private ArrayList<String[]> markers; //List of markers
    
    public LiveMap(ArrayList<String[]> markers){
        this.markers = markers;
        URL url = null;
        frame = new JFrame();
        /** 
         * Read Map from static Google Map API.
         */
        try{
            url = new URL("https://maps.googleapis.com/maps/api/staticmap?"

                    //+ "?center=Toronto,ON"
                    + "&zoom=11"
                    + "&scale=2"
                    + "&size=2000x2000"
                    + "&format=gif"
                    //+ "&maptype=roadmap"
                    + "&path=fillcolor:0xAA000030%7Ccolor:0xFFFFFF00%7C"
                    + "enc:s{_jGhpaeNwrSirzAdaGupBv{BaxE|yEv~E|wOpkWvcBnlD~uFlqBlZ`wB_@xkGkxA~qBk@loBcy@juExrHznItf@jmE{z_@zsQ"
                    
                    /**
                     * Markers:
                     */
                    //+ "&markers=color:blue%7Clabel:S%7C40.702147,-74.015794"
                    + generateMarkerList()
                    + "&key=AIzaSyDo1Y6WecWV0NO3UoXKDSyn8ILVQTEetCY"
                );
        }catch (MalformedURLException e){
            System.err.println("URL is null or unknown protocol: \n" + e.getMessage());
        }
        /*
         * Set image to map of Toronto
         */
        try{
            map = ImageIO.read(url);
        }catch (IOException e){
            System.err.append("Error during reading URL: \n" + e.getMessage());
        }
        frame.setSize(1400,1000);
        JLabel background = new JLabel(new ImageIcon(map)) {
            public void paintComponent (Graphics g) {
                super.paintComponent (g);
                g.drawImage (map, 0, 0, getWidth (), getHeight (), null);
            }
        };
        frame.add(background);
        frame.setVisible(true);
        
    }
    /**
     * Generates a string consisting of a series of Google Map markers
     * @return str to be parsed by URL 
     */
    private String generateMarkerList(){
        String str = "";
        for (String[] item: markers){
            str = str + generateMarker(item[1],item[0]);
        }
        return str;
    }
    
    /**
     * Generates a single color coded marker
     * @param concern The type of concern to color code
     * @param location Where the marker will be placed
     * @return 
     */
    private String generateMarker(String location, String concern){
        String url = "&markers=" + markerStyle(concern)+location.replaceAll(" ","+");
        return url;
    }
    
    /**
     * Generates a color code for a marker
     * @param concern the concern to base the color off of
     * @return 
     */
    private String markerStyle(String concern){
        String style = "size:small%7C";
        String[][] colors = {
            {"U","red"},{"P","green"},{"C","yellow"},{"E","blue"},
            {"T","purple"},{"F","brown"},{"M","white"},{"G","gray"}};
        String color = "color:";
        for (String[] element : colors) {
            if ((""+concern.charAt(0)).equals(element[0])){
                color = color + element[1];
            }
        }
        style = style + color + "%7C";
        return style;
    }
    
}
