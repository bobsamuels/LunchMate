import lunchmate.Comment
import lunchmate.Lunch
import lunchmate.Rating
import lunchmate.Restaurant
import lunchmate.Vote
import lunchmate.auth.Role
import lunchmate.auth.User
import lunchmate.auth.UserRole

class BootStrap {
    def springSecurityService

    def init = { servletContext ->

      def user = new User(username: 'twakeen', password: 'password', enabled: true, email: 'tom.wakeen@gmail.com').save()
      def user2 = new User(username: 'bsamuels', password:'password', enabled: true, email: 'bsamuels@widen.com').save()
      def admin = new User(username: 'admin', password: 'password', enabled: true, email: 'samu0030@gmail.com').save()


      Restaurant res1 = new Restaurant(name:"Noodles", url:"http://www.noodles.com").save()
      Restaurant res2 = new Restaurant(name:"Red Robin", url:"http://www.redrobin.com", menuUrl:"http://www.test.com/menu.html").save()
      Restaurant res3 = new Restaurant(name:"BW3", url:"http://www.buffalowildwings.com", menuUrl:"http://www.test.com/menu.html").save()
      Restaurant res4 = new Restaurant(name:"Country Corners").save()
      Restaurant res5 = new Restaurant(name:"The Original Pancake House").save()
      Restaurant res6 = new Restaurant(name:"Smokey Jons Barbeque").save()
      Restaurant res7 = new Restaurant(name:"Taco Johns", url:"http://www.tacojohns.com").save()

      new Rating(restaurant: res1, score: 4, user: user).save()
      new Rating(restaurant: res1, score: 3, user: user2).save()
      new Rating(restaurant: res2, score: 4, user: user).save()
      new Rating(restaurant: res2, score: 1, user: user2).save()
      new Rating(restaurant: res3, score: 2, user: user).save()
      new Rating(restaurant: res4, score: 3, user: user2).save()

      new Comment(restaurant: res1, text: 'Pretty good noodles.', user: user).save()
      new Comment(restaurant: res1, text: 'Great mac and cheese.', user: user2).save()
      new Comment(restaurant: res2, text: 'Pretty good burgers.', user: user).save()
      new Comment(restaurant: res3, text: 'Good stuff.', user: user2).save()
      new Comment(restaurant: res4, text: 'ZBCB!', user: user).save()
      new Comment(restaurant: res4, text: 'Not a fan.', user: user2).save()
      new Comment(restaurant: res5, text: 'Meh.  Pancakes are overrated.', user: user2).save()
      new Comment(restaurant: res6, text: 'They put me in a meat coma!', user: user).save()
      new Comment(restaurant: res7, text: 'It was food....I guess.', user: user).save()
      new Comment(restaurant: res7, text: 'Where else can you get sick on $3?', user: user2).save()

      def today = new Date()

      def _restaurants = [res1,res2, res3]
      Lunch lunch1 = new Lunch(scheduledDate: (today - 1).format("MM/dd/yyyy"), lunchType:Lunch.Type.GENERATED, restaurants: _restaurants)
      lunch1.save()

      _restaurants = [res4,res5, res6]
      Lunch lunch2 = new Lunch(scheduledDate: (today - 8).format("MM/dd/yyyy"), lunchType:Lunch.Type.GENERATED, restaurants: _restaurants)
      lunch2.save()

      _restaurants = [res7,res1, res3]
      Lunch lunch3 = new Lunch(scheduledDate: (today - 15).format("MM/dd/yyyy"), lunchType:Lunch.Type.GENERATED, restaurants: _restaurants)
      lunch3.save()

       _restaurants = [res4,res2, res6]
      Lunch lunch4 = new Lunch(scheduledDate: (today - 22).format("MM/dd/yyyy"), lunchType:Lunch.Type.GENERATED, restaurants: _restaurants)
      lunch4.save()

      new Vote(lunch: lunch1, restaurant: res1, user:user).save()
      new Vote(lunch: lunch1, restaurant: res1, user:user2).save()
      new Vote(lunch: lunch2, restaurant: res4, user:user).save()
      new Vote(lunch: lunch3, restaurant: res7, user:user2).save()
      new Vote(lunch: lunch4, restaurant: res6, user:user).save()
      new Vote(lunch: lunch4, restaurant: res6, user:user2).save()

    }
    def destroy = {
    }
}
