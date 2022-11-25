package main.example.demo;

public enum Marker {
	BAD_REQUEST("BAD_REQUEST"), BUSINESS_ERROR("BUSINESS_ERROR"), INTERNAL_ERROR("INTERNAL_ERROR"),
	SECURITY_ERROR("SECURITY_ERROR"), RPC_COMMUNICATION_ERROR("RPC_COMMUNICATION_ERROR");

	private String marker;

	private Marker(String marker) {

		this.marker = marker;
	}

	public String getMarker() {

		return this.marker;
	}
}
