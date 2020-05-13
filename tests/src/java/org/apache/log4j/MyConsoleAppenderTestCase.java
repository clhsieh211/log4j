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
public class MyConsoleAppenderTestCase extends TestCase {

    public void testConstructorWithLayout() {
        Appender app = new ConsoleAppender(new SimpleLayout());
        String layoutName = MyConsoleAppenderTestCase.class.getPackage().getName() + ".SimpleLayout";

        assertEquals(layoutName, app.getLayout().getClass().getName());
    }

    public void testTargetWarn() throws IOException {
        String errMsgPath = "output/CoverageTest/targetWarn";
        FileOutputStream ferr = new FileOutputStream(errMsgPath, false);
        PrintStream err = new PrintStream(ferr);
        System.setErr(err);

        ConsoleAppender app = new ConsoleAppender();
        String val = "str";
        app.targetWarn(val);

        String buf = (new BufferedReader(new FileReader(errMsgPath))).readLine();

        assertEquals("log4j:WARN ["+val+"] should be System.out or System.err.", buf);
    }

    public void testFollow() {
        ConsoleAppender app = new ConsoleAppender();
        app.setFollow(true);
        assertTrue(app.getFollow());
    }

    public void testTarget() {
        ConsoleAppender app = new ConsoleAppender();
        String target = "System.err";
        app.setTarget(target);
        assertEquals(target, app.getTarget());
    }

    public void testActivateOptionsWithTrueSysOut() {
        ConsoleAppender app = new ConsoleAppender();
        app.setFollow(true);
        app.setTarget("System.out");
        app.activateOptions();
        assertNotNull(app.qw);
    }

    public void testActivateOptionsWithTrueSysErr() {
        ConsoleAppender app = new ConsoleAppender();
        app.setFollow(true);
        app.setTarget("System.err");
        app.activateOptions();
        assertNotNull(app.qw);
    }

    public void testActivateOptionsWithFalseSysOut() {
        ConsoleAppender app = new ConsoleAppender();
        app.setFollow(false);
        app.setTarget("System.out");
        app.activateOptions();
        assertNotNull(app.qw);
    }

    public void testActivateOptionsWithFalseSysErr() {
        ConsoleAppender app = new ConsoleAppender();
        app.setFollow(false);
        app.setTarget("System.err");
        app.activateOptions();
        assertNotNull(app.qw);
    }

}

