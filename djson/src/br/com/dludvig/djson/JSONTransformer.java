package br.com.dludvig.djson;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

public class JSONTransformer {

	private static final Logger log = Logger.getLogger(JSONTransformer.class.getName() );

	public JSONTransformer() {
		// TODO Auto-generated constructor stub
	}

	public void transform(OutputStream streamReturn, Object object) {
		transform(streamReturn, object, ReturnType.JSON);
	}

	public void transform(OutputStream streamReturn, Object object, ReturnType returnType) {

		log.info("Object converter has been initialized.");
		
		if (streamReturn == null)
			throw new JSONException("The OutputStream has not been initialized.");
		
		TypeContext typeContext = new TypeContext(returnType.getType());
		JSONParse jsonParse = new JSONParse(typeContext);
		
		try {
			streamReturn.write( jsonParse.parse(object).getBytes() );
			
			log.info("Object converter is finished.");
		} catch (IOException e) {
			throw new JSONException("An error occurred while writing the OutputStream", e);
		}
	}

	public void transform(OutputStream streamReturn, List<?> list) {
		transform(streamReturn, list, ReturnType.JSON);
	}

	public void transform(OutputStream streamReturn, List<?> list, ReturnType returnType) {

		log.info("Object list converter has been started.");
		
		if (streamReturn == null)
			throw new JSONException("The OutputStream have not been initialized.");
		
		TypeContext typeContext = new TypeContext(returnType.getType());
		JSONParse jsonParse = new JSONParse(typeContext);

		jsonParse.parseList(list);

		try {
			streamReturn.write( jsonParse.parseList(list).getBytes() );
			
			log.info("Object list converter is finished.");
		} catch (IOException e) {
			throw new JSONException("An error occurred while writing the OutputStream", e);
		}

	}

}