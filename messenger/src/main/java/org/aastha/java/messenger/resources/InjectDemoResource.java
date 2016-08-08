package org.aastha.java.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)

public class InjectDemoResource {
	@GET
	@Path("/annotations")
	public String getParamUsingAnnotation(@MatrixParam("param") String matrixParam,
			@HeaderParam("customHeaderValue") String Headervalue,
			@CookieParam("name") String cookie){
		return "Matrix param: "+matrixParam+" Header Param :"+Headervalue +"\nCookie Param:"+cookie;
	}
	
	@GET
	@Path("/context")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
			@Context HttpHeaders headers){
		String path=uriInfo.getAbsolutePath().toString();
		String cookies=headers.getCookies().toString();
		return "Path :"+path+"\nCookie :"+cookies;
	}

}
