/*
 * Copyright 2004-2010 Information & Software Engineering Group (188/1)
 *                     Institute of Software Technology and Interactive Systems
 *                     Vienna University of Technology, Austria
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.ifs.tuwien.ac.at/dm/somtoolbox/license.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.tuwien.ifs.somtoolbox.apps.viewer;

import at.tuwien.ifs.somtoolbox.visualization.Palette;

/**
 * Notifies listeners of a change in the currently selected {@link Palette}.
 * 
 * @author Rudolf Mayer
 * @version $Id: PaletteChangeListener.java 4133 2011-02-08 12:55:55Z mayer $
 */
public interface PaletteChangeListener {

    public void paletteChanged(Palette palette);

}
