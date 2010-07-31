package org.netmelody.osnamer.server;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.netmelody.osnamer.client.GreetingService;
import org.netmelody.osnamer.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
        GreetingService {

    public String greetServer(String name) throws IllegalArgumentException {
        // Verify that the input is valid. 
        if (!FieldVerifier.isValidName(name)) {
            // If the input is not valid, throw an IllegalArgumentException back to
            // the client.
            throw new IllegalArgumentException(
                    "Name must be at least 4 characters long");
        }

        FilterBean bean = createFilter(name);
        bean.setURL("http://github.com/search?type=Repositories&q=" + name);
        return bean.getNodes().toHtml();
    }
    
    private FilterBean createFilter(String projectName) {
        NodeClassFilter filter0 = new NodeClassFilter ();
        filter0.setMatchClass (Div.class);
        HasAttributeFilter filter1 = new HasAttributeFilter ();
        filter1.setAttributeName ("class");
        filter1.setAttributeValue ("result");
        NodeClassFilter filter2 = new NodeClassFilter ();
        filter2.setMatchClass (LinkTag.class);
        RegexFilter filter3 = new RegexFilter ();
        filter3.setStrategy (RegexFilter.MATCH);
        filter3.setPattern (".*\\/ " + projectName + "$");
        HasChildFilter filter4 = new HasChildFilter ();
        filter4.setRecursive (false);
        filter4.setChildFilter (filter3);
        NodeFilter[] array0 = new NodeFilter[2];
        array0[0] = filter2;
        array0[1] = filter4;
        AndFilter filter5 = new AndFilter ();
        filter5.setPredicates (array0);
        HasChildFilter filter6 = new HasChildFilter ();
        filter6.setRecursive (true);
        filter6.setChildFilter (filter5);
        NodeFilter[] array1 = new NodeFilter[3];
        array1[0] = filter0;
        array1[1] = filter1;
        array1[2] = filter6;
        AndFilter filter7 = new AndFilter ();
        filter7.setPredicates (array1);
        NodeFilter[] array2 = new NodeFilter[1];
        array2[0] = filter7;
        FilterBean bean = new FilterBean ();
        bean.setFilters (array2);
        return bean;
    }
}

