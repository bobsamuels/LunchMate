<div style="margin: -5px; padding:5px; background-color:white;"><H2><g:formatDate date="${lunch.scheduledDate}" format="EEE,  MMM dd, yyyy"/></H2></div>
<div class="lunchDetails">
    <H3>The contenders are</H3>
    <ol>
        <g:each in="${lunch.restaurants.sort{it.name}}" var="restaurant">
            <li><strong>${restaurant.name}</strong> <a target="_blank" href="${restaurant.url}">WebSite</a>
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
            <g:if test="${lunch.open && !lunch.votedFor}">
                <g:remoteLink class="voteButton" update="${lunch.id}_lunch" action="addVote" controller="lunch" params="['restaurantId':restaurant.id, 'lunchId': lunch.id]">Vote For ${restaurant.name}!</g:remoteLink>
            </g:if>
            </li>
        </g:each>
    </ol>
    <g:if test="${lunch.open}">
        <a target="_blank" href="https://widen.presently.com/api/twitter/statuses/update">Post To Present.ly</a>
    </g:if>
</div>
<g:if test="${! lunch.open || lunch.votedFor}">
    <div class="lunchHistory">
        <g:if test="${! lunch.open}">
            <div>Lunch voting closed.  The winner is: ${lunch.winningRestaurant?.name}</div>
        </g:if>
         <g:if test="${lunch.votedFor}">
                <div>You voted for: ${lunch.votedFor.name}</div>
         </g:if>
    </div>
</g:if>