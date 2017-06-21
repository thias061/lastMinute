/*
 * Creation : 15 juin 2017
 */
/**
 * 
 */
package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.constants.FlightConstants;
import main.java.constants.FlightConstants.AirLine;
import main.java.model.Flight;
import main.java.model.Search;

/**
 * TODO : Description
 * 
 * @author mPagiusco
 *
 */
public class FlightSearch {

    public static final String SEPARATOR = ",";
    public static final long MILLISECS_PER_DAY = 86400000;
    
    /**
     * @param args
     */
    public static List<Flight> main(Search search) {
        
        ArrayList<Flight> flightResults = new ArrayList<Flight>();

        try {

            System.out.println("Searching for flight from " + search.getOrigin() + " to " 
                    + search.getDestination() + " on " + search.getDeparture()
                    + "...");

            ArrayList<Flight> flightList = searchingFlights(search);
            
            if(flightList != null && !flightList.isEmpty())
            {
                for(Flight flight : flightList)
                {
                    Date departureDate = search.getDeparture();
                    Date searchingDate = search.getSearchingDate();
                    
                    if (departureDate != null && searchingDate != null) {
                        // Calculating days difference between departure date and today
                        long daysNumber = (departureDate.getTime() - searchingDate.getTime()) / MILLISECS_PER_DAY;
                        // Calculating adult price with days difference
                        BigDecimal adultPrice = calculateAdultPriceByDate(flight.getPrice(), daysNumber);
                        //Getting company name and infant price from airline
                        AirLine airLineInfo = FlightConstants.getAirLineInfo(flight.getNumber());
                        //Getting origin airport name from constants
                        String originAirport = FlightConstants.AIRPORT.get(flight.getOrigin());
                        //Getting destination airport name from constants
                        String destinationAirport = FlightConstants.AIRPORT.get(flight.getDestination());
                        if (adultPrice != null) {
                            BigDecimal finalPrice = new BigDecimal(0);
                            Flight flightResult = new Flight();

                            Integer adultNumber = search.getAdultNumber();
                            Integer childNumber = search.getChildNumber();
                            Integer infantNumber = search.getInfantNumber();

                            if (adultNumber != null) {
                                //full price (i.e. price resulting from the *days to departure date* rule) 
                                finalPrice = finalPrice.add(adultPrice.multiply(new BigDecimal(adultNumber)));
                            }

                            if (childNumber != null) {
                                //33% discount of the price calculated according to the *days to departure date* rule  
                                BigDecimal childPrice = adultPrice.multiply(new BigDecimal(67).divide(new BigDecimal(100)));
                                finalPrice = finalPrice.add(childPrice.multiply(new BigDecimal(childNumber)));
                            }

                            if (infantNumber != null) {
                                //fixed price depending on the airline. Rule *days to departure date* is not applied for infants
                                BigDecimal infantPrice = airLineInfo.getInfantPrice();
                                finalPrice = finalPrice.add(infantPrice.multiply(new BigDecimal(infantNumber)));
                            }
                            //Getting flight name from enum
                            flightResult.setCompany(airLineInfo.getFlightName());
                            flightResult.setNumber(flight.getNumber());
                            flightResult.setOrigin(originAirport);
                            flightResult.setDestination(destinationAirport);

                            //Rounding finalPrice with 2 decimals
                            finalPrice = finalPrice.setScale(2, BigDecimal.ROUND_FLOOR);
                            flightResult.setPrice(finalPrice);
                            flightResults.add(flightResult);
                            
                        } else {
                            System.out.println(FlightConstants.NO_PRICE_MESSAGE + flight.getNumber());
                        }
                    }
                    else {
                        System.out.println(FlightConstants.NO_FLIGHT_MESSAGE);
                    }
                }
            } else {
                System.out.println(FlightConstants.NO_FLIGHT_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flightResults;
    }

    /**
     * Calculating adultPrice with departure days
     * @param price
     * @param daysNumber
     * @return
     */
    private static BigDecimal calculateAdultPriceByDate(BigDecimal price, long daysNumber) {
        BigDecimal calculatedPrice = null;
        // more than 30 -> 80%
        if (daysNumber > 30) {
            calculatedPrice = price.multiply(new BigDecimal(80).divide(new BigDecimal(100)));
        }
        // 30 - 16 -> 100%
        else if (daysNumber >= 16 && daysNumber <= 30) {
            calculatedPrice = price.multiply(new BigDecimal(100).divide(new BigDecimal(100)));
        }
        // 15 - 3 -> 120%
        else if (daysNumber >= 3 && daysNumber <= 15) {
            calculatedPrice = price.multiply(new BigDecimal(120).divide(new BigDecimal(100)));
        }
        // less that 3 -> 150%
        else if (daysNumber <= 3) {
            // if departure day is today or after
            if (daysNumber >= 0) {
                calculatedPrice = price.multiply(new BigDecimal(150).divide(new BigDecimal(100)));
            }
            // past departure date
            else {
                System.out.println(FlightConstants.DATE_AFTER_MESSAGE);
            }
        }
        return calculatedPrice;
    }
    
    /**
     * Searching flights from flightList
     * @param search
     * @return
     * @throws NumberFormatException
     * @throws IOException
     */
    private static ArrayList<Flight> searchingFlights(Search search) throws NumberFormatException, IOException {
        
        ArrayList<Flight> flightList;
        ArrayList<Flight> result = new ArrayList<Flight>();
        //Getting flight list from csv
        flightList = getFlightListFromCSV("flights.csv");
        
        for(Flight flight : flightList)
        {
            boolean existsFlight = flight.getOrigin().equals(search.getOrigin()) 
                    && flight.getDestination().equals(search.getDestination());
            
            if (existsFlight) {
                result.add(flight);
            }
        }
        
        return result;
    }


    /**
     * Method building flights list from csv
     * @param line
     * @return
     * @throws IOException 
     * @throws NumberFormatException 
     */
    private static ArrayList<Flight> getFlightListFromCSV(String fileInput) 
            throws IOException {
        
        ArrayList<Flight> list = new ArrayList<Flight>();
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(fileInput));
        
        String line;
        
        while ((line = br.readLine()) != null) {
            //If there is some non numeric price in the csv, 
            //we don't load it into the list 
            try {
                String[] fields = line.split(SEPARATOR);
                Flight flight = new Flight();
                flight.setOrigin(fields[0]);
                flight.setDestination(fields[1]);
                flight.setNumber(fields[2]);
                flight.setPrice(new BigDecimal(fields[3]));
                list.add(flight);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        br.close();
        return list;
    }

}
