package com.javaudemy.SpringBoot_Ionic.domain.enums;

public enum ClientType {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int code;
	private String description;
	
	private ClientType(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientType toIntegerEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for(ClientType ct: ClientType.values()) {
			if(code.equals(ct.getCode())) {
				return ct;
			}
		}
		
		throw new IllegalArgumentException("Invalid id" + code);
	}
	
	public static ClientType toStringEnum(String description) {
		if(description == null) {
			return null;
		}
		
		for(ClientType ps: ClientType.values()) {
			if(description.equals(ps.getDescription())) {
				return ps;
			}
		}
		
		throw new IllegalArgumentException("Invalid description" + description);
	}
}
