package com.util;

import java.io.File;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class FileUtil
{
  public static File getDestFile(String fileInfoName)
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    String dest = request.getServletContext().getRealPath("/") + getRelativeFilePath(fileInfoName);
    
    return new File(dest);
  }
  
  public static String getRelativeFilePath(String fileInfoName)
  {
    Calendar calendar = Calendar.getInstance();
    String path = calendar.get(1) + "/" + (calendar.get(2) + 1) + "/" + fileInfoName;
    
    return path;
  }
  
  public static String rtnFileType(String fileName)
  {
    if ((fileName == null) || (fileName.length() == 0)) {
      return null;
    }
    fileName = fileName.toLowerCase();
    if ((fileName.endsWith(".jpg")) || (fileName.endsWith(".gif")) || (fileName.endsWith(".jpeg")) || (fileName.endsWith(".bmp")) || (fileName.endsWith(".tga")) || (fileName.endsWith(".png"))) {
      return "image";
    }
    /*if (VideoType.isVideo(fileName)) {
      return "video";
    }*/
    if ((fileName.endsWith(".mp3")) || (fileName.endsWith(".midi")) || (fileName.endsWith(".wav")) || (fileName.endsWith(".wma"))) {
      return "audio";
    }
    return "application";
  }
  
  public static String getFileSuffix(String fileName)
  {
    return fileName.substring(fileName.lastIndexOf("."), fileName.length()).toLowerCase();
  }
  
  public static boolean isImage(String fileName)
  {
    fileName = fileName.toLowerCase();
    return (fileName.endsWith(".png")) || (fileName.endsWith(".jpg")) || (fileName.endsWith(".gif")) || (fileName.endsWith(".jpeg")) || (fileName.endsWith(".bmp"));
  }
}

