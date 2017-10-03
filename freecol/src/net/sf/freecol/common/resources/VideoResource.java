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

import java.net.MalformedURLException;
import java.net.URI;


/**
 * A <code>Resource</code> wrapping a <code>Video</code>.
 * 
 * @see Resource
 * @see Video
 */
public class VideoResource extends Resource {

    private Video video;

    
    /**
     * Do not use directly.
     *
     * @param resourceLocator The <code>URI</code> used when loading this
     *     resource.
     */
    public VideoResource(URI resourceLocator) throws Exception {
    	this.initializeResource(resourceLocator);
    }
    
    protected VideoResource() {}

    /**
     * Gets the <code>Video</code> represented by this resource.
     *
     * @return The <code>Video</code> in its original size.
     */
    public Video getVideo() {
        return this.video;
    }


	@Override
	public boolean satisfiesURI(URI uri) {
		return (!"urn".equals(uri.getScheme())
				&& uri.getPath().endsWith(".ogg") && uri.getPath().endsWith(".video.ogg"));
	}


	@Override
	public void initializeResource(URI uri) {
		this.setResourceLocator(uri);
        try {
			this.video = new Video(uri.toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}
