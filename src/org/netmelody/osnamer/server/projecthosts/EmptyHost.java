package org.netmelody.osnamer.server.projecthosts;

import org.netmelody.osnamer.server.ProjectHost;

public final class EmptyHost implements ProjectHost {

    @Override
    public boolean isHosting(String projectName) {
        return false;
    }

}
