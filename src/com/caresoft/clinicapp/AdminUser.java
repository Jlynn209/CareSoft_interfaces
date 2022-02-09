package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
	
    private Integer employeeID;
    private String role;
    private ArrayList<String>securityIncidents;
    private int pin;
    
    public AdminUser(Integer id, String role) {
    	setEmployeeID(id);
    	setRole(role);
    	this.securityIncidents = new ArrayList<String>();
    }
    
    public boolean assignPin(int pin) {
    	if (pin < 100000) {
			return false;
		}
		else {
			this.setPin(pin);
			return true;
		}
    }
    
    public ArrayList<String> reportSecurityIncidents() {
    	return securityIncidents;
    }
    
    public boolean accessAuthorized(Integer confirmedAuthID) {
		if (confirmedAuthID.equals(this.getEmployeeID())) {
			return true;
		}
		else {
			return false;
		}
	}
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.employeeID, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.employeeID, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}
    
    
}
