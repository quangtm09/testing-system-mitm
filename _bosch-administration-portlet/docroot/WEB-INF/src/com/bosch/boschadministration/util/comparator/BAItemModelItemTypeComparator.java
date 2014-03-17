package com.bosch.boschadministration.util.comparator;

import com.bosch.boschadministration.model.BAItemModel;
import com.liferay.portal.kernel.util.OrderByComparator;


/**
 * The Class BAItemModelItemTypeComparator.
 */
public class BAItemModelItemTypeComparator extends OrderByComparator {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8705745607383372749L;

    /** The order by asc. */
    public static String ORDER_BY_ASC = "status ASC";

    /** The order by desc. */
    public static String ORDER_BY_DESC = "status DESC";

    /**
     * Instantiates a new bA item model item type comparator.
     */
    public BAItemModelItemTypeComparator() {
        this(false);
    }

    /**
     * Instantiates a new bA item model item type comparator.
     *
     * @param asc the asc
     */
    public BAItemModelItemTypeComparator(final boolean asc) {
        this._asc = asc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.liferay.portal.kernel.util.OrderByComparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final Object obj1, final Object obj2) {
        final BAItemModel instance1 = (BAItemModel) obj1;
        final BAItemModel instance2 = (BAItemModel) obj2;
        final int value = instance1.getItemType().toLowerCase().compareTo(instance2.getItemType().toLowerCase());

        if (this._asc) {
            return value;
        } else {
            return -value;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.liferay.portal.kernel.util.OrderByComparator#getOrderBy()
     */
    @Override
    public String getOrderBy() {

        if (this._asc) {
            return ORDER_BY_ASC;
        } else {
            return ORDER_BY_DESC;
        }
    }

    /** The _asc. */
    private final boolean _asc;

}
