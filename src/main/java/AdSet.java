import java.util.ArrayList;

/**
 * AdSet class represents a group of Ads that share settings like title, platform, budget, CPC ...etc
 * Ads are represented with Array list of Ad class
 * As long as AdSet remaining budget is greater than zero ads and clicks can be added
 * the status represent if the adSet is active and can handle more clicks if not => state become completed
 */
public class AdSet {

    //Enum Creation
    enum Platform {
        ios, android, desktop
    };
    enum Status {
        Active, Completed
    }

    //Optional Fields
    private int id;
    private String title;
    private Platform platform;
    private int remainingBudget;

    //Required Fields
    private int budget;
    private int costPerClick;
    private Status status;

    ArrayList<Ad> Ads = new ArrayList<>();

    /**
     * The AdSet constructor (Set to private To use builder DP)
     * it takes an instance of the AdSet builder nested class.
     * @param builder
     */
    private AdSet(AdSetBuilder builder){
        this.id = builder.id;
        this.budget = builder.budget;
        this.costPerClick = builder.costPerClick;
        this.remainingBudget = builder.remainingBudget;
        this.status = builder.status;
        this.platform = builder.platform;
        this.title = builder.title;
    }

    /**
     * Getter Methods that gets all class Fields (id, title, budget, remaining_budget, CPC, status, platform and remaining budget)
     * NO setters to provide immutability
     * @return
     */
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getBudget() {
        return budget;
    }

    public int getRemainingBudget() {
        return remainingBudget;
    }

    public int getCostPerClick() {
        return costPerClick;
    }

    public Status getStatus() {
        return status;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);;
    }

    public void setRemainingBudget(int remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    /**
     * The AdSet class is complex class that has many fields (required and optional)
     * => we used the Builder DP where we can construct an object step by step without filling all fields
     *    the object is created step by step and the final step (build) will return the object
     */
    public static class AdSetBuilder{
        private int id;
        private int budget;
        private int costPerClick;
        private int remainingBudget;
        private String title;
        private Platform platform;
        private Status status;

        public AdSetBuilder(int budget, int costPerClick){
            this.budget = budget;
            this.remainingBudget = budget;
            this.costPerClick = costPerClick;
            this.status = Status.Active;
        }

        public AdSetBuilder id(int id){
            this.id = id;
            return this;
        }

        public AdSetBuilder remainingBudget(int remainingBudget){
            this.remainingBudget = remainingBudget;
            return this;
        }

        public AdSetBuilder title(String title){
            this.title = title;
            return this;
        }

        public AdSetBuilder platform(String platform){
            this.platform = Platform.valueOf(platform);
            return this;
        }

        /**
         * Return the final constructed AdSet.
         * @return
         */
        public AdSet build(){
            AdSet adSet = new AdSet(this);
            return adSet;
        }
    }
}
