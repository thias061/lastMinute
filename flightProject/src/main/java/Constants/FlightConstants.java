/*
 * Creation : 16 juin 2017
 */
package main.java.Constants;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FlightConstants {

    /** Message indicating that there is no flight for this search*/
    public static final String NO_FLIGHT_MESSAGE = "No flight available for this search";
    
    /** Message indicating past departure date*/
    public static final String DATE_AFTER_MESSAGE = "Incorrect departure date...please put a departure date after today";
    
    /** Message indicating no price founded for flight*/
    public static final String NO_PRICE_MESSAGE = "No price founded for flight ";
    
    /** Couple code airport code/airport name*/
    public static final Map<String, String> AIRPORT = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("MAD", "Madrid");
            put("BCN", "Barcelona");
            put("LHR", "London");
            put("CDG", "Paris");
            put("FRA", "Frankfurt");
            put("IST", "Istanbul");
            put("AMS", "Amsterdam");
            put("FCO", "Rome");
            put("CPH", "Copenhagen");
        }
    });

    /** Using enum to take couple code Company/Infant price from flight number (also can be used a HashMap)*/
    public enum AirLine {

        /** The Iberia info. */
        IBERIA("IBERIA", new BigDecimal(10)),
        
        /** The British Airways info. */
        BRITISH("BRITISH AIRWAYS", new BigDecimal(15)),
        
        /** The Lufthansa info. */
        LUFTHANSA("LUFTHANSA", new BigDecimal(7)),
        
        /** The Ryanair info. */
        RYANAIR("RYANAIR", new BigDecimal(20)),
        
        /** The Turkish Airlines info. */
        TURKISH("TURKISH AIRLINES", new BigDecimal(5)),
        
        /** The Easyjet info. */
        EASYJET("EASYJET", new BigDecimal(19.90)),
        
        /** The Vueling info. */
        VUELING("VUELING", new BigDecimal(10));
        
        /** Flight name*/
        private String flightName;

        /** Infant price*/
        private BigDecimal infantPrice;
        
        /**
         * Instantiates a new document status.
         * 
         * @param code
         *            the code
         * @param label
         *            the label
         */
        AirLine(String flightName, BigDecimal infantPrice) {
            this.setFlightName(flightName);
            this.setInfantPrice(infantPrice);
        }


        /**
         * Getter flightCode
         * 
         * @return the flightCode
         */
        public String getFlightName() {
            return flightName;
        }

        /**
         * Setter flightCode
         * 
         * @param flightCode the flightCode to set
         */
        public void setFlightName(String flightName) {
            this.flightName = flightName;
        }


        /**
         * Getter infantPrice
         * 
         * @return the infantPrice
         */
        public BigDecimal getInfantPrice() {
            return infantPrice;
        }


        /**
         * Setter infantPrice
         * 
         * @param infantPrice the infantPrice to set
         */
        public void setInfantPrice(BigDecimal infantPrice) {
            this.infantPrice = infantPrice;
        }

    }

    /**
     * Getting airline from flight number
     * @param flightNumber
     * @return
     */
    public static AirLine getAirLineInfo(String flightNumber) {
        if(flightNumber.startsWith("IB"))
            return AirLine.IBERIA;
        else if(flightNumber.startsWith("BA"))
            return AirLine.BRITISH;
        else if(flightNumber.startsWith("TK"))
            return AirLine.TURKISH;
        else if(flightNumber.startsWith("LH"))
            return AirLine.LUFTHANSA;
        else if(flightNumber.startsWith("FR"))
            return AirLine.RYANAIR;
        else if(flightNumber.startsWith("VY"))
            return AirLine.VUELING;
        else if(flightNumber.startsWith("U2"))
            return AirLine.EASYJET;
        return null;
    }
}
