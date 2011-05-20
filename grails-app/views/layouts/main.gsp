<%@ page import="lunchmate.Lunch" %>
<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body style="margin-top:6px;">
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div>
            <div style="padding-left:8px;float:left;">
                <img src="${resource(dir:'images', file:'lunchmate.jpg')}" height="94px" width="125px" alt="LunchMate"/>
            </div>
            <div style="float:right; padding:8px; border: 1px solid #ccc;">
                    Welcome Back twakeen! <g:link controller="logout">logout</g:link>
            </div>
        </div>
        <BR style="clear:both;"/>
        <div styel="padding:8px;">
        <ul id="menu">
            <li><g:link action="index" controller="restaurant" title="Places To Eat">Places To Eat</g:link></li>
            <li><g:link action="index" controller="lunch" title="Lunch Time!">Upcoming Lunches</g:link></li>
            <li><g:link action="create" controller="lunch" title="Schedule a Lunch">Schedule a Lunch</g:link></li>
            <li><g:link action="list" controller="lunch" title="Old Lunches">Old Lunches</g:link></li>
         </ul>
        </div>
        <BR style="clear:both;"/>
        <div style="padding-left:8px;">
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
                <BR/>
            </g:if>

            <g:layoutBody />
        </div>
    </body>
</html>