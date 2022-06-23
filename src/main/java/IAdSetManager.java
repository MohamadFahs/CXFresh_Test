
import java.util.HashMap;
import java.util.Map;

/**
 * IAdSetManager is an interface that have 3 main methods with different functionality
 * Method 1 CheckIfAdSetIsActive : is used to check if the status of an adSet is Active or not
 * Method 2 UpdateAdSetBudget : used to add clicks to the Ad and Edit the Remaining budget and Status of the Targeted AdSet
 * Method 3 RetrieveAdSetPerformancePerCountry : used to return an array of class AdSetAnalysis where the AdClicks are grouped by country
 *          it also indicates the number of clicks and total price in each country
 *
 *   Remark: the methods are defined as static method => we can use the methods and pass the arguments without creating an instance.
 */

public interface IAdSetManager {
    /**
     * This function test the Status of an AdSet if the Status is Active => return true else return false
     * @param adSet
     * @return
     */
    public static boolean CheckIfAdSetIsActive(AdSet adSet){
        return (adSet.getStatus().toString().equals("Active"));
    }

    /**
     *1-  At first this function check if the Status if the AdSet is active or not => if active it continuous it work else it throws an Completed exception
     *2-  if we passed the exception the second step is to check if remaining budget is 0 => if so we edit the status t0 Completed and return
     *3-  IF we passed the exception and the remaining budget is available we start iterating and adding the clicks to the add
     *  => we decrement the remaining budget according to the CPC
     *      -> as the budget is not 0 we continue adding clicks
     *      -> if the budget is 0 we stop iterating (break)
     * @param adSet
     * @param adClicks
     */
    public static void UpdateAdSetBudget(AdSet adSet, AdClick [] adClicks){
        //1- check the exception
        try {
            if(!CheckIfAdSetIsActive(adSet))
                throw new CompletedException();
        } catch (CompletedException e) {
            e.printStackTrace();
        }

        //2- check if budget =0 and change the State
        if(adSet.getRemainingBudget() == 0) {
            adSet.setStatus("Completed");
            return;
        }

        adSet.Ads.add(new Ad.AdBuilder(adSet).build());
        Ad ad = adSet.Ads.get(adSet.Ads.size()-1);

        for(int i=0 ; i<adClicks.length ; i++){
            if(adSet.getRemainingBudget() == 0)
                break;
            ad.clicks.add(adClicks[i]);
            adSet.setRemainingBudget(adSet.getRemainingBudget()- adSet.getCostPerClick());
        }
    }

    /**
     * -> we initialize a Map with key a string (the country) and value Integer (number of clicks)
     * we iterate through the Ads in each ad we also iterate through the clicks array list
     *  -> check if the country of the click is available in the map
     *      => if the country is not available we add it to the map and set value = 1
     *      => if the country is available we increment the value of this country in the map by 1
     * -> we create an array of AdSetAnalysis class with the same size of the map
     *    we iterate on each key and initialize the fields of the object and calculate the overall price
     * -> we finally return the array of AdSetAnalysis.
     * @param adSet
     * @return
     */
    public static AdSetAnalysis[] RetrieveAdSetPerformancePerCountry(AdSet adSet){
        Map<String, Integer> map = new HashMap<>();

        for(int i=0 ; i<adSet.Ads.size() ; i++){
            Ad ad = adSet.Ads.get(i);
            for(int j=0 ; j<ad.clicks.size() ; j++){
                if(map.containsKey(ad.clicks.get(j).getCountry()))
                    map.put(ad.clicks.get(j).getCountry(), map.get(ad.clicks.get(j).getCountry())+1);
                else
                    map.put(ad.clicks.get(j).getCountry(),1);
            }
        }

        AdSetAnalysis [] adSetAnalysis = new AdSetAnalysis[map.size()];
        int i=0;
        for(Map.Entry<String, Integer> country  : map.entrySet()){
            adSetAnalysis[i++] = new AdSetAnalysis(country.getKey(), country.getValue(), country.getValue()*adSet.getCostPerClick());
        }
        return adSetAnalysis;
    }
}
