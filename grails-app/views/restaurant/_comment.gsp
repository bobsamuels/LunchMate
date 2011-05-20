 <div class="resComments" style="padding-bottom:5px;">
            <H3>Comments</H3>
            <g:each in="${restaurant.comments}" var="comment" status="i">
                <div style="padding:2px;">
                    <strong>${comment.text}</strong> - <i>${comment.user.username}</i>
                </div>
            </g:each>
</div>
<g:if test="${!restaurant?.getUserComment()}">
    <g:formRemote name="addComment" url="${[action:'addComment']}" controller="restaurant" action="addComment" update="${restaurant.id}_comment">
        <g:hiddenField name="restaurantId" value="${restaurant.id}"/>
        <g:textArea name="comment" cols="30" rows="4">Your comment</g:textArea>
        <BR/>
        <g:submitButton name="Add" value="Add Comment"/>
    </g:formRemote>
</g:if>
