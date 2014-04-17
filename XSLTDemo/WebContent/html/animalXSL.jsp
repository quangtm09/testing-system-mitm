<h2><a href="/XSLTDemo/xml/animal.xsl">animal.xsl</a></h2>
				<br>
				<div style="font-family: courier new; width: 100%">
							<!-- HTML generated using hilite.me --><pre style="margin: 0; line-height: 125%"><span style="color: #557799">&lt;?xml version=&quot;1.0&quot;?&gt;</span>
			<span style="color: #007700">&lt;xsl:stylesheet</span> <span style="color: #0000CC">xmlns:end=</span><span style="background-color: #fff0f0">&quot;http://www.cookwood.com/ns/end_species1&quot;</span>
				<span style="color: #0000CC">xmlns:xsl=</span><span style="background-color: #fff0f0">&quot;http://www.w3.org/1999/XSL/Transform&quot;</span>
				<span style="color: #0000CC">version=</span><span style="background-color: #fff0f0">&quot;1.0&quot;</span>
			<span style="color: #007700">&gt;&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;/&quot;</span><span style="color: #007700">&gt;&lt;html&gt;&lt;head&gt;&lt;title&gt;</span>Endangered Species<span style="color: #007700">&lt;/title&gt;&lt;/head&gt;</span>
			<span style="color: #007700">&lt;body</span> <span style="color: #0000CC">bgcolor=</span><span style="background-color: #fff0f0">&quot;white&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;xsl:apply-templates</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;end:endangered_species/animal&quot;</span><span style="color: #007700">/&gt;</span>
			<span style="color: #007700">&lt;/body&gt;</span>
			<span style="color: #007700">&lt;/html&gt;</span>
			<span style="color: #007700">&lt;/xsl:template&gt;</span>
			
			
			<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;animal&quot;</span><span style="color: #007700">&gt;</span>
			<span style="color: #007700">&lt;p</span> <span style="color: #0000CC">align=</span><span style="background-color: #fff0f0">&quot;center&quot;</span><span style="color: #007700">&gt;&lt;xsl:apply-templates</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;picture&quot;</span><span style="color: #007700">/&gt;</span>
				<span style="color: #007700">&lt;br/&gt;&lt;font</span> <span style="color: #0000CC">size=</span><span style="background-color: #fff0f0">&quot;+3&quot;</span><span style="color: #007700">&gt;&lt;xsl:apply-templates</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;name&quot;</span> <span style="color: #007700">/&gt;&lt;/font&gt;&lt;/p&gt;</span>
				
				<span style="color: #007700">&lt;table</span> <span style="color: #0000CC">width=</span><span style="background-color: #fff0f0">&quot;100%&quot;</span> <span style="color: #0000CC">border=</span><span style="background-color: #fff0f0">&quot;2&quot;</span><span style="color: #007700">&gt;</span>
			<span style="color: #007700">&lt;tr&gt;&lt;th&gt;</span>Subspecies<span style="color: #007700">&lt;/th&gt;&lt;th&gt;</span>Region<span style="color: #007700">&lt;/th&gt;&lt;th&gt;</span>Number Left<span style="color: #007700">&lt;/th&gt;&lt;th&gt;</span>As Of<span style="color: #007700">&lt;/th&gt;&lt;/tr&gt;</span>
				
				<span style="color: #007700">&lt;xsl:for-each</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;subspecies&quot;</span><span style="color: #007700">&gt;</span>	
				<span style="color: #007700">&lt;xsl:sort</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;population&quot;</span> <span style="color: #0000CC">data-type=</span><span style="background-color: #fff0f0">&quot;number&quot;</span><span style="color: #007700">/&gt;</span>
					<span style="color: #007700">&lt;xsl:sort</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;population/@year&quot;</span> <span style="color: #0000CC">data-type=</span><span style="background-color: #fff0f0">&quot;number&quot;</span><span style="color: #007700">/&gt;</span>
					<span style="color: #007700">&lt;tr&gt;&lt;td&gt;&lt;xsl:apply-templates</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;name&quot;</span><span style="color: #007700">/&gt;&lt;/td&gt;</span>
					<span style="color: #007700">&lt;td&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;region&quot;</span><span style="color: #007700">/&gt;&lt;/td&gt;</span>
					<span style="color: #007700">&lt;td&gt;&lt;xsl:apply-templates</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;population&quot;</span><span style="color: #007700">/&gt;&lt;/td&gt;</span>
					<span style="color: #007700">&lt;td&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;population/@year&quot;</span><span style="color: #007700">/&gt;&lt;/td&gt;&lt;/tr&gt;</span>
					<span style="color: #007700">&lt;/xsl:for-each&gt;</span>
					<span style="color: #007700">&lt;tr&gt;&lt;td</span> <span style="color: #0000CC">align=</span><span style="background-color: #fff0f0">&quot;right&quot;</span><span style="color: #007700">&gt;&lt;b&gt;</span>Total:<span style="color: #007700">&lt;/b&gt;&lt;/td&gt;&lt;td&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;count(subspecies)&quot;</span><span style="color: #007700">/&gt;</span> subspecies<span style="color: #007700">&lt;/td&gt;&lt;td&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;sum(subspecies/population)&quot;</span><span style="color: #007700">/&gt;&lt;/td&gt;&lt;td&gt;&lt;br/&gt;&lt;/td&gt;&lt;/tr&gt;</span>
					<span style="color: #007700">&lt;/table&gt;</span>
				<span style="color: #007700">&lt;xsl:apply-templates</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;threats&quot;</span><span style="color: #007700">/&gt;</span>
			
			<span style="color: #007700">&lt;/xsl:template&gt;</span>
			
			<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;name[@language=&#39;English&#39;]&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;nobr&gt;&lt;b&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;.&quot;</span><span style="color: #007700">/&gt;</span>: <span style="color: #007700">&lt;/b&gt;&lt;/nobr&gt;</span>
				<span style="color: #007700">&lt;/xsl:template&gt;</span>
			
			<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;name[@language=&#39;Latin&#39;]&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;nobr&gt;&lt;i&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;.&quot;</span><span style="color: #007700">/&gt;&lt;/i&gt;&lt;/nobr&gt;</span>
			<span style="color: #007700">&lt;/xsl:template&gt;</span>
			<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;subspecies/name[@language=&#39;Latin&#39;]&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;nobr&gt;&lt;i&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;translate(substring(../../name[@language=&#39;Latin&#39;],1,1),&#39;abcdefghijklmnopqrstuvwxyz&#39;,&#39;ABCDEFGHIJKLMNOPQRSTUVWXYZ&#39;)&quot;</span><span style="color: #007700">/&gt;</span>.<span style="color: #007700">&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;substring(substring-after(../../name[@language=&#39;Latin&#39;],&#39; &#39;),1,1)&quot;</span><span style="color: #007700">/&gt;</span>. <span style="color: #007700">&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;.&quot;</span><span style="color: #007700">/&gt;&lt;/i&gt;&lt;/nobr&gt;</span>
			<span style="color: #007700">&lt;/xsl:template&gt;</span>
			
			<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;population&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;xsl:choose&gt;</span>
					<span style="color: #007700">&lt;xsl:when</span> <span style="color: #0000CC">test=</span><span style="background-color: #fff0f0">&quot;. = 0&quot;</span><span style="color: #007700">&gt;</span>
						<span style="color: #007700">&lt;font</span> <span style="color: #0000CC">color=</span><span style="background-color: #fff0f0">&quot;red&quot;</span> <span style="color: #0000CC">title=</span><span style="background-color: #fff0f0">&quot;that means there are no more left&quot;</span><span style="color: #007700">&gt;</span>Extinct<span style="color: #007700">&lt;/font&gt;</span>
					<span style="color: #007700">&lt;/xsl:when&gt;</span>
					
					<span style="color: #007700">&lt;xsl:otherwise&gt;</span>
						<span style="color: #007700">&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;.&quot;</span><span style="color: #007700">/&gt;</span> (<span style="color: #007700">&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;format-number(. div sum(../../subspecies/population), &#39;##0.0%&#39;)&quot;</span><span style="color: #007700">/&gt;</span>)
					<span style="color: #007700">&lt;/xsl:otherwise&gt;</span>
				<span style="color: #007700">&lt;/xsl:choose&gt;</span>
				<span style="color: #007700">&lt;/xsl:template&gt;</span>
			
			<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;threats&quot;</span><span style="color: #007700">&gt;</span>
			<span style="color: #007700">&lt;p&gt;</span>The mighty <span style="color: #007700">&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;../name[@language=&#39;English&#39;]&quot;</span><span style="color: #007700">/&gt;</span> faces numerous threats, among them <span style="color: #007700">&lt;xsl:for-each</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;threat&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;.&quot;</span><span style="color: #007700">/&gt;</span>
				<span style="color: #007700">&lt;xsl:choose&gt;</span>
				<span style="color: #007700">&lt;xsl:when</span> <span style="color: #0000CC">test=</span><span style="background-color: #fff0f0">&quot;position()=last()&quot;</span><span style="color: #007700">&gt;</span>.<span style="color: #007700">&lt;/xsl:when&gt;</span>
				<span style="color: #007700">&lt;xsl:when</span> <span style="color: #0000CC">test=</span><span style="background-color: #fff0f0">&quot;position()=last()-1&quot;</span><span style="color: #007700">&gt;</span>, and <span style="color: #007700">&lt;/xsl:when&gt;</span>
				<span style="color: #007700">&lt;xsl:otherwise&gt;</span>, <span style="color: #007700">&lt;/xsl:otherwise&gt;</span>
				<span style="color: #007700">&lt;/xsl:choose&gt;</span>
				
				<span style="color: #007700">&lt;/xsl:for-each&gt;&lt;/p&gt;</span>
				
			
				<span style="color: #007700">&lt;/xsl:template&gt;</span>
				<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;picture&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;img&gt;</span>
				<span style="color: #007700">&lt;xsl:apply-templates</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;@*&quot;</span><span style="color: #007700">/&gt;</span>
				<span style="color: #007700">&lt;/img&gt;</span>
				<span style="color: #007700">&lt;/xsl:template&gt;</span>
				
				<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;picture/@filename&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;xsl:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;src&quot;</span><span style="color: #007700">&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;.&quot;</span><span style="color: #007700">/&gt;</span>
				<span style="color: #007700">&lt;/xsl:attribute&gt;</span>
				<span style="color: #007700">&lt;/xsl:template&gt;</span>
				
				<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;picture/@x&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;xsl:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;width&quot;</span><span style="color: #007700">&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;ceiling(. div 2)&quot;</span><span style="color: #007700">/&gt;</span>
				<span style="color: #007700">&lt;/xsl:attribute&gt;</span>
				<span style="color: #007700">&lt;/xsl:template&gt;</span>
				<span style="color: #007700">&lt;xsl:template</span> <span style="color: #0000CC">match=</span><span style="background-color: #fff0f0">&quot;picture/@y&quot;</span><span style="color: #007700">&gt;</span>
				<span style="color: #007700">&lt;xsl:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;height&quot;</span><span style="color: #007700">&gt;&lt;xsl:value-of</span> <span style="color: #0000CC">select=</span><span style="background-color: #fff0f0">&quot;ceiling(. div 2)&quot;</span><span style="color: #007700">/&gt;</span>
				<span style="color: #007700">&lt;/xsl:attribute&gt;</span>
				<span style="color: #007700">&lt;/xsl:template&gt;</span>
				
				
						<span style="color: #007700">&lt;/xsl:stylesheet&gt;</span>
						</pre></div>