package com.neoris.turnosrotativos.exceptions;

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BussinessException(String message) {
        super(message);
    }
}
