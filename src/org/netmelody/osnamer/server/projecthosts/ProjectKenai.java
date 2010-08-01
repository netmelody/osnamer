package org.netmelody.osnamer.server.projecthosts;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.netmelody.osnamer.server.ProjectHost;

public final class ProjectKenai implements ProjectHost {

    @Override
    public boolean isHosting(String projectName) {
        FilterBean bean = createFilter(projectName);
        bean.setURL("http://projectkenai.com/projects?commit=search&q=" + projectName);
        return bean.getNodes().size() > 0;
    }
    
    private FilterBean createFilter(String projectName) {
        NodeClassFilter filter0 = new NodeClassFilter();
        filter0.setMatchClass(Div.class);
        HasAttributeFilter filter1 = new HasAttributeFilter();
        filter1.setAttributeName("class");
        filter1.setAttributeValue("info");
        NodeClassFilter filter2 = new NodeClassFilter();
        filter2.setMatchClass(Div.class);
        HasAttributeFilter filter3 = new HasAttributeFilter();
        filter3.setAttributeName("class");
        filter3.setAttributeValue("projectTitle");
        NodeClassFilter filter4 = new NodeClassFilter();
        filter4.setMatchClass(LinkTag.class);
        RegexFilter filter5 = new RegexFilter();
        filter5.setStrategy(RegexFilter.MATCH);
        filter5.setPattern(projectName);
        HasChildFilter filter6 = new HasChildFilter();
        filter6.setRecursive(false);
        filter6.setChildFilter(filter5);
        NodeFilter[] array0 = new NodeFilter[2];
        array0[0] = filter4;
        array0[1] = filter6;
        AndFilter filter7 = new AndFilter();
        filter7.setPredicates(array0);
        HasChildFilter filter8 = new HasChildFilter();
        filter8.setRecursive(false);
        filter8.setChildFilter(filter7);
        NodeFilter[] array1 = new NodeFilter[3];
        array1[0] = filter2;
        array1[1] = filter3;
        array1[2] = filter8;
        AndFilter filter9 = new AndFilter();
        filter9.setPredicates(array1);
        HasChildFilter filter10 = new HasChildFilter();
        filter10.setRecursive(false);
        filter10.setChildFilter(filter9);
        NodeFilter[] array2 = new NodeFilter[3];
        array2[0] = filter0;
        array2[1] = filter1;
        array2[2] = filter10;
        AndFilter filter11 = new AndFilter();
        filter11.setPredicates(array2);
        NodeFilter[] array3 = new NodeFilter[1];
        array3[0] = filter11;
        FilterBean bean = new FilterBean();
        bean.setFilters(array3);
        return bean;
    }
}
