package com.chinasoft.lgh.movies.datacollector.collect.analyze;

import com.chinasoft.lgh.movies.datacollector.collect.path.PathConfigure;
import com.chinasoft.lgh.movies.datacollector.model.Movie;
import com.chinasoft.lgh.movies.datacollector.model.MovieImage;
import com.chinasoft.lgh.movies.datacollector.service.MovieImageService;
import com.chinasoft.lgh.movies.datacollector.service.MovieService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator
 */
public class HtmlAnalyzer implements Analyzer {

    private static final Log LOG = LogFactory.getLog(HtmlAnalyzer.class);

    private MovieService movieService;

    private MovieImageService movieImageService;

    private List<Movie> movies = new ArrayList<>(1000);


    private enum BasicInfoEnum{

        name("片　　名"),

        translateName("译　　名"),

        age("年　　代"),

        region("产　　地"),

        type("类　　别"),

        language("语　　言"),

       words("字　　幕"),

        onlineDate("上映日期"),

        iMDBScore("IMDb评分"),

        doubanScore("豆瓣评分"),

        fileType("文件格式"),

        fileSize("文件大小"),

        fileDimension("视频尺寸"),
        fileTimeLength("片　　长"),

        director("导　　演"),

         actors("主　　演"),

         description("简　　介"),

         award("获奖情况");

        private String value;

        BasicInfoEnum(String value) {
            this.value = value;
        }
    }

    public HtmlAnalyzer(MovieService movieService,
                        MovieImageService movieImageService) {
        this.movieService = movieService;
        this.movieImageService = movieImageService;
    }

    @Override
    public List<String> analyze(String result, PathConfigure pathConfigure) {
        LOG.info("analyze start...");
        Document document = Jsoup.parse(result);
        if (document == null) {
            LOG.error("soup parse HTML failed.");
        }
        for (String cssQuery : pathConfigure.getCssQuery()) {
            Elements elements = document.select(cssQuery);
            if (elements == null) {
                LOG.info("query : " + cssQuery + " match nothing");
                continue;
            }
            Iterator<Element> elementIterator = elements.iterator();
            while (elementIterator.hasNext()){
                Element element = elementIterator.next();
                extractMovieInfo(element);
            }
        }

        if(!movies.isEmpty()){
            if(movieService == null){
                LOG.error("movie service is null, can not save movies");
            }else {
                List<Movie> movieList = new ArrayList<>(movies.size());
                movieList.addAll(movies);
                movieService.batchSave(movieList);
            }

        }
        LOG.info("analyze end , extract " + movies.size() + " movies");
        if(!movies.isEmpty()){
            movies.clear();
        }

        return resolveUrls(document,pathConfigure);
    }

    /**
     * extract movie info
     * @param element document
     */
    private void extractMovieInfo(Element element) {
        if(element == null){
            return;
        }
        Element basicElement = element.selectFirst("p");
        if(basicElement == null){
            return;
        }
        Elements elements = basicElement.select("img");
        if(basicElement == null){
            return;
        }
        String basicInfo = basicElement.toString().replace("<br>","\n");
        Element urlE = element.selectFirst("table>tbody>tr>td>a");
        int i = 0;
        if(basicInfo.indexOf('◎')>0){
            basicInfo = basicInfo.substring(basicInfo.indexOf('◎'));
            i += 1;
        }
        if(basicInfo.indexOf("<img")>0){
            basicInfo = basicInfo.substring(0,basicInfo.indexOf("<img"));
            i += 1;
        }
        Movie movie = null;
        if(i == 2){
            movie = new Movie();
            String[] properties = basicInfo.split("◎");
            for(String property : properties){
                property = property.trim();
                if(!StringUtils.isEmpty(property)){
                    movie.setId(UUID.randomUUID().toString());
                    resolveBasicProperty(movie,property);
                    resolveImageSrc(movie,elements);
                }
            }
            if(urlE != null){
                movie.setFtpUrl(urlE.text());
            }
            movies.add(movie);
        }


    }

    private void resolveImageSrc(Movie movie, Elements elements) {
        if(elements == null){
            return;
        }
        Iterator<Element> elementIterator = elements.iterator();
        List<MovieImage> movieImages = new ArrayList<>();
        int i = 0;
        while (elementIterator.hasNext()){
            Element element = elementIterator.next();
            String src = element.attr("src").trim();
            if(StringUtils.isEmpty(src)){
                continue;
            }
            if(i == 0){
                movie.setMainImage(src);
            }else {
                movieImages.add(new MovieImage(movie.getId(),src,i));
            }
            i++;
        }
        if(!movieImages.isEmpty()){
            movieImageService.batchSave(movieImages);
        }

    }

    /**
     * resolve basic property
     * @param movie movie
     * @param property property info
     */
    private void resolveBasicProperty(Movie movie, String property) {
        for (BasicInfoEnum basicInfoEnum:BasicInfoEnum.values()){
            if(property.startsWith(basicInfoEnum.value)){
                property = property.substring(basicInfoEnum.value.length());
                property = property.trim();
                property = property.replaceAll("&nbsp;","");
                property = property.replaceAll("<.*>","");
                if(property.length()> 1000){
                    continue;
                }
                switch (basicInfoEnum){
                    case name:
                        movie.setName(property);
                        break;
                    case translateName:
                        movie.setTranslateName(property);
                        break;
                    case age:
                        movie.setAge(property);
                        break;
                    case type:
                        movie.setType(property);
                        break;
                    case award:
                        movie.setAward(property);
                        break;
                    case words:
                        movie.setWords(property);
                        break;
                    case actors:
                        movie.setActors(property);
                        break;
                    case region:
                        movie.setRegion(property);
                        break;
                    case director:
                        movie.setDirector(property);
                        break;
                    case fileSize:
                        movie.setFileSize(property);
                        break;
                    case fileType:
                        movie.setType(property);
                        break;
                    case language:
                        movie.setLanguage(property);
                        break;
                    case iMDBScore:
                        movie.setiMDBScore(property);
                        break;
                    case onlineDate:
                        movie.setOnlineDate(property);
                        break;
                    case description:
                        movie.setDescription(property);
                        break;
                    case doubanScore:
                        movie.setDoubanScore(property);
                        break;
                    case fileDimension:
                        movie.setFileDimension(property);
                        break;
                    case fileTimeLength:
                        movie.setFileTimeLength(property);
                        break;
                }
            }
        }
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
