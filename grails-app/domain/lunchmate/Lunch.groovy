package lunchmate

class Lunch {

  enum Type {PICKED{
    public String toString(){
      return "Picked"
    }
  }, GENERATED{
    public String toString(){
      return "Generated"
    }
  }
  }

  static transients = ['open', 'winningRestaurant', 'votedFor']

  def lunchService
  def voteService
  Date scheduledDate
  Type lunchType

  static hasMany = [restaurants:Restaurant, votes:Vote]

  Boolean isOpen()
  {
    return lunchService.isOpen(this)
  }

  Restaurant getVotedFor()
  {
    return voteService.getVotedFor(this)
  }

  Restaurant getWinningRestaurant()
  {
    lunchService.getChosenRestaurant(this)
  }

  static constraints = {
  }
}
