/**
 * AdSetAnalysis class is a class that have 3 fields Country of the clicks, number of clicks and the price of the clicks
 * it is used in the RetrieveAdSetPerformancePerCountry function.
 */
public class AdSetAnalysis {
    /**
     * Fields are set to private to provide Encapsulation and  immutability
     */
    private String country;
    private int clicks;
    private int price;

    /**
     * Regular constructor of the class
     * @param country
     * @param clicks
     * @param price
     */
    public AdSetAnalysis(String country, int clicks, int price){
        this.country = country;
        this.clicks = clicks;
        this.price = price;
    }

    /**
     * Getters of the class
     * @return
     */
    public String getCountry() {
        return country;
    }

    public int getClicks() {
        return clicks;
    }

    public int getPrice() {
        return price;
    }

    /**
     * In case we want to print the result of the AdSetAnalysis in specific form we override the toString function
     * @return
     */
    @Override
    public String toString() {
        return " {" + "Country:" + country + ", Clicks:" + clicks + ", Price:" + price + "$ }";
    }
}
