package com.vj.model;

public enum AssetState {
	
		NEW("New"), MODIFIED("Modified"), DELETED("Deleted");

		private AssetState(final String assetState) {
			this.assetState = assetState;
		}

		private String assetState;

		public String getAssetState() {
			return assetState;
		}
}
