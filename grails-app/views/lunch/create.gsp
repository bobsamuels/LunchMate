<%@ page import="lunchmate.Lunch" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
	    <title>Upcoming Lunches</title>
        <meta name="layout" content="main"></meta>
        <g:javascript library="prototype"/>
  </head>
<body>
<H1>Schedule a Lunch</H1>
<div class="formDiv">
<g:if test="${canToday || canTomorrow}">
<g:form name="createLunch" action="save" controller="lunch">
    <div>
        <fieldset>
            <legend>How</legend>
            <g:radio value="${Lunch.Type.GENERATED}" name="type"/> Make one for me <BR/>
            <g:radio value="${Lunch.Type.PICKED}" name="type"/> I'll pick the restaurants
        </fieldset>
    </div>

    <div id="restaurants" style="display:none;">
        <fieldset>
            <legend>Where</legend>
        <ol>
            <li>
                <g:select id="restaurant1" name="restaurant1" from="${restaurants}" optionKey="id" optionValue="name"/>
            </li>
            <li>
                <g:select id="restaurant2" name="restaurant2" from="${restaurants}" optionKey="id" optionValue="name"/>
            </li>
            <li>
                <g:select id="restaurant3" name="restaurant3" from="${restaurants}" optionKey="id" optionValue="name"/>
            </li>
        </ol>
        </fieldset>
    </div>

    <div>
        <fieldset>
            <legend>When</legend>
        <g:if test="${canToday}">
            <g:radio value="today" name="when"/>Today<BR/>
        </g:if>
        <g:if test="${canTomorrow}">
            <g:radio value="tomorrow" name="when"/>Tomorrow
        </g:if>
        </fieldset>
    </div>
    <BR/>
    <g:submitButton name="Create" value="Create Lunch!"/>

</g:form>
</g:if>
<g:else>
    <div>Lunches are already scheduled for today and tomorrow.  <g:link action="index" controller="lunch" title="Lunch Time!">Vote on them now!</g:link></div>
</g:else>
</div>
<script type="text/javascript">
    function showHideRestaurants(){
        var value = $F('type');
        if(value && value.toLowerCase() == 'generated')
        {
            $('restaurants').hide();
        }
        else
        {
            $('restaurants').show();
        }
    }

    Form.getInputs('createLunch', 'radio', 'type').each(function(item){
        item.observe('click', showHideRestaurants);
    });
</script>
</body>
</html>