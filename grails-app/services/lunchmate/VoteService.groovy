package lunchmate

class VoteService {

    static transactional = true
    def springSecurityService

    int getNumberOfVotes(Restaurant restaurant, Lunch lunch)
    {
      List<Vote> votes = Vote.findAllByLunchAndRestaurant(lunch,restaurant)
      return votes.size()
    }

    Restaurant getVotedFor(Lunch lunch)
    {
      Vote vote = lunch.votes.find{it.user.equals(springSecurityService.currentUser)}
      vote?.restaurant
    }
}
