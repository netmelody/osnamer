package org.netmelody.osnamer.client;

import org.netmelody.osnamer.shared.ProjectHostName;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectNameLookupServiceAsync {

    void isInUse(ProjectHostName hostName, String projectName,
                 AsyncCallback<Boolean> callback) throws IllegalArgumentException;

}
