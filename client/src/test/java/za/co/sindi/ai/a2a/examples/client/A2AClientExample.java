/**
 * 
 */
package za.co.sindi.ai.a2a.examples.client;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.UUID;

import za.co.sindi.ai.a2a.client.A2ACardResolver;
import za.co.sindi.ai.a2a.client.Client;
import za.co.sindi.ai.a2a.client.ClientConfig.ClientConfigBuilder;
import za.co.sindi.ai.a2a.client.ClientFactory;
import za.co.sindi.ai.a2a.types.AgentCard;
import za.co.sindi.ai.a2a.types.Message;
import za.co.sindi.ai.a2a.types.Message.Role;
import za.co.sindi.ai.a2a.types.TextPart;
import za.co.sindi.ai.a2a.types.TransportProtocol;
import za.co.sindi.ai.a2a.utils.Messages;
import za.co.sindi.commons.utils.Arrays;

/**
 * @author Buhake Sindi
 * @since 13 December 2025
 */
public class A2AClientExample {

	public static void main(String[] args) {
		HttpClient.Builder httpClientBuilder = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30));
		ClientConfigBuilder clientConfigBuilder = new ClientConfigBuilder();
		clientConfigBuilder.httpClient(httpClientBuilder.build())
						   .supportedTransports(TransportProtocol.values());
		
		A2ACardResolver resolver = new A2ACardResolver(httpClientBuilder.build(), "http://localhost:9080");
		AgentCard agentCard = resolver.getAgentCard();
		
		ClientFactory clientFactory = new ClientFactory(clientConfigBuilder.build());
		Client client = clientFactory.create(agentCard);
		client.addEventConsumer((clientEventOrMessage, card) -> {
			if (clientEventOrMessage != null) {
				if (clientEventOrMessage.isRightPresent()) {
					System.out.println(Messages.getMessageText(clientEventOrMessage.getRight()));
				}
			}
		});
		
		Message userMessage = new Message(Role.USER, Arrays.toArray(new TextPart("Hi there!")), UUID.randomUUID().toString());
		client.sendMessage(userMessage);
	}
}
