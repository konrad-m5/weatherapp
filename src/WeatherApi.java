import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WeatherApi {

    public static JSONObject weatherData(String city){

        JSONObject cityData = getLocationData(city);
        double latitude = (double) cityData.get("latitude");
        double longitude = (double) cityData.get("longitude");

        String urlString ="https://api.open-meteo.com/v1/forecast?latitude=" +
                latitude+ "&longitude="+ longitude + "&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m";

        try{

            HttpURLConnection connection = fetchApiResponse(urlString);

            if(connection.getResponseCode() != 200){

                System.out.println("Error fetching weather data");
                return null;

            }

            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                resultJson.append(scanner.nextLine());
            }

            scanner.close();

            connection.disconnect();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(resultJson.toString());

            JSONObject hourly = (JSONObject) jsonObject.get("hourly");

            JSONArray time = (JSONArray) hourly.get("time");
            int index = findIndexOfCurrentHour(time);

            JSONArray temperatureList = (JSONArray) hourly.get("temperature_2m");
            double temperature = (double) temperatureList.get(index);

            JSONArray weatherCode = (JSONArray) hourly.get("weathercode");
            String weatherCondition = convertWeatherCode((long) weatherCode.get(index));





        }catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }


    // Fetch location data by city name, will find the very first result
    public static JSONObject getLocationData(String cityName) {

        // Implement API call to get location data by city name
        cityName = cityName.replaceAll(" ", "+");

        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                cityName + "&count=1&language=en&format=json";

        try{
            HttpURLConnection apiConnection = fetchApiResponse(urlString);

            if(apiConnection.getResponseCode() != 200) {
                System.out.println("Error could not connect to API");
                return null;
            }// end if

            String jsonResponse = readApiResponse(apiConnection);

            JSONParser parser = new JSONParser();
            JSONObject responseObj = (JSONObject) parser.parse(jsonResponse);

            JSONArray resultsArray = (JSONArray) responseObj.get("results");
            return (JSONObject) resultsArray.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Placeholder return
    }

    private static HttpURLConnection fetchApiResponse(String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection apiConnection = (HttpURLConnection) url.openConnection();
            apiConnection.setRequestMethod("GET");

            return apiConnection;
        } catch (IOException e) {
            e.printStackTrace();

        }// end try-catch

        return null;

    }// end fetchApiResponse

    private static String readApiResponse(HttpURLConnection apiConnection){

        try{
            StringBuilder resultJson = new StringBuilder();

            Scanner scanner = new Scanner(apiConnection.getInputStream());

            while(scanner.hasNextLine()){
                resultJson.append(scanner.nextLine());
            }// end while

            scanner.close();

            return resultJson.toString();

        }catch (IOException e){
            e.printStackTrace();
        }// end try-catch

        return null;


    }// end readApiResponse

    private static int findIndexOfCurrentHour(JSONArray timeList){
        String currentHour = getCurrentTime();

        for(int i = 0; i < timeList.size(); i++){
            String time = (String) timeList.get(i);
            if (time.equals(currentHour)){

                return i;
            }
        }

        return 0;
    }

    public static String getCurrentTime(){

        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:00");

        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;

    }// End getCurrentTime

    private static String convertWeatherCode(long weatherCode){
        String weatherCondition = "";
        if (weatherCode == 0L){
            weatherCondition = "Clear";
        }else if (weatherCode <= 3L && weatherCode > 0L){
            weatherCondition = "Cloudy";
        }else if (weatherCode <= 67L && weatherCode >= 51L){

            

        }

        return null;

    }
}
