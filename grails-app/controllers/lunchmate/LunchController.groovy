
package lunchmate

import grails.plugins.springsecurity.Secured



class LunchController {

    def lunchService
    def springSecurityService

    @Secured(['ROLE_USER'])
    def index = {
      [upcomingLunches:lunchService.findUpcomingLunches()]

    }

    @Secured(['ROLE_USER'])
    def list = {
        if (!params.sort) params.sort = "scheduledDate"
        if (!params.order) params.order = "desc"
        def lunches = lunchService.findArchiveLunches(params)
        [lunches: lunches]
    }

    @Secured(['ROLE_USER'])
    def create = {

      Boolean today = lunchService.canScheduleFor(new Date())
      Boolean tomorrow = lunchService.canScheduleFor(new Date() + 1)
      [lunch:new Lunch(), restaurants: Restaurant.listOrderByName(), canToday:today, canTomorrow:tomorrow]
    }

    @Secured(['ROLE_USER'])
    def save = {
      Lunch lunch = lunchService.createLunch(params)
      redirect(action:'index')
    }

    @Secured(['ROLE_USER'])
    def addVote = {
      Lunch lunch = Lunch.read(params.lunchId)
      Restaurant restaurant = Restaurant.read(params.restaurantId)
      Vote vote = new Vote(lunch:lunch, restaurant:restaurant, user: springSecurityService.currentUser).save()
      render(template:'lunchDetails', model:['lunch':lunch])
    }

}
