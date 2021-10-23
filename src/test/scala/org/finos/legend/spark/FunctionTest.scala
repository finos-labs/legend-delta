/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2021 Databricks - see NOTICE.md file
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

import org.finos.legend.engine.language.pure.grammar.from.PureGrammarParser
import org.finos.legend.engine.protocol.pure.v1.model.valueSpecification.ValueSpecification
import org.scalatest.flatspec.AnyFlatSpec

trait FunctionTest extends AnyFlatSpec {

  implicit class StringPure(lambda: String) {
    def toValueSpec: ValueSpecification = {
      val parser = PureGrammarParser.newInstance()
      val parsed = parser.parseLambda(lambda, "id", true)
      require(parsed.body != null && parsed.body.size() > 0)
      parsed.body.get(0)
    }
  }

}
