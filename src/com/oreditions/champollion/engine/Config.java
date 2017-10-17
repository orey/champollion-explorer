/*
 * Config.java
 *
 * Created on 10 novembre 2007, 12:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.oreditions.champollion.engine;

import java.util.*;
import java.io.*;

/**
 * This class encapsulates some treatments around the .properties files
 * required by the package.
 * @author orey
 */
public class Config
{
    //Constants
    protected static final int LIMIT_DIR_NUMBER = 10;
	
    protected static final String COLUMN_NUMBER = "cn";
    protected static final String DIRECTORY_PREFIX = "dir";
    protected static final String INPUT_DIR = "inputdir";
    protected static final String OUTPUTFILENAME = "outputfilename";
    protected static final String DEFAULTFILENAME = "calendar.html";
    protected static final String OUTPUTFOLDER = "outputfolder";
    protected static final String DEFAULTOUTPUTFOLDER = ".";

    //Variables
    protected Properties properties = new Properties();
    protected static Config singleton = null;
    protected String PROPERTIES = null;

    //public static
    
    /**
     * Constructor is protected. This class runs in a singleton mode.
     * @param path Full path of the property file
     */
    protected Config(String path)
    {
        PROPERTIES = path;
        try
        {
            properties.load(new FileInputStream(PROPERTIES));
        }
        catch (IOException e)
        {
            System.out.println("Config class: Configuration file not found");
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    /**
     * API of the Config file
     * @param key
     * @return the value
     */
    public String getProperty(String key)
    {
        return properties.getProperty(key);
    }
    
    /**
     * Access to the singleton : created of not, else a reference is provided
     * @return the singleton
     */
    public static Config getInstance(String path)
    {
        if (singleton==null)
            singleton = new Config(path);
        return singleton;
    }

    public static Config getInstance()
    {
        if (singleton==null)
            throw new RuntimeException("Config class: bad use of the getInstance " +
                    "API ; should be used when the singleton is created and not before.");
        return singleton;
    }

    
    /**
     * This method reads the content of the configuration file to get the list
     * of various directories to inspect
     * @return the vector of directories
     */
    public Vector<String> getListOfDirectories()
    {
        String dir;
        Vector<String> list = new Vector<String>();
        for (int i=1;i<10;i++)
        {
                dir = properties.getProperty(DIRECTORY_PREFIX + i);
                if ((dir==null)||(dir.equals("")))
                        break;
                list.add(dir);
        }
    	return list;
    }
    
    public String getOutputFilename()
    {
    	String output = properties.getProperty(OUTPUTFILENAME);
        if ((output==null)||(output.equals("")))
        {
        	output = DEFAULTFILENAME;
            System.err.println("Config::getOutputFilename warning: could not" +
                    "find output filename. Defaulting on : '" + DEFAULTFILENAME + "'");
        }
        return output;
    }
    
    public String getOutputFolder()
    {
    	String output = properties.getProperty(OUTPUTFOLDER);
        if ((output==null)||(output.equals("")))
        {
        	output = DEFAULTOUTPUTFOLDER;
            System.err.println("Config::getOutputFolder warning: output folder is " +
                    "either wrong or not present in the .properties file.");
            System.err.println("Please fill '" + OUTPUTFOLDER +
                    "' variable in .properties files or check the existence of the" +
                    "folder. Defaulting on : '" + DEFAULTOUTPUTFOLDER + "'");
        }
        //String temp = Config.linuxStyleFolder();
        return output + File.separator;
    }

    protected static String linuxStyleFolder(String dir)
    {
        StringTokenizer st = new StringTokenizer(dir,"\\",true);
        String newdir = "", el = null;
        while(st.hasMoreTokens())
        {
            el = st.nextToken();
            if (el.equals("\\"))
                newdir += "/";
            else
                newdir +=el;
        }
        return newdir;
    }

    public static void main(String[] args)
    {
        String temp = "C:\\toto\\titi";
        System.out.println(temp);
        System.out.println(Config.linuxStyleFolder(temp));

    }

}
