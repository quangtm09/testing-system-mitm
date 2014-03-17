package com.bosch.rts.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityHome;

import com.bosch.rts.entity.Configuration;

/**
 * The Class ConfigurationHome.
 */
@Name("configurationHome")
public class ConfigurationHome extends EntityHome<Configuration>
{
    
    /** serialVersionUID. */
	private static final long serialVersionUID = 5858878138248960766L;
	
	/** The configuration id. */
	@RequestParameter Long configurationId;

    /* (non-Javadoc)
     * @see org.jboss.seam.framework.Home#getId()
     */
    @Override
    public Object getId()
    {
        if (configurationId == null)
        {
            return super.getId();
        }
        else
        {
            return configurationId;
        }
    }

    /* (non-Javadoc)
     * @see org.jboss.seam.framework.EntityHome#create()
     */
    @Override @Begin
    public void create() {
        super.create();
    }

}
