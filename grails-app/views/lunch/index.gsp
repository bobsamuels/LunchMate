<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
	    <title>Upcoming Lunches</title>
        <meta name="layout" content="main"></meta>
        <g:javascript library="prototype"/>
  </head>
<body>
    <H1>Upcoming Lunches</H1>
    <g:if test="${upcomingLunches}">
        <g:each in="${upcomingLunches}" var="lunch">
            <div class="lunchDiv" id="${lunch.id}_lunch">
                <g:render template="lunchDetails" model="['lunch':lunch]"/>
            </div>
        </g:each>
    </g:if>
    <g:else>
        <div class="lunchDiv">
            No lunch for you today
        </div>
    </g:else>


  </body>
</html>