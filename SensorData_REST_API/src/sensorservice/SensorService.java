package sensorservice;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;


@Path("/sensors")
public class SensorService implements ContainerResponseFilter{
	ArrayList<SensorData> sensors;
	public SensorService() {
		sensors = SensorDataDB.getSensorData();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SensorData> getSensorData() {
		return SensorDataDB.getSensorData();
	}
	
	@Path("{sid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SensorData getSensorData(@PathParam("sid") String sid) {
		  for(SensorData b : SensorDataDB.getSensorData()) {
			  if ( b.getSid().equals(sid))
				   return b;
		  }
		  // book with the given id is not found, so throw 404 error
		  throw new NotFoundException(); 
	}
	
	
    @Path("{sid}/{co2}/{smoke}/{floor}/{room}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public SensorData addSensor(
			@PathParam("sid") String sid,
			@PathParam("co2") int co2,
			@PathParam("smoke") int smoke,
			@PathParam("floor") int floor,
			@PathParam("room") int room ) 
	{
		
		SensorData s = new SensorData(sid,co2, smoke, floor,room);
		SensorDataDB.addSensorData(s);
		return s; 
	}
	

	@Path("{sid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public SensorData deleteSensor(@PathParam("sid") String sid) {
		
		for(SensorData s : SensorDataDB.getSensorData()) {
			  if ( s.getSid().equals(sid) ){
				SensorDataDB.removeSensorData(s.getSid());
				return s;
			  }
		}	
		return null;
	}
	
	
	
	@Path("{sid}/{co2}/{smoke}/{floor}/{room}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public SensorData updateSensor(
			@PathParam("sid") String sid,
			@PathParam("co2") int co2,
			@PathParam("smoke") int smoke,
			@PathParam("floor") int floor,
			@PathParam("room") int room ) 
	{
		
		for(SensorData s : SensorDataDB.getSensorData()) {
			  if ( s.getSid().equals(sid) ){
				SensorData newSensorData = new SensorData(sid,co2,smoke,floor,room);  
				SensorDataDB.updateSensorData( s.getSid() , newSensorData);
				return newSensorData; 
			  }
		}	
		return null;
	}
	
	
	
	@Path("{sid}/{co2}/{smoke}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public SensorData updateSensor(
			@PathParam("sid") String sid,
			@PathParam("co2") int co2,
			@PathParam("smoke") int smoke) 
	{
		
		for(SensorData s : SensorDataDB.getSensorData()) {
			  if ( s.getSid().equals(sid) ){
				SensorData newSensorData = new SensorData(sid,co2,smoke,s.getFloorNo(),s.getRoomNo());  
				SensorDataDB.updateSensorData(  s.getSid() , newSensorData);
				return newSensorData; 
			  }
		}	
		return null;
	}
	

	

	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		// TODO Auto-generated method stub
		response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        return;
	}
	
	
}

