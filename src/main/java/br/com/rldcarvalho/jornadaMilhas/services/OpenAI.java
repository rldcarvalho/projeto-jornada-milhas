package br.com.rldcarvalho.jornadaMilhas.services;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.flywaydb.core.internal.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class OpenAI {
    @Value("${services.openai.apikey}")
    private String apiKey;

    public String generateAIText(String prompt){
        OpenAiService service = new OpenAiService(apiKey);

        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model("gpt-3.5-turbo")
                .maxTokens(1000)
                .build();

        return service.createCompletion(completionRequest).getChoices().get(0).getText().replace("\n", "");
    }

}
