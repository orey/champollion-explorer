/**
 * 
 */
package com.oreditions.champollion.HTMLrendering;

import com.oreditions.champollion.visitor.*;
import com.oreditions.champollion.engine.Config;
import com.oreditions.champollion.model.calendar.*;
import com.oreditions.champollion.model.typesize.*;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * @author orey
 *
 */
public class HTMLRenderer
{
    //Constants
    protected final static String TITLE = "Champollion Explorer";

    protected final static String VIEW_NAME_CALENDAR = "Calendar View";
    protected final static String VIEW_NAME_TYPESIZE = "Type Size View";

    protected final static String FRAME_PREFIX_CALENDAR = "calendar";
    protected final static String FRAME_PREFIX_TYPESIZE = "typesize";

    protected String introstring = null;
    
    //********************en dessous, c'est pourri


    protected static final String DEFAULT_DIR = "./";
    protected static final String DEFAULT_NAME = "Calendar";
    protected static final String DEFAULT_EXT = ".html";
    protected static final String OTHER_EXT = ".htm";
    protected static final String LEFT = "left";
    protected static final String RIGHT = "right";

    //variables
    protected String framefilename = null;
    protected String leftframe = null, rightframe = null;
    
    protected CCalendar cal = null;
    protected HTMLCalendarVisitor cvisitor = null;
    protected TSHead head = null;
    protected HTMLTSVisitor tsvisitor = null;

    protected HTMLContainerFrame container = null;
    protected HTMLTopFrame top = null;
    

    /**
     * Constructor
     * @throws java.io.FileNotFoundException
     */
    public HTMLRenderer() throws FileNotFoundException
    {
        container = new HTMLContainerFrame();
        container.init(TITLE);
        prepareIntroString();
        top = new HTMLTopFrame();
        top.init(introstring);
    }

    
    protected void prepareIntroString()
    {
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        DateFormat df2 = DateFormat.getTimeInstance(DateFormat.LONG);
        introstring = TITLE + " - Generated : " + df.format(date) + ", "+ df2.format(date);
    }


    public void processCCalendar(CCalendar cal) throws FileNotFoundException
    {
        System.out.println("Calendar dump - begin");

        //We create the container and the appropriate link on the top
        HTMLBottomFrame bot = new HTMLBottomFrame();
        bot.init(FRAME_PREFIX_CALENDAR);
        top.addLink(bot.getName(), VIEW_NAME_CALENDAR);

        //We create the two views : index and content
        HTMLIndexFrame index = new HTMLIndexFrame();
        index.init(bot.getIndexName(),
                bot.getContentName(),
                bot.getContentTargetName(),
                "Chronological index");
        HTMLContentFrame content = new HTMLContentFrame();
        content.init(bot.getContentName(),"Chronological content");

        //We create the visitor
        this.cal = cal;
        cvisitor = new HTMLCalendarVisitor(index, content);

        //We visit
        cal.acceptVisitor(cvisitor);

        //We termonate all streams
        content.terminate();
        index.terminate();
        bot.terminate();

        System.out.println("Calendar dump - end");
    }

    public void processTSHead(TSHead head) throws FileNotFoundException
    {
        System.out.println("Type Size dump - begin");

        //We create the container and the appropriate link on the top
        HTMLBottomFrame bot = new HTMLBottomFrame();
        bot.init(FRAME_PREFIX_TYPESIZE);
        top.addLink(bot.getName(), VIEW_NAME_TYPESIZE);

        //We create the two views : index and content
        HTMLIndexFrame index = new HTMLIndexFrame();
        index.init(bot.getIndexName(),
                bot.getContentName(),
                bot.getContentTargetName(),
                "Type Size index");
        HTMLContentFrame content = new HTMLContentFrame();
        content.init(bot.getContentName(),"Type Size content");

        //We create the visitor
        this.head = head;
        tsvisitor = new HTMLTSVisitor(index, content);

        //We visit
        head.acceptVisitor(tsvisitor);

        //We terminate all streams
        content.terminate();
        index.terminate();
        bot.terminate();

        System.out.println("Type Size dump - end");
	}

    public void terminate()
    {
        top.terminate();
        container.terminate();
    }

    /**
     * Static method that published the title
     * @return
     */
    public static String getTitle(){return TITLE;}

}
