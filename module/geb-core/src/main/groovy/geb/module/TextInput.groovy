/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package geb.module

import geb.Module
import geb.error.InvalidModuleBaseException

class TextInput extends Module {

    @Override
    protected void initialized() {
        if (!empty) {
            if (tag().toLowerCase() != "input") {
                throw new InvalidModuleBaseException("Specified base element for ${TextInput.name} module was '${tag()}' but only input is allowed as the base element.")
            }
            def type = getAttribute("type")
            if (type != "text") {
                throw new InvalidModuleBaseException("Specified base element for ${TextInput.name} module was an input of type '$type' but only input of type text is allowed as the base element.")
            }
        }
    }

    String getText() {
        value()
    }

    void setText(String text) {
        value(text)
    }
}