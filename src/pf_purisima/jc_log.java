/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pf_purisima;
import java.util.ArrayList;
/**
 *
 * @author LhaaR
 */
public class jc_log {
    //Data
    private ArrayList<jc_error> log;
    private int idx;
    
    
    public jc_log(){
        log = new ArrayList();
        idx = 0;
    }
    
    public void add(jc_error error){
        log.add(error);
    }
    
    public jc_error get_next(){
        jc_error error;
        if (log.isEmpty()){
            return null;
        }
        else{
            error = log.get(idx);
            idx++;
            return error;
        }
    }
    
    public void clear_data(){
        log.clear();
        idx = 0;
    }
}
