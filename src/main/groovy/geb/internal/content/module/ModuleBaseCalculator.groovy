/* Copyright 2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package geb.internal.content.module

import geb.error.InvalidPageContent
import be.roam.hue.doj.Doj

class ModuleBaseCalculator {

	static Doj calculate(Class moduleClass, Doj startingBase, Map params) {
		def moduleBaseDefinition = moduleClass.base
		if (!moduleBaseDefinition) {
			startingBase
		} else {
			// Clone it because the same closure may be used
			// via through a subclass and have a different base
			def moduleBaseDefinitionClone = moduleBaseDefinition.clone()
			moduleBaseDefinitionClone.delegate = new ModuleBaseDefinitionDelegate(startingBase, params)
			def moduleBase = moduleBaseDefinitionClone()
			
			if (!(moduleBase instanceof Doj)) {
				throw new InvalidPageContent("The static 'base' parameter of module class $moduleClass did not return a Doj")
			}
			
			moduleBase
		}
	}
	
}