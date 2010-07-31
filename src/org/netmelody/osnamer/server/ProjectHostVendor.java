package org.netmelody.osnamer.server;

import java.util.HashMap;
import java.util.Map;

import org.netmelody.osnamer.server.projecthosts.EmptyHost;
import org.netmelody.osnamer.server.projecthosts.GitHub;
import org.netmelody.osnamer.shared.ProjectHostName;

public final class ProjectHostVendor {

    private final Map<ProjectHostName, ProjectHost> hostMap = new HashMap<ProjectHostName, ProjectHost>();
    {
        hostMap.put(ProjectHostName.GITHUB, new GitHub());
    }
    
    public ProjectHost getHostFor(ProjectHostName hostName) {
        if (hostMap.containsKey(hostName)) {
            return hostMap.get(hostName);
        }
        return new EmptyHost();
    }
}
