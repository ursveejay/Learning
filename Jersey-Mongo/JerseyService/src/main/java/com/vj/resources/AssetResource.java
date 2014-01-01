package com.vj.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.vj.model.Asset;
import com.vj.model.AssetState;
import com.vj.model.CreateAssetRequest;
import com.vj.model.UpdateAssetRequest;
import com.vj.util.MongoDBUtil;

@Path("/asset")
public class AssetResource {
	
	  @GET
	  @Path("/id/{id}")
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public Asset getAssetById(@PathParam("id") String assetId) {
		  
		//Using Spring		  
		Query assetQuery = new Query();
		assetQuery.addCriteria(Criteria.where("_id").is(new ObjectId(assetId)));
		Asset assetFound = MongoDBUtil.getMongoOperation().findOne(assetQuery, Asset.class);
		System.out.println(assetFound.getAssetName());
		return assetFound;
	  }
	  
	  @POST
	  @Path("/create")
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public String createAsset(CreateAssetRequest createAssetReq){
		  Asset newAsset = new Asset();
		  newAsset.setAssetName(createAssetReq.getAssetName());
		  newAsset.setAssetDesc(createAssetReq.getAssetDesc());
		  newAsset.setAssetState(AssetState.NEW);
		  
		  //Inserting the asset
		  MongoDBUtil.getMongoOperation().insert(newAsset);
		  
		  //Getting the asset
		  Query assetQuery = new Query();
		  assetQuery.addCriteria(Criteria.where("assetName").is(newAsset.getAssetName()));
		  Asset assetFound = MongoDBUtil.getMongoOperation().findOne(assetQuery, Asset.class);
		  System.out.println(assetFound.getAssetName());
		  
		  //Returning the asset ID
		  return assetFound.getAssetId();
	  }
	  
	  @PUT
	  @Path("/update")
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public Asset updateAsset(UpdateAssetRequest updateAssetReq){
		  String assetName = updateAssetReq.getAssetName();
		  
		  //Getting the asset
		  Query assetQuery = new Query();
		  assetQuery.addCriteria(Criteria.where("assetName").is(assetName));
		  Asset assetFound = MongoDBUtil.getMongoOperation().findOne(assetQuery, Asset.class);
		  
		  //Updating
		  assetFound.setAssetDesc(updateAssetReq.getAssetDesc());
		  assetFound.setAssetState(AssetState.MODIFIED);
		  
		  //Saving Again
		  MongoDBUtil.getMongoOperation().save(assetFound);
		  
		//Getting the asset Again
		  Asset updatedAsset = MongoDBUtil.getMongoOperation().findOne(assetQuery, Asset.class);
		  
		  return updatedAsset;
	  }
	  
	  @DELETE
	  @Path("/id/{id}")
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public Asset deleteAsset(@PathParam("id") String assetId) {
		  
		//Using Spring		  
		Query assetQuery = new Query();
		assetQuery.addCriteria(Criteria.where("_id").is(new ObjectId(assetId)));
		Asset assetFound = MongoDBUtil.getMongoOperation().findOne(assetQuery, Asset.class);
		System.out.println(assetFound.getAssetName());
		
		//Soft Delete
		  assetFound.setAssetState(AssetState.DELETED);
		  
		  //Saving Again
		  MongoDBUtil.getMongoOperation().save(assetFound);
		  
		//Getting the asset Again
		  Asset deletedAsset = MongoDBUtil.getMongoOperation().findOne(assetQuery, Asset.class);
		  
		  return deletedAsset;
	  }
	  
}
