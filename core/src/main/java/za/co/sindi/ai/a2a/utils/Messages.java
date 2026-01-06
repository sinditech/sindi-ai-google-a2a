/**
 * 
 */
package za.co.sindi.ai.a2a.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import za.co.sindi.ai.a2a.types.Message;
import za.co.sindi.ai.a2a.types.Message.Role;
import za.co.sindi.ai.a2a.types.Part;
import za.co.sindi.ai.a2a.types.TextPart;

/**
 * @author Buhake Sindi
 * @since 24 October 2025
 */
public final class Messages {

	private Messages() {
		throw new AssertionError("Private Constructor.");
	}
	
	/**
	 * Creates a new agent message containing a single {@link TextPart}.
	 * 
	 * @param text The text content of the message.
	 * @return A new {@link Message} object with role <code>agent</code>.
	 */
	public static Message newAgentTextMessage(final String text) {
		return newAgentTextMessage(text, null, null);
	}
	
	/**
	 * Creates a new agent message containing a single {@link TextPart}.
	 * 
	 * @param text The text content of the message.
	 * @param contextId The context ID for the message.
	 * @param taskId The task ID for the message.
	 * @return A new {@link Message} object with role <code>agent</code>.
	 */
	public static Message newAgentTextMessage(final String text, final String contextId, final String taskId) {
		return newAgentPartsMessage(List.of(new TextPart(text)), contextId, taskId);
	}
	
	/**
	 * Creates a new agent message containing a list of Parts.
	 * 
	 * @param parts The list of {@link Part} objects for the message content.
	 * @return A new {@link Message} object with role <code>agent</code>.
	 */
	public static Message newAgentPartsMessage(final List<Part> parts) {
		return newAgentPartsMessage(parts, null, null);
	}
	
	/**
	 * Creates a new agent message containing a list of Parts.
	 * 
	 * @param parts The list of {@link Part} objects for the message content.
	 * @param contextId The context ID for the message.
	 * @param taskId The task ID for the message.
	 * @return A new {@link Message} object with role <code>agent</code>.
	 */
	public static Message newAgentPartsMessage(final List<Part> parts, final String contextId, final String taskId) {
		Message message = new Message(Role.AGENT, Objects.requireNonNull(parts, "A list of parts is required.").toArray(new Part[parts.size()]), UUID.randomUUID().toString());
		message.setContextId(contextId);
		message.setTaskId(taskId);
		return message;
	}
	
	/**
	 * Extracts and joins all text content from a Message's parts.
	 * 
	 * @param message The {@link Message} object.
	 * @return A single string containing all text content, or an empty string if no text parts are found.
	 */
	public static String getMessageText(final Message message) {
		return getMessageText(message, null);
	}
	
	/**
	 * Extracts and joins all text content from a Message's parts.
	 * 
	 * @param message The {@link Message} object.
	 * @param delimiter The string to use when joining text from multiple TextParts.
	 * @return  A single string containing all text content, or an empty string if no text parts are found.
	 */
	public static String getMessageText(final Message message, final String delimiter) {
		String delim = delimiter;
		if (delim == null) delim = "\n";
		
		return String.join(delim, Parts.getTextParts(Arrays.asList(message.getParts())));
	}
}
