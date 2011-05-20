package lunchmate



class RestaurantController {

    def userService

    def index = {redirect(action:list) }

    def list = {
      [restaurants:Restaurant.listOrderByName()]
    }

    def create = {
        [restaurant:new Restaurant()]
    }

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
            Rating rating = new Rating(restaurant:restaurant, score: params.rating.toInteger(), user: userService.currentUser).save()
          }
          if(params.comment.size() > 0)
          {
            Comment comment = new Comment(restaurant: restaurant, text: params.comment, user: userService.currentUser).save()
          }

          flash.message = "Created new restaurant: ${restaurant.name}".toString()
          redirect(action:list)
        }
    }

    def rate = {
      Restaurant restaurant = Restaurant.read(params.restaurantId)
      int score = params.rating.toInteger()
      Rating rating = new Rating(restaurant:restaurant, score: score, user: userService.currentUser).save()
      render(template:'rating', model:['restaurant':restaurant])
    }

    def addComment = {
      Restaurant restaurant = Restaurant.read(params.restaurantId)
      def commentText = params.comment
      Comment comment = new Comment(restaurant:restaurant, text: commentText, user: userService.currentUser).save()
      render(template:'comment', model:['restaurant':restaurant])
    }

    def editRating = {
      Rating rating = Rating.get(params.ratingId)
      Restaurant restaurant = Restaurant.read(rating.restaurant.id)
      rating.delete(flush:true)
      render(template:'rating', model:['restaurant':restaurant])
    }

}
