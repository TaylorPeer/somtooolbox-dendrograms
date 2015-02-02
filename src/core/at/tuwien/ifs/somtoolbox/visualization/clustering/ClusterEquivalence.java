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
package at.tuwien.ifs.somtoolbox.visualization.clustering;

/**
 * @author Doris Baum
 * @version $Id: ClusterEquivalence.java 3883 2010-11-02 17:13:23Z frank $
 */
public class ClusterEquivalence implements Comparable<ClusterEquivalence> {
    public double percentage = -1;

    public int cluster1 = -1;

    public int cluster2 = -1;

    @Override
    public int compareTo(ClusterEquivalence other) {
        return Double.compare(this.percentage, other.percentage);
    }
}
