/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2021 FINOS Legend2Delta contributors - see NOTICE.md file
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.finos.legend.spark

import java.io.File
import java.net.URISyntaxException
import java.nio.file.Path

import org.finos.legend.sdlc.serialization.EntityLoader

import scala.collection.JavaConverters._

object LegendFileLoader extends LegendLoader {

  @Override
  def loadResources(resourceName: String): Legend = {
    val path = getPath(resourceName)
    val entityLoader: EntityLoader = EntityLoader.newEntityLoader(path)
    val entities = entityLoader.getAllEntities.iterator().asScala.map(e => (e.getPath, e)).toMap
    new Legend(entities)
  }

  @throws[URISyntaxException]
  private def getPath(resourceDirectory: String): Path = {
    val file = new File(resourceDirectory)
    if (!file.exists()) throw new IllegalAccessException(s"File $resourceDirectory does not exist")
    if (!file.isDirectory) throw new IllegalAccessException("Not a directory: " + resourceDirectory)
    file.toPath
  }

}