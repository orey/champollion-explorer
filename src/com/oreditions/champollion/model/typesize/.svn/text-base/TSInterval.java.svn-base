/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.champollion.model.typesize;

import com.oreditions.champollion.visitor.TSVisitable;
import com.oreditions.champollion.visitor.TSVisitor;
import java.io.File;
import java.util.TreeMap;

/**
 *
 * @author olivier
 */
public class TSInterval implements TSVisitable
{
    protected long inf = 0, sup = 0;
    protected String descr=null, ext = null;
    protected TreeMap<Long, File> sizes = new TreeMap<Long, File>();
    
    public TSInterval(long inf, long sup, String descr, String ext)
    {
        this.inf = inf;
        //sup can be -1 is there is no sup
        this.sup = sup;
        this.descr = descr;
        this.ext = ext;
    }

    public void addFile(File f)
    {
        sizes.put(f.length(), f);
    }

    public void acceptVisitor(TSVisitor vis)
    {
        vis.visit(this);
    }

    public TreeMap<Long, File> getSizes(){return sizes;}
    public String getDescription(){return descr;}
    public String getExtension(){return ext;}
    public long getSup(){return sup;}
}
