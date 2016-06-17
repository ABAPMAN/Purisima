/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pf_purisima;

/**
 *
 * @author LhaaR
 */
public class jc_error {
    //Data
    private int id;
    private String description;
    
    public jc_error(int id, String description){
        this.id = id;
        this.description = description;
    }
    
    public String get_description(){
        return description;
    }
    
    public int get_id(){
        return id;
    }
}
