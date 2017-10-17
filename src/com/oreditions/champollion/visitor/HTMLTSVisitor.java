/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.champollion.visitor;

import com.oreditions.champollion.HTMLrendering.*;
import com.oreditions.champollion.model.typesize.TSHead;
import com.oreditions.champollion.model.typesize.TSInterval;
import com.oreditions.champollion.model.typesize.TSType;
import com.oreditions.champollion.util.ISO8859_1toHTML;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author olivier
 */
public class HTMLTSVisitor extends HTMLContext implements TSVisitor
{

    public HTMLTSVisitor(HTMLIndexFrame index, HTMLContentFrame content)
    {
        super(index, content);
    }

    public void visit(TSHead head)
    {
        TreeMap<String, TSType> types = head.getTypesTree();

        //Main loop
        Collection<TSType> col = types.values();
        for (TSType t : col)
            t.acceptVisitor(this);
    }

    public void visit(TSType type)
    {
        //1. reate content for type
        String temp = type.getExtension();
        content.println("<h3><a id=\"" + temp +"\">EXTENSION " + temp + "</a></h2>");

        //Generate the link in the index at the same time
        index.println("<p><font size=\"-1\"><b><a href=\"file://" + index.getContentName() + "#" + temp
                + "\" target=\"" + index.getContentTarget() + "\">'" + temp + "' extension ("
                + String.valueOf(type.getNumberOfFiles()) +" files)</a></b></font></p>");

        //2. initiate the loop under
        Collection<TSInterval> col = type.getIntervals().descendingMap().values();
        for (TSInterval inter : col)
            inter.acceptVisitor(this);
    }

    
    public void visit(TSInterval interval)
    {
        if (interval.getSizes().size() == 0)
            return;
        
        //Generate the tag
        String temp = interval.getDescription();
        //For the tag, se need a unique tag in the page
        String temp2 = interval.getExtension() + String.valueOf(interval.getSup());

        //Generate the tag in the right frame
        content.println("<b><a id=\"" + temp2 + "\">" + temp + "</a></b>");

        //generate entry in the index
        index.println("<li><font size=\"-1\"><a href=\"file://" + index.getContentName() + "#" + temp2
                + "\" target=\"" + index.getContentTarget() + "\">" + temp
                + " ("+ String.valueOf(interval.getSizes().size()) +")</a></font></li>");

        //the loop on the elements
        Collection<File> col = interval.getSizes().values();
        TreeMap<String,File> map = new TreeMap<String,File>();
        for (File f : col)
            map.put(f.getName(),f);
        col = map.values();
        
        content.println("<p><font size=\"-1\">");
        for (File file : col)
        {
            content.println(String.valueOf(file.length()) + " bytes - <a href=\"file://"
                + ISO8859_1toHTML.convertASCIItoHTML(file.getAbsolutePath())
                + "\" target=\"_new\">"
                + ISO8859_1toHTML.convertASCIItoHTML(file.getName())
                + "</a> - <a href=\"file://"
                + ISO8859_1toHTML.convertASCIItoHTML(
                    file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf(file.getName())))
                + "\" target=\"_new\">"
                + "Folder</a><br>");
        }
        content.println("</font></p>");
    }

}
