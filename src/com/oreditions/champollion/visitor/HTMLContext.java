/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.champollion.visitor;

import com.oreditions.champollion.HTMLrendering.*;

/**
 *
 * @author olivier
 */
public class HTMLContext
{
    protected HTMLIndexFrame index= null;
    protected HTMLContentFrame content = null;

    public HTMLContext(HTMLIndexFrame index, HTMLContentFrame content)
    {
        this.index = index;
        this.content = content;
    }
}
