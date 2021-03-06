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
import java.net.URL;


/**
 * A <code>Resource</code> wrapping a <code>FAFile</code>.
 *
 * @see Resource
 * @see FAFile
 */
public class FAFileResource extends Resource {

    private FAFile FAFile;


    public FAFileResource(FAFile FAFile) {
        this.FAFile = FAFile;
    }
    
    protected FAFileResource() {}


    /**
     * Do not use directly.
     *
     * @param resourceLocator The <code>URI</code> used when loading this
     *      resource.
     */
    public FAFileResource(URI resourceLocator) throws Exception {
    	this.initializeResource(resourceLocator);
    }


    /**
     * Gets the <code>FAFile</code> represented by this resource.
     *
     * @return The <code>FAFile</code> for this resource, or the default
     *     Java FAFile if none found.
     */
    public FAFile getFAFile() {
        return FAFile;
    }

	@Override
	public boolean satisfiesURI(URI uri) {
		return (!"urn".equals(uri.getScheme())
				&& uri.getPath().endsWith(".faf"));
	}

	@Override
	public void initializeResource(URI uri){
		this.setResourceLocator(uri);
        URL url;
		try {
			url = uri.toURL();
			FAFile = new FAFile(url.openStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
