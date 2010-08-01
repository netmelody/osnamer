package org.netmelody.osnamer.server;

import java.util.HashMap;
import java.util.Map;

import org.netmelody.osnamer.server.projecthosts.Bitbucket;
import org.netmelody.osnamer.server.projecthosts.CodePlex;
import org.netmelody.osnamer.server.projecthosts.EmptyHost;
import org.netmelody.osnamer.server.projecthosts.GitHub;
import org.netmelody.osnamer.server.projecthosts.Sourceforge;
import org.netmelody.osnamer.shared.ProjectHostName;

public final class ProjectHostVendor {

    private final Map<ProjectHostName, ProjectHost> hostMap = new HashMap<ProjectHostName, ProjectHost>();
    {
        hostMap.put(ProjectHostName.GITHUB, new GitHub());
        hostMap.put(ProjectHostName.BITBUCKET, new Bitbucket());
        hostMap.put(ProjectHostName.SOURCEFORGE, new Sourceforge());
        hostMap.put(ProjectHostName.CODEPLEX, new CodePlex());
    }
    
    public ProjectHost getHostFor(ProjectHostName hostName) {
        if (hostMap.containsKey(hostName)) {
            return hostMap.get(hostName);
        }
        return new EmptyHost();
    }
}
