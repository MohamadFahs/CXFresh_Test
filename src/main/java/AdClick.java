import java.util.Date;

/**
 * AdClick class represents a click on the ad that is defined by different attributes
 * Date date will be automatically set using the java.util.Date library of the current time of adding the click
 * The country field will be passed as required field to build the click
 */
public class AdClick {
    //Required Fields
    private String country;

    //Optional Fields
    private Date date;
    private int userId;
    private int adId;


    /**
     * * The AdClick constructor (Set to private To use builder DP)
     * * it takes an instance of the AdClick builder nested class.
     * @param builder
     */
    private AdClick(AdClickBuilder builder){
        this.country = builder.country;
        this.date = builder.date;
        this.adId = builder.adId;
        this.userId = builder.userId;
    }

    /**
     * Getter Methods that gets all class fields (adId, UserId, country, date)
     * No setters to provide immutability
     * @return
     */
    public int getAdId() {
        return adId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCountry() {
        return country;
    }

    public Date getDate() {
        return date;
    }

    /**
     * The AdClick class is complex class that has many fields (required and optional)
     * => we used the Builder DP where we can construct an object step by step without filling all fields
     *    the object is created step by step and the final step (build) will return the object
     */
    public static class AdClickBuilder{
        private String country;
        private int userId;
        private int adId;
        private java.util.Date date;

        public AdClickBuilder(String country){
            this.country = country;
            this.date = new Date();
        }

        public AdClickBuilder adId(int id){
            this.adId = id;
            return this;
        }

        public AdClickBuilder userId(int id){
            this.userId = id;
            return this;
        }

        /**
         * Return the final constructed AdClick
         * @return
         */
        public AdClick build(){
            AdClick adClick = new AdClick(this);
            return adClick;
        }

    }
}
