package org.netmelody.osnamer.server.projecthosts;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.BulletList;
import org.netmelody.osnamer.server.ProjectHost;

public final class Launchpad implements ProjectHost {

    @Override
    public boolean isHosting(String projectName) {
        FilterBean bean = createFilter(projectName);
        bean.setURL("https://launchpad.net/+search?field.text=" + projectName);
        return bean.getNodes().size() > 0;
    }
    
    private FilterBean createFilter(String projectName) {
        NodeClassFilter filter0 = new NodeClassFilter();
        filter0.setMatchClass(BulletList.class);
        HasAttributeFilter filter1 = new HasAttributeFilter();
        filter1.setAttributeName("class");
        filter1.setAttributeValue("exact-matches");
        NodeFilter[] array0 = new NodeFilter[2];
        array0[0] = filter0;
        array0[1] = filter1;
        AndFilter filter2 = new AndFilter();
        filter2.setPredicates(array0);
        NodeFilter[] array1 = new NodeFilter[1];
        array1[0] = filter2;
        FilterBean bean = new FilterBean();
        bean.setFilters(array1);
        return bean;
    }
}
