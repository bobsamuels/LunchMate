package lunchmate

import grails.plugins.springsecurity.Secured

class VoteController {

    @Secured(['ROLE_USER'])
    def create={
      Lunch _lunch = Lunch.read(params.id)
      [vote: new Vote(), lunch:_lunch]
    }

    @Secured(['ROLE_USER'])
    def save = {
      Lunch _lunch = Lunch.read(params.lunchId)
      Restaurant _restaurant = Restaurant.read(params.chosenRestaraunt)
      new Vote(lunch: _lunch, restaurant: _restaurant).save()
      redirect(action:'view', controller:'lunch', params:['id':_lunch.id])
    }


}
