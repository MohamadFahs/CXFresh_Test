import java.util.ArrayList;

/**
 * Ad is a class that represents an ad with its fields
 * the required fields is the AdSet field where each Created Ad must belong to specific Adset
 * the other fields (id, title, imageLink, url) are optional
 * An array List clicks is used to represent the clicks on each ad
 */

public class Ad {

    //Required Fields
    private AdSet adset;

    //Optional Fields
    private int id;
    private String title;
    private String imageLink;
    private String url;

    ArrayList<AdClick> clicks = new ArrayList<>();

    /**
     * The Ad constructor (Set to private To use builder DP)
     * it takes an instance of the Ad builder nested class.
     * @param builder
     */
    private Ad(AdBuilder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.url = builder.url;
        this.imageLink = builder.imageLink;
        this.adset = builder.adset;
    }

    /**
     * Getters Method that gets all class fields (id, title, imageLink, Url and AdSet)
     * NO setters to provide immutability
     * @return
     */
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getUrl() {
        return url;
    }

    public AdSet getAdSet() {
        return adset;
    }

    /**
     * The Ad class is complex class that has many fields (required and optional)
     * => we used the Builder DP where we can construct an object step by step without filling all fields
     *    the object is created step by step and the final step (build) will return the object
     */
    public static class AdBuilder{
        private int id;
        private String title;
        private String imageLink;
        private String url;
        private AdSet adset;

        public AdBuilder(AdSet adset){
            this.adset = adset;
        }

        public AdBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public AdBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public AdBuilder setImageLink(String imageLink) {
            this.imageLink = imageLink;
            return this;
        }

        public AdBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * Return the final constructed Ad
         * @return
         */
        public Ad build(){
            Ad ad = new Ad(this);
            return ad;
        }
    }
}
