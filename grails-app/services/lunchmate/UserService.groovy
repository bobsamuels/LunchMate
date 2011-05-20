package lunchmate

import lunchmate.auth.User

class UserService {

    static transactional = true

    User getCurrentUser()
    {
      return User.read(1)
    }

}
