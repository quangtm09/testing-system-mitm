package dao.impl;

import javax.ejb.Stateless;

import model.Role;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import dao.AbstractHibernateDaoSupport;
import dao.RoleDao;

/**
 * Home object for domain model class Account.
 *
 * @see .Account
 * @author Hibernate Tools
 */
@Stateless
public class RoleDaoImpl extends AbstractHibernateDaoSupport<Role, Integer>
implements RoleDao {
	private static final Logger log = Logger.getLogger(RoleDaoImpl.class);

	public RoleDaoImpl() {
		super(Role.class);
	}

	@Override
	public boolean saveRole(final Role role) {
		log.info("Save Role " + role);
		return save(role);
	}

	@Override
	public Role getRoleById(final Integer roleId) {
		log.info("Search Role " + roleId);
		return findById(roleId);
	}

	@Override
	public boolean updateRole(final Role role) {
		log.info("Update Role " + role);
		return update(role);
	}

	@Override
	public boolean deleteRole(final Role role) {
		log.info("Delete Role " + role);
		return delete(role);
	}

}
