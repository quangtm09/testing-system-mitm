package com.bosch.boschadministration.util.comparator;

import com.bosch.boschadministration.model.BAItemModel;
import com.liferay.portal.kernel.util.OrderByComparator;


/**
 * The Class BAItemModelItemValueComparator.
 */
public class BAItemModelItemValueComparator extends OrderByComparator {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2730924012233921687L;

    /** The order by asc. */
    public static String ORDER_BY_ASC = "status ASC";

    /** The order by desc. */
    public static String ORDER_BY_DESC = "status DESC";

    /**
     * Instantiates a new bA item model item value comparator.
     */
    public BAItemModelItemValueComparator() {
        this(false);
    }

    /**
     * Instantiates a new bA item model item value comparator.
     *
     * @param asc the asc
     */
    public BAItemModelItemValueComparator(final boolean asc) {
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
        final int value = instance1.getItemValue().toLowerCase().compareTo(instance2.getItemValue().toLowerCase());

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
