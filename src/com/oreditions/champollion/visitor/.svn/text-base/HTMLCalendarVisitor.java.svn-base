/*
 * HTMLVisitor.java
 *
 * Created on 25 novembre 2007, 16:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.oreditions.champollion.visitor;

import com.oreditions.champollion.HTMLrendering.HTMLContentFrame;
import com.oreditions.champollion.HTMLrendering.HTMLIndexFrame;
import com.oreditions.champollion.util.ISO8859_1toHTML;
import com.oreditions.champollion.model.calendar.*;
import com.oreditions.champollion.engine.Config;
import java.util.*;
import java.io.*;
import java.text.DateFormat;


/**
 *
 * @author Olivier
 */
public class HTMLCalendarVisitor extends HTMLContext implements CalendarVisitor
{
    //Constants
    protected final static String[] monthstrings= {"January","February", "March",
        "April", "May", "June", "July", "August", "September", "October",
        "November", "December"};
    protected final static String daystrings[] = {"Sunday", "Monday", "Tuesday",
        "Wednesday", "Thursday", "Friday", "Saturday" };

    protected final static String mef = "style=\"border:1px solid #aaa; " +
            "background:#FFFFFF; padding-left:0em; padding-right:0em; " +
            "padding-top:0em; padding-bottom:0em; font-size:smaller;\"";


    protected Config conf;
    protected int[] width = {33,33,34};
    protected int day, dow;
  
    
    /** Creates a new instance of HTMLVisitor */
    public HTMLCalendarVisitor(HTMLIndexFrame index, HTMLContentFrame content)
    {
        super(index, content);
    }

   
    public void visit(CCalendar cal)
    {
        TreeMap<Integer,CYear> years = cal.getYearsInCalendar();
        
        //Main loop
        Collection<CYear> coll = years.descendingMap().values();
        for(CYear y : coll)
            y.acceptVisitor(this);
    }
    
    
    public void visit(CYear year)
    {
        TreeMap<Integer, CMonth> months = year.getMonthsInYear();
        //Generate the tag
        String temp = String.valueOf(year.getYear());
        
        //Generate the year line in the right frame
        content.println("<H2><A id=\"" + temp +"\">YEAR " + temp + "</A></H2>");

        //Generate the link in the index at the same time
        index.println("<P><B><A href=\"file://" + index.getContentName() + "#" + temp
                + "\" target=\"" + index.getContentTarget() + "\">Year " + temp + "</A></B></P>");
        Collection<CMonth> coll = months.descendingMap().values();
        for(CMonth m : coll)
            m.acceptVisitor(this);
    }
    
    public void visit(CMonth month)
    {
        //Generate the tag
        String temp = monthstrings[month.getMonth()] + month.getYear();

        //Generate the tag in the right frame
        content.println("<H3><A id=\"" + temp + "\">" + monthstrings[month.getMonth()] + "</A></H3>");

        //generate entry in the index
        index.println("<li><font size=\"-1\"><A href=\"file://" + index.getContentName() + "#" + temp
                + "\" target=\"" + index.getContentTarget() + "\">" + monthstrings[month.getMonth()]
                + "</A></font></li>");

        //Main loop
        TreeMap<Integer,CDay> days = month.getDaysInMonth();
        Collection<CDay> coll = days.descendingMap().values();
        
        //management of x columns
        int i=0;
        content.println("<table width=\"100%\" " + mef +">");
        for(CDay d : coll)
        {
            switch(i)
            {
                case 0:
                    //ouverture du row et du premier jour
                    content.println("<tr><td width=\""+ width[i] +"%\" valign=\"top\" " + mef + ">");
                    d.acceptVisitor(this);
                    content.println("</td>");
                    //on incrémente
                    i++;
                    break;
                case 1:
                    //we are in the middle
                    content.println("<td width=\""+ width[i] +"%\" valign=\"top\" " + mef + ">");
                    d.acceptVisitor(this);
                    content.println("</td>");
                    i++;
                    break;
                case 2:
                    //fermeture du row
                    content.println("<td width=\""+ width[i] +"%\" valign=\"top\" " + mef + ">");
                    d.acceptVisitor(this);
                   content.println("</td></tr>");
                    //on réinitialise
                    i=0;
                    break;
                default:
                    throw new RuntimeException("HTMLVisitor: should not occur");
            }
        }

        //Avant de terminer la table, voir la valeur de i pour voir si des rows
        //ne seraient pas à fermer
        switch(i)
        {
            case 2:
                //on est au dernier row mais il nous faut ajouter une cellule vide
                content.println("<td></td></tr>");
                break;
            case 1:
                //on est au milieu mais il nous faut ajouter 2 cellules vides
                content.println("<td></td><td></td></tr>");
                break;
            case 0:
                //on a rien à faire
                break;
            default:
                throw new RuntimeException("HTMLVisitor: should not occur");
        }
        //dans tous les cas, il faut fermer la table
        content.println("</table>");
    }
    
    public void visit(CDay day)
    {
        TreeMap<Date,File> files = day.getFilesInDay();
        content.println("<table><tr><td><h4>" + daystrings[day.getDOW()-1] + " " + day.getDay() + "</h4></td></tr>");
        
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
        Collection<File> coll = files.descendingMap().values();
        for (File file : coll)
        {
            StringTokenizer st = new StringTokenizer((new Date(file.lastModified()).toString()));
            String temp1 = st.nextToken(), temp2=st.nextToken(), temp3=st.nextToken();            
            content.println("<tr><td><font size=\"-1\">" + st.nextToken() + " : "
                + "<a href=\"file://"
                + ISO8859_1toHTML.convertASCIItoHTML(file.getAbsolutePath())
                + "\" target=\"_new\">"
                + ISO8859_1toHTML.convertASCIItoHTML(file.getName())
                + "</a> - <a href=\"file://"
                + ISO8859_1toHTML.convertASCIItoHTML(
                    file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf(file.getName())))
                + "\" target=\"_new\">"
                + "Folder</a></font></tr></td>");
        }
        content.println("</table>");
    }

}

