/**
 * 
 */
var maxValue;
function replaceSpelcialCharater(text) {
	return text.replace(/:/g, '\\:');
}
function checkValueFromAndTo(fromSpinnerId, toSpinnerId) {	
	fromSpinnerId = replaceSpelcialCharater(fromSpinnerId);
	toSpinnerId = replaceSpelcialCharater(toSpinnerId);
	fromSpinnerInput = jQuery('#' + fromSpinnerId + ' input[type=text]');
	toSpinnerInput = jQuery('#' + toSpinnerId + ' input[type=text]');
	fromVal = fromSpinnerInput.val();
	toVal = toSpinnerInput.val();
	fromValInt = parseInt(fromVal);
	toValInt = parseInt(toVal);
	if (fromValInt >= toValInt && fromValInt != 0) {
		toSpinnerInput.val(fromValInt + 1);		
	} 	
}

function checkValueFromAndToEqAllowed(fromSpinnerId, toSpinnerId) {
	fromSpinnerId = replaceSpelcialCharater(fromSpinnerId);
	toSpinnerId = replaceSpelcialCharater(toSpinnerId);
	fromSpinnerInput = jQuery('#' + fromSpinnerId + ' input[type=text]');
	toSpinnerInput = jQuery('#' + toSpinnerId + ' input[type=text]');
	fromVal = fromSpinnerInput.val();
	toVal = toSpinnerInput.val();
	fromValInt = parseInt(fromVal);
	toValInt = parseInt(toVal);
	if (fromValInt >= toValInt) {
		toSpinnerInput.val(fromValInt);
	}
}
function trimFormInputText(formElement) {
	jQuery(formElement).find('input:text').each(function() {
		jQuery(this).val(jQuery.trim(jQuery(this).val()));
	});
}
function checkMaxValue(spinerID) {
	spinerID = replaceSpelcialCharater(fromSpinnerId);
	spinerInput = jQuery('#' + spinerID + ' input[type=text]');
	val = spinerInput.val();
	if (val > maxValue) {
		val = maxValue;
	}
}

function applyEnterEventHandler() {
	jQuery("input[type=text]").bind('keydown', function(e) {
		if (e.which == 13 || e.which == 3) {

			openWaitDialog();
			excuteSearch();
		}
	});
}
