package com.bosch.rts.validator;

import java.util.Comparator;
import java.util.Date;

import com.bosch.rts.entity.RecruitRequestHasAttachment;

/**
 * The Class AttachmentComparator.
 */
public class AttachmentComparator implements Comparator<RecruitRequestHasAttachment>
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(RecruitRequestHasAttachment att1, RecruitRequestHasAttachment att2) {
		
		Date d1 = null;
		Date d2 = null;
		
		if(att1 != null && att1.getAttachmentCreatedOn() != null){
			d1 = att1.getAttachmentCreatedOn();
		}
		
		if(att2 != null && att2.getAttachmentCreatedOn() != null){
			d2 = att2.getAttachmentCreatedOn();
		}
		
		int result = 0;
		
		if(d1 == null && d2 == null){
			result = 0;
		}
		else if(d1 == null){
			result = -1;
		}
		else if(d2 == null){
			result = 1;
		}
		else{
			result = d1.compareTo(d2);
		}
		
		//order by DESC
		result = 0 - result;
		
		return result;
	}

}
