package org.example.CoinMapClient;

import io.restassured.response.Response;
import org.example.fileReaderSetup.FileGetParams;
import org.example.utils.RestAssuredUtils;
import org.example.preRequisite.APIBaseTest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinMapClientTest extends APIBaseTest {
    private static final Logger logger = LoggerFactory.getLogger(CoinMapClientTest.class);
    int atmVenueCount = 0, cafeCount = 0, shoppingCount = 0, foodCount = 0,
            lodgingCount = 0, attractionCount = 0, defaultCount = 0;


    @Test(priority = 1)
    public void getCategoriesCount() {


        // Make a GET call to the CoinMap API endpoint using the common method
        Response response = RestAssuredUtils.GET(FileGetParams.paramLookUp("BaseUrl"), getHeaders());

        // Get the JSON response body as a string
        assert response != null;
        String responseBody = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray venuesArray = jsonObject.getJSONArray("venues");
        System.out.println("response:" + venuesArray.length());
        StringBuilder foodVenues = new StringBuilder();

        for (int i = 0; i < venuesArray.length(); i++) {

            JSONObject venue = venuesArray.getJSONObject(i);
            String category = venue.getString("category");

            // Increment the corresponding category count
            switch (category) {
                case "atm":
                    atmVenueCount++;
                    break;
                case "cafe":
                    cafeCount++;
                    break;
                case "shopping":
                    shoppingCount++;
                    break;
                case "food":
                    foodCount++;
                    break;
                case "lodging":
                    lodgingCount++;
                    break;
                case "attraction":
                    attractionCount++;
                    break;
                case "default":
                    defaultCount++;
                    break;
                default:
                    break;
            }
            if ("food".equals(category)) {
                String name = venue.getString("name");
                String geolocation = venue.getString("geolocation_degrees");
                foodVenues.append(name).append(" (").append(geolocation).append(")\n");
            }
        }
        logger.info("category details");
        System.out.println("Total 'atm' venues count: " + atmVenueCount);
        System.out.println("Cafe Count: " + cafeCount);
        System.out.println("Shopping Count: " + shoppingCount);
        System.out.println("Food Count: " + foodCount);
        System.out.println("Lodging Count: " + lodgingCount);
        System.out.println("Attraction Count: " + attractionCount);
        System.out.println("Default Count: " + defaultCount);


    }

    @Test(priority=2)
    public void getFoodCategoryDetails() {

        Response response = RestAssuredUtils.GET(FileGetParams.paramLookUp("BaseUrl"), getHeaders());

        // Get the JSON response body as a string
        assert response != null;
        String responseBody = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray venuesArray = jsonObject.getJSONArray("venues");
        System.out.println("response:" + venuesArray.length());
        StringBuilder foodVenues = new StringBuilder();

        for (int i = 0; i < venuesArray.length(); i++) {

            JSONObject venue = venuesArray.getJSONObject(i);
            String category = venue.getString("category");

            if ("food".equals(category)) {
                String name = venue.getString("name");
                String geolocation = venue.getString("geolocation_degrees");
                foodVenues.append(name).append(" (").append(geolocation).append(")\n");
            }
            System.out.println("Food Venues:******");
            System.out.println(foodVenues);
        }
    }
}