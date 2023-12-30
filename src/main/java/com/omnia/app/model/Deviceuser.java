package com.omnia.app.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="deviceuser")
public class Deviceuser extends  DateAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private UUID deviceuuid ;
	

	
	private String deviceName ; 
	private String devicemac ;
	private String deviceIp ;
	
	private String deviceType ;

	public Deviceuser(Long id, UUID deviceuuid, String deviceName, String devicemac, String deviceIp,
			String deviceType) {
		super();
		this.id = id;
		this.deviceuuid = deviceuuid;
		this.deviceName = deviceName;
		this.devicemac = devicemac;
		this.deviceIp = deviceIp;
		this.deviceType = deviceType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getDeviceuuid() {
		return deviceuuid;
	}

	public void setDeviceuuid(UUID deviceuuid) {
		this.deviceuuid = deviceuuid;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDevicemac() {
		return devicemac;
	}

	public void setDevicemac(String devicemac) {
		this.devicemac = devicemac;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
	
	

}
