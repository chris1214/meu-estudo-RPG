package minhamesarpg
import grails.rest.Resource
@Resource(uri = '/application/json')
class ChatMesa {
    String userText
    String text
    String user
    Integer userID
    Integer mesaID
    static constraints = {
    }
}
