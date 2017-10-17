/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.champollion.HTMLrendering;

import com.oreditions.champollion.engine.Config;
import java.io.FileNotFoundException;

/**
 *
 * @author olivier
 */
public class HTMLBottomFrame extends HTMLFile
{
    protected static final String LEFT = "left";
    protected static final String RIGHT = "right";
    protected static final String LEFTNAME = "left.html";
    protected static final String RIGHTNAME = "right.html";

    protected String prefix = null;

    protected String indexname = null;
    protected String contentname = null;

    /**
     * 
     * @param prefix used for dual use with two cases
     * @throws java.io.FileNotFoundException
     */
    public HTMLBottomFrame(){}

    public void init(String prefix) throws FileNotFoundException
    {
        super.init(Config.getInstance().getOutputFolder() + prefix + HTMLContainerFrame.getBottomPageName(),
                HTMLRenderer.getTitle());
        this.prefix = prefix;
        indexname = Config.getInstance().getOutputFolder() + prefix + LEFTNAME;
        contentname = Config.getInstance().getOutputFolder() + prefix + RIGHTNAME;
        ps.println("<frameset cols=\"20%,80%\">"
            +"<frame src=\"file://"
            + indexname
            + "\" name=\"" + LEFT + "\">"
            + "<frame src=\"file://"
            + contentname
            + "\" name=\"" + RIGHT + "\"></frameset>");
    }

    public String getIndexName(){return indexname;}
    public String getContentName(){return contentname;}
    public String getContentTargetName(){return RIGHT;}


}
