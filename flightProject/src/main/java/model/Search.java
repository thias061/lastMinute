/*
 * Creation : 16 juin 2017
 */
package main.java.model;

import java.util.Date;

public class Search {
    
    /** Origin of the flight */
    private String origin;

    /** Destination of the flight */
    private String destination;
    
    /** Departure of the flight */
    private Date departure;
    
    /** Departure of the flight */
    private Date searchingDate;
    
    /** Number of adults */
    private Integer adultNumber;
    
    /** Number of child */
    private Integer childNumber;
    
    /** Number of infant */
    private Integer infantNumber;

    public Search() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Getter origin
     * 
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Setter origin
     * 
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Getter destination
     * 
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Setter destination
     * 
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Getter departure
     * 
     * @return the departure
     */
    public Date getDeparture() {
        return departure;
    }

    /**
     * Setter departure
     * 
     * @param departure the departure to set
     */
    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    /**
     * Getter adultNumber
     * 
     * @return the adultNumber
     */
    public Integer getAdultNumber() {
        return adultNumber;
    }

    /**
     * Setter adultNumber
     * 
     * @param adultNumber the adultNumber to set
     */
    public void setAdultNumber(Integer adultNumber) {
        this.adultNumber = adultNumber;
    }

    /**
     * Getter childNumber
     * 
     * @return the childNumber
     */
    public Integer getChildNumber() {
        return childNumber;
    }

    /**
     * Setter childNumber
     * 
     * @param childNumber the childNumber to set
     */
    public void setChildNumber(Integer childNumber) {
        this.childNumber = childNumber;
    }

    /**
     * Getter infantNumber
     * 
     * @return the infantNumber
     */
    public Integer getInfantNumber() {
        return infantNumber;
    }

    /**
     * Setter infantNumber
     * 
     * @param infantNumber the infantNumber to set
     */
    public void setInfantNumber(Integer infantNumber) {
        this.infantNumber = infantNumber;
    }

    /**
     * Getter searchingDate
     * 
     * @return the searchingDate
     */
    public Date getSearchingDate() {
        return searchingDate;
    }

    /**
     * Setter searchingDate
     * 
     * @param searchingDate the searchingDate to set
     */
    public void setSearchingDate(Date searchingDate) {
        this.searchingDate = searchingDate;
    }

}
