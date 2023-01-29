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
public class HTMLContentFrame extends HTMLFile
{
    public HTMLContentFrame(){}

    public void init(String name, String headerstring) throws FileNotFoundException
    {
        super.init(name, HTMLRenderer.getTitle());
        ps.println("<h4>" + headerstring + "</h4>");
    }

    public void println(String sec)
    {
        ps.println(sec);
    }


}
