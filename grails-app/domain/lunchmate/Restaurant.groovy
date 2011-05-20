package lunchmate

class Restaurant {

    def restaurantService
    String name
    String url

    static hasMany = [ratings:Rating, comments:Comment]

    def getUserRating(){
      restaurantService.getRatingForUser(this)
    }

    def getUserComment(){
      restaurantService.getCommentForUser(this)
    }

    def getAverageRating(){
      restaurantService.getAverageRating(this)
    }

    static constraints = {
      url(nullable:true, blank:true)
    }

    static mapping = {
     sort "name"
    }
}
