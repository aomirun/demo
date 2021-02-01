// **********************************************************************
// This file was generated by a TARS parser!
// TARS version 1.7.2.
// **********************************************************************

package com.example.tarsmqserver.service.rsaserver;

public enum SignType {

	MD5withRSA(0),
	SHA1withRSA(1),
	SHA256withRSA(2);

	private final int value;

	private SignType(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.name() + ":" + this.value;
	}

	public static SignType convert(int value) {
		for(SignType v : values()) {
			if(v.value() == value) {
				return v;
			}
		}
		return null;
	}
}
