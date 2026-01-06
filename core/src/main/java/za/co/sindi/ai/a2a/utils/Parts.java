/**
 * 
 */
package za.co.sindi.ai.a2a.utils;

import java.util.List;
import java.util.Map;

import za.co.sindi.ai.a2a.types.DataPart;
import za.co.sindi.ai.a2a.types.FileBase;
import za.co.sindi.ai.a2a.types.FilePart;
import za.co.sindi.ai.a2a.types.FileWithBytes;
import za.co.sindi.ai.a2a.types.FileWithUri;
import za.co.sindi.ai.a2a.types.Part;
import za.co.sindi.ai.a2a.types.TextPart;

/**
 * @author Buhake Sindi
 * @since 24 October 2025
 */
public final class Parts {

	private Parts() {
		throw new AssertionError("Private Constructor.");
	}
	
	/**
	 * Extracts text content from all {@link TextPart} objects in a list of Parts.
	 * @param parts A list of {@link Part} objects
	 * @return A list of strings containing the text content from any {@link TextPart} objects found.
	 */
	public static List<String> getTextParts(final List<Part> parts) {
		return parts.stream().filter(part -> part instanceof TextPart).map(part -> ((TextPart)part).getText()).toList();
	}
	
	/**
	 * Extracts text content from all {@link DataPart} objects in a list of Parts.
	 * @param parts A list of {@link Part} objects
	 * @return A list of {@link Map}&lt;String, Object&gt; containing the data from any {@link DataPart} objects found.
	 */
	public static List<Map<String, Object>> getDataParts(final List<Part> parts) {
		return parts.stream().filter(part -> part instanceof DataPart).map(part -> ((DataPart)part).getData()).toList();
	}
	
	/**
	 * Extracts text content from all {@link FilePart} objects in a list of Parts.
	 * @param parts A list of {@link Part} objects
	 * @return A list of {@link FileWithBytes} or {@link FileWithUri} objects containing the file data from any {@link FilePart} objects found.
	 */
	public static List<FileBase> getFileParts(final List<Part> parts) {
		return parts.stream().filter(part -> part instanceof FilePart).map(part -> ((FilePart)part).getFile()).toList();
	}
}
