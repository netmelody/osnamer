package org.netmelody.osnamer.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.HasAttributeFilter;
import org.netmelody.osnamer.client.GreetingService;
import org.netmelody.osnamer.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ProjectNameLookupImpl extends RemoteServiceServlet implements GreetingService {

    public String greetServer(String name) throws IllegalArgumentException {
        // Verify that the input is valid. 
        if (!FieldVerifier.isValidName(name)) {
            // If the input is not valid, throw an IllegalArgumentException back to
            // the client.
            throw new IllegalArgumentException(
                    "Name must be at least 4 characters long");
        }
        
        HasAttributeFilter filter0 = new HasAttributeFilter();
        filter0.setAttributeName("class");
        filter0.setAttributeValue("result");
        NodeFilter[] array0 = new NodeFilter[1];
        array0[0] = filter0;
        FilterBean bean = new FilterBean();
        bean.setFilters(array0);
        bean.setURL("http://github.com/search?type=Repositories&q=" + name);
        bean.getNodes();

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        // Escape data from the client to avoid cross-site script vulnerabilities.
        name = escapeHtml(name);
        userAgent = escapeHtml(userAgent);

        return "Hello, " + name + "!<br><br>I am running " + serverInfo
                + ".<br><br>It looks like you are using:<br>" + userAgent;
    }

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     * 
     * @param html the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }
}
