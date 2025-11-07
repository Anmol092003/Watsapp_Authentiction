package hcl.validation;

import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class WhatsAppController {

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam String to) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://graph.facebook.com/v24.0/your_watsapp_id/messages"))
                    .header("Authorization", "Bearer {Your access tocken}")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("""
                {
                  "messaging_product": "whatsapp",
                  "to": "%s",
                  "type": "template",
                  "template": {
                    "name": "hello_world",
                    "language": {
                      "code": "en_US"
                    }
                  }
                }
                """.formatted(to)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return "Response: " + response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending message";
        }
    }

}
