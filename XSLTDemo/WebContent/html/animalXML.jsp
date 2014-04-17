
				<h2><a href="/XSLTDemo/xml/animal_without_xsl.xml">animal.xml</a> <span style="font-size: 10px">(click <a href="/XSLTDemo/xml/animal.xml">here</a> for XML imported XSL)</span></h2>
				<br>
				<div style="width: 100%; padding-right: 20px">
					<!-- HTML generated using hilite.me --><pre style="margin: 0; line-height: 125%"><span style="color: #557799">&lt;?xml version=&quot;1.0&quot;?&gt;</span>
<span style="color: #888888">&lt;!--There are two new attributes in the end:endangered_species element: the xmlns:xsi attribute which specifies the namespace for all xsi-prefixed components (like the one that immediately follows), and the xsi:schemaLocation attribute which specifies, within a single set of double quotation marks, a namespace that may be used in this XML document, and the actual schema file in which its members are defined. (Otherwise, this document is identical to validating.xml.) It can be validated with animal.xsd (or any of its variations).--&gt;</span>
<span style="color: #007700">&lt;end:endangered_species</span> <span style="color: #0000CC">xmlns:end=</span><span style="background-color: #fff0f0">&quot;http://www.cookwood.com/ns/end_species1&quot;</span> <span style="color: #0000CC">xmlns:xsi=</span><span style="background-color: #fff0f0">&quot;http://www.w3.org/2001/XMLSchema-instance&quot;</span>
<span style="color: #0000CC">xsi:schemaLocation=</span><span style="background-color: #fff0f0">&quot;http://www.cookwood.com/ns/end_species1 animal.xsd&quot;</span><span style="color: #007700">&gt;</span>
<span style="color: #007700">&lt;animal&gt;</span>
	<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Tiger<span style="color: #007700">&lt;/name&gt;</span>
	<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>panthera tigris<span style="color: #007700">&lt;/name&gt;</span>
	<span style="color: #007700">&lt;threats&gt;&lt;threat&gt;</span>poachers<span style="color: #007700">&lt;/threat&gt;</span>
	<span style="color: #007700">&lt;threat&gt;</span>habitat destruction<span style="color: #007700">&lt;/threat&gt;</span>
	<span style="color: #007700">&lt;threat&gt;</span>trade in tiger bones for traditional Chinese medicine (TCM)<span style="color: #007700">&lt;/threat&gt;</span>
	<span style="color: #007700">&lt;/threats&gt;</span>
	<span style="color: #007700">&lt;weight&gt;</span>500 pounds<span style="color: #007700">&lt;/weight&gt;</span>
	<span style="color: #007700">&lt;length&gt;</span>3 yards from nose to tail<span style="color: #007700">&lt;/length&gt;</span>
	<span style="color: #007700">&lt;source</span> <span style="color: #0000CC">sectionid=</span><span style="background-color: #fff0f0">&quot;120&quot;</span> <span style="color: #0000CC">newspaperid=</span><span style="background-color: #fff0f0">&quot;21&quot;</span><span style="color: #007700">&gt;&lt;/source&gt;</span>
	<span style="color: #007700">&lt;picture</span> <span style="color: #0000CC">filename=</span><span style="background-color: #fff0f0">&quot;../img/tiger.jpg&quot;</span> <span style="color: #0000CC">x=</span><span style="background-color: #fff0f0">&quot;400&quot;</span> <span style="color: #0000CC">y=</span><span style="background-color: #fff0f0">&quot;394&quot;</span><span style="color: #007700">/&gt;</span>
		
	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Amur or Siberian<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>P.t. altaica<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>Far East Russia<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1999&quot;</span><span style="color: #007700">&gt;</span>445<span style="color: #007700">&lt;/population&gt;</span>
		<span style="color: #007700">&lt;/subspecies&gt;</span>
	
	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Balian<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>P.t. balica<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>Bali<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1937&quot;</span><span style="color: #007700">&gt;</span>0<span style="color: #007700">&lt;/population&gt;</span>
	<span style="color: #007700">&lt;/subspecies&gt;</span>
	
	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Javan<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>P.t. sondaica<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>Java<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1972&quot;</span><span style="color: #007700">&gt;</span>0<span style="color: #007700">&lt;/population&gt;</span>
	<span style="color: #007700">&lt;/subspecies&gt;</span>

	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Caspian<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>P.t. virgata<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>Caspian Sea<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1950&quot;</span><span style="color: #007700">&gt;</span>0<span style="color: #007700">&lt;/population&gt;</span>
	<span style="color: #007700">&lt;/subspecies&gt;</span>
	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Bengal<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>P.t. tigris<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>India<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1999&quot;</span><span style="color: #007700">&gt;</span>3159<span style="color: #007700">&lt;/population&gt;</span>
		<span style="color: #007700">&lt;/subspecies&gt;</span>

	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Sumatran<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>P.t. sumatrae<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>India, Bangladesh<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1999&quot;</span><span style="color: #007700">&gt;</span>400<span style="color: #007700">&lt;/population&gt;</span>
		<span style="color: #007700">&lt;/subspecies&gt;</span>

	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Amoy<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>P.t. amoyensis<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>South China<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1999&quot;</span><span style="color: #007700">&gt;</span>20<span style="color: #007700">&lt;/population&gt;</span>
	<span style="color: #007700">&lt;/subspecies&gt;</span>
	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Indo-chinese<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>P.t. corbetti<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>Indo-China<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1998&quot;</span><span style="color: #007700">&gt;</span>1227<span style="color: #007700">&lt;/population&gt;</span>
		<span style="color: #007700">&lt;/subspecies&gt;</span>
	<span style="color: #007700">&lt;/animal&gt;</span>
<span style="color: #007700">&lt;animal&gt;</span>
	
	<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Black Rhino<span style="color: #007700">&lt;/name&gt;</span>
	<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>diceros bicornis<span style="color: #007700">&lt;/name&gt;</span>
	<span style="color: #007700">&lt;threats&gt;&lt;threat&gt;</span>poaching to satisfy demand for rhino horn in traditional Asian medicines and as decorative dagger handles in Middle East<span style="color: #007700">&lt;/threat&gt;</span>
	<span style="color: #007700">&lt;threat&gt;</span>habitat destruction<span style="color: #007700">&lt;/threat&gt;</span>
	<span style="color: #007700">&lt;/threats&gt;</span>
	<span style="color: #007700">&lt;source</span> <span style="color: #0000CC">sectionid=</span><span style="background-color: #fff0f0">&quot;101&quot;</span> <span style="color: #0000CC">newspaperid=</span><span style="background-color: #fff0f0">&quot;21&quot;</span><span style="color: #007700">&gt;&lt;/source&gt;</span>
	<span style="color: #007700">&lt;picture</span> <span style="color: #0000CC">filename=</span><span style="background-color: #fff0f0">&quot;../img/rhino.jpg&quot;</span> <span style="color: #0000CC">x=</span><span style="background-color: #fff0f0">&quot;400&quot;</span> <span style="color: #0000CC">y=</span><span style="background-color: #fff0f0">&quot;316&quot;</span><span style="color: #007700">/&gt;</span>
	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Southern Black Rhino<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>D.b. minor<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>Zimbabwe and South Africa<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1999&quot;</span><span style="color: #007700">&gt;</span>1365<span style="color: #007700">&lt;/population&gt;</span>
		<span style="color: #007700">&lt;/subspecies&gt;</span>
	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Southwestern Black Rhino<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>D.b. bicornis<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>Namibia<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1999&quot;</span><span style="color: #007700">&gt;</span>740<span style="color: #007700">&lt;/population&gt;</span>
		<span style="color: #007700">&lt;/subspecies&gt;</span>
	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Eastern Black Rhino<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>D.b. michaeli<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>Kenya (Ethiopia and Rwanda)<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1999&quot;</span><span style="color: #007700">&gt;</span>485<span style="color: #007700">&lt;/population&gt;</span>
		<span style="color: #007700">&lt;/subspecies&gt;</span>
	<span style="color: #007700">&lt;subspecies&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">&gt;</span>Northwestern Black Rhino<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;name</span> <span style="color: #0000CC">language=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">&gt;</span>D.b. longipes<span style="color: #007700">&lt;/name&gt;</span>
		<span style="color: #007700">&lt;region&gt;</span>Cameroon<span style="color: #007700">&lt;/region&gt;</span>
		<span style="color: #007700">&lt;population</span> <span style="color: #0000CC">year=</span><span style="background-color: #fff0f0">&quot;1999&quot;</span><span style="color: #007700">&gt;</span>10<span style="color: #007700">&lt;/population&gt;</span>
		<span style="color: #007700">&lt;/subspecies&gt;</span>
		<span style="color: #007700">&lt;/animal&gt;</span>	 		
	<span style="color: #007700">&lt;/end:endangered_species&gt;</span>
</pre>
					
				</div>		
