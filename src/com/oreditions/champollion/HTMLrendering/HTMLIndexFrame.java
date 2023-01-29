/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.champollion.HTMLrendering;

import java.io.FileNotFoundException;

/**
 *
 * @author olivier
 */
public class HTMLIndexFrame extends HTMLFile
{
    protected String contentname = null;
    protected String contenttarget = null;


    public HTMLIndexFrame(){}

    public void init(String indexname,
            String contentname,
            String contenttarget,
            String headerstring) throws FileNotFoundException
    {
        super.init(indexname, HTMLRenderer.getTitle());
        this.contenttarget = contenttarget;
        this.contentname = contentname;
        ps.println("<h4>" + headerstring + "</h4>");
    }


    public void println(String sec)
    {
        ps.println(sec);
    }

    public String getContentTarget(){return contenttarget;}
    public String getContentName(){return contentname;}

    
}
