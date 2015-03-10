package cc.playmc.lilypadcompass;

public class ExchangeRate {
	 
    private double getRate(String from, String to) {
       
        return 100;
    }
 
    public double getEuroUsdRate() {
        return getRate("EUR", "USD");
    }
 
    public double getGoldEuroRate() {
        return getRate("XAU", "EUR");
    }
 
    public double getSilverEuroRate() {
        return getRate("XAG", "EUR");
    }
 
    public static void main(String args[]) {
        ExchangeRate exchange = new ExchangeRate();
 
        double rateSilver = exchange.getSilverEuroRate();
        System.out.println("Silver/Euro: " + rateSilver);
 
        double rateGold = exchange.getGoldEuroRate();
        System.out.println("Gold/Euro: " + rateGold);
 
        double rateUsd = exchange.getEuroUsdRate();
        System.out.println("Euro/USD: " + rateUsd);
    }
}