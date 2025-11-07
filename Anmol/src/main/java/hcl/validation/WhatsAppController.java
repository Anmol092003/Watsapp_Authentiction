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
                    .uri(URI.create("https://graph.facebook.com/v24.0/850736108124567/messages"))
                    .header("Authorization", "Bearer EAAVITKuFU8kBP5iETZABD1OsFWuUPbeZAZAGSFDDlgtCGW2CCNoPnuVA2tB2KwD1omDTRxSVdtpBwGoA3YEnxd2hCBmmu7YkAnHBaWmZBhGoEkeXoWQust7alGtPEREg10ZBa2qhCL06XItZCr3dvuGUudvKLiZAzEMCiZCRNXODpQDgepejwFjj7Q9kUzSLQCv7nGaZBDiT7lIDrXCZB1TA1Gfp3c6UsbfycKQTQNEDULusYhFtDavhwTXDQbZAAWZA43Ufu1Xr3XoIt8IvN7wrnyJXDv4K")
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
