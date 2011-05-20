package lunchmate

class LunchController {

    def lunchService
    def userService

    def index = {
      [upcomingLunches:lunchService.findUpcomingLunches()]

    }
    def list = {
        if (!params.sort) params.sort = "scheduledDate"
        if (!params.order) params.order = "desc"
        def lunches = lunchService.findArchiveLunches(params)
        [lunches: lunches]
    }
    def create = {

      Boolean today = lunchService.canScheduleFor(new Date())
      Boolean tomorrow = lunchService.canScheduleFor(new Date() + 1)
      [lunch:new Lunch(), restaurants: Restaurant.listOrderByName(), canToday:today, canTomorrow:tomorrow]
    }

    def save = {
      Lunch lunch = lunchService.createLunch(params)
      redirect(action:'index')
    }

    def addVote = {
      Lunch lunch = Lunch.read(params.lunchId)
      Restaurant restaurant = Restaurant.read(params.restaurantId)
      Vote vote = new Vote(lunch:lunch, restaurant:restaurant, user: userService.currentUser).save()
      render(template:'lunchDetails', model:['lunch':lunch])
    }

}
