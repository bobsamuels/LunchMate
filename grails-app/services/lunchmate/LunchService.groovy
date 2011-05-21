package lunchmate

import java.text.DateFormat
import java.text.SimpleDateFormat

class LunchService {

    static transactional = true
    static final dateFormat = "MM/dd/yyyy"
    def restaurantService
    def springSecurityService

    static final DateFormat sdf = new SimpleDateFormat(dateFormat)

    def findUpcomingLunches(){
      Date today = new Date()
      Date tomorrow = new Date()+ 1
      def results = Lunch.findAllByScheduledDateBetween(today.format(dateFormat), tomorrow.format(dateFormat))
      return results
    }

    def findArchiveLunches(params)
    {
     Date today = new Date()
     return Lunch.findAllByScheduledDateLessThan(today.format(dateFormat), params)
    }

    Boolean canScheduleFor(Date date)
    {
      if(Lunch.findByScheduledDate(date.format(dateFormat)))
      {return false}
      else
      {

        return true
      }
    }

    Restaurant getChosenRestaurant(Lunch lunch)
    {
      def votes = Vote.findAllByLunch(lunch)

      int maxVotes =0
      Restaurant chosenRestaurant
      if(votes){
        votes.groupBy{it.restaurant}.each{key,val->
          if(val.size() > maxVotes)
          {
            chosenRestaurant = key
            maxVotes = val.size()
          }
        }
      }
      //need tiebreak
      return chosenRestaurant
    }

    Boolean isOpen(Lunch lunch)
    {
      def today = sdf.parse(new Date().format(dateFormat))
      def cal = Calendar.getInstance()
      def cutOffTime = Calendar.getInstance()
      cutOffTime.clear()
      cutOffTime.set(Calendar.HOUR_OF_DAY, 11)
      cutOffTime.set(Calendar.MINUTE, 30)

      def currentTime = Calendar.getInstance()
      currentTime.clear()
      currentTime.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY))
      currentTime.set(Calendar.MINUTE, cal.get(Calendar.MINUTE))

      if(lunch.scheduledDate > today)
      {
        return true
      }
      else if(lunch.scheduledDate == today)
      {
        if(currentTime <= cutOffTime){
          return true
        }
      }

      return false
    }


    Lunch createLunch(params)
    {
      Lunch newLunch = new Lunch()
      List<Restaurant> restaurants = []
      println params
      if(params.type.equals(Lunch.Type.PICKED.toString()))
      {
        newLunch.lunchType = Lunch.Type.PICKED
        //Validate the restaurants are different
         restaurants = [Restaurant.read(params.restaurant1), Restaurant.read(params.restaurant2), Restaurant.read(params.restaurant3)]
      }
      else{
        newLunch.lunchType = Lunch.Type.GENERATED
        //generate 3 restaurants
         restaurants = restaurantService.pickRestaurants()
      }

      newLunch.restaurants = restaurants

      if(params.when.equals('today'))
      {
        newLunch.scheduledDate =  sdf.parse(new Date().format(dateFormat))
      }
      else
      {
        newLunch.scheduledDate = (sdf.parse((new Date()+1).format(dateFormat)))
      }
      newLunch.save(flush:true)
      return newLunch
    }




}
