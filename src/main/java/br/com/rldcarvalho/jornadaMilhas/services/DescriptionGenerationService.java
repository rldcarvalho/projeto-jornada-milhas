package br.com.rldcarvalho.jornadaMilhas.services;

import br.com.rldcarvalho.jornadaMilhas.model.destination.Destination;
import org.springframework.stereotype.Service;

@Service
public class DescriptionGenerationService {
    OpenAI openAI = new OpenAI();

    public String generateDescription(Destination destination) {
            return openAI.generateAIText("Faça um resumo sobre " + destination.getName() +
                    " enfatizando o porquê este lugar é incrível. Utilize uma linguagem informal e até 100 caracteres " +
                    "no máximo em cada parágrafo. Crie 2 parágrafos neste resumo.");
    }
}
