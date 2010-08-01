package org.netmelody.osnamer.server.projecthosts;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.netmelody.osnamer.server.ProjectHost;

public final class Bitbucket implements ProjectHost {
    @Override
    public boolean isHosting(String projectName) {
        FilterBean bean = createFilter(projectName);
        bean.setURL("http://bitbucket.org/repo/all/?name=" + projectName);
        return bean.getNodes().size() > 0;
    }
    
    private FilterBean createFilter(String projectName) {
        NodeClassFilter filter0 = new NodeClassFilter();
        filter0.setMatchClass(Div.class);
        HasAttributeFilter filter1 = new HasAttributeFilter();
        filter1.setAttributeName("class");
        filter1.setAttributeValue("repos-all-repository crow1");
        HasAttributeFilter filter2 = new HasAttributeFilter();
        filter2.setAttributeName("class");
        filter2.setAttributeValue("repos-all-repository crow2");
        NodeFilter[] array0 = new NodeFilter[2];
        array0[0] = filter1;
        array0[1] = filter2;
        OrFilter filter3 = new OrFilter();
        filter3.setPredicates(array0);
        NodeClassFilter filter4 = new NodeClassFilter();
        filter4.setMatchClass(LinkTag.class);
        RegexFilter filter5 = new RegexFilter();
        filter5.setStrategy(RegexFilter.MATCH);
        filter5.setPattern(projectName);
        HasChildFilter filter6 = new HasChildFilter();
        filter6.setRecursive(false);
        filter6.setChildFilter(filter5);
        NodeFilter[] array1 = new NodeFilter[2];
        array1[0] = filter4;
        array1[1] = filter6;
        AndFilter filter7 = new AndFilter();
        filter7.setPredicates(array1);
        HasChildFilter filter8 = new HasChildFilter();
        filter8.setRecursive(true);
        filter8.setChildFilter(filter7);
        NodeFilter[] array2 = new NodeFilter[3];
        array2[0] = filter0;
        array2[1] = filter3;
        array2[2] = filter8;
        AndFilter filter9 = new AndFilter();
        filter9.setPredicates(array2);
        NodeFilter[] array3 = new NodeFilter[1];
        array3[0] = filter9;
        FilterBean bean = new FilterBean();
        bean.setFilters(array3);
        return bean;
    }
}
