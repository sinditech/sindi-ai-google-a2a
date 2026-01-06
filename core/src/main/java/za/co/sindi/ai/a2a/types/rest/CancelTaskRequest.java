/**
 * 
 */
package za.co.sindi.ai.a2a.types.rest;

import java.util.Objects;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

/**
 * @author Buhake Sindi
 * @since 18 November 2025
 */
public record CancelTaskRequest(String name) {
	
	public CancelTaskRequest {
		name = Objects.requireNonNull(name, "A name is required.");
	}

	@JsonbCreator
	public static CancelTaskRequest create(@JsonbProperty("name") String name) {
		return new CancelTaskRequest(name); 
	}
}
