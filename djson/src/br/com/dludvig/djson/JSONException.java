package br.com.dludvig.djson;

@SuppressWarnings("serial")
public class JSONException extends RuntimeException {

	public JSONException() {}

	public JSONException(String msg) {
		super(msg);
	}

	public JSONException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public JSONException(Throwable cause) {
		super(cause);
	}
}