package org.netmelody.osnamer.server.projecthosts;

import java.util.Locale;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.netmelody.osnamer.server.ProjectHost;

public final class Ohloh implements ProjectHost {

    @Override
    public boolean isHosting(String projectName) {
        FilterBean bean = createFilter(projectName);
        bean.setURL("https://www.ohloh.net/p?q=" + projectName);
        return bean.getNodes().size() > 0;
    }
    
    private FilterBean createFilter(String projectName) {
        NodeClassFilter filter0 = new NodeClassFilter();
        filter0.setMatchClass(Div.class);
        HasAttributeFilter filter1 = new HasAttributeFilter();
        filter1.setAttributeName("class");
        filter1.setAttributeValue("name");
        NodeClassFilter filter2 = new NodeClassFilter();
        filter2.setMatchClass(LinkTag.class);
        StringFilter filter3 = new StringFilter();
        filter3.setCaseSensitive(false);
        filter3.setLocale(Locale.UK);
        filter3.setPattern(projectName);
        HasChildFilter filter4 = new HasChildFilter();
        filter4.setRecursive(false);
        filter4.setChildFilter(filter3);
        NodeFilter[] array0 = new NodeFilter[2];
        array0[0] = filter2;
        array0[1] = filter4;
        AndFilter filter5 = new AndFilter();
        filter5.setPredicates(array0);
        HasChildFilter filter6 = new HasChildFilter();
        filter6.setRecursive(false);
        filter6.setChildFilter(filter5);
        NodeFilter[] array1 = new NodeFilter[3];
        array1[0] = filter0;
        array1[1] = filter1;
        array1[2] = filter6;
        AndFilter filter7 = new AndFilter();
        filter7.setPredicates(array1);
        NodeFilter[] array2 = new NodeFilter[1];
        array2[0] = filter7;
        FilterBean bean = new FilterBean();
        bean.setFilters(array2);
        return bean;
    }
}
