package murach.util;

import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;

public class MailUtilResend {

    public static void sendMail(
            String to,
            String from,
            String subject,
            String body,
            boolean bodyIsHTML)
            throws Exception {

        // Get API key from environment variable
        String apiKey = System.getenv("RESEND_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException(
                    "RESEND_API_KEY environment variable is not set."
            );
        }

       
        Resend resend = new Resend(apiKey);

      
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("onboarding@resend.dev")
                .to(to)
                .subject(subject)
                .text(body)
                .build();

        // Send email
        resend.emails().send(params);
    }
}