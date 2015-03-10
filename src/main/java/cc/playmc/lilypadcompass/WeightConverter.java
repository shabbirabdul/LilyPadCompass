package cc.playmc.lilypadcompass;

public class WeightConverter {

	 private int getPoundToKg(double pound) {
	      
	        double kg = pound * 0.45359237;
	        return (int)Math.floor(kg);
	    }
	 
	    private double getPoundToGrams(double pound) {
	      
	        double kg = pound * 0.45359237;
	        return (kg - getPoundToKg(pound)) * 1000;
	    }
	 
	    private int getOunceToKg(double ounce) {
	      
	        double kg = ounce * 0.0283495231;
	        return (int)Math.floor(kg);
	    }
	 
	    private double getOunceToGrams(double ounce) {
	      
	        double kg = ounce * 0.0283495231;
	        return (kg - getOunceToKg(ounce)) * 1000;
	    }
}
