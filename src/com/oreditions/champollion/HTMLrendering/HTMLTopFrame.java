/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.champollion.HTMLrendering;

import com.oreditions.champollion.engine.Config;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author olivier
 */
public class HTMLTopFrame extends HTMLFile
{
    protected String introstring = null;
    protected HashMap<String,String> links = new HashMap<String,String>();

    public HTMLTopFrame(){}

    public void init(String introstring) throws FileNotFoundException
    {
        super.init(Config.getInstance().getOutputFolder() + HTMLContainerFrame.getTopPageName(),
            HTMLRenderer.getTitle());
        this.introstring = introstring;
    }

    public void addLink(String linkpage, String linkname)
    {
        links.put(linkpage, linkname);
    }

    public void terminate()
    {
        ps.println("<p><font size=\"-1\"><b>" + introstring);
        Set<String> set = links.keySet();
        for (String s : set)
            ps.println(" - " + "[<a href=\"file://" + s + "\" target=\""
                + HTMLContainerFrame.getBottomTargetName() +"\" >"
                + links.get(s) + "</a>]");
        ps.println("</b></font></p>");
        super.terminate();
    }

}
