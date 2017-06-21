package test;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import main.java.FlightSearch;
import main.java.model.Flight;
import main.java.model.Search;

import org.junit.Assert;
import org.junit.Test;

/*
 * Creation : 19 juin 2017
 * Mathias Pagiusco
 */

public class FlightSearchTest {

    private static final String COPENHAGUE = "CPH";
    private static final String PARIS = "CDG";
    private static final String ISTAMBUL = "IST";
    private static final String LONDON = "LHR";
    private static final String FRANKFURT = "FRA";
    private static final String AMSTERDAM = "AMS";
    private static final String MADRID = "MAD";
    private static final String BARCELONA = "BCN";
    private static final String ROME = "FCO";
    private Search search;
    
    /**
     * Amsterdan to Frankfurt
     * 1 Adult
     * 31 days to departure
     */
    @Test
    public void test1AdultAMSToFRA() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 31 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 31);

        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(AMSTERDAM);
        search.setDestination(FRANKFURT);
        search.setAdultNumber(new Integer(1));
        search.setChildNumber(new Integer(0));
        search.setInfantNumber(new Integer(0));

        List<Flight> results = FlightSearch.main(search);

        for(Flight result : results)
        {
            BigDecimal expectedPrice = null;
            BigDecimal price = result.getPrice();
            
            if (result.getNumber().equals("TK2372")) {
                expectedPrice = new BigDecimal(157.60).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("TK2659")) {
                expectedPrice = new BigDecimal(198.40).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("LH5909")) {
                expectedPrice = new BigDecimal(90.40).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            
            Assert.assertTrue(price.compareTo(expectedPrice) == 0);
        }
    }
    
    /**
     * London to Istambul
     * 2 Adult
     * 1 Child
     * 1 Infant
     * 15 days to departure
     */
    @Test
    public void test2Adult1Child1InfantLHRToIST() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 15 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 15);

        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(LONDON);
        search.setDestination(ISTAMBUL);
        search.setAdultNumber(new Integer(2));
        search.setChildNumber(new Integer(1));
        search.setInfantNumber(new Integer(1));

        List<Flight> results = FlightSearch.main(search);

        for(Flight result : results)
        {
            BigDecimal expectedPrice = null;
            BigDecimal price = result.getPrice();
            
            if (result.getNumber().equals("TK8891")) {
                expectedPrice = new BigDecimal(806).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("LH1085")) {
                expectedPrice = new BigDecimal(481.19).setScale(2, BigDecimal.ROUND_HALF_UP);
            } 
            
            Assert.assertTrue(price.compareTo(expectedPrice) == 0);
        }
    }
    
    /**
     * Barcelona to Madrid
     * 1 Adult
     * 2Child
     * 2 days to departure
     */
    @Test
    public void test1Adult2ChildrenBCNToMAD() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 2 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 2);

        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(BARCELONA);
        search.setDestination(MADRID);
        search.setAdultNumber(new Integer(1));
        search.setChildNumber(new Integer(2));
        search.setInfantNumber(new Integer(0));

        List<Flight> results = FlightSearch.main(search);

        for(Flight result : results)
        {
            BigDecimal expectedPrice = null;
            BigDecimal price = result.getPrice();
            
            if (result.getNumber().equals("IB2171")) {
                expectedPrice = new BigDecimal(909.09).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("LH5496")) {
                expectedPrice = new BigDecimal(1028.43).setScale(2, BigDecimal.ROUND_HALF_UP);
            } 
            
            Assert.assertTrue(price.compareTo(expectedPrice) == 0);
        }
    }
    
    /**
     * Paris to Frankfurt
     * 1 Adult
     * 31 days to departure
     */
    @Test
    public void test1AdultCDGToFRA() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 31 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 31);

        //Searching from AMS to FRA with 1 adult
        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(PARIS);
        search.setDestination(FRANKFURT);
        search.setAdultNumber(new Integer(1));
        search.setChildNumber(new Integer(0));
        search.setInfantNumber(new Integer(0));

        List<Flight> results = FlightSearch.main(search);
        
        //No flight available
        Assert.assertTrue(results.isEmpty());
    }
    
    /**
     * Copenhague to Barcelona
     * 1 Adult
     * 1 Child
     * 3 days to departure
     * U22593 = 436.87
     * FR7949 = 352.70
     */
    @Test
    public void test1Adult1ChildCPHToBCN() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 2 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 3);

        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(COPENHAGUE);
        search.setDestination(BARCELONA);
        search.setAdultNumber(new Integer(1));
        search.setChildNumber(new Integer(1));
        search.setInfantNumber(new Integer(0));

        List<Flight> results = FlightSearch.main(search);

        for(Flight result : results)
        {
            BigDecimal expectedPrice = null;
            BigDecimal price = result.getPrice();
            
            if (result.getNumber().equals("U22593")) {
            	//(218 * 120%) + 67% * (218 * 120%)
                expectedPrice = new BigDecimal(436.87).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("FR7949")) {
            	//(176 * 120%) + 67% * (176 * 120%)
                expectedPrice = new BigDecimal(352.70).setScale(2, BigDecimal.ROUND_HALF_UP);
            } 
            
            Assert.assertTrue(price.compareTo(expectedPrice) == 0);
        }
    }
    
    /**
     * Istambul to Madrid
     * 1 Adult
     * 1 Infant
     * 10 days to departure
     * IB8688  = 190
     * LH1115  = 199
     */
    @Test
    public void test1Adult1InfantISTToMAD() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 2 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 10);

        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(ISTAMBUL);
        search.setDestination(MADRID);
        search.setAdultNumber(new Integer(1));
        search.setChildNumber(new Integer(0));
        search.setInfantNumber(new Integer(1));

        List<Flight> results = FlightSearch.main(search);

        for(Flight result : results)
        {
            BigDecimal expectedPrice = null;
            BigDecimal price = result.getPrice();
            
            if (result.getNumber().equals("IB8688")) {
            	//(150 * 120%) + 10
                expectedPrice = new BigDecimal(190).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("LH1115")) {
            	//(160 * 120%) + 7
                expectedPrice = new BigDecimal(199).setScale(2, BigDecimal.ROUND_HALF_UP);
            } 
            
            Assert.assertTrue(price.compareTo(expectedPrice) == 0);
        }
    }
    
    /**
     * Rome to Paris
     * 1 Adult
     * 1 Child
     * 1 Infant
     * 16 days to departure
     * VY4335  = 273.86
     * VY2974  = 195.37
     */
    @Test
    public void test1Adult1Child1InfantFCOToCDG() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 2 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 16);

        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(ROME);
        search.setDestination(PARIS);
        search.setAdultNumber(new Integer(1));
        search.setChildNumber(new Integer(1));
        search.setInfantNumber(new Integer(1));

        List<Flight> results = FlightSearch.main(search);

        for(Flight result : results)
        {
            BigDecimal expectedPrice = null;
            BigDecimal price = result.getPrice();
            
            if (result.getNumber().equals("VY4335")) {
            	//(158 * 100%) + 67% * (158) + 10
                expectedPrice = new BigDecimal(273.86).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("VY2974")) {
            	//(111 * 100%) + 67% * (111) + 10
                expectedPrice = new BigDecimal(195.37).setScale(2, BigDecimal.ROUND_HALF_UP);
            } 
            
            Assert.assertTrue(price.compareTo(expectedPrice) == 0);
        }
    }
    
    /**
     * Frankfurt to London
     * 2 Adults
     * 1 Child
     * 1 Infant
     * 20 days to departure
     * 
     * BA8162  = 562.35
     * IB9443  = 688.18
     * TK3167  = 320.06
     */
    @Test
    public void test2Adults1Child1InfantFRAToLHR() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 2 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 20);

        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(FRANKFURT);
        search.setDestination(LONDON);
        search.setAdultNumber(new Integer(2));
        search.setChildNumber(new Integer(1));
        search.setInfantNumber(new Integer(1));

        List<Flight> results = FlightSearch.main(search);

        for(Flight result : results)
        {
            BigDecimal expectedPrice = null;
            BigDecimal price = result.getPrice();
            
            if (result.getNumber().equals("BA8162")) {
            	//2 * (205 * 100%) + (67% * 205) + 15
                expectedPrice = new BigDecimal(562.35).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("IB9443")) {
            	//2 * (254 * 100%) + (67% * 254) + 10
                expectedPrice = new BigDecimal(688.18).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("TK3167")) {
            	//2 * (118 * 100%) + (67% * 118) + 5
                expectedPrice = new BigDecimal(320.06).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            
            Assert.assertTrue(price.compareTo(expectedPrice) == 0);
        }
    }
    
    /**
     * London to Amsterdam
     * 3 Adults
     * 2 Infants
     * 30 days to departure
     * 
     * IB4737  = 350
     * BA9561  = 789
     * BA6657  = 396
     */
    @Test
    public void test3Adults1InfantLHRToAMS() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 2 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 30);

        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(LONDON);
        search.setDestination(AMSTERDAM);
        search.setAdultNumber(new Integer(3));
        search.setChildNumber(new Integer(0));
        search.setInfantNumber(new Integer(2));

        List<Flight> results = FlightSearch.main(search);

        for(Flight result : results)
        {
            BigDecimal expectedPrice = null;
            BigDecimal price = result.getPrice();
            
            if (result.getNumber().equals("IB4737")) {
            	//3 * (110 * 100%) + (2 * 10)
                expectedPrice = new BigDecimal(350).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("BA9561")) {
            	//3 * (253 * 100%) + (2 * 15)
                expectedPrice = new BigDecimal(789).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if (result.getNumber().equals("BA6657")) {
            	//3 * (122 * 100%) + (2 * 15)
                expectedPrice = new BigDecimal(396).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            
            Assert.assertTrue(price.compareTo(expectedPrice) == 0);
        }
    }
    
    /**
     * Madrid to Frankfurt
     * 1 Adult
     * 2 Children
     * 1 Infant
     * 60 days to departure
     * 
     * BA8982 = 335.11
     */
    @Test
    public void test1Adult2Children1InfantMADToFRA() {
        
        search = new Search();
        Date now = new Date();
        //Setting departure at 2 from now
        Calendar departure = new GregorianCalendar();
        departure.setTime(now);
        departure.add(Calendar.DAY_OF_YEAR, 60);

        search.setSearchingDate(now);
        search.setDeparture(departure.getTime());

        search.setOrigin(MADRID);
        search.setDestination(FRANKFURT);
        search.setAdultNumber(new Integer(1));
        search.setChildNumber(new Integer(2));
        search.setInfantNumber(new Integer(1));

        List<Flight> results = FlightSearch.main(search);

        for(Flight result : results)
        {
            BigDecimal expectedPrice = null;
            BigDecimal price = result.getPrice();
            
            if (result.getNumber().equals("BA8982")) {
            	//(171 * 80%) + 2 * 67% * (171 * 80%) + 15
                expectedPrice = new BigDecimal(335.11).setScale(2, BigDecimal.ROUND_HALF_UP);
            } 
            
            Assert.assertTrue(price.compareTo(expectedPrice) == 0);
        }
    }
}
