/*
 * Copyright 2016 OrientDB LTD (info--at--orientdb.com)
 * All Rights Reserved. Commercial License.
 *
 * NOTICE:  All information contained herein is, and remains the property of
 * OrientDB LTD and its suppliers, if any.  The intellectual and
 * technical concepts contained herein are proprietary to
 * OrientDB LTD and its suppliers and may be covered by United
 * Kingdom and Foreign Patents, patents in process, and are protected by trade
 * secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from OrientDB LTD.
 *
 * For more information: http://www.orientdb.com
 */

package com.mdm.configuration.api;

import java.util.List;

/**
 * @author Gabriele Ponzi
 * @email <g.ponzi--at--orientdb.com>
 */

public class OEdgeMappingInformation {

  private OConfiguredEdgeClass        belongingEdge;                        // mandatory
  private String                      fromTableName;                                      // mandatory
  private String                      toTableName;                                        // mandatory
  private List<String>                fromColumns;                                  // mandatory
  private List<String>                toColumns;                                    // mandatory
  private String                      direction;                                          // mandatory
  private OAggregatedJoinTableMapping representedJoinTableMapping;   // may be null if the edge does not represent a join table

  // Lazy loading: classes are stored in this variables in order to avoid a double scan of the vertices when fromProperty and toProperty are requested.
  private OConfiguredVertexClass fromVertexClass;
  private OConfiguredVertexClass toVertexClass;

  public OEdgeMappingInformation(OConfiguredEdgeClass belongingEdge) {
    this.belongingEdge = belongingEdge;
  }

  public OConfiguredEdgeClass getBelongingEdge() {
    return this.belongingEdge;
  }

  public void setBelongingEdge(OConfiguredEdgeClass belongingEdge) {
    this.belongingEdge = belongingEdge;
  }

  public String getFromTableName() {
    return this.fromTableName;
  }

  public void setFromTableName(String fromTableName) {
    this.fromTableName = fromTableName;
  }

  public String getToTableName() {
    return this.toTableName;
  }

  public void setToTableName(String toTableName) {
    this.toTableName = toTableName;
  }

  public List<String> getFromColumns() {
    return this.fromColumns;
  }

  public void setFromColumns(List<String> fromColumns) {
    this.fromColumns = fromColumns;
  }

  public List<String> getToColumns() {
    return this.toColumns;
  }

  public void setToColumns(List<String> toColumns) {
    this.toColumns = toColumns;
  }

  public String getFromClass() {
    if(this.fromVertexClass == null) {
      this.fromVertexClass = belongingEdge.getGlobalConfiguration().getVertexClassByTableName(this.fromTableName);
    }
    return this.fromVertexClass.getName();
  }

  public String getToClass() {
    if(this.toVertexClass == null) {
      this.toVertexClass = belongingEdge.getGlobalConfiguration().getVertexClassByTableName(this.toTableName);
    }
    return this.toVertexClass.getName();
  }

  public String[] getFromProperties() {
    if(this.fromVertexClass == null) {
      this.fromVertexClass = belongingEdge.getGlobalConfiguration().getVertexClassByTableName(this.toTableName);
    }
    return this.fromVertexClass.getPropertiesByColumns(this.fromColumns);
  }

  public String[] getToProperty() {
    if(this.toVertexClass == null) {
      this.toVertexClass = belongingEdge.getGlobalConfiguration().getVertexClassByTableName(this.toTableName);
    }
    return this.toVertexClass.getPropertiesByColumns(this.toColumns);
  }

  public String getDirection() {
    return this.direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public OAggregatedJoinTableMapping getRepresentedJoinTableMapping() {
    return this.representedJoinTableMapping;
  }

  public void setRepresentedJoinTableMapping(OAggregatedJoinTableMapping representedJoinTableMapping) {
    this.representedJoinTableMapping = representedJoinTableMapping;
  }

}