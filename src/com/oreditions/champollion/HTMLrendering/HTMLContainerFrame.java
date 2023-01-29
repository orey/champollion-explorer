/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.champollion.HTMLrendering;

import com.oreditions.champollion.engine.Config;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author olivier
 */
public class HTMLContainerFrame extends HTMLFile
{
    protected static final String mainPage = "index.html";
    protected static final String top = "top.html";
    protected static final String bottom = "bottom.html";
    protected static final String TARGETTOP = "top";
    protected static final String TARGETBOTTOM = "bottom";


    public HTMLContainerFrame(){}

    public void init(String title) throws FileNotFoundException
    {
        super.init(Config.getInstance().getOutputFolder() + mainPage, title);
        ps.println("<frameset rows=\"7%,93%\"><frame src=\"file://"
                + Config.getInstance().getOutputFolder()
                + top + "\" name=\"" + TARGETTOP + "\"><frame src=\"file://"
                + Config.getInstance().getOutputFolder()
                + "calendar" + bottom + "\" name=\"" + TARGETBOTTOM + "\"></frameset>");
        //        Ici au dessus, c'est un peu sale...
    }

    
    public static String getTopPageName(){return top;}
    public static String getBottomPageName(){return bottom;}
    public static String getBottomTargetName(){return TARGETBOTTOM;}

}
