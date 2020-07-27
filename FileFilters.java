package image.processing;

import java.io.*;

class PPMFileFilter extends javax.swing.filechooser.FileFilter {
  @Override
  public boolean accept(File file) {
    if(file.isDirectory())
      return true;
    String name = file.getName();    
    return name.length()>4 && name.substring(name.length()-4, name.length()).toLowerCase().equals(".ppm");
  }
  
  @Override
  public String getDescription() { return "PPM File"; }
}

class YUVFileFilter extends javax.swing.filechooser.FileFilter {
  @Override
  public boolean accept(File file) {
    if(file.isDirectory())
      return true;
    String name = file.getName();
    return name.length()>4 && name.substring(name.length()-4, name.length()).toLowerCase().equals(".yuv");
  }
  
  @Override
  public String getDescription() { return "YUV File"; }
}