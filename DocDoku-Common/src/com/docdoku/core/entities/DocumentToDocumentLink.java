/*
 * DocDoku, Professional Open Source
 * Copyright 2006, 2007, 2008, 2009, 2010 DocDoku SARL
 *
 * This file is part of DocDoku.
 *
 * DocDoku is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DocDoku is distributed in the hope that it will be useful,  
 * but WITHOUT ANY WARRANTY; without even the implied warranty of  
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
 * GNU General Public License for more details.  
 *  
 * You should have received a copy of the GNU General Public License  
 * along with DocDoku.  If not, see <http://www.gnu.org/licenses/>.  
 */

package com.docdoku.core.entities;

import com.docdoku.core.entities.keys.DocumentKey;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This is a link class used to connect two Document objects.
 * Documents are not linked directly but rather through this class to get
 * a loosely coupling.  
 * 
 * @author Florent GARIN
 * @version 1.0, 02/06/08
 * @since   V1.0
 */
@javax.persistence.IdClass(com.docdoku.core.entities.keys.DocumentToDocumentLinkKey.class)
@javax.persistence.Entity
public class DocumentToDocumentLink implements Serializable, Cloneable{
    
    
    
    @ManyToOne(optional=false, fetch=FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name="FROMDOCUMENT_ITERATION", referencedColumnName="ITERATION"),
        @JoinColumn(name="FROMDOCUMENT_MASTERDOCUMENT_ID", referencedColumnName="MASTERDOCUMENT_ID"),
        @JoinColumn(name="FROMDOCUMENT_MASTERDOCUMENT_VERSION", referencedColumnName="MASTERDOCUMENT_VERSION"),
        @JoinColumn(name="FROMDOCUMENT_WORKSPACE_ID", referencedColumnName="WORKSPACE_ID")
    })
    private Document fromDocument;
    
    @Column(name = "FROMDOCUMENT_ITERATION", nullable = false, insertable = false, updatable = false)
    @javax.persistence.Id
    private int fromDocumentIteration;
    
    @Column(name = "FROMDOCUMENT_MASTERDOCUMENT_ID", length=50, nullable = false, insertable = false, updatable = false)
    @javax.persistence.Id
    private String fromDocumentMasterDocumentId="";
    
    @Column(name = "FROMDOCUMENT_MASTERDOCUMENT_VERSION", length=10, nullable = false, insertable = false, updatable = false)
    @javax.persistence.Id
    private String fromDocumentMasterDocumentVersion="";
    
    @Column(name = "FROMDOCUMENT_WORKSPACE_ID", length=50, nullable = false, insertable = false, updatable = false)
    @javax.persistence.Id
    private String fromDocumentWorkspaceId="";
    
    
    
    @Column(name = "TODOCUMENT_ITERATION")
    @javax.persistence.Id
    private int toDocumentIteration;

    @Column(name = "TODOCUMENT_MASTERDOCUMENT_ID", length=50)
    @javax.persistence.Id
    private String toDocumentMasterDocumentId="";

    @Column(name = "TODOCUMENT_MASTERDOCUMENT_VERSION", length=10)
    @javax.persistence.Id
    private String toDocumentMasterDocumentVersion="";

    @Column(name = "TODOCUMENT_WORKSPACE_ID", length=50)
    @javax.persistence.Id
    private String toDocumentWorkspaceId="";
    
    private String comment;
    
    
    public DocumentToDocumentLink() {
    }
    
    public DocumentToDocumentLink(Document pFromDocument, Document pToDocument, String pComment){
        setFromDocument(pFromDocument);
        setToDocument(pToDocument);
        comment=pComment;
    }
    
    public DocumentToDocumentLink(Document pFromDocument, DocumentKey pToDocumentKey){
        setFromDocument(pFromDocument);
        setToDocument(pToDocumentKey);
    }

    public DocumentKey getToDocumentKey(){
        return new DocumentKey(toDocumentWorkspaceId,toDocumentMasterDocumentId,toDocumentMasterDocumentVersion,toDocumentIteration);
    }
    
    public String getComment() {
        return comment;
    }

    @XmlTransient
    public Document getFromDocument() {
        return fromDocument;
    }

    public int getFromDocumentIteration() {
        return fromDocumentIteration;
    }

    public String getFromDocumentMasterDocumentId() {
        return fromDocumentMasterDocumentId;
    }

    public String getFromDocumentMasterDocumentVersion() {
        return fromDocumentMasterDocumentVersion;
    }

    public String getFromDocumentWorkspaceId() {
        return fromDocumentWorkspaceId;
    }

    public int getToDocumentIteration() {
        return toDocumentIteration;
    }

    public String getToDocumentMasterDocumentId() {
        return toDocumentMasterDocumentId;
    }

    public String getToDocumentMasterDocumentVersion() {
        return toDocumentMasterDocumentVersion;
    }

    public String getToDocumentWorkspaceId() {
        return toDocumentWorkspaceId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setToDocumentIteration(int toDocumentIteration) {
        this.toDocumentIteration = toDocumentIteration;
    }

    public void setToDocumentMasterDocumentId(String toDocumentMasterDocumentId) {
        this.toDocumentMasterDocumentId = toDocumentMasterDocumentId;
    }

    public void setToDocumentMasterDocumentVersion(String toDocumentMasterDocumentVersion) {
        this.toDocumentMasterDocumentVersion = toDocumentMasterDocumentVersion;
    }

    public void setToDocumentWorkspaceId(String toDocumentWorkspaceId) {
        this.toDocumentWorkspaceId = toDocumentWorkspaceId;
    }
    

    
    public void setFromDocument(Document pFromDocument) {
        fromDocument = pFromDocument;
        fromDocumentIteration=fromDocument.getIteration();
        fromDocumentMasterDocumentId=fromDocument.getMasterDocumentId();
        fromDocumentMasterDocumentVersion=fromDocument.getMasterDocumentVersion();
        fromDocumentWorkspaceId=fromDocument.getWorkspaceId();
    }
    
    public void setToDocument(Document pToDocument) {
        toDocumentIteration=pToDocument.getIteration();
        toDocumentMasterDocumentId=pToDocument.getMasterDocumentId();
        toDocumentMasterDocumentVersion=pToDocument.getMasterDocumentVersion();
        toDocumentWorkspaceId=pToDocument.getWorkspaceId();
    }
    
    public void setToDocument(DocumentKey pToDocumentKey) {
        toDocumentIteration=pToDocumentKey.getIteration();
        toDocumentMasterDocumentId=pToDocumentKey.getMasterDocumentId();
        toDocumentMasterDocumentVersion=pToDocumentKey.getMasterDocumentVersion();
        toDocumentWorkspaceId=pToDocumentKey.getWorkspaceId();
    }
    
    @Override
    public String toString() {
        return toDocumentMasterDocumentId  + "-" + toDocumentMasterDocumentVersion + "-" + toDocumentIteration;
    }
    
    @Override
    public boolean equals(Object pObj) {
        if (this == pObj) {
            return true;
        }
        if (!(pObj instanceof DocumentToDocumentLink))
            return false;
        DocumentToDocumentLink link = (DocumentToDocumentLink) pObj;
        return ((link.fromDocumentWorkspaceId.equals(fromDocumentWorkspaceId))
        && (link.fromDocumentMasterDocumentId.equals(fromDocumentMasterDocumentId))
        && (link.fromDocumentMasterDocumentVersion.equals(fromDocumentMasterDocumentVersion))
        && (link.fromDocumentIteration==fromDocumentIteration)
        && (link.toDocumentWorkspaceId.equals(toDocumentWorkspaceId))
        && (link.toDocumentMasterDocumentId.equals(toDocumentMasterDocumentId))
        && (link.toDocumentMasterDocumentVersion.equals(toDocumentMasterDocumentVersion))
        && (link.toDocumentIteration==toDocumentIteration));
    }

    @Override
    public int hashCode() {
        int hash = 1;
	hash = 31 * hash + fromDocumentWorkspaceId.hashCode();
	hash = 31 * hash + fromDocumentMasterDocumentId.hashCode();
        hash = 31 * hash + fromDocumentMasterDocumentVersion.hashCode();
        hash = 31 * hash + fromDocumentIteration;
        hash = 31 * hash + toDocumentWorkspaceId.hashCode();
	hash = 31 * hash + toDocumentMasterDocumentId.hashCode();
        hash = 31 * hash + toDocumentMasterDocumentVersion.hashCode();
        hash = 31 * hash + toDocumentIteration;
	return hash;
    }
    

    @Override
    public DocumentToDocumentLink clone() {
        DocumentToDocumentLink clone = null;
        try {
            clone = (DocumentToDocumentLink) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        
        return clone;
    }
}
