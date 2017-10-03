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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A factory class for creating <code>Resource</code> instances.
 * @see Resource
 */
public class ResourceFactory {

    private static final Logger logger = Logger.getLogger(ResourceFactory.class.getName());

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
            if ("urn".equals(uri.getScheme())) {
                if (uri.getSchemeSpecificPart().startsWith(ColorResource.SCHEME)) {
                    ColorResource resource = new ColorResource(uri);
                    rm.add(key, resource);
                } else if (uri.getSchemeSpecificPart().startsWith(FontResource.SCHEME)) {
                    FontResource resource = new FontResource(uri);
                    rm.add(key, resource);

                }
            } else if (uri.getPath().endsWith("\"")
                    && uri.getPath().lastIndexOf('"',
                            uri.getPath().length()-1) >= 0) {
                StringResource resource = new StringResource(uri);
                rm.add(key, resource);
            } else if (uri.getPath().endsWith(".faf")) {
                FAFileResource resource = new FAFileResource(uri);
                rm.add(key, resource);
            } else if (uri.getPath().endsWith(".sza")) {
                SZAResource resource = new SZAResource(uri);
                rm.add(key, resource);

            } else if (uri.getPath().endsWith(".ttf")) {
                FontResource resource  = new FontResource(uri);
                rm.add(key, resource);
            } else if (uri.getPath().endsWith(".wav")) {
                AudioResource resource = new AudioResource(uri);
                rm.add(key, resource);
            } else if (uri.getPath().endsWith(".ogg")) {
                if (uri.getPath().endsWith(".video.ogg")) {
                    VideoResource resource = new VideoResource(uri);
                    rm.add(key, resource);
                } else {
                    AudioResource resource = new AudioResource(uri);
                    rm.add(key, resource);
                }
            } else {
                ImageResource resource = new ImageResource(uri);
                rm.add(key, resource);
            }
            

        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to create resource with URI: " + uri, e);
        }
    }

}
