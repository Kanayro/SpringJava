import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Consumer {
    public static void main(String [] args){
        RestTemplate restTemplate = new RestTemplate();
        Random random = new Random();
        double scale = Math.pow(10,1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> SensorToSend = new HashMap<>();



        SensorToSend.put("name","SensorClient");

        HttpEntity<Map<String,String>> request = new HttpEntity<>(SensorToSend);

        String url1 = "http://localhost:8080/sensors/registration";

        String response = restTemplate.postForObject(url1,request,String.class);

        System.out.println(response);



        String url2 = "http://localhost:8080/measurements/add";

        for(int i =0;i<1000;i++){
            Map<String,Object> thRequests = new HashMap<>();
            double a = Math.ceil(rnd(-100,100))/scale;

            thRequests.put("value", a );
            thRequests.put("raining",random.nextBoolean());
            thRequests.put("sensor",SensorToSend);

            HttpEntity<Map<String,Object>> addMeasurement = new HttpEntity<>(thRequests,headers);
            restTemplate.postForObject(url2,addMeasurement,String.class);

        }

        String url3 = "http://localhost:8080/measurements";
        String getMeasurement = restTemplate.getForObject(url3, String.class);

    }



    public static double rnd(double min, double max) {
        max -= min;
        return (double) (Math.random() * ++max) + min;
    }
}
