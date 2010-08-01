package org.netmelody.osnamer.server.projecthosts;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableColumn;
import org.netmelody.osnamer.server.ProjectHost;

public final class Sourceforge implements ProjectHost {

    @Override
    public boolean isHosting(String projectName) {
        FilterBean bean = createFilter(projectName);
        bean.setURL("http://sourceforge.net/search/?words=" + projectName);
        return bean.getNodes().size() > 0;
    }
    
    private FilterBean createFilter(String projectName) {
        NodeClassFilter filter0 = new NodeClassFilter();
        filter0.setMatchClass(TableColumn.class);
        HasAttributeFilter filter1 = new HasAttributeFilter();
        filter1.setAttributeName("class");
        filter1.setAttributeValue("description");
        NodeClassFilter filter2 = new NodeClassFilter();
        filter2.setMatchClass(LinkTag.class);
        HasAttributeFilter filter3 = new HasAttributeFilter();
        filter3.setAttributeName("href");
        filter3.setAttributeValue("/projects/" + projectName + "/");
        NodeFilter[] array0 = new NodeFilter[2];
        array0[0] = filter2;
        array0[1] = filter3;
        AndFilter filter4 = new AndFilter();
        filter4.setPredicates(array0);
        HasChildFilter filter5 = new HasChildFilter();
        filter5.setRecursive(true);
        filter5.setChildFilter(filter4);
        NodeFilter[] array1 = new NodeFilter[3];
        array1[0] = filter0;
        array1[1] = filter1;
        array1[2] = filter5;
        AndFilter filter6 = new AndFilter();
        filter6.setPredicates(array1);
        NodeFilter[] array2 = new NodeFilter[1];
        array2[0] = filter6;
        FilterBean bean = new FilterBean();
        bean.setFilters(array2);
        return bean;
    }

}
