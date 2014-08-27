<div class="agittemplate" id="main-content" role="main">
	#if ($browserSniffer.isIe($request) && $browserSniffer.getMajorVersion($request) < 8)
		<table class="portlet-layout row-fluid">
		<tr>
			<td class="aui span3 portlet-column portlet-column-first" id="column-1">
				$processor.processColumn("column-1", "portlet-column-content portlet-column-content-first")
			</td>
			<td class="aui span6 portlet-column" id="column-2">
				$processor.processColumn("column-2", "portlet-column-content")
			</td>
			<td class="aui span3 portlet-column portlet-column-last" id="column-3">
                $processor.processColumn("column-3", "portlet-column-content portlet-column-content-last")
            </td>
		</tr>
		</table>
	#else
		<div class="portlet-layout row-fluid">
			<div class="span3 portlet-column portlet-column-first" id="column-1">
				$processor.processColumn("column-1", "portlet-column-content portlet-column-content-first")
			</div>

			<div class="span6 portlet-column" id="column-2">
				$processor.processColumn("column-2", "portlet-column-content")
			</div>
			
			<div class="span3 portlet-column portlet-column-last" id="column-3">
                $processor.processColumn("column-3", "portlet-column-content portlet-column-content-last")
            </div>
		</div>
	#end
</div>