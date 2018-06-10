package com.chinasoft.lgh.movies.datacollector.collect.analyze;

import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 */
public class HtmlAnalyzer implements Analyzer {

    private static final Log LOG = LogFactory.getLog(HtmlAnalyzer.class);

    @Override
    public List<String> analyze(String result, PathConfigure pathConfigure) {
        LOG.info("analyze start...");
        Document document = Jsoup.parse(result);
        if (document == null) {
            LOG.error("soup parse HTML failed.");
        }
        for (String cssQuery : pathConfigure.getCssQuery()) {
            LOG.info("start execute query: " + cssQuery);
            Elements elements = document.select(cssQuery);
            if (elements == null) {
                LOG.info("query : " + cssQuery + " match nothing");
                continue;
            }
            Iterator<Element> elementIterator = elements.iterator();
            while (elementIterator.hasNext()){
                Element element = elementIterator.next();
                System.out.println(element.hasText()?element.text():element.nodeName());
            }
        }


        return resolveUrls(document,pathConfigure);
    }

    /**
     * resolve subUrls
     * @return subUrls
     * @param document document
     * @param pathConfigure path configure
     */
    private List<String> resolveUrls(Document document, PathConfigure pathConfigure) {
        if(document == null || pathConfigure.getCurrentDeep() > pathConfigure.getDeep()){
            return null;
        }
        Elements elements = document.select("a");
        if(elements == null){
            return null;
        }
        List<String> urls = new ArrayList<>();
        Iterator<Element> elementIterator = elements.iterator();
        while (elementIterator.hasNext()){
            Element element = elementIterator.next();
            String href = element.attr("href");
            String url = pathConfigure.getBaseUrl() + href;
            if(!StringUtils.isEmpty(href) && pathConfigure.getSubUrlMatcher().match(href)
                    && !pathConfigure.getExpiredUrl().contains(url)){
                urls.add(url);
            }
        }
        pathConfigure.addDeep();
        return urls;
    }
}
