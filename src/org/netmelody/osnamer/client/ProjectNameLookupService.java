package org.netmelody.osnamer.client;

import org.netmelody.osnamer.shared.ProjectHostName;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("projectNameLookup")
public interface ProjectNameLookupService extends RemoteService {

    boolean isInUse(ProjectHostName hostName, String projectName) throws IllegalArgumentException;
}
