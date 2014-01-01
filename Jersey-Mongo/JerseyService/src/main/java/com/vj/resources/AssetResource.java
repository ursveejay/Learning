package com.vj.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import com.vj.util.MongoDBUtil;

@Path("/asset")
public class AssetResource {
	
	  @GET
	  @Path("/id/{id}")
	  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	  public Asset getAssetById(@PathParam("id") String assetId) {
		  
		//Without using Spring
		/*DB db = MongoDBUtil.getDBInstance();
		DBCollection table = db.getCollection("assets");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", new ObjectId(assetId));
		
		DBCursor cursor = table.find(searchQuery);
	 
		while (cursor.hasNext()) {
			System.out.println("Found");
			System.out.println(cursor.next());
		}*/

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

}
