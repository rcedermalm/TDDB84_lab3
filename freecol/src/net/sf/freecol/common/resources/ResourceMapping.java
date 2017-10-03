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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Represents a mapping between identifiers and resources.
 *
 * @see Resource
 */
public final class ResourceMapping {

    /** Mapping between an object identifier and a resource. */
    private final HashMap<String, Resource> resources;

    /**
     * Creates a new empty <code>ResourceMapping</code>.
     */
    public ResourceMapping() {
        resources = new HashMap<>();
    }


    public boolean add(String key, Resource value) {
        resources.put(key, value);
        return true;
    }
    
    /**
     * Create another mapping for a Resource under a different key.
     *
     * @param key The key to find the existing Resource.
     * @param keyNew The new key for the duplicate.
     * @return true on success
     */
    public boolean duplicateResource(String key, String keyNew) {
        Resource resource = resources.get(key);
        if(resource != null) {
            return add(keyNew, resource);
        }
        return false;
    }
    
    /**
     * Adds all mappings from the given <code>ResourceMapping</code> to
     * this object.
     *
     * @param rc The <code>ResourceMapping</code>.
     */
    public void addAll(ResourceMapping rc) {
        if (rc != null) {
        	resources.putAll(rc.resources);
        }
    }
    
    /**
     * Returns all the mappings between IDs and <code>Resource</code>s
     * that are kept by this object.
     *
     * @return An unmodifiable <code>Map</code>.
     */
    public Map<String, Resource> getResources() {
        HashMap<String, Resource> result = new HashMap<>();
        result.putAll(resources);
        
        return result;
    }
    
    public Map<String, ImageResource> getImageResources() {
    	HashMap<String, ImageResource> imageResources = new HashMap<String, ImageResource>();
    	
		for(String key: resources.keySet()) {
    		if(key.startsWith("image.")) {
    			imageResources.put(key, (ImageResource) resources.get(key));
    		}
    	}
        return imageResources;
    }
    
    public boolean containsKey(String key) {
        return resources.containsKey(key);
    }
 
    /**
     * Gets the <code>Resource</code> by identifier.
     *
     * @param key The resource identifier.
     * @return The <code>Resource</code>.
     */
    public Resource getResource(String key) {
        return resources.get(key);
    }
    
    /**
     * Get the keys in this mapping with a given prefix as a list.
     *
     * @param prefix The prefix to check for.
     * @return A list of keys.
     */
    public List<String> getKeys(String prefix) {
        return resources.keySet().stream()
            .filter(k -> k.startsWith(prefix)).collect(Collectors.toList());
    }

    /**
     * Get the image keys in this mapping with a given prefix as a set.
     *
     * @param prefix The prefix to check for.
     * @return The set of keys.
     */
    public Set<String> getKeySet(String prefix) {
        return resources.keySet().stream()
            .filter(k -> k.startsWith(prefix)).collect(Collectors.toSet());
    }

    /**
     * Get the image keys in this mapping with a given prefix and
     * suffix as a list.
     *
     * @param prefix The prefix to check for.
     * @param suffix The suffix to check for.
     * @return A list of keys.
     */
    public List<String> getKeys(String prefix, String suffix) {
        return resources.keySet().stream()
            .filter(k -> k.startsWith(prefix) && k.endsWith(suffix))
            .collect(Collectors.toList());
    }
}
