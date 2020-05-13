/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.log4j;

import junit.framework.TestCase;
import org.apache.log4j.helpers.LogLog;

import java.io.*;


/**
 A superficial but general test of log4j.
 */
public class MyAsyncAppenderTestCase extends TestCase {

    public void testGetAllAppender() {
        AsyncAppender app = new AsyncAppender();
        app.addAppender(new ConsoleAppender());
        assertNotNull(app.getAllAppenders());
    }

    public void testGetAppender() {
        String name = "name";
        AsyncAppender app = new AsyncAppender();
        ConsoleAppender c = new ConsoleAppender();
        c.setName(name);
        app.addAppender(c);
        assertEquals(c.toString(), app.getAppender(name).toString());
    }

    public void testLocationInfo() {
        AsyncAppender app = new AsyncAppender();
        app.setLocationInfo(true);
        assertTrue(app.getLocationInfo());
    }

    public void testIsAttached() {
        AsyncAppender app = new AsyncAppender();
        ConsoleAppender c = new ConsoleAppender();
        app.addAppender(c);
        assertTrue(app.isAttached(c));
    }

    public void testRequiresLayout() {
        AsyncAppender app = new AsyncAppender();
        assertFalse(app.requiresLayout());
    }

    public void testRemoveAllAppender() {
        AsyncAppender app = new AsyncAppender();
        app.addAppender(new ConsoleAppender());
        app.addAppender(new FileAppender());
        app.removeAllAppenders();
        assertNull(app.getAllAppenders());
    }

    public void testRemoveAppenderByRef() {
        AsyncAppender app = new AsyncAppender();
        ConsoleAppender c = new ConsoleAppender();
        app.addAppender(c);
        app.removeAppender(c);
        assertFalse(app.getAllAppenders().hasMoreElements());
    }

    public void testRemoveAppenderByName() {
        AsyncAppender app = new AsyncAppender();
        ConsoleAppender c = new ConsoleAppender();
        String name = "name";
        c.setName(name);
        app.removeAppender(name);
        assertNull(app.getAllAppenders());
    }

    public void testGetBufferSize() {
        AsyncAppender app = new AsyncAppender();
        app.setBufferSize(1024);
        assertEquals(1024, app.getBufferSize());
    }

    public void testBlocking() {
        AsyncAppender app = new AsyncAppender();
        app.setBlocking(true);
        assertTrue(app.getBlocking());
    }
}

