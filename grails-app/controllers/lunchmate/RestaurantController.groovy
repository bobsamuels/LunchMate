package lunchmate

import grails.plugins.springsecurity.Secured



class RestaurantController {

    def springSecurityService

    @Secured(['ROLE_USER'])
    def index = {redirect(action:list) }

    @Secured(['ROLE_USER'])
    def list = {
      [restaurants:Restaurant.listOrderByName()]
    }

    @Secured(['ROLE_USER'])
    def create = {
        [restaurant:new Restaurant()]
    }

    @Secured(['ROLE_USER'])
    def save = {
        Restaurant restaurant = new Restaurant(name: params.name, url: params.url)
        if(restaurant.hasErrors())
        {
          render(view:'create', [restaurant:restaurant])
        }
        else
        {
          restaurant.save()
          if(params.rating)
          {
            Rating rating = new Rating(restaurant:restaurant, score: params.rating.toInteger(), user: springSecurityService.currentUser).save()
          }
          if(params.comment.size() > 0)
          {
            Comment comment = new Comment(restaurant: restaurant, text: params.comment, user: springSecurityService.currentUser).save()
          }

          flash.message = "Created new restaurant: ${restaurant.name}".toString()
          redirect(action:list)
        }
    }

    @Secured(['ROLE_USER'])
    def rate = {
      Restaurant restaurant = Restaurant.read(params.restaurantId)
      int score = params.rating.toInteger()
      Rating rating = new Rating(restaurant:restaurant, score: score, user: springSecurityService.currentUser).save()
      render(template:'rating', model:['restaurant':restaurant])
    }

    @Secured(['ROLE_USER'])
    def addComment = {
      Restaurant restaurant = Restaurant.read(params.restaurantId)
      def commentText = params.comment
      Comment comment = new Comment(restaurant:restaurant, text: commentText, user: springSecurityService.currentUser).save()
      render(template:'comment', model:['restaurant':restaurant])
    }

    @Secured(['ROLE_USER'])
    def editRating = {
      Rating rating = Rating.get(params.ratingId)
      Restaurant restaurant = Restaurant.read(rating.restaurant.id)
      rating.delete(flush:true)
      render(template:'rating', model:['restaurant':restaurant])
    }

}
