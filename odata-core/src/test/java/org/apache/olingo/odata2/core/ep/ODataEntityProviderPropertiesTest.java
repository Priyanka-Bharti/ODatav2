/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 *        or more contributor license agreements.  See the NOTICE file
 *        distributed with this work for additional information
 *        regarding copyright ownership.  The ASF licenses this file
 *        to you under the Apache License, Version 2.0 (the
 *        "License"); you may not use this file except in compliance
 *        with the License.  You may obtain a copy of the License at
 * 
 *          http://www.apache.org/licenses/LICENSE-2.0
 * 
 *        Unless required by applicable law or agreed to in writing,
 *        software distributed under the License is distributed on an
 *        "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *        KIND, either express or implied.  See the License for the
 *        specific language governing permissions and limitations
 *        under the License.
 ******************************************************************************/
package org.apache.olingo.odata2.core.ep;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;

import org.apache.olingo.odata2.api.commons.InlineCount;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.testutil.fit.BaseTest;
import org.junit.Test;

/**
 *  
 */
public class ODataEntityProviderPropertiesTest extends BaseTest {

  @Test
  public void buildFeedProperties() throws Exception {
    URI serviceRoot = new URI("http://localhost:80/");
    EntityProviderWriteProperties properties = EntityProviderWriteProperties.serviceRoot(serviceRoot)
        .inlineCountType(InlineCount.ALLPAGES)
        .inlineCount(1)
        .nextLink("http://localhost")
        .build();

    assertEquals("Wrong base uri.", "http://localhost:80/", properties.getServiceRoot().toASCIIString());
    assertEquals("Wrong inline count type.", InlineCount.ALLPAGES, properties.getInlineCountType());
    assertEquals("Wrong inline count.", Integer.valueOf(1), properties.getInlineCount());
    assertEquals("Wrong nextLink", "http://localhost", properties.getNextLink());
  }

  @Test
  public void buildPropertiesDefaults() throws Exception {
    URI baseUri = new URI("http://localhost:80/");
    EntityProviderWriteProperties properties = EntityProviderWriteProperties.serviceRoot(baseUri).build();

    assertEquals("http://localhost:80/", properties.getServiceRoot().toASCIIString());
    assertNull(properties.getInlineCountType());
    assertNull(properties.getInlineCount());
    assertNull(properties.getMediaResourceMimeType());
    assertNull(properties.getNextLink());
  }

  @Test
  public void buildEntryProperties() throws Exception {
    final String mediaResourceMimeType = "text/html";
    final URI serviceRoot = new URI("http://localhost:80/");
    final EntityProviderWriteProperties properties = EntityProviderWriteProperties.serviceRoot(serviceRoot)
        .mediaResourceMimeType(mediaResourceMimeType)
        .build();
    assertEquals("Wrong mime type.", "text/html", properties.getMediaResourceMimeType());
  }
}
