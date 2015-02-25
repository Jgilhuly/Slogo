package model;

public class Variable {
     private Double myValue;
     
     public Variable(Double num){
         setValue(num);
     }
     
     private void setValue(Double num){
         myValue = num;
     }
     
     private Double getValue(){
         return myValue;
     }
}
