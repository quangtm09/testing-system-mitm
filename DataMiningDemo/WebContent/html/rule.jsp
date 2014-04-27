<%@include file="/html/init.jsp"%>

<%
	Map map = (Map) request.getAttribute("map");
%>

<c:set var="map" value="<%=map%>"></c:set>

<c:choose>
    <c:when test="${not empty map['ruleList']}">
        <c:choose>
            <c:when test="${map['isSearching'] eq false}">
                <div class="alert alert-success">Found ${fn:length(map['ruleList'])} rules. (Processed from total ${map['numStudents']} students, ${map['numSubjects']} subjects)</div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-success">Found ${fn:length(map['ruleList'])} rules.</div>
            </c:otherwise>
        </c:choose>        
        <c:set var="counter" value="1"/>
        <c:choose>
            <c:when test="${map['metricType'] == 0}">
                <table id="newspaper-a" style="width: 1000px">
                    <thead>
                        <tr>
                            <th>Number</th>
                            <th scope="col">Rule</th>
                            <th>Confidence</th>
                            <th scope="col">Antecedent Support</th>
                            <th scope="col">Consequent Support</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${map['ruleList']}" var="rule">
                            <tr>
                                <td>${counter}</td>
                                <td>
                                    <span style="color: black"><strong>IF</strong></span><br>
                                    <span style="color: #347235">${rule.antecedent}</span><br>
                                    <span style="color: black"><strong>THEN</strong></span><br>
                                    <span style="color: #347235">${rule.consequent}</span>
                                </td>
                                <td title="Confidence">${rule.confidence}</td>
                                <td title="Antecedent Support">${rule.antecedentSupport}</td>
                                <td title="Consequent Support">${rule.consequentSupport}</td>
                            </tr>
                            <c:set var="counter" value="${counter + 1}"/>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <table id="newspaper-a" style="width: 1100px">
                    <thead>
                        <tr>
                            <th>Number</th>
                            <th scope="col">Rule</th>
                            <th>Confidence</th>
                            <th>Lift</th>
                            <th>Leverage</th>
                            <th>Conviction</th>
                            <th scope="col">Antecedent Support</th>
                            <th scope="col">Consequent Support</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${map['ruleList']}" var="rule">
                            <tr>
                                <td>${counter}</td>
                                <td>
                                    <span style="color: black"><strong>IF</strong></span><br>
                                    <span style="color: #347235">${rule.antecedent}</span><br>
                                    <span style="color: black"><strong>THEN</strong></span><br>
                                    <span style="color: #347235">${rule.consequent}</span>
                                </td>
                                <td title="Confidence">${rule.confidence}</td>
                                <td title="Lift">${rule.lift}</td>
                                <td title="Leverage">${rule.leverage} [${rule.leverageExamples}]</td>
                                <td title="Conviction">${rule.conviction}</td>
                                <td title="Antecedent Support">${rule.antecedentSupport}</td>
                                <td title="Consequent Support">${rule.consequentSupport}</td>
                            </tr>
                            <c:set var="counter" value="${counter + 1}"/>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <div class="alert alert-danger">
            No rules found.
        </div>
    </c:otherwise>
</c:choose>
