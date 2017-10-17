

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oreditions.champollion.visitor;

import com.oreditions.champollion.model.typesize.*;

/**
 *
 * @author olivier
 */
public interface TSVisitor
{
    public void visit(TSHead head);
    public void visit(TSType type);
    public void visit(TSInterval interval);
}
