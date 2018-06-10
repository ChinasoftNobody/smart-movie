package com.chinasoft.lgh.movies.datacollector.collect.analyze;

import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;

/**
 * @author Administrator
 */
public class HtmlAnalyzer implements Analyzer {

    private static final Log LOG = LogFactory.getLog(HtmlAnalyzer.class);

    @Override
    public void analyze(String result, PathConfigure pathConfigure) {
        LOG.info("analyze start...");
        Document document = Jsoup.parse(result);
        if (document == null) {
            LOG.error("soup parse HTML failed.");
        }
        for (String cssQuery : pathConfigure.getCssQuery()) {
            LOG.info("start execute query: " + cssQuery);
            Elements elements = document.select(cssQuery);
            if (elements == null) {
                LOG.error("query : " + cssQuery + " match nothing");
                continue;
            }
            Iterator<Element> elementIterator = elements.iterator();
            while (elementIterator.hasNext()){
                Element element = elementIterator.next();
                System.out.println(element.hasText()?element.text():element.nodeName());
            }
        }

    }
}
