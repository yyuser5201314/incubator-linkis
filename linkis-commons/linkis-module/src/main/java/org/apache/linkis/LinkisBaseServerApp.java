/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.linkis;

import org.apache.linkis.common.utils.Utils;
import org.apache.linkis.server.utils.LinkisMainHelper;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkisBaseServerApp {

  private static final Logger logger = LoggerFactory.getLogger(LinkisBaseServerApp.class);

  public static void main(String[] args) throws ReflectiveOperationException {

    String userName = Utils.getJvmUser();
    String hostName = Utils.getComputerName();
    // val allArgs = args ++
    System.setProperty("hostName", hostName);
    System.setProperty("userName", userName);

    String serviceName = System.getProperty(LinkisMainHelper.SERVER_NAME_KEY());

    System.setProperty("spring.application.name", serviceName);
    LinkisMainHelper.formatPropertyFiles(serviceName);
    String[] allArgs =
        (String[]) ArrayUtils.addAll(args, LinkisMainHelper.getExtraSpringOptions("linkis"));
    String argsString = StringUtils.join(allArgs, "\n");
    String startLog = String.format("Ready to start %s with args: %s.", serviceName, argsString);
    logger.info(startLog);
    DataWorkCloudApplication.main(allArgs);
  }
}
