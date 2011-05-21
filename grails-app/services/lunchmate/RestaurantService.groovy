package lunchmate

import java.math.RoundingMode

class RestaurantService {

    static transactional = true
    def voteService
    def springSecurityService

    def getAverageRating(Restaurant restaurant) {
      List<Rating> ratings = Rating.findAllByRestaurant(restaurant)
      if(ratings)
        return (ratings.sum(){it.score}/ratings.size()).setScale(2, RoundingMode.HALF_UP)
      else
        return 0
    }

    List<Restaurant> pickRestaurants()
    {
      List<RestaurantRating> restaurantRatings = []
      RestaurantRating currentRest
      BigDecimal score = 0
      int numberOfDays = 0
      Restaurant.list().each{restaurant->
        numberOfDays = 0
        currentRest = new RestaurantRating(restaurant)

        //get average rating
        score = getAverageRating(restaurant) * (1/4)
        //get days since last visit
        score += getNumberOfDaysSinceLastLunch(restaurant) * (1/3)
        //get average votes on lunches
        score += getAverageVotesPerLunch(restaurant) * (1/3)

        println "Restaurant ${restaurant}, score: ${score}"
        currentRest.score = score
        restaurantRatings.add(currentRest)

      }

        List<RestaurantRating> sortedRatings =restaurantRatings.sort{
          - it.score
        }

        [sortedRatings.get(0).restaurant, sortedRatings.get(1).restaurant, sortedRatings.get(2).restaurant]

    }

   int getNumberOfDaysSinceLastLunch(Restaurant restaurant)
      {
        int numberOfDays = 0

        List<Lunch> lunches = getLunchesByWinningRestaurant(restaurant)
          if(lunches)
          {
            Date lastLunchDate = lunches.sort{it.scheduledDate}.get(0).scheduledDate
            numberOfDays = new Date() - lastLunchDate
          }
          if(numberOfDays == 0 || numberOfDays > 30)
          {
            numberOfDays = 30
          }

        return numberOfDays
  }

   BigDecimal getAverageVotesPerLunch(Restaurant restaurant)
    {
      Integer totalVotes = 0
      List<Lunch> availableLunches = getLunchesByAvailableRestaurant(restaurant)
      availableLunches.each{it->
         totalVotes += voteService.getNumberOfVotes(restaurant, it)
        }
      if(totalVotes && availableLunches)
        return totalVotes / availableLunches.size()
      else
        return 0.0
    }

  List<Lunch> getLunchesByWinningRestaurant(Restaurant _restaurant)
      {
        List<Lunch> lunches = []
        Lunch.list().each{
          if(it.winningRestaurant.equals(_restaurant))
          {
            lunches.add(it)
          }
        }

        return lunches
      }


   List<Lunch> getLunchesByAvailableRestaurant(Restaurant _restaurant)
    {
      List<Lunch> lunches = []
      Lunch.list().each{
        if(it.restaurants.contains(_restaurant))
        {
          lunches.add(it)
        }
      }
    }

     Rating getRatingForUser(Restaurant _restaurant)
     {
       _restaurant.ratings.find{it.user.equals(springSecurityService.currentUser)}
     }

    Comment getCommentForUser(Restaurant _restaurant)
     {
       _restaurant.comments.find{it.user.equals(springSecurityService.currentUser)}
     }

    private class RestaurantRating
    {
      Restaurant restaurant
      BigDecimal score



      RestaurantRating(Restaurant restaurant)
      {
        this.restaurant = restaurant
      }

      RestaurantRating()
      { }

    }

}
