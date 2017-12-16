/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lottery;

/**
 *
 * @author PHIVH
 */
public class LocationPair {
    private String first;
    private String second;
    private String number;
    
    public LocationPair() {
    }

    public LocationPair(String first, String second, String number) {
        this.first = first;
        this.second = second;
        this.number = number;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
}
