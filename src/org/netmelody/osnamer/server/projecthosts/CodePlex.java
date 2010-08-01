package org.netmelody.osnamer.server.projecthosts;

import java.util.Locale;

import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableRow;
import org.netmelody.osnamer.server.ProjectHost;

public final class CodePlex implements ProjectHost {

    @Override
    public boolean isHosting(String projectName) {
        FilterBean bean = createFilter(projectName);
        bean.setURL("http://www.codeplex.com/site/search?query=" + projectName);
        return bean.getNodes().size() > 0;
    }
    
    private FilterBean createFilter(String projectName) {
        NodeClassFilter filter0 = new NodeClassFilter();
        filter0.setMatchClass(TableRow.class);
        HasAttributeFilter filter1 = new HasAttributeFilter();
        filter1.setAttributeName("class");
        filter1.setAttributeValue("ProjectDirectoryListItem");
        NodeClassFilter filter2 = new NodeClassFilter();
        filter2.setMatchClass(LinkTag.class);
        HasAttributeFilter filter3 = new HasAttributeFilter();
        filter3.setAttributeName("class");
        filter3.setAttributeValue("SubHeader");
        StringFilter filter4 = new StringFilter();
        filter4.setCaseSensitive(false);
        filter4.setLocale(Locale.UK);
        filter4.setPattern(projectName);
        HasChildFilter filter5 = new HasChildFilter();
        filter5.setRecursive(false);
        filter5.setChildFilter(filter4);
        NodeFilter[] array0 = new NodeFilter[3];
        array0[0] = filter2;
        array0[1] = filter3;
        array0[2] = filter5;
        AndFilter filter6 = new AndFilter();
        filter6.setPredicates(array0);
        HasChildFilter filter7 = new HasChildFilter();
        filter7.setRecursive(true);
        filter7.setChildFilter(filter6);
        NodeFilter[] array1 = new NodeFilter[3];
        array1[0] = filter0;
        array1[1] = filter1;
        array1[2] = filter7;
        AndFilter filter8 = new AndFilter();
        filter8.setPredicates(array1);
        NodeFilter[] array2 = new NodeFilter[1];
        array2[0] = filter8;
        FilterBean bean = new FilterBean();
        bean.setFilters(array2);
        return bean;
    }
}
