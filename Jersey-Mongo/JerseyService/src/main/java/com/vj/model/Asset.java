package com.vj.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@XmlRootElement
@Document(collection = "assets")
public class Asset {
	
	@Id
	private String assetId;
	private String assetName;
	private String assetDesc;
	private AssetState assetState;
	
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
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
