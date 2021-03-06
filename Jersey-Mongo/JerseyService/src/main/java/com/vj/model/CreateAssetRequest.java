package com.vj.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreateAssetRequest {
	
	private String assetName;
	private String assetDesc;
	private AssetState assetState;

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
	public AssetState getAssetState() {
		return assetState;
	}
	public void setAssetState(AssetState assetState) {
		this.assetState = assetState;
	}

}
