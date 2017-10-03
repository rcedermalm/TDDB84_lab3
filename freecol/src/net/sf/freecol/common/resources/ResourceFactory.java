/**
 *  Copyright (C) 2002-2015   The FreeCol Team
 *
 *  This file is part of FreeCol.
 *
 *  FreeCol is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  FreeCol is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with FreeCol.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.freecol.common.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A factory class for creating <code>Resource</code> instances.
 * @see Resource
 */
public class ResourceFactory {

    private static final Logger logger = Logger.getLogger(ResourceFactory.class.getName());
    
    private static ArrayList<Resource> prototypes = new ArrayList<>();
    
    static {
    	prototypes.add(new ColorResource());
    	prototypes.add(new FontResource());
    	prototypes.add(new StringResource());
    	prototypes.add(new FAFileResource());
    	prototypes.add(new SZAResource());
    	prototypes.add(new AudioResource());
    	prototypes.add(new VideoResource());
    	prototypes.add(new ImageResource());
    }

    /**
     * Returns an instance of <code>Resource</code> with the
     * given <code>URI</code> as the parameter.
     *
     * @param uri The <code>URI</code> used when creating the
     *      instance.
     * @param rm Where the resource should be mapped and stored.
     * @param key The key value that the resource should be stored under.
     */
    
    public static void createResource(URI uri, ResourceMapping rm, String key) {
    	if(rm.containsKey(key))
            return;
        try {     
        	for(Resource r : prototypes) {
        		if(r.satisfiesURI(uri)) {      			
        			Resource newResource = r.clone();
        			newResource.initializeResource(uri);
        			rm.add(key, newResource);
        			break;
        		}
        	} 
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to create resource with URI: " + uri, e);
        }
    }

}


