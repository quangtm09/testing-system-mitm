package com.bosch.boschadministration.permission;

import com.liferay.portal.security.permission.PermissionChecker;

/**
 * The Class BAPermission.
 */
public class BAPermission {

    /**
     * Contains.
     * 
     * @param permissionChecker
     *            the permission checker
     * @param resourceName
     *            the resource name
     * @param groupId
     *            the group id
     * @param actionId
     *            the action id
     * @return true, if successful
     */
    public static boolean contains(final PermissionChecker permissionChecker, final String resourceName,
            final long groupId, final String actionId) {

        return permissionChecker.hasPermission(groupId, resourceName, groupId, actionId);
    }

}
