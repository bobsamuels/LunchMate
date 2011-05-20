<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
	    <title>Archived Lunches</title>
        <meta name="layout" content="main"></meta>
        <link type="text/css" href="${resource(dir:'js/css', file:'jquery-ui-1.8.12.custom.css')}"/>
         <g:javascript src="js/jquery-1.5.1.min.js"/>
         <g:javascript src="js/jquery-ui-1.8.12.custom.min.js"/>
         <script type="text/javascript">
jQuery(document).ready(function() {
      jQuery(".lunchDiv").hide();
      jQuery(".divHeading").click(function()
      {
        jQuery(this).next(".lunchDiv").slideToggle(500);
      });
    });
</script>

  </head>
<body>
    <H1>Old Lunches</H1>
    <g:if test="${lunches}">
        <g:each in="${lunches}" var="lunch">
            <p class="divHeading"><g:formatDate date="${lunch.scheduledDate}" format="EEE,  MMM dd, yyyy"/></p>
            <div class="lunchDiv" id="${lunch.id}_lunch">
                    <div class="lunchDetails">
                        <H3>The contenders were</H3>
                        <ol>
                            <g:each in="${lunch.restaurants.sort{it.name}}" var="restaurant">
                                <li><strong>${restaurant.name}</strong> <a href="${restaurant.url}">WebSite</a>
                                    <div class="resComments">
                                        Comments:
                                        <g:each in="${restaurant.comments}" var="comment" status="i">
                                            <div class="${(i % 2 == 0) ? 'even' : 'odd'}" style="padding-top:5px; padding-bottom:5px;">
                                                ${comment.text} - <i>${comment.user.username}</i>
                                            </div>
                                        </g:each>
                                    </div>
                                    <div class="resDetails">
                                       Average Rating: ${restaurant.averageRating}
                                    </div>
                                </li>
                            </g:each>
                        </ol>
                    </div>
                    <div class="lunchHistory">
                            <div>The winner was: <strong>${lunch.winningRestaurant?.name}</strong></div>
                         <g:if test="${lunch.votedFor}">
                                <div>You voted for: <strong>${lunch.votedFor.name}</strong></div>
                         </g:if>
                    </div>
            </div>
        </g:each>
    </g:if>
    <g:else>
        <div class="lunchDiv">
           No old lunches found.
        </div>
    </g:else>


  </body>
</html>