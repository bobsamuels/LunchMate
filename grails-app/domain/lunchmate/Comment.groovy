package lunchmate

import lunchmate.auth.User

class Comment {
    String text
    User user
    Restaurant restaurant

    static constraints = {
    }
}
