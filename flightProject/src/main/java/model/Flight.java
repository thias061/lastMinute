/*
 * Creation : 15 juin 2017
 */
package main.java.model;

import java.math.BigDecimal;

public class Flight {

    /** Origin of the flight */
    private String origin;

    /** Destination of the flight */
    private String destination;
    
    /** Number of the flight */
    private String number;
    
    /** Price of the flight */
    private BigDecimal price;
    
    /** Name of the airLine */
    private String company;
    
    
    public Flight() {

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
     * Getter number
     * 
     * @return the number
     */
    public String getNumber() {
        return number;
    }


    /**
     * Setter number
     * 
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }


    /**
     * Getter price
     * 
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }


    /**
     * Setter price
     * 
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    /**
     * Getter company
     * 
     * @return the company
     */
    public String getCompany() {
        return company;
    }


    /**
     * Setter company
     * 
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

}
