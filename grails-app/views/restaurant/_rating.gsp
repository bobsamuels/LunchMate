<g:if test="${restaurant?.getUserRating()}">
    You have rated this retaraunt as <strong>${restaurant.getUserRating().score} stars</strong> . <g:remoteLink update="${restaurant.id}_rate" action="editRating" controller="restaurant" params="['ratingId':restaurant.getUserRating().id]">Edit</g:remoteLink>
</g:if>
<g:else>
    <g:formRemote name="rating" url="${[action:'rate']}" controller="restaurant" action="rate" update="${restaurant.id}_rate">
        <g:hiddenField name="restaurantId" value="${restaurant.id}"/>
        <input name="rating" type="radio" class="star" value="1"/>
        <input name="rating" type="radio" class="star" value="2"/>
        <input name="rating" type="radio" class="star" value="3"/>
        <input name="rating" type="radio" class="star" value="4"/>
        <input name="rating" type="radio" class="star" value="5"/>
        <g:submitButton name="Rate" value="Rate"/>
    </g:formRemote>
</g:else>