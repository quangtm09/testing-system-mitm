/**
 * 
 */
package com.bosch.rts.entity;

/**
 * The Class DummyAllUser.
 *
 * @author bit71hc
 */
public class DummyAllUser extends User {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1084162626753797583L;
	
	/** The instance. */
	private static DummyAllUser instance;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "All User";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return -1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return obj instanceof DummyAllUser;
	}

	/**
	 * Gets the single instance of DummyAllUser.
	 *
	 * @return single instance of DummyAllUser
	 */
	public static DummyAllUser getInstance() {
		if (instance == null) {
			instance = new DummyAllUser();
		}
		return instance;
	}
}
