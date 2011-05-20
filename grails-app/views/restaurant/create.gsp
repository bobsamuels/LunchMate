<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
	    <title>New Restaurant</title>
        <meta name="layout" content="main"></meta>
           <link type="text/css" href="${resource(dir:'js/css', file:'jquery-ui-1.8.12.custom.css')}"/>
    <g:javascript src="js/jquery-1.5.1.min.js"/>
    <g:javascript src="js/jquery-ui-1.8.12.custom.min.js"/>

    <script src='http://jquery-star-rating-plugin.googlecode.com/svn/trunk/jquery.MetaData.js' type="text/javascript" language="javascript"></script>
    <script src='http://jquery-star-rating-plugin.googlecode.com/svn/trunk/jquery.rating.js' type="text/javascript" language="javascript"></script>
    <link href='http://jquery-star-rating-plugin.googlecode.com/svn/trunk/jquery.rating.css' type="text/css" rel="stylesheet"/>
    </head>
<body>
<BR/>
<H1>New Restaurant</H1>
<BR/>
<div class="formDiv">
    <g:form action="save" controller="restaurant">
        <div class="leftCol">
            <label for="name">Name</label>
        </div>
        <div class="rightCol">
            <g:textField name="name" value="${restaurant.name}"/>
        </div>
        <BR style="clear:both;"/>
        <div class="leftCol">
            <label for="url">Url</label>
        </div>
        <div class="rightCol">
            <g:textField name="url" value="${restaurant.url}"/>
        </div>
        <BR style="clear:both;"/>
        <div class="leftCol">
            <label for="comment">Comment</label>
        </div>
        <div class="rightCol">
            <g:textArea name="comment" cols="30" rows="8"/>
        </div>
        <BR style="clear:both;"/>
        <div class="leftCol">
            <label for="rating">Rating</label>
        </div>
        <div class="rightCol">
            <input name="rating" type="radio" class="star" value="1"/>
            <input name="rating" type="radio" class="star" value="2"/>
            <input name="rating" type="radio" class="star" value="3"/>
            <input name="rating" type="radio" class="star" value="4"/>
            <input name="rating" type="radio" class="star" value="5"/>
        </div>
        <BR style="clear:both"/>

        <div style="padding-top:10px;">
            <g:submitButton name="Create" value="Create"/>
        </div>
    </g:form>

</div>
</body>
</html>