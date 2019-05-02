/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Capteurs;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class Horodateur {
    private LocalDateTime date;

    public Horodateur() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        this.date = LocalDateTime.now();  
    }
    
    public LocalDateTime getDate() {
        return date.now();
    }
    
    
}
