package com.tag.domain.enumeration;

public enum UserRole {
	
	ROLE_USER("User"),
	ROLE_ADMIN("Admin");
	
	private final String displayName;
	
	UserRole(String displayName) {
        this.displayName = displayName;
    }

	public String getDisplayName() {
		return displayName;
	}
}
