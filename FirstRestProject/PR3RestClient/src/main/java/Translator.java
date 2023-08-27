import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

public class Translator {
    public static void main(String[] args) {
        System.out.println("Введите предложение на русском языке: ");
        Scanner scanner  = new Scanner(System.in);
        String tr = scanner.nextLine();

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization","Bearer"+ " " );
    }
}
