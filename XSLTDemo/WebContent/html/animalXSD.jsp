<h2><a href="/XSLTDemo/xml/animal.xsd">animal.xsd</a></h2>
				<br>
				<div style="font-family: courier new; width: 100%">
<!-- HTML generated using hilite.me --><pre style="margin: 0; line-height: 125%"><span style="color: #557799">&lt;?xml version=&quot;1.0&quot;?&gt;</span>
 
<span style="color: #888888">&lt;!--In this example, we declare a target namespace (for the globally-declared components) and then we say that all of the non-prefixed global components come from that namespace (that we just associated them with).--&gt;</span>

<span style="color: #007700">&lt;xsd:schema</span> <span style="color: #0000CC">xmlns:xsd=</span><span style="background-color: #fff0f0">&quot;http://www.w3.org/2001/XMLSchema&quot;</span> <span style="color: #0000CC">targetNamespace=</span><span style="background-color: #fff0f0">&quot;http://www.cookwood.com/ns/end_species1&quot;</span> <span style="color: #0000CC">xmlns=</span><span style="background-color: #fff0f0">&quot;http://www.cookwood.com/ns/end_species1&quot;</span><span style="color: #007700">&gt;</span>

<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;endangered_species&quot;</span><span style="color: #007700">&gt;</span>
	<span style="color: #007700">&lt;xsd:complexType&gt;</span>
		<span style="color: #007700">&lt;xsd:sequence&gt;</span>
			<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;animal&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;animalType&quot;</span> <span style="color: #0000CC">maxOccurs=</span><span style="background-color: #fff0f0">&quot;unbounded&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;/xsd:sequence&gt;</span>
	<span style="color: #007700">&lt;/xsd:complexType&gt;</span>
<span style="color: #007700">&lt;/xsd:element&gt;</span>

<span style="color: #007700">&lt;xsd:complexType</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;animalType&quot;</span><span style="color: #007700">&gt;</span>
	<span style="color: #007700">&lt;xsd:sequence&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;name&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;nameType&quot;</span> <span style="color: #0000CC">minOccurs=</span><span style="background-color: #fff0f0">&quot;2&quot;</span> <span style="color: #0000CC">maxOccurs=</span><span style="background-color: #fff0f0">&quot;100&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;threats&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;threatsType&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;weight&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:string&quot;</span> <span style="color: #0000CC">minOccurs=</span><span style="background-color: #fff0f0">&quot;0&quot;</span> <span style="color: #0000CC">maxOccurs=</span><span style="background-color: #fff0f0">&quot;1&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;length&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:string&quot;</span> <span style="color: #0000CC">minOccurs=</span><span style="background-color: #fff0f0">&quot;0&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;source&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;sourceType&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;picture&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;pictureType&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;subspecies&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;subspeciesType&quot;</span> <span style="color: #0000CC">minOccurs=</span><span style="background-color: #fff0f0">&quot;1&quot;</span> <span style="color: #0000CC">maxOccurs=</span><span style="background-color: #fff0f0">&quot;unbounded&quot;</span><span style="color: #007700">/&gt;</span>
	<span style="color: #007700">&lt;/xsd:sequence&gt;</span>
<span style="color: #007700">&lt;/xsd:complexType&gt;</span>

<span style="color: #007700">&lt;xsd:complexType</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;nameType&quot;</span><span style="color: #007700">&gt;</span>
	<span style="color: #007700">&lt;xsd:simpleContent&gt;</span>
		<span style="color: #007700">&lt;xsd:extension</span> <span style="color: #0000CC">base=</span><span style="background-color: #fff0f0">&quot;xsd:string&quot;</span><span style="color: #007700">&gt;</span>
			<span style="color: #007700">&lt;xsd:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;language&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;languageType&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;/xsd:extension&gt;</span>
	<span style="color: #007700">&lt;/xsd:simpleContent&gt;</span>
<span style="color: #007700">&lt;/xsd:complexType&gt;</span>

<span style="color: #007700">&lt;xsd:simpleType</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;languageType&quot;</span><span style="color: #007700">&gt;</span>
	<span style="color: #007700">&lt;xsd:restriction</span> <span style="color: #0000CC">base=</span><span style="background-color: #fff0f0">&quot;xsd:string&quot;</span><span style="color: #007700">&gt;</span>
		<span style="color: #007700">&lt;xsd:enumeration</span> <span style="color: #0000CC">value=</span><span style="background-color: #fff0f0">&quot;English&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:enumeration</span> <span style="color: #0000CC">value=</span><span style="background-color: #fff0f0">&quot;Latin&quot;</span><span style="color: #007700">/&gt;</span>
	<span style="color: #007700">&lt;/xsd:restriction&gt;</span>
<span style="color: #007700">&lt;/xsd:simpleType&gt;</span>

<span style="color: #007700">&lt;xsd:complexType</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;threatsType&quot;</span><span style="color: #007700">&gt;</span>
	<span style="color: #007700">&lt;xsd:sequence&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;threat&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:string&quot;</span> <span style="color: #0000CC">maxOccurs=</span><span style="background-color: #fff0f0">&quot;unbounded&quot;</span><span style="color: #007700">/&gt;</span>
	<span style="color: #007700">&lt;/xsd:sequence&gt;</span>
<span style="color: #007700">&lt;/xsd:complexType&gt;</span>

<span style="color: #007700">&lt;xsd:complexType</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;sourceType&quot;</span><span style="color: #007700">&gt;</span>
	<span style="color: #007700">&lt;xsd:complexContent&gt;</span>
		<span style="color: #007700">&lt;xsd:restriction</span> <span style="color: #0000CC">base=</span><span style="background-color: #fff0f0">&quot;xsd:anyType&quot;</span><span style="color: #007700">&gt;</span>
		<span style="color: #007700">&lt;xsd:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;sectionid&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:string&quot;</span> <span style="color: #0000CC">use=</span><span style="background-color: #fff0f0">&quot;required&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;newspaperid&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:string&quot;</span> <span style="color: #0000CC">use=</span><span style="background-color: #fff0f0">&quot;required&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;/xsd:restriction&gt;</span>
	<span style="color: #007700">&lt;/xsd:complexContent&gt;</span>
<span style="color: #007700">&lt;/xsd:complexType&gt;</span>

<span style="color: #007700">&lt;xsd:complexType</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;pictureType&quot;</span><span style="color: #007700">&gt;</span>
	<span style="color: #007700">&lt;xsd:complexContent&gt;</span>
		<span style="color: #007700">&lt;xsd:restriction</span> <span style="color: #0000CC">base=</span><span style="background-color: #fff0f0">&quot;xsd:anyType&quot;</span><span style="color: #007700">&gt;</span>
		<span style="color: #007700">&lt;xsd:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;filename&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:anyURI&quot;</span> <span style="color: #0000CC">use=</span><span style="background-color: #fff0f0">&quot;required&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;x&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:integer&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;y&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:integer&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;/xsd:restriction&gt;</span>
	<span style="color: #007700">&lt;/xsd:complexContent&gt;</span>
<span style="color: #007700">&lt;/xsd:complexType&gt;</span>

<span style="color: #007700">&lt;xsd:complexType</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;subspeciesType&quot;</span><span style="color: #007700">&gt;</span>
	<span style="color: #007700">&lt;xsd:sequence&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;name&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;nameType&quot;</span> <span style="color: #0000CC">minOccurs=</span><span style="background-color: #fff0f0">&quot;1&quot;</span> <span style="color: #0000CC">maxOccurs=</span><span style="background-color: #fff0f0">&quot;unbounded&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;region&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:string&quot;</span><span style="color: #007700">/&gt;</span>
		<span style="color: #007700">&lt;xsd:element</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;population&quot;</span><span style="color: #007700">&gt;</span>
			<span style="color: #007700">&lt;xsd:complexType&gt;</span>
				<span style="color: #007700">&lt;xsd:simpleContent&gt;</span>
					<span style="color: #007700">&lt;xsd:extension</span> <span style="color: #0000CC">base=</span><span style="background-color: #fff0f0">&quot;xsd:nonNegativeInteger&quot;</span><span style="color: #007700">&gt;</span>
						<span style="color: #007700">&lt;xsd:attribute</span> <span style="color: #0000CC">name=</span><span style="background-color: #fff0f0">&quot;year&quot;</span> <span style="color: #0000CC">type=</span><span style="background-color: #fff0f0">&quot;xsd:gYear&quot;</span><span style="color: #007700">/&gt;</span>
					<span style="color: #007700">&lt;/xsd:extension&gt;</span>
				<span style="color: #007700">&lt;/xsd:simpleContent&gt;</span>
			<span style="color: #007700">&lt;/xsd:complexType&gt;</span>
		<span style="color: #007700">&lt;/xsd:element&gt;</span>
	<span style="color: #007700">&lt;/xsd:sequence&gt;</span>	
<span style="color: #007700">&lt;/xsd:complexType&gt;</span>
		
<span style="color: #007700">&lt;/xsd:schema&gt;</span>

		


		
</pre></div>
