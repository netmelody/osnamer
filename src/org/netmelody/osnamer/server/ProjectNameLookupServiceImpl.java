package org.netmelody.osnamer.server;

import org.netmelody.osnamer.client.ProjectNameLookupService;
import org.netmelody.osnamer.shared.FieldVerifier;
import org.netmelody.osnamer.shared.ProjectHostName;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ProjectNameLookupServiceImpl extends RemoteServiceServlet implements ProjectNameLookupService {

    private final ProjectHostVendor projectHostVendor = new ProjectHostVendor();
    
    public boolean isInUse(ProjectHostName hostName, String projectName) {
        if (!FieldVerifier.isValidName(projectName)) {
            throw new IllegalArgumentException("Name must be at least 4 characters long");
        }
        
        return projectHostVendor.getHostFor(hostName).isHosting(projectName);
    }
}
