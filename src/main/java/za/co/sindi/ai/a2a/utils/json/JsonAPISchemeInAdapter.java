/**
 * 
 */
package za.co.sindi.ai.a2a.utils.json;

import jakarta.json.bind.adapter.JsonbAdapter;
import za.co.sindi.ai.a2a.types.APIKeySecurityScheme.In;

/**
 * @author Buhake Sindi
 * @since 04 November 2025
 */
public class JsonAPISchemeInAdapter implements JsonbAdapter<In, String> {

	@Override
	public String adaptToJson(In format) throws Exception {
		// TODO Auto-generated method stub
		if (format == null) return null;
		return format.toString();
	}

	@Override
	public In adaptFromJson(String value) throws Exception {
		// TODO Auto-generated method stub
		if (value == null) return null;
		return In.of(value);
	}
}
