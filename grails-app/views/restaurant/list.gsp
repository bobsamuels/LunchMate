<%@ page import="org.apache.jasper.tagplugins.jstl.core.Url; lunchmate.Restaurant" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
	    <title>Restaurants</title>
        <meta name="layout" content="main"></meta>
   <link type="text/css" href="${resource(dir:'js/css', file:'jquery-ui-1.8.12.custom.css')}"/>
    <g:javascript src="js/jquery-1.5.1.min.js"/>
    <g:javascript src="js/jquery-ui-1.8.12.custom.min.js"/>

    <script src='http://jquery-star-rating-plugin.googlecode.com/svn/trunk/jquery.MetaData.js' type="text/javascript" language="javascript"></script>
    <script src='http://jquery-star-rating-plugin.googlecode.com/svn/trunk/jquery.rating.js' type="text/javascript" language="javascript"></script>
    <link href='http://jquery-star-rating-plugin.googlecode.com/svn/trunk/jquery.rating.css' type="text/css" rel="stylesheet"/>
    <g:javascript library="prototype"/>

        <script type="text/javascript">
        jQuery(document).ready(function() {
            jQuery(".resContent").hide();
            jQuery(".divHeading").click(function()
            {
                jQuery(this).next(".resContent").slideToggle(500);
            });
            });
        </script>
</head>
<body>

<H1>Restaurants</H1>
<g:each in="${restaurants}" var="restaurant" status="i">
    <p class="divHeading">${restaurant.name} <span style="float:right;"><g:if test="${restaurant.url}"><a target="_blank" href="${restaurant.url}">Website</a></g:if></span></p>
    <div class="resContent" id="${restaurant.id}" style="clear:both; padding:5px;">
        <p style="clear:both;"></p>
        <div id="${restaurant.id}_comment">
            <g:render template="comment"  model="['restaurant':restaurant]"/>
        </div>
        <div class="resDetails">
           Average Rating: ${restaurant.averageRating}
        </div>
        <div id="${restaurant.id}_rate" class="resDetails">
            <g:render template="rating" model="['restaurant':restaurant]"/>
        </div>
    </div>
</g:each>
<div style="padding:5px;">
    <g:link action="create" controller="restaurant" class="linkButton">Create New Restaurant</g:link>
</div>

</body>
</html>

