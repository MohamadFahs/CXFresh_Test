import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.Assert.*;

/**
 * JUnit Unit Test class that test the implemented IAdSetManager static methods.
 */

public class ValidateAdSetBudget {

    /**
     * Test 1: will test the remaining budget, Status and the output of the AdSet retrieve grouped by country
     *     the budget = $500, CPC = $1 => 100 clicks from lebanon, 200 clicks from UAE
     *    Expected output:
     *   Status = Active , Remaining Budget = $200, Retrieve  {Country:Lebanon, Clicks:100, Price:100$ } {Country:UAE, Clicks:200, Price:200$ }
     */
    @Test
    public void UpdateAdSetBudget100L200UAE() {
        /**
         * adSet object building using the Builder DP
         */
        AdSet adSet = new AdSet.AdSetBuilder(500, 1).build();

        /**
         * Adding a new Ad to the adSet
         */
        adSet.Ads.add(new Ad.AdBuilder(adSet).build());

        /**
         * Creating the array of clicks that will be used to update the budget of the adSet variable
         * with 100 Lebanese clicks and 200 UAE
         */
        AdClick[] adClicks = new AdClick[300];
        for (int i = 0; i < 100; i++)
            adClicks[i] = new AdClick.AdClickBuilder("Lebanon").build();
        for (int i = 100; i < 300; i++)
            adClicks[i] = new AdClick.AdClickBuilder("UAE").build();

        /**
         * Adding clicks and updating the AdSet budget using the Manager interface
         */
        IAdSetManager.UpdateAdSetBudget(adSet, adClicks);

        /**
         * Assert that the AdSet Status is Active, RemainingBudget is 200$.
         */
        assertEquals("Active", adSet.getStatus().toString());
        assertEquals(200, adSet.getRemainingBudget());

        /**
         * Assert that the AdSet Analysis is valid (100 for Lebanon,200 for UAE).
         */
        String A = " {Country:Lebanon, Clicks:100, Price:100$ } {Country:UAE, Clicks:200, Price:200$ }";
        AdSetAnalysis[] adSetAnalysis = IAdSetManager.RetrieveAdSetPerformancePerCountry(adSet);

        assertTrue((adSetAnalysis[0].toString() + adSetAnalysis[1].toString()).equals(A));
    }

    /**
     *  Test 2: will test the remaining budget, Status and the output of the AdSet retrieve grouped by country
     *  the budget = $500, CPC = $1 => 200 clicks from lebanon, 300 clicks from UAE
     *  Expected output:
     *  Status = Active , Remaining Budget = 0, Retrieve  {Country:Lebanon, Clicks:200, Price:200$ } {Country:UAE, Clicks:300, Price: 300$ }
     */
    @Test
    public void UpdateAdSetBudget200L300UAE() {
        /**
         * adSet object building using the Builder DP
         */
        AdSet adSet = new AdSet.AdSetBuilder(500, 1).build();

        /**
         * Adding a new Ad to the adSet
         */
        adSet.Ads.add(new Ad.AdBuilder(adSet).build());

        /**
         * Creating the array of clicks that will be used to update the budget of the adSet variable
         * with 200 Lebanese clicks and 300 UAE
         */
        AdClick[] adClicks = new AdClick[500];
        for (int i = 0; i < 200; i++)
            adClicks[i] = new AdClick.AdClickBuilder("Lebanon").build();
        for (int i = 200; i < 500 ; i++)
            adClicks[i] = new AdClick.AdClickBuilder("UAE").build();

        /**
         * Adding clicks and updating the AdSet budget using the Manager interface
         */
        IAdSetManager.UpdateAdSetBudget(adSet, adClicks);

        /**
         * Assert that the AdSet Status is Active, RemainingBudget is 0$.
         */
        assertEquals("Active", adSet.getStatus().toString());
        assertEquals(0, adSet.getRemainingBudget());

        /**
         * Assert that the AdSet Analysis is valid (200 for Lebanon,300 for UAE).
         */
        String A = " {Country:Lebanon, Clicks:200, Price:200$ } {Country:UAE, Clicks:300, Price:300$ }";
        AdSetAnalysis[] adSetAnalysis = IAdSetManager.RetrieveAdSetPerformancePerCountry(adSet);

        assertTrue((adSetAnalysis[0].toString() + adSetAnalysis[1].toString()).equals(A));
    }

    /**
     * We have an empty budget AdSet (0$ budget) => if we tried to add new clicks it will not be able to add them
     *                                           => the function must change the state of the adSet into completed
     * we assert the the budget will be 0$ and the state will change to completed
     * -> in the last test we will try to add clicks to an adSet with sate = Completed => the function must throw an exception
     */

    @Test
    public void UpdateAdSetBudgetNoBudget() {
        /**
         * adSet object building using the Builder DP
         */
        AdSet adSet = new AdSet.AdSetBuilder(0, 1).build();

        /**
         * Adding a new Ad to the adSet
         */
        adSet.Ads.add(new Ad.AdBuilder(adSet).build());

        /**
         * Creating the array of clicks that will be used to update the budget of the adSet variable
         * with 100 Lebanese clicks and 100 UAE
         */
        AdClick[] adClicks = new AdClick[200];
        for (int i = 0; i < 100; i++)
            adClicks[i] = new AdClick.AdClickBuilder("Lebanon").build();
        for (int i = 100; i < 200 ; i++)
            adClicks[i] = new AdClick.AdClickBuilder("UAE").build();


        /**
         * Adding clicks and updating the AdSet budget using the Manager interface
         */
        IAdSetManager.UpdateAdSetBudget(adSet, adClicks);

        /**
         * Assert that the AdSet Status is Completed, RemainingBudget is 0$.
         */
        assertEquals("Completed", adSet.getStatus().toString());
        assertEquals(0, adSet.getRemainingBudget());

        /**
         *  after the state of the ad set is changed to complete if we try t call the Update budget again an excepetion will be thrown
         */

        IAdSetManager.UpdateAdSetBudget(adSet, adClicks);
    }


}