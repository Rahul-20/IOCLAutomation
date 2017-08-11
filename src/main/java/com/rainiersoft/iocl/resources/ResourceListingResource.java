/*package com.rainiersoft.iocl.resources;

import javax.ws.rs.core.Context;

import org.glassfish.jersey.server.model.*;

@Path("/")
public class ResourceListingResource
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response showAll( @Context Application application,
                             @Context HttpServletRequest request)
    {
        String basePath = request.getRequestURL().toString();
 
        ObjectNode root = JsonNodeFactory.instance.objectNode();
        ArrayNode resources = JsonNodeFactory.instance.arrayNode();
 
        root.put( "resources", resources );
 
        for ( Class<?> aClass : application.getClasses() )
        {
            if ( isAnnotatedResourceClass( aClass ) )
            {
                AbstractResource resource = IntrospectionModeller.createResource( aClass );
                ObjectNode resourceNode = JsonNodeFactory.instance.objectNode();
                String uriPrefix = resource.getPath().getValue();
 
                for ( AbstractSubResourceMethod srm : resource.getSubResourceMethods() )
                {
                    String uri = uriPrefix + "/" + srm.getPath().getValue();
                    addTo( resourceNode, uri, srm, joinUri(basePath, uri) );
                }
 
                for ( AbstractResourceMethod srm : resource.getResourceMethods() )
                {
                    addTo( resourceNode, uriPrefix, srm, joinUri( basePath, uriPrefix ) );
                }
 
                resources.add( resourceNode );
            }
 
        }
 
 
        return Response.ok().entity( root ).build();
    }
 
    private void addTo( ObjectNode resourceNode, String uriPrefix, AbstractResourceMethod srm, String path )
    {
        if ( resourceNode.get( uriPrefix ) == null )
        {
            ObjectNode inner = JsonNodeFactory.instance.objectNode();
            inner.put("path", path);
            inner.put("verbs", JsonNodeFactory.instance.arrayNode());
            resourceNode.put( uriPrefix, inner );
        }
 
        ((ArrayNode) resourceNode.get( uriPrefix ).get("verbs")).add( srm.getHttpMethod() );
    }
 
 
    private boolean isAnnotatedResourceClass( Class rc )
    {
        if ( rc.isAnnotationPresent( Path.class ) )
        {
            return true;
        }
 
        for ( Class i : rc.getInterfaces() )
        {
            if ( i.isAnnotationPresent( Path.class ) )
            {
                return true;
            }
        }
 
        return false;
    }
 
}*/