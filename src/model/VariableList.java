package model;

import java.util.HashMap;
import java.util.Map;

public class VariableList {
    private Map<String, Double> vList;
    
    public VariableList(){
        vList = new HashMap<String,Double>();
    }
    
    public boolean contains (String name) {
        return vList.containsKey(name);
    }

    public void addVariable(String name) {
        if(!contains(name)){
            vList.put(name, null);
        }
    }
    
    public void setValue(String variable, Double value){
        vList.put(variable, value);
    }
    
    

}
