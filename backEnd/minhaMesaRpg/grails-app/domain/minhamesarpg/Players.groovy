package minhamesarpg
import grails.rest.Resource
@Resource(uri = '/application/json')
class Players {
    String user
    Integer userId
    Integer mesaId
    static constraints = {
        user nullable: false,blanck: false
        userId nullable: false,blanck: false
        mesaId nullable: false,blanck: false
    }
}
