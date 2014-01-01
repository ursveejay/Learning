package com.vj.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateAssetRequest {
	
	private String assetName;
	private String assetDesc;

	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetDesc() {
		return assetDesc;
	}
	public void setAssetDesc(String assetDesc) {
		this.assetDesc = assetDesc;
	}

}

