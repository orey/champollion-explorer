/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.champollion.HTMLrendering;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author olivier
 */
public abstract class HTMLFile
{
    protected String name = null, title = null;
    protected PrintStream ps = null;
    
    public HTMLFile(){}

    public void init(String fullname, String title) throws FileNotFoundException
    {
        name = fullname;
        this.title = title;
        FileOutputStream output = new FileOutputStream(new File(name));
        ps = new PrintStream(output);
        ps.println("<html><head><title>" + title + "</title>"
            +"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" /></head>");
    }

    public String getName(){return name;}

    public void terminate()
    {
        ps.println("</html>");
        ps.flush();
        ps.close();
    }

}
