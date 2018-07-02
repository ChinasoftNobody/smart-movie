package com.chinasoft.lgh.movies.datacollector.service.impl;

import com.chinasoft.lgh.movies.datacollector.mapper.LocateImageMapper;
import com.chinasoft.lgh.movies.datacollector.model.LocateImage;
import com.chinasoft.lgh.movies.datacollector.service.LocateImageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.io.OutputStream;

@Service
@Transactional(rollbackOn = Exception.class)
public class LocateImageServiceImpl implements LocateImageService {

    private static final Log LOG = LogFactory.getLog(LocateImageServiceImpl.class);

    @Resource
    private LocateImageMapper locateImageMapper;

    @Override
    public boolean saveLocateImage(LocateImage locateImage) {
        return locateImageMapper.saveLocateImage(locateImage);
    }

    @Override
    public LocateImage queryById(String id) {
        return locateImageMapper.queryById(id);
    }

    @Override
    public void writeImage(HttpServletResponse response, LocateImage locateImage) {
        byte[] bytes = locateImage.getImage();
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        }catch (Exception e){
            LOG.error("write image to response error: ", e);
        }
    }
}
